package cn.cucsi.bsd.ucc.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.mapper.ImportBatchMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferMapper;
import cn.cucsi.bsd.ucc.common.mapper.UccCustomersMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.AllocationTaskService;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AllocationTaskServiceImpl  implements AllocationTaskService {
	
	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private UccCustomersMapper uccCustomersMapper;
	@Autowired
	private TaskTransferMapper taskTransferMapper;
	@Autowired
	private UccDeptsService uccDeptsService;
	@Autowired
	private ImportBatchMapper importBatchMapper;

	@Override
	@Transactional("txManager")
	public void allocationTask(String userId, String alloc, String barchs, String endDate) throws Exception {
		// 根据用户ID获取部门主键
		String userDeptID = "";
        try {
            List<UccDepts> UccDeptsList = uccDeptsService.selectByUserId(userId);
            for(UccDepts uccDepts : UccDeptsList){
                userDeptID = uccDepts.getDeptId();
            }
            // 更新客户表信息
            if(UccDeptsList.get(0).getDeptLevel() <= 1){
                updateCrm();
            }
            // 查询待分配任务
            Map<String, Object> whereMap = new HashMap<String, Object>();

            if(UccDeptsList.get(0).getDeptLevel() <= 1){
                whereMap.put("taskStatus", "0");
                whereMap.put("importPersonId", userId);
            }else {
                //whereMap.put("taskStatus", "1");
                whereMap.put("taskStatusList", "'0', '1'");
            }
            whereMap.put("roperateDeptId", userDeptID.toString());
            whereMap.put("barchs", barchs);
            List<TaskDetail> taskDetailList = taskDetailMapper.selectByWhere(whereMap);
            // 主键
            UUIDGenerator generator = new UUIDGenerator();
            // 流转时间
            Timestamp transferTime = new Timestamp(System.currentTimeMillis());
            List<TaskTransfer> taskTransferList = new ArrayList<TaskTransfer>();
            TaskTransfer taskTransfer = null;
            if(!MyUtils.isBlank(taskDetailList)){
                for(TaskDetail taskDetail : taskDetailList){
                    taskTransfer = new TaskTransfer();
                    String taskTransferUuid = generator.generate();
                    taskTransfer.setTaskTransferId(taskTransferUuid);
                    taskTransfer.setTaskDetailId(taskDetail.getTaskDetailId()); //任务明细表主键
                    taskTransfer.setTransferStatus("1"); //流转状态    0:未分派、1：未接收、2：待办、3：在办
                    taskTransfer.setTransfeRoperate(TaskTransfer.ALLOCATION); //流转操作  0:创建、1：分派、2：接收、3：回退
                    taskTransfer.setTransferTime(transferTime); //流转时间
                    taskTransfer.setOperatorId(userId); //操作员
                    taskTransfer.setRoperatePersonId(userId);//受理员
                    taskTransfer.setRoperateDeptId(userDeptID.toString());//受理部门
                    taskTransfer.setOperatorDept(userDeptID.toString());
                    if(Task.ALLOCDEFULT.equals(alloc)){
                        // 默认分派
                        if(taskDetail.getDevelopmentDept() != null && !"".equals(taskDetail.getDevelopmentDept())
                                && !"-1".equals(taskDetail.getDevelopmentDept())){
                            taskTransfer.setRoperateDeptId(taskDetail.getDevelopmentDept());
                        }else if(taskDetail.getDeptAreaId() != null && !"".equals(taskDetail.getDeptAreaId())
                                && !"-1".equals(taskDetail.getDeptAreaId())){
                            taskTransfer.setRoperateDeptId(taskDetail.getDeptAreaId());
                        }else if(taskDetail.getDeptMeshId() != null && !"".equals(taskDetail.getDeptMeshId())
                                && !"-1".equals(taskDetail.getDeptMeshId())){
                            taskTransfer.setRoperateDeptId(taskDetail.getDeptMeshId());
                        }
                    }else if(Task.ALLOCAREA.equals(alloc)){
                        // 分派到包区
                        if(taskDetail.getDeptAreaId() != null && !"".equals(taskDetail.getDeptAreaId())
                                && !"-1".equals(taskDetail.getDeptAreaId())){
                            taskTransfer.setRoperateDeptId(taskDetail.getDeptAreaId());
                        }else if(taskDetail.getDeptMeshId() != null && !"".equals(taskDetail.getDeptMeshId())
                                && !"-1".equals(taskDetail.getDeptMeshId())){
                            taskTransfer.setRoperateDeptId(taskDetail.getDeptMeshId());
                        }
                    }else if(Task.ALLOCMESH.equals(alloc)){
                        // 分派到网格
                        if(taskDetail.getDeptMeshId() != null && !"".equals(taskDetail.getDeptMeshId())
                                && !"-1".equals(taskDetail.getDeptMeshId())){
                            taskTransfer.setRoperateDeptId(taskDetail.getDeptMeshId());
                        }
                    }
                    taskTransferList.add(taskTransfer);
                }
            }
            // 插入任务流转表
            Map<String, Object> taskTransfermap = new HashMap<String, Object>();
            taskTransfermap.put("list", taskTransferList);
            taskTransferMapper.insertGroup(taskTransfermap);

            // 更新任务状态、任务截止日期
            for(TaskTransfer transfer : taskTransferList){

                TaskDetail taskDetail = new TaskDetail();

                taskDetail.setTaskDetailId(transfer.getTaskDetailId());

                taskDetail.setStatus(transfer.getTransferStatus());

                taskDetail.setRoperateDeptId(transfer.getRoperateDeptId());

                taskDetail.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));

                //taskDetailMapper.updateTaskByTaskDetail(taskDetail);//完事再解开
            }

            // 修改数据导入批次表
            Map<String, Object> barchsMap = new HashMap<String, Object>();

            barchsMap.put("importBarchs", barchs);

            barchsMap.put(ImportBatch.BATCHFLAG, ImportBatch.BATCHFLAGA);

            importBatchMapper.updateFlagByBatch(barchsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	private void updateCrm() throws Exception {
		try {
			// 查询任务表 客户信息
			List<TaskDetail> taskDetailList = taskDetailMapper.getCustomerInfo();
			// 获取所有业务号码
			Map<String, String> businesscodeMap = new HashMap<String, String>();
			String businessCodes = "'";

			if(!MyUtils.isBlank(taskDetailList)){
				for(TaskDetail taskDetail : taskDetailList){
					businessCodes = businessCodes + taskDetail.getBusinessCode() + "','";
				}
				businessCodes = businessCodes.substring(0, businessCodes.lastIndexOf(","));
				businesscodeMap.put("businessCode", businessCodes);
			}
			// 根据业务号码 查询客户表
			List<UccCustomers> customerList = uccCustomersMapper.selectByBusinessCodes(businesscodeMap);
			Map<String, UccCustomers> cusMap = new HashMap<String, UccCustomers>();

			if(!MyUtils.isBlank(customerList)){
				// 数据整合 组织最新客户信息
				for(UccCustomers uccCustomers : customerList){
					cusMap.put(uccCustomers.getBusinesscode(), uccCustomers);
				}
			}
			UccCustomers cus = null;
			if(!MyUtils.isBlank(taskDetailList)){
				for(TaskDetail taskDetail : taskDetailList){
					cus = cusMap.get(taskDetail.getBusinessCode());
					if(cus == null){
						cus = new UccCustomers();
						cus.setBusinesscode(taskDetail.getBusinessCode());
						cus.setTariffname(taskDetail.getTariffName());
						cus.setStatus(taskDetail.getStatus());
						cus.setContacts(taskDetail.getContacts());
						cus.setPonlogo(taskDetail.getPonLogo());
						cus.setInstalledaddress(taskDetail.getInstalledAddress());
						cus.setPhone(taskDetail.getPhoneNumber());
						cus.setPhone2(taskDetail.getPhoneNumber2());
						cus.setPhone3(taskDetail.getPhoneNumber3());
						cus.setCustName(taskDetail.getUserName());
						cus.setDefultPhone(taskDetail.getPhoneNumber());
						cus.setProductType(taskDetail.getProductType());
						cus.setRate(taskDetail.getRate());
						cus.setType(0);
						cusMap.put(taskDetail.getBusinessCode(), cus);
					}else {
						// 客户姓名变更时删除变更号码
						if(cus.getCustName() != null && !cus.getCustName().equals(taskDetail.getUserName())){
							cus.setChangephone1("");
							cus.setChangephone2("");
							cus.setChangephone3("");
						}
						cus.setBusinesscode(taskDetail.getBusinessCode());
						cus.setTariffname(taskDetail.getTariffName());
						cus.setStatus(taskDetail.getStatus());
						cus.setContacts(taskDetail.getContacts());
						cus.setPonlogo(taskDetail.getPonLogo());
						cus.setInstalledaddress(taskDetail.getInstalledAddress());
						cus.setPhone(taskDetail.getPhoneNumber());
						cus.setPhone2(taskDetail.getPhoneNumber2());
						cus.setPhone3(taskDetail.getPhoneNumber3());
						cus.setCustName(taskDetail.getUserName());
						cus.setDefultPhone(taskDetail.getPhoneNumber());
						cus.setProductType(taskDetail.getProductType());
						cus.setRate(taskDetail.getRate());
						cus.setType(0);
					}
				}
			}
			// 根据业务号码 删除客户表数据
			uccCustomersMapper.deleteByBusinessCodes(businesscodeMap);
			// 插入最新客户信息
			List<UccCustomers> cusNewList = new ArrayList<UccCustomers>();
			Iterator<Map.Entry<String, UccCustomers>> titer = cusMap.entrySet().iterator();//获得map的Iterator
			while(titer.hasNext()) {
                Map.Entry<String, UccCustomers> entry = (Map.Entry<String, UccCustomers>)titer.next();
                cusNewList.add((UccCustomers) entry.getValue());
            }
			Map<String, Object> cusNewmap = new HashMap<String, Object>();
			cusNewmap.put("list", cusNewList);
			uccCustomersMapper.insertGroup(cusNewmap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void editTaskDept(String meshID, String areaID, String developmentID, String taskDetailIds)
			throws Exception {

		/*taskDetailIds = "'" + taskDetailIds.replace(",", "','") + "'";
		
		Map<String, Object> taskDetailMap = new HashMap<String, Object>();
		
		taskDetailMap.put("taskDetailIds", taskDetailIds);

		if(meshID != null && !meshID.isEmpty() && !"-1".equals(meshID)){
			taskDetailMap.put("meshID", meshID);
		}
		if(areaID != null && !areaID.isEmpty() && !"-1".equals(areaID)){
			taskDetailMap.put("areaID", areaID);
		}
		if(developmentID != null && !developmentID.isEmpty() && !"-1".equals(developmentID)){
			taskDetailMap.put("developmentID", developmentID);
		}

		if(taskDetailMap.entrySet().size() > 1){
			taskDetailMapper.editTaskDept(taskDetailMap);
		}

//		if(meshID != null && !meshID.isEmpty() && !"-1".equals(meshID)){
//			taskDetailMap.put("meshID", meshID);
//			taskDetailMap.put("areaID", areaID);
//			taskDetailMap.put("developmentID", developmentID);
//			taskDetailMapper.editTaskDept(taskDetailMap);
//		}else if(("-1".equals(meshID)) && (areaID != null && !areaID.isEmpty() && !"-1".equals(areaID))){
//			taskDetailMap.put("areaID", areaID);
//			taskDetailMap.put("developmentID", developmentID);
//			taskDetailMapper.editTaskDept(taskDetailMap);
//		}else if("-1".equals(meshID) && "-1".equals(areaID) && (developmentID != null && !developmentID.isEmpty() && !"-1".equals(developmentID))){
//			taskDetailMap.put("areaID", areaID);
//			taskDetailMap.put("developmentID", developmentID);
//			taskDetailMapper.editTaskDept(taskDetailMap);
//		}*/
	}

	@Override
	@Transactional("txManager")
	public List<TaskDetail> selectAllocationBySearch(TaskDetailSearch search) throws Exception {
		searchDeal(search);
		// 分页查询
		search.setup(taskDetailMapper.selectBySearchCount(search), search.getShowLines());
		return taskDetailMapper.selectBySearch(search);
	}
	
	@Override
	@Transactional("txManager")
	public List<String> selectTaskDetailIdBySearch(TaskDetailSearch search) throws Exception{
		try {
			searchDeal(search);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("searchDeal方法执行异常");
		}
		return taskDetailMapper.selectTaskDetailIdBySearch(search);
	}
	
	private void searchDeal(TaskDetailSearch search) throws Exception{
		if(search.getImportBatch() !=null){
			search.setImportBatch(search.getImportBatch().trim());
		}
		// 部门
		//List<UccDepts> UccDeptsList = UccDeptsService.selectByUserId(search.getUserId());
		List<UccDepts> UccDeptsList = null;//临时

		// 增加权限控制  登录人是中心权限 查询流转操作为0的数据  登录人是网格或包区权限查询流转操作为1的数据
		if(UccDeptsList != null && UccDeptsList.size() > 0){
			if(UccDeptsList.get(0).getDeptLevel() <= 1){
				search.setTaskStatus("0");
				search.setImportPersonId(search.getUserId().toString());
			}else {
//				search.setTaskStatus("1");
				search.setTaskStatusList("'0', '1'");
			}
		}
		search.setRoperateDeptId(UccDeptsList.get(0).getDeptId().toString());
	}
}
