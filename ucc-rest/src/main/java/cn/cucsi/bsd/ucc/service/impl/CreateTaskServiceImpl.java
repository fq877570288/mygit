package cn.cucsi.bsd.ucc.service.impl;

import java.sql.Timestamp;
import java.util.*;
import java.util.Map.Entry;
import cn.cucsi.bsd.ucc.common.mapper.DataImportMapper;
import cn.cucsi.bsd.ucc.common.mapper.ImportBatchMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferMapper;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.BusinessService;
import cn.cucsi.bsd.ucc.service.CreateTaskService;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateTaskServiceImpl implements CreateTaskService {
	private static Logger logger = Logger.getLogger(CreateTaskServiceImpl.class);

	@Autowired
	private DataImportMapper dataImportMapper;
	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private TaskTransferMapper taskTransferMapper;
	@Autowired
	private ImportBatchMapper importBatchMapper;
	@Autowired
	private UccDeptsService uccDeptsService;
	@Autowired
	private BusinessService businessService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Override
//	@Transactional("txManager")
	public void createNewTask(String barchs, String userId, String oldTaskBatch) throws Exception {

		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		DataImportMapper dataImportMapperForBatch = sqlSession.getMapper(DataImportMapper.class);
		TaskDetailMapper taskDetailMapperForBatch = sqlSession.getMapper(TaskDetailMapper.class);
		TaskTransferMapper taskTransferMapperForBatch = sqlSession.getMapper(TaskTransferMapper.class);
		ImportBatchMapper importBatchMapperForBatch = sqlSession.getMapper(ImportBatchMapper.class);
		try {
			String importBarchs = "'" + barchs.replaceAll(",", "','") + "'";
			Map<String, Object> barchsMap = new HashMap<String, Object>();
			barchsMap.put("importBarchs", importBarchs);
			// 获取导入数据
			List<DataImport> dataImportList = dataImportMapperForBatch.selectByBarchs(barchsMap);
			// 部门
			String userDeptID = "";
			List<UccDepts> userDeptList = uccDeptsService.selectByUserId(userId);
			if(!MyUtils.isBlank(userDeptList)){
				for(UccDepts uccDepts : userDeptList){
					userDeptID = uccDepts.getDeptId();
				}
			}
			// 部门ID最大值
			String maxDeptID = uccDeptsService.selectMaxDeptID();
			// 主键
			UUIDGenerator generator = new UUIDGenerator();
			Map<String, Object> taskMap = new HashMap<String, Object>();
			List<TaskDetail> taskDetailList = new ArrayList<TaskDetail>();
			TaskDetail taskDetail = null;

			// 任务编码
			String taskCode = "";
			Date date = new Date();
			TaskTransfer taskTransfer = null;

			// 流转时间
			Timestamp transferTime = new Timestamp(System.currentTimeMillis());

			List<TaskTransfer> taskTransferList = new ArrayList<TaskTransfer>();
			DataImport dataImport = null;
			List<UccDepts> UccDeptsNewList = new ArrayList<UccDepts>();
			Map<String, UccDepts> UccDeptsNewMap = new HashMap<String, UccDepts>();
			UccDepts uccDepts = null;
			Long MeshID = null;
			Long AreaID = null;
			Long DevelopmentID = null;
			Long maxDeptIDTemp = null;

			// 组织数据
			if(dataImportList != null && dataImportList.size() > 0){
				for(int i=0; i<dataImportList.size(); i++){

					dataImport = dataImportList.get(i);
					// 任务明细表
					taskDetail = new TaskDetail();
					// 任务明细表主键
					String taskDetailUuid = generator.generate();
					// 任务类型
					TaskType taskType = businessService.selectByNameInCache(dataImport.getTaskTypeName());
					// 部门
					// 判断数据库中是否存在该部门
					UccDepts deptMesh = uccDeptsService.selectByNameInCache(dataImport.getDeptMeshName());
					UccDepts deptArea = uccDeptsService.selectByNameInCache(dataImport.getDeptAreaName());
					UccDepts deptDevelopment = uccDeptsService.selectByNameInCache(dataImport.getDevelopmentDept());

					// 判断UccDeptsNewMap中是否存在该部门
					UccDepts meshNew = UccDeptsNewMap.get(dataImport.getDeptMeshName());
					UccDepts areaNew = UccDeptsNewMap.get(dataImport.getDeptAreaName());
					UccDepts developmentNew = UccDeptsNewMap.get(dataImport.getDevelopmentDept());

					if(deptMesh == null && meshNew == null){
						// 网格
						uccDepts = new UccDepts();
						maxDeptIDTemp =  Long.parseLong(maxDeptID);
						MeshID = ++maxDeptIDTemp;
						uccDepts.setDeptId(MeshID+"");
						uccDepts.setDeptLevel(2); // 网格2级部门
						uccDepts.setDeptCreateTime(date);
						uccDepts.setCreatedUserId(userId);
						uccDepts.setDeptAdmin(userId);
						uccDepts.setDeptPid(userDeptID);
						uccDepts.setDeptName(dataImport.getDeptMeshName());

						UccDeptsNewMap.put(dataImport.getDeptMeshName(), uccDepts);
					}
					if(deptArea == null && areaNew == null){
						meshNew = UccDeptsNewMap.get(dataImport.getDeptMeshName());
						String pid = deptMesh == null ? meshNew.getDeptId() : deptMesh.getDeptId();
						// 包区
						uccDepts = new UccDepts();
						maxDeptIDTemp = Long.parseLong(maxDeptID);
						AreaID = ++maxDeptIDTemp;
						uccDepts.setDeptId(AreaID+"");
						uccDepts.setDeptLevel(3); // 包区3级部门
						uccDepts.setDeptCreateTime(date);
						uccDepts.setCreatedUserId(userId);
						uccDepts.setDeptAdmin(userId);
						uccDepts.setDeptPid(pid);
						uccDepts.setDeptName(dataImport.getDeptAreaName());

						UccDeptsNewMap.put(dataImport.getDeptAreaName(), uccDepts);
					}
					if(dataImport.getDevelopmentDept() != null && !"".equals(dataImport.getDevelopmentDept())){
						if(developmentNew == null && deptDevelopment == null){
							areaNew = UccDeptsNewMap.get(dataImport.getDeptAreaName());
							String pid = deptArea == null ? areaNew.getDeptId() : deptArea.getDeptId();
							// 发展部门
							uccDepts = new UccDepts();
							maxDeptIDTemp =  Long.parseLong(maxDeptID);
							DevelopmentID = ++maxDeptIDTemp;
							uccDepts.setDeptId(DevelopmentID+"");
							uccDepts.setDeptLevel(4); // 发展部门4级部门
                            uccDepts.setDeptCreateTime(date);
							uccDepts.setCreatedUserId(userId);
							uccDepts.setDeptAdmin(userId);
							uccDepts.setDeptPid(pid);
							uccDepts.setDeptName(dataImport.getDevelopmentDept());

							UccDeptsNewMap.put(dataImport.getDeptAreaName(), uccDepts);
						}
					}
					String dlpId = "";
					if(DevelopmentID != null){
						dlpId = DevelopmentID.toString();
					}
					taskDetail.setTaskDetailId(taskDetailUuid);
					if(oldTaskBatch != null && oldTaskBatch.length() > 0){
						taskDetail.setImportBatch(oldTaskBatch);
					}else {
						taskDetail.setImportBatch(dataImport.getImportBatch());
					}
					// 任务编码
					taskCode = taskDetail.getImportBatch() + i;
					taskDetail.setImportPersonId(dataImport.getImportPersonId());
					taskDetail.setImportTime(dataImport.getImportTime());
					taskDetail.setBusinessCode(dataImport.getBusinessCode()); // 业务编码
					taskDetail.setTaskTypeId(taskType.getTaskTypeId()); //任务类型
					taskDetail.setDeptMeshId(deptMesh == null ? MeshID.toString() : deptMesh.getDeptId().toString()); //网格
					taskDetail.setDeptAreaId(deptArea == null ? AreaID.toString() : deptArea.getDeptId().toString()); //包区
					taskDetail.setTaskCode(taskCode); //任务编码
					taskDetail.setProductType(dataImport.getProductType()); //产品类型
					taskDetail.setPhoneNumber(dataImport.getPhoneNumber1());
					taskDetail.setRate(dataImport.getVelocity()); //速率
					taskDetail.setResponsible(dataImport.getResponsible()); //责任体
					taskDetail.setNetStop(dataImport.getNetStop()); //网别
					taskDetail.setPackageName(dataImport.getPackageName()); //套餐名称
					taskDetail.setContractName(dataImport.getContractName()); //合约名称
					taskDetail.setContractStartTime(dataImport.getContractStartTime()); //合约开始时间
					taskDetail.setContractEndTime(dataImport.getContractEndTime()); //合约结束时间
					taskDetail.setActivationTime(dataImport.getActivationTime()); //激活时间
					taskDetail.setDevelopmentDept(deptDevelopment == null ? dlpId : deptDevelopment.getDeptId().toString()); //发展部门
					taskDetail.setCustomfields1(dataImport.getCustomfields1()); //自定义字段1
					taskDetail.setCustomfields2(dataImport.getCustomfields2());
					taskDetail.setCustomfields3(dataImport.getCustomfields3());
					taskDetail.setCustomfields4(dataImport.getCustomfields4());
					taskDetail.setCustomfields5(dataImport.getCustomfields5());
					taskDetail.setCustomfields6(dataImport.getCustomfields6());
					taskDetail.setCustomfields7(dataImport.getCustomfields7());
					taskDetail.setCustomfields8(dataImport.getCustomfields8());
					taskDetail.setCustomfields9(dataImport.getCustomfields9());
					taskDetail.setCustomfields10(dataImport.getCustomfields10());
					taskDetail.setCustomfields11(dataImport.getCustomfields11());
					taskDetail.setCustomfields12(dataImport.getCustomfields12());
					taskDetail.setCustomfields13(dataImport.getCustomfields13());
					taskDetail.setCustomfields14(dataImport.getCustomfields14());
					taskDetail.setCustomfields15(dataImport.getCustomfields15());
					taskDetail.setContacts(dataImport.getContacts());
					taskDetail.setStatus(dataImport.getStatus());
					taskDetail.setInstalledAddress(dataImport.getInstalledAddress());
					taskDetail.setPonLogo(dataImport.getPonLogo());
					taskDetail.setTariffName(dataImport.getTariffName());
					taskDetail.setPhoneNumber2(dataImport.getPhoneNumber2());
					taskDetail.setPhoneNumber3(dataImport.getPhoneNumber3());
					taskDetail.setTaskStatus("0");
					taskDetail.setRoperateDeptId(userDeptID.toString());
					taskDetail.setUserName(dataImport.getUserName());
					taskDetail.setDefultPhone(dataImport.getPhoneNumber1()); //默认显示号码
					taskDetail.setInitMeshId(deptMesh == null ? MeshID.toString() : deptMesh.getDeptId().toString()); //网格(初始)
					taskDetail.setInitAreaId(deptArea == null ? AreaID.toString() : deptArea.getDeptId().toString()); //包区(初始)
					taskDetail.setInitDevelopment(deptDevelopment == null ? dlpId : deptDevelopment.getDeptId().toString()); //发展部门(初始)

					taskDetailList.add(taskDetail);

					// 组织任务流转表数据
					taskTransfer = new TaskTransfer();
					String taskTransferUuid = generator.generate();
					taskTransfer.setTaskTransferId(taskTransferUuid);
					taskTransfer.setTaskDetailId(taskDetailUuid); //任务明细表主键
					taskTransfer.setTransferStatus("0"); //流转状态    0:未分派、1：未接收、2：待办、3：在办、4：办结
					taskTransfer.setTransfeRoperate(TaskTransfer.CREATE); //流转操作  0:创建、1：分派、2：接收、3：回退
					taskTransfer.setTransferTime(transferTime); //流转时间
					taskTransfer.setOperatorId(userId); //操作员
					taskTransfer.setRoperatePersonId(userId);//受理员
					taskTransfer.setOperatorDept(userDeptID.toString());
					taskTransfer.setRoperateDeptId(userDeptID.toString());

					taskTransferList.add(taskTransfer);
				}
				List<Task> taskList = new ArrayList<Task>();
				Iterator<Entry<String, Object>> iter = taskMap.entrySet().iterator(); //获得map的Iterator
				while(iter.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>)iter.next();
					taskList.add((Task) entry.getValue());
				}
				// 插入任务表
	//			Map<String, Object> taskmap = new HashMap<String, Object>();
	//			taskmap.put("list", taskList);
	//			taskMappert.insertGroup(taskmap);

				// 插入任务明细表
				Map<String, Object> taskDetailmap = new HashMap<String, Object>();
				taskDetailmap.put("list", taskDetailList);
				taskDetailMapperForBatch.insertGroup(taskDetailmap);

				// 插入任务流转表
				Map<String, Object> taskTransfermap = new HashMap<String, Object>();
				taskTransfermap.put("list", taskTransferList);
				taskTransferMapperForBatch.insertGroup(taskTransfermap);

				// 插入新导入的部门数据
				if(UccDeptsNewMap != null && UccDeptsNewMap.size() > 0){
					Iterator<Entry<String, UccDepts>> UccDeptsiter = UccDeptsNewMap.entrySet().iterator();  //获得map的Iterator
					while(UccDeptsiter.hasNext()) {
						Entry<String, UccDepts> entry = (Entry<String, UccDepts>)UccDeptsiter.next();
						UccDeptsNewList.add((UccDepts) entry.getValue());
					}
					Map<String, Object> deptNewmap = new HashMap<String, Object>();
					deptNewmap.put("list", UccDeptsNewList);
					uccDeptsService.insertGroup(deptNewmap);
				}
				// 修改数据导入批次表
				if(oldTaskBatch == null || "".equals(oldTaskBatch)){
					barchsMap.put(ImportBatch.BATCHFLAG, ImportBatch.BATCHFLAGY);
					importBatchMapperForBatch.updateFlagByBatch(barchsMap);
				}
				// 删除数据导入表
				dataImportMapperForBatch.deleteByBatch(barchsMap);
				sqlSession.commit();
				sqlSession.clearCache();
			}
		}catch(Throwable t){
			logger.error(t.getMessage(), t);
			t.printStackTrace();
			sqlSession.rollback();
		}finally{
			sqlSession.close();
		}
	}

	/***
	 * 替换现有任务
	 */
	@Override
	@Transactional("txManager")
	public void createOldTask(String barchs, String userId, String oldTaskBatch) throws Exception {
		
		// 根据批次删除 除在办或办结之外的任务
		Map<String, Object> oldTaskBatchMap = new HashMap<String, Object>();
		oldTaskBatchMap.put("oldTaskBatch", oldTaskBatch);
		taskDetailMapper.deleteBYBatch(oldTaskBatchMap);

		// 删除导入批次表中barchs批次数据
		Map<String, Object> barchsMap = new HashMap<String, Object>();
		String importBarchs = "'" + barchs.replaceAll(",", "','") + "'";
		barchsMap.put("importBarchs", importBarchs);
		importBatchMapper.deleteBYBatchs(barchsMap);
		
		// 创建任务
		createNewTask(barchs, userId, oldTaskBatch);
	}

	@Override
	public List<String> autoSearchTaskBatch(Map<String, Object> map) throws Exception {
		return importBatchMapper.autoSearchTaskBatch(map);
	}
}
