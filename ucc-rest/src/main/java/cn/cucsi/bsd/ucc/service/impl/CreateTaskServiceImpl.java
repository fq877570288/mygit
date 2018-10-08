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
import cn.cucsi.bsd.ucc.service.UccUserService;
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
	@Autowired
	private UccUserService uccUserService;

	/***
	 * 新建任务
	 */
	@Override
//	@Transactional("txManager")
	public Map<String,Object> createNewTask(String barchs, String userId, String oldTaskBatch) throws Exception {

		Map<String,Object> createTaskMap = new HashMap<String,Object>();
		createTaskMap.put("msg", "新建任务失败!");
		createTaskMap.put("code", "-1");

		SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		DataImportMapper dataImportMapperForBatch = sqlSession.getMapper(DataImportMapper.class);
		TaskDetailMapper taskDetailMapperForBatch = sqlSession.getMapper(TaskDetailMapper.class);
		TaskTransferMapper taskTransferMapperForBatch = sqlSession.getMapper(TaskTransferMapper.class);
		ImportBatchMapper importBatchMapperForBatch = sqlSession.getMapper(ImportBatchMapper.class);
		try {
			String importBarchs = "'" + barchs.replaceAll(",", "','") + "'";
			Map<String, Object> barchsMap = new HashMap<String, Object>();
			barchsMap.put("importBarchs", importBarchs);
			System.out.println("创建任务测试 importBarchs:::" + importBarchs);
			// 获取导入数据
			List<DataImport> dataImportList = dataImportMapperForBatch.selectByBarchs(barchsMap);
			System.out.println("创建任务测试--按批次查询获取导入数据 dataImportList:::" + dataImportList.size());
			if(MyUtils.isBlank(dataImportList)){
				createTaskMap.put("msg", "创建任务时,按批次查询获取导入数据为空!");
				return createTaskMap;
			}
			// 部门
			String userDeptID = "";
			List<UccDepts> userDeptList = uccDeptsService.selectByUserId(userId);
			System.out.println("创建任务测试 userDeptList:::" + userDeptList.size());
			if(!MyUtils.isBlank(userDeptList)){
				for(UccDepts uccDepts : userDeptList){
					userDeptID = uccDepts.getDeptId();
				}
			}else{
				createTaskMap.put("msg", "创建任务时,根据userId查询部门信息为空!");
				return createTaskMap;
			}
			// 部门ID最大值(当时是参照哈分项目，id是自增的，该项目id为uuid 所以没用了)
			//String maxDeptID = uccDeptsService.selectMaxDeptID();
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

			String meshDeptId = "";
			String areaDeptId = "";
			Long DevelopmentID = null;

			UccUsers uccUsers = uccUserService.findOne(userId);
			if(MyUtils.isBlank(uccUsers)){
				createTaskMap.put("msg", "根据userId:::"+userId+"查询用户信息为空!");
				return createTaskMap;
			}
			String domainId = uccUsers.getDomainId()==null?"":uccUsers.getDomainId();
			if(MyUtils.isBlank(domainId)){
				createTaskMap.put("msg", "用户userId:::"+userId+"查询租户为空!");
				return createTaskMap;
			}
			// 组织数据
			if(dataImportList != null && dataImportList.size() > 0){
				for(int i=0; i<dataImportList.size(); i++){

					dataImport = dataImportList.get(i);
					// 任务明细表
					taskDetail = new TaskDetail();
					// 任务明细表主键
					String taskDetailUuid = generator.generate();
					// 任务类型
					TaskType taskType = null;
					try {
						taskType = businessService.selectByNameInCache(dataImport.getTaskTypeName());
						System.out.println("创建任务测试 taskType:::" + taskType);
					} catch (Exception e) {
						e.printStackTrace();
						createTaskMap.put("msg", "第"+i+"条创建任务时,查询任务类型时发生异常!");
						return createTaskMap;
					}
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
//						UUIDGenerator generator = new UUIDGenerator();
						meshDeptId = generator.generate();

						uccDepts = new UccDepts();
						uccDepts.setDeptId(meshDeptId);
						uccDepts.setDeptLevel(2); // 网格2级部门
						uccDepts.setDeptCreateTime(date);
						uccDepts.setCreatedUserId(userId);
						uccDepts.setDeptAdmin(userId);
						uccDepts.setDeptPid(userDeptID);
						uccDepts.setDeptName(dataImport.getDeptMeshName());
						uccDepts.setDomainId(domainId);

						UccDeptsNewMap.put(dataImport.getDeptMeshName(), uccDepts);
					}
					if(deptArea == null && areaNew == null){

						areaDeptId = generator.generate();
						// 包区
						uccDepts = new UccDepts();
						uccDepts.setDeptId(areaDeptId);
						uccDepts.setDeptLevel(3); // 包区3级部门
						uccDepts.setDeptCreateTime(date);
						uccDepts.setCreatedUserId(userId);
						uccDepts.setDeptAdmin(userId);
						uccDepts.setDeptPid(meshDeptId);
						uccDepts.setDeptName(dataImport.getDeptAreaName());
						uccDepts.setDomainId(domainId);

						UccDeptsNewMap.put(dataImport.getDeptAreaName(), uccDepts);
					}
					if(dataImport.getDevelopmentDept() != null && !"".equals(dataImport.getDevelopmentDept())){
						if(developmentNew == null && deptDevelopment == null){
							areaNew = UccDeptsNewMap.get(dataImport.getDeptAreaName());
							String pid = deptArea == null ? areaNew.getDeptId() : deptArea.getDeptId();
							// 发展部门
							uccDepts = new UccDepts();

							uccDepts.setDeptId(generator.generate());
							uccDepts.setDeptLevel(4); // 发展部门4级部门
                            uccDepts.setDeptCreateTime(date);
							uccDepts.setCreatedUserId(userId);
							uccDepts.setDeptAdmin(userId);
							uccDepts.setDeptPid(areaDeptId);
							uccDepts.setDeptName(dataImport.getDevelopmentDept());
							uccDepts.setDomainId(domainId);

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
					taskDetail.setDeptMeshId(deptMesh == null ? meshDeptId.toString() : deptMesh.getDeptId().toString()); //网格
					taskDetail.setDeptAreaId(deptArea == null ? areaDeptId.toString() : deptArea.getDeptId().toString()); //包区
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
					taskDetail.setInitMeshId(deptMesh == null ? meshDeptId.toString() : deptMesh.getDeptId().toString()); //网格(初始)
					taskDetail.setInitAreaId(deptArea == null ? areaDeptId.toString() : deptArea.getDeptId().toString()); //包区(初始)
					taskDetail.setInitDevelopment(deptDevelopment == null ? dlpId : deptDevelopment.getDeptId().toString()); //发展部门(初始)
					taskDetail.setDomainId(domainId);

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
					taskTransfer.setDomainId(domainId);

					taskTransferList.add(taskTransfer);
				}
				List<Task> taskList = new ArrayList<Task>();
				Iterator<Entry<String, Object>> iter = taskMap.entrySet().iterator(); //获得map的Iterator
				while(iter.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>)iter.next();
					taskList.add((Task) entry.getValue());
				}
				// 插入任务表
				//Map<String, Object> taskmap = new HashMap<String, Object>();
				//taskmap.put("list", taskList);
				//taskMappert.insertGroup(taskmap);

				// 插入任务明细表
				Map<String, Object> taskDetailmap = new HashMap<String, Object>();
				taskDetailmap.put("list", taskDetailList);
				int aa = taskDetailMapperForBatch.insertGroup(taskDetailmap);
				System.out.println("创建任务测试 插入任务明细表返回:::" + aa);
				if(aa<=0){
					createTaskMap.put("msg", "创建任务时,插入任务明细表发生异常!");
					return createTaskMap;
				}

				// 插入任务流转表
				Map<String, Object> taskTransfermap = new HashMap<String, Object>();
				taskTransfermap.put("list", taskTransferList);
				int bb = taskTransferMapperForBatch.insertGroup(taskTransfermap);
				System.out.println("创建任务测试 插入任务流转表:::" + bb);
				if(bb<=0){
					createTaskMap.put("msg", "创建任务时,插入任务流转表发生异常!");
					return createTaskMap;
				}

				// 插入新导入的部门数据
				if(UccDeptsNewMap != null && UccDeptsNewMap.size() > 0){
					Iterator<Entry<String, UccDepts>> UccDeptsiter = UccDeptsNewMap.entrySet().iterator();  //获得map的Iterator
					while(UccDeptsiter.hasNext()) {
						Entry<String, UccDepts> entry = (Entry<String, UccDepts>)UccDeptsiter.next();
						UccDeptsNewList.add((UccDepts) entry.getValue());
					}
					Map<String, Object> deptNewmap = new HashMap<String, Object>();
					deptNewmap.put("list", UccDeptsNewList);
					try {
						int cc = uccDeptsService.insertGroup(deptNewmap);
						System.out.println("创建任务测试 插入新导入的部门数据返回:::" + cc);
					} catch (Exception e) {
						e.printStackTrace();
						createTaskMap.put("msg", "创建任务时,插入新导入的部门数据时发生异常!");
						return createTaskMap;
					}
				}
				// 修改数据导入批次表
				if(oldTaskBatch == null || "".equals(oldTaskBatch)){
					barchsMap.put(ImportBatch.BATCHFLAG, ImportBatch.BATCHFLAGY);
					try {
						int ii = importBatchMapperForBatch.updateFlagByBatch(barchsMap);
						System.out.println("新建任务 修改数据导入批次表返回结果:::" + ii);
					} catch (Exception e) {
						e.printStackTrace();
						createTaskMap.put("msg", "创建任务时,修改数据导入批次表时发生异常!");
						return createTaskMap;
					}
				}
				// 删除数据导入表
				try {
					dataImportMapperForBatch.deleteByBatch(barchsMap);
				} catch (Exception e) {
					e.printStackTrace();
					createTaskMap.put("msg", "创建任务时,删除数据导入表时发生异常!");
					return createTaskMap;
				}
				sqlSession.commit();
				sqlSession.clearCache();
				createTaskMap.put("code", "0");
				createTaskMap.put("msg", "新建任务操作成功！");
			}
		}catch(Exception t){
			logger.error(t.getMessage(), t);
			t.printStackTrace();
			sqlSession.rollback();
			createTaskMap.put("code", "-1");
		}finally{
			sqlSession.close();
		}
		return createTaskMap;
	}

	/***
	 * 替换现有任务
	 */
	@Override
	@Transactional
	public Map<String,Object> createOldTask(String barchs, String userId, String oldTaskBatch) throws Exception {

		Map<String,Object> createOldTaskMap = new HashMap<String,Object>();
		createOldTaskMap.put("msg", "替换现有任务失败!");
		createOldTaskMap.put("code", "-1");
		try {
			// 根据批次删除 除在办或办结之外的任务
			Map<String, Object> oldTaskBatchMap = new HashMap<String, Object>();
			oldTaskBatchMap.put("oldTaskBatch", oldTaskBatch);
			try {
				taskDetailMapper.deleteBYBatch(oldTaskBatchMap);
			} catch (Exception e) {
				e.printStackTrace();
				createOldTaskMap.put("msg", "替换现有任务，根据批次删除 除在办或办结之外的任务时发生异常!");
				return createOldTaskMap;
			}
			// 删除导入批次表中barchs批次数据
			Map<String, Object> barchsMap = new HashMap<String, Object>();
			String importBarchs = "'" + barchs.replaceAll(",", "','") + "'";
			barchsMap.put("importBarchs", importBarchs);
			try {
				importBatchMapper.deleteBYBatchs(barchsMap);
			} catch (Exception e) {
				e.printStackTrace();
				createOldTaskMap.put("msg", "替换现有任务，删除导入批次表中barchs批次数据时发生异常!");
				return createOldTaskMap;
			}
			// 创建任务
			try {
				createNewTask(barchs, userId, oldTaskBatch);
			} catch (Exception e) {
				e.printStackTrace();
				createOldTaskMap.put("msg", "替换现有任务，调用创建任务方法时发生异常!");
				return createOldTaskMap;
			}
			createOldTaskMap.put("code", "0");
			createOldTaskMap.put("msg", "替换现有任务操作成功！");
			return createOldTaskMap;
		} catch (Exception e) {
			e.printStackTrace();
			createOldTaskMap.put("msg", "替换现有任务时发生异常!");
			return createOldTaskMap;
		}
	}

	@Override
	public List<String> autoSearchTaskBatch(Map<String, Object> map) throws Exception {
		return importBatchMapper.autoSearchTaskBatch(map);
	}

	@Override
	public List<String> selectAllImportBatch() throws Exception {
		return importBatchMapper.selectAllImportBatch();
	}
}
