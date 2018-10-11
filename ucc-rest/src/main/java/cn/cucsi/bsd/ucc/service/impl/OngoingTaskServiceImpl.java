package cn.cucsi.bsd.ucc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.mapper.*;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.OngoingTaskService;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import cn.cucsi.bsd.ucc.service.UccUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OngoingTaskServiceImpl implements OngoingTaskService {

	private static Logger logger = Logger.getLogger(OngoingTaskServiceImpl.class);

	@Autowired
	private UccCustomersMapper uccCustomersMapper;
	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private TaskTransferMapper taskTransferMapper;
	@Autowired
	private UccDeptsService uccDeptsService;
	@Autowired
	private UccUserService uccUserService;
	@Autowired
	private TaskRecordMapper taskRecordMapper;
	@Autowired
	private TaskTransferRecordMapper taskTransferRecordMapper;
	
	@Override
	public List<UccCustomers> selectOngoingBySearch(TaskDetailSearch taskDetailSearch) throws Exception {
		try {
			// 部门
			//List<UccDepts> uccDeptsist = uccDeptsService.selectByUserId(taskDetailSearch.getUserId());
			//taskDetailSearch.setRoperateDeptId(uccDeptsist.get(0).getDeptId());
			taskDetailSearch.setOperatorId(taskDetailSearch.getUserId());
			// 分页查询
			taskDetailSearch.setup(uccCustomersMapper.selectOngoingBySearchCount(taskDetailSearch), taskDetailSearch.getShowLines());

			return uccCustomersMapper.selectOngoingBySearch(taskDetailSearch);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TaskDetail> selectDetailByBusinessCode(String businessCode,String domainId) throws Exception {
		try {
			Map<String, Object> whereMap = new HashMap<String, Object>();
			whereMap.put("businessCode", businessCode);
			whereMap.put("domainId", domainId);
			return taskDetailMapper.selectDetailByCode(whereMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/***
	 * 保存任务明细
	 */
	@Override
	public Map<String,Object> saveChangePhone(String changePhone, String fPhone, String businessCode,String domainId) throws Exception {

		Map<String,Object> dosaveChangePhoneMap = new HashMap<String,Object>();
		dosaveChangePhoneMap.put("msg", "保存任务明细失败!");
		dosaveChangePhoneMap.put("code", "-1");
		try {
			UccCustomers customer = new UccCustomers();

			if("changePhone1".equals(fPhone)){
                customer.setChangephone1(changePhone==null?"":changePhone);
                customer.setChangephone2("-1");
                customer.setChangephone3("-1");
            }else if("changePhone2".equals(fPhone)){
                customer.setChangephone2(changePhone==null?"":changePhone);
                customer.setChangephone1("-1");
                customer.setChangephone3("-1");
            }else if("changePhone3".equals(fPhone)){
                customer.setChangephone3(changePhone==null?"":changePhone);
                customer.setChangephone2("-1");
                customer.setChangephone1("-1");
            }
			customer.setBusinesscode(businessCode);
			customer.setDefultPhone(changePhone);
			customer.setDomainId(domainId);

			if("defultPhone".equals(fPhone)){
                uccCustomersMapper.updateDefultPhone(customer);
            }else {
                uccCustomersMapper.updateChangePhone(customer);
            }
			dosaveChangePhoneMap.put("code", "0");
			dosaveChangePhoneMap.put("msg", "保存任务明细操作成功!");
			return dosaveChangePhoneMap;

		} catch (Exception e) {
			e.printStackTrace();
			return dosaveChangePhoneMap;
		}
	}

	@Override
	public String custmIsBlack(String businessCode,String domainId) throws Exception {
		return uccCustomersMapper.custmIsBlack(businessCode,domainId);
	}

	/***
	 * 保存任务明细
	 */
	@Override
	@Transactional
	public Map<String,Object> saveDetail(String callinfo, String userId, String cdrId,String domainId,String taskDetailIds) throws Exception {

		Map<String,Object> doSaveDetailMap = new HashMap<String,Object>();
		doSaveDetailMap.put("msg", "保存任务明细失败!");
		doSaveDetailMap.put("code", "-1");
		try {
			// 主键
			UUIDGenerator generator = new UUIDGenerator();
			// 流转时间
			Timestamp transferTime = new Timestamp(System.currentTimeMillis());
			// 部门
			//List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);
			//String deptId = uccDeptsList.get(0).getDeptId();
			TaskDetailSearch taskDetailSearch = new TaskDetailSearch();
			taskDetailSearch.setUserId(userId);
			taskDetailSearch.setTaskDetailIds(taskDetailIds);
			String deptId = taskDetailMapper.selDeptByUserIdFromTaskDetail(taskDetailSearch);

			List<TaskTransfer> taskTransferList = new ArrayList<TaskTransfer>();
			TaskTransfer taskTransfer = null;
			TaskTransferRecord taskTransferRecord = null;
			List<TaskTransferRecord> taskTransferRecordList = new ArrayList<TaskTransferRecord>();
			if(callinfo == null || "".equals(callinfo)){
				doSaveDetailMap.put("msg", "参数callinfo不能为空!");
				return doSaveDetailMap;
            }
			// 所有办结任务
			List<TaskRecord> taskRecordList = new ArrayList<TaskRecord>();
			TaskRecord taskRecord = null;
			String [] callinfos = callinfo.split(",");

			for(String cinfo : callinfos){
                if(cinfo != null && !"".equals(cinfo)){
                    String [] cinfos = cinfo.split("_", -1);
                    if(cinfos.length <4){
                        logger.error("cinfos不到4位 callinfo: "+callinfo);
                        logger.error("cinfos不到4位 cinfo: "+cinfo);
                    }
                    if("4".equals(cinfos[3])){
                        TaskDetail taskDetail = taskDetailMapper.selectByPrimaryKeyForWEB(cinfos[0]);
                        if(taskDetail==null){
                            logger.error("taskDetail为空,也许是重复提交导致的，忽略本条任务: "+cinfo);
                            continue;
                        }
                        taskRecord = new TaskRecord();

                        taskRecord.setTaskRecordId(cinfos[0]);
                        taskRecord.setOperatorId(userId);
                        taskRecord.setOperatorDept(deptId);
                        taskRecord.setCallResult(cinfos[1]);
                        taskRecord.setCallMemo(cinfos[2]);
                        taskRecord.setCdrId(cdrId);
                        taskRecord.setImportBatch(taskDetail.getImportBatch());
                        taskRecord.setImportPersonId(taskDetail.getImportPersonId());
                        taskRecord.setImportTime(taskDetail.getImportTime());
                        taskRecord.setBusinessCode(taskDetail.getBusinessCode()); // 业务编码
                        taskRecord.setTaskTypeId(taskDetail.getTaskTypeId()); //任务类型
                        taskRecord.setDeptMeshId(taskDetail.getDeptMeshId()); //网格
                        taskRecord.setDeptAreaId(taskDetail.getDeptAreaId()); //包区
                        taskRecord.setTaskCode(taskDetail.getTaskCode()); //任务编码
                        taskRecord.setResponsible(taskDetail.getResponsible()); //责任体
                        taskRecord.setNetStop(taskDetail.getNetStop()); //网别
                        taskRecord.setPackageName(taskDetail.getPackageName()); //套餐名称
                        taskRecord.setContractName(taskDetail.getContractName()); //合约名称
                        taskRecord.setContractStartTime(taskDetail.getContractStartTime()); //合约开始时间
                        taskRecord.setContractEndTime(taskDetail.getContractEndTime()); //合约结束时间
                        taskRecord.setActivationTime(taskDetail.getActivationTime()); //激活时间
                        taskRecord.setDevelopmentId(taskDetail.getDevelopmentDept()); //发展部门
                        taskRecord.setCustomfields1(taskDetail.getCustomfields1()); //自定义字段1
                        taskRecord.setCustomfields2(taskDetail.getCustomfields2());
                        taskRecord.setCustomfields3(taskDetail.getCustomfields3());
                        taskRecord.setCustomfields4(taskDetail.getCustomfields4());
                        taskRecord.setCustomfields5(taskDetail.getCustomfields5());
                        taskRecord.setCustomfields6(taskDetail.getCustomfields6());
                        taskRecord.setCustomfields7(taskDetail.getCustomfields7());
                        taskRecord.setCustomfields8(taskDetail.getCustomfields8());
                        taskRecord.setCustomfields9(taskDetail.getCustomfields9());
                        taskRecord.setCustomfields10(taskDetail.getCustomfields10());
                        taskRecord.setCustomfields11(taskDetail.getCustomfields11());
                        taskRecord.setCustomfields12(taskDetail.getCustomfields12());
                        taskRecord.setCustomfields13(taskDetail.getCustomfields13());
                        taskRecord.setCustomfields14(taskDetail.getCustomfields14());
                        taskRecord.setCustomfields15(taskDetail.getCustomfields15());
                        taskRecord.setInitMeshId(taskDetail.getInitMeshId()); //网格(初始)
                        taskRecord.setInitAreaId(taskDetail.getInitAreaId()); //包区(初始)
                        taskRecord.setInitDevelopment(taskDetail.getInitDevelopment()); //发展部门(初始)
                        taskRecord.setRecordTime(transferTime);
                        taskRecord.setEndDate(taskDetail.getEndDate());
                        taskRecord.setDomainId(domainId);

                        taskRecordList.add(taskRecord);

                        List<TaskTransfer> transferList = taskTransferMapper.selectTransferByTaskDetailId(cinfos[0]);

                        if(MyUtils.isBlank(transferList)){
							doSaveDetailMap.put("msg", "保存任务明细 执行根据TaskDetailId查询流转明细时方失败!");
							return doSaveDetailMap;
						}
                        for(TaskTransfer transfer : transferList){
                            taskTransferRecord = new TaskTransferRecord();
                            //String taskTransferRecordUuid = generator.generate();
                            taskTransferRecord.setRoperateDeptId(transfer.getRoperateDeptId());
                            taskTransferRecord.setTaskRecordId(transfer.getTaskDetailId()); //归档表主键
                            //20150524 lanfh TransferRecordId改为等于TaskTransferId
                            taskTransferRecord.setTaskTransferRecordId(transfer.getTaskTransferId()); //流转归档表主键
                            taskTransferRecord.setTransferStatus(transfer.getTransferStatus()); //流转状态    0:未分派、1：未接收、2：待办、3：在办 4：办结
                            taskTransferRecord.setTransferTime(transfer.getTransferTime()); //流转时间
                            taskTransferRecord.setOperatorId(transfer.getOperatorId()); //操作员
                            taskTransferRecord.setOperatorDept(transfer.getOperatorDept());
                            taskTransferRecord.setCallResult(transfer.getCallResult());
                            taskTransferRecord.setCallMemo(transfer.getCallMemo());
                            taskTransferRecord.setCdrId(transfer.getCdrId());
                            taskTransferRecord.setRecordTime(transferTime);
                            taskTransferRecord.setRecordOperatorId(userId);
                            taskTransferRecord.setRecordOperatorDept(deptId);
                            taskTransferRecord.setRoperatePersonId(userId);
                            taskTransferRecord.setRoperateDeptId(deptId);
                            taskTransferRecord.setDomainId(domainId);

                            taskTransferRecordList.add(taskTransferRecord);
                        }
                        taskTransferRecord = new TaskTransferRecord();

                        String taskTransferRecordUuid = generator.generate();

                        taskTransferRecord.setRoperateDeptId(deptId);
                        taskTransferRecord.setTaskRecordId(cinfos[0]); //归档表主键
                        taskTransferRecord.setTaskTransferRecordId(taskTransferRecordUuid); //流转归档表主键
                        taskTransferRecord.setTransferStatus(cinfos[3]); //流转状态    0:未分派、1：未接收、2：待办、3：在办 4：办结
                        taskTransferRecord.setTransferTime(transferTime); //流转时间
                        taskTransferRecord.setOperatorId(userId); //操作员
                        taskTransferRecord.setOperatorDept(deptId);
                        taskTransferRecord.setCallResult(cinfos[1]);
                        taskTransferRecord.setCallMemo(cinfos[2]);
                        taskTransferRecord.setCdrId(cdrId);
                        taskTransferRecord.setRecordTime(transferTime);
                        taskTransferRecord.setRecordOperatorId(userId);
                        taskTransferRecord.setRecordOperatorDept(deptId);
                        taskTransferRecord.setRoperatePersonId(userId);
                        taskTransferRecord.setRoperateDeptId(deptId);
                        taskTransferRecord.setDomainId(domainId);

                        taskTransferRecordList.add(taskTransferRecord);
                    }else {
                        taskTransfer = new TaskTransfer();
                        String taskTransferUuid = generator.generate();

                        taskTransfer.setRoperateDeptId(deptId);
                        taskTransfer.setRoperatePersonId(userId);
                        taskTransfer.setTaskDetailId(cinfos[0]);
                        taskTransfer.setTaskTransferId(taskTransferUuid);
                        taskTransfer.setTransferStatus(cinfos[3]); //流转状态 0:未分派、1：未接收、2：待办、3：在办
                        taskTransfer.setTransferTime(transferTime); //流转时间
                        taskTransfer.setOperatorId(userId); //操作员
                        taskTransfer.setOperatorDept(deptId);
                        taskTransfer.setCallResult(cinfos[1]);
                        taskTransfer.setCallMemo(cinfos[2]);
                        taskTransfer.setCdrId(cdrId);
                        taskTransfer.setDomainId(domainId);

                        taskTransferList.add(taskTransfer);
                    }
                }
            }
			Map<String, Object> map = new HashMap<String, Object>();

			if(taskTransferList != null && taskTransferList.size() > 0){
                map.put("list", taskTransferList);
				try {
					taskTransferMapper.insertGroup(map);
				} catch (Exception e) {
					e.printStackTrace();
					doSaveDetailMap.put("msg", "保存任务明细 执行批量插入流转明细时失败!");
					return doSaveDetailMap;
				}
			}
			if(!MyUtils.isBlank(taskTransferList)){
				// 更新任务状态
				for(TaskTransfer transfer : taskTransferList){
					try {
						taskDetailMapper.updateTaskStatus(transfer);
					} catch (Exception e) {
						e.printStackTrace();
						doSaveDetailMap.put("msg", "保存任务明细 执行更新任务状态时失败!");
						return doSaveDetailMap;
					}
				}
			}
			if(taskTransferRecordList != null && taskTransferRecordList.size() > 0){
                // 批量插入任务流转归档表
                map.put("list", taskTransferRecordList);
				try {
					taskTransferRecordMapper.insertGroup(map);
				} catch (Exception e) {
					e.printStackTrace();
					doSaveDetailMap.put("msg", "保存任务明细 执行批量插入任务流转归档表时失败!");
					return doSaveDetailMap;
				}

				// 删除流转表
				try {
					taskTransferMapper.deleteWithListId(taskTransferRecordList);
				} catch (Exception e) {
					e.printStackTrace();
					doSaveDetailMap.put("msg", "保存任务明细 执行删除流转表时失败!");
					return doSaveDetailMap;
				}
			}
			if(taskRecordList != null && taskRecordList.size() > 0){
                // 批量插入归档表
                map.put("list", taskRecordList);
				try {
					taskRecordMapper.insertGroup(map);
				} catch (Exception e) {
					e.printStackTrace();
					doSaveDetailMap.put("msg", "保存任务明细 执行批量插入归档表时失败!");
					return doSaveDetailMap;
				}
				// 删除任务表
				try {
					taskDetailMapper.deleteWithListId(taskRecordList);
				} catch (Exception e) {
					e.printStackTrace();
					doSaveDetailMap.put("msg", "保存任务明细 执行删除任务表时失败!");
					return doSaveDetailMap;
				}
			}
			doSaveDetailMap.put("code", "0");
			doSaveDetailMap.put("msg", "保存任务明细操作成功!");
			return doSaveDetailMap;
		} catch (Exception e) {
			e.printStackTrace();
			return doSaveDetailMap;
		}
	}
	@Override
	public List<TaskTransfer> selectCallNotesByDetailIds(String taskDetailIds) throws Exception {
		
		if(taskDetailIds != null && !"".equals(taskDetailIds)){
			taskDetailIds = "'" + taskDetailIds.replace(",", "','") + "'";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskDetailIds", taskDetailIds);
			return taskTransferMapper.selectCallNotesByDetailIdsForWEB(map);
		}
		return null;
	}

	@Override
	public void cusfsSave(Map<String, Object> map) throws Exception {
		uccUserService.cusfsSave(map);
	}

	@Override
	@Transactional
	public String saveAndGoonDetail(String callinfo, String userId, String taskTypeId, String cdrId, String businessCode,String domainId,String taskDetailIds) throws Exception {
		
		// 保存任务信息
		saveDetail(callinfo, userId, cdrId,domainId,taskDetailIds);
		List<UccCustomers> customerList = null;
		TaskDetailSearch search = new TaskDetailSearch();
		// 部门
		//List<UccDepts> uccDeptsList = uccDeptsService.selectByUserId(userId);
		TaskDetailSearch taskDetailSearch = new TaskDetailSearch();
		taskDetailSearch.setUserId(userId);
		taskDetailSearch.setTaskDetailIds(taskDetailIds);
		String deptId = taskDetailMapper.selDeptByUserIdFromTaskDetail(taskDetailSearch);
		search.setRoperateDeptId(deptId);

		// 查询下一个businessCode 状态为待办
		search.setTaskStatus("2");
		search.setBusinessCode(businessCode);
		search.setShowLines(1);
		if(taskTypeId!=null && !taskTypeId.isEmpty()){
			search.setTaskTypeId(taskTypeId);
		}
		customerList = uccCustomersMapper.selectNextTaskBySearch(search);
		if(customerList != null && customerList.size() > 0){
			for(UccCustomers customer : customerList){
				if(!businessCode.equals(customer.getBusinesscode())){
					return customer.getBusinesscode();
				}
			}
		}else {
			// 没有状态为待办任务  查询在办任务  都没有 提示任务全部完成
			search.setTaskStatus("3");
			customerList = uccCustomersMapper.selectNextTaskBySearch(search);

			if(customerList != null && customerList.size() > 0){
				for(UccCustomers customer : customerList){
					if(!businessCode.equals(customer.getBusinesscode())){
						return customer.getBusinesscode();
					}
				}
			}
		}
		return null;
	}
}
