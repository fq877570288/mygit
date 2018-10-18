package cn.cucsi.bsd.ucc.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
import cn.cucsi.bsd.ucc.common.mapper.TaskDetailMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskRecordMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferMapper;
import cn.cucsi.bsd.ucc.common.mapper.TaskTransferRecordMapper;
import cn.cucsi.bsd.ucc.common.untils.UUIDGenerator;
import cn.cucsi.bsd.ucc.data.domain.*;
import cn.cucsi.bsd.ucc.service.CompleteTaskService;
import cn.cucsi.bsd.ucc.service.UccDeptsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompleteTaskServiceImpl implements CompleteTaskService {

	private static Logger logger = Logger.getLogger(CompleteTaskServiceImpl.class);

	@Autowired
	private TaskDetailMapper taskDetailMapper;
	@Autowired
	private UccDeptsService uccDeptsService;
	@Autowired
	private TaskTransferMapper taskTransferMapper;
	@Autowired
	private TaskRecordMapper taskRecordMapper;
	@Autowired
	private TaskTransferRecordMapper taskTransferRecordMapper;
	
	@Override
	@Transactional
	public List<TaskDetail> selectCompleteBySearch(TaskDetailSearch search) throws Exception {
		
		// 部门
		//List<UccDepts> ngtDeptList = uccDeptsService.selectByUserId(search.getUserId());
		String deptID = taskDetailMapper.selDeptByUserIdFromTaskDetail(search);
		search.setTaskStatus("4");
		search.setRoperateDeptId(deptID);
		// 分页查询
		search.setup(taskDetailMapper.selectCompleteBySearchCount(search), search.getShowLines());
		return taskDetailMapper.selectCompleteBySearch(search);
	}

	private int updateTaskStatusByEndDate(TaskDetailSearch search, List<TaskDetail> taskDetailList, String deptId, String userId, String callResult)throws Exception {

		if(taskDetailList == null || taskDetailList.isEmpty()) {
			logger.info("Execute updateTaskStatusByEndDate end.  taskDetail count is 0");
			return 0;
		}
		logger.info("Execute updateTaskStatusByEndDate ...  taskDetail count is "+taskDetailList.size());
		// 主键
		UUIDGenerator generator = new UUIDGenerator();
		// 流转时间
		Timestamp transferTime = new Timestamp(System.currentTimeMillis());
		List<TaskTransferRecord> taskTransferRecordList = new ArrayList<TaskTransferRecord>();

		// 所有办结任务
		List<TaskRecord> taskRecordList = new ArrayList<TaskRecord>();
		List<String> taskDetailIdList = new ArrayList<>();
		TaskDetailSearch taskDetailSearch = null;

		for (TaskDetail taskDetail : taskDetailList) {
			taskDetailIdList.add(taskDetail.getTaskDetailId());

			TaskRecord taskRecord = new TaskRecord();
			taskDetailSearch = new TaskDetailSearch();
			taskDetailSearch.setUserId(userId);
			taskDetailSearch.setDomainId(taskDetail.getDomainId());
			taskDetailSearch.setTaskDetailId(taskDetail.getTaskDetailId());
			// 部门
			deptId = taskDetailMapper.selDeptByUserIdFromTaskDetail(taskDetailSearch);
			taskRecord.setTaskRecordId(taskDetail.getTaskDetailId());
			taskRecord.setOperatorId(userId);
			taskRecord.setOperatorDept(deptId);
			taskRecord.setCallResult(callResult); //分派者关闭
			taskRecord.setCallMemo("");
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
			taskRecord.setDomainId(taskDetail.getDomainId());
			taskRecordList.add(taskRecord);

			TaskTransferRecord taskTransferRecord = new TaskTransferRecord();
			taskTransferRecord.setRoperateDeptId(deptId);
			String taskTransferRecordUuid = generator.generate();
			taskTransferRecord.setTaskRecordId(taskDetail.getTaskDetailId()); //归档表主键
			taskTransferRecord.setTaskTransferRecordId(taskTransferRecordUuid); //流转归档表主键
			taskTransferRecord.setTransferStatus("4"); //流转状态    0:未分派、1：未接收、2：待办、3：在办 4：办结
			taskTransferRecord.setTransferTime(transferTime); //流转时间
			taskTransferRecord.setCallResult(callResult); //超时办结
			taskTransferRecord.setCallMemo("");
			taskTransferRecord.setRecordTime(transferTime);
			taskTransferRecord.setRecordOperatorId(userId);
			taskTransferRecord.setRecordOperatorDept(deptId);
			taskTransferRecord.setRoperatePersonId(userId);
			taskTransferRecord.setRoperateDeptId(deptId);
			taskTransferRecord.setOperatorDept(deptId);
			taskTransferRecord.setOperatorId(userId);
			taskTransferRecord.setDomainId(taskDetail.getDomainId());

			taskTransferRecordList.add(taskTransferRecord);
		}
		List<TaskTransfer> transferList = taskTransferMapper.selectTransferByTaskDetailIdInList(taskDetailIdList);
		for(TaskTransfer transfer : transferList){

			TaskTransferRecord taskTransferRecord = new TaskTransferRecord();
			taskTransferRecord.setRoperateDeptId(transfer.getRoperateDeptId());
			taskTransferRecord.setTaskRecordId(transfer.getTaskDetailId()); //归档表主键
			taskTransferRecord.setTaskTransferRecordId(transfer.getTaskTransferId()); //流转归档表主键
			taskTransferRecord.setTransferStatus(transfer.getTransferStatus()); //流转状态    0:未分派、1：未接收、2：待办、3：在办 4：办结
			taskTransferRecord.setTransferTime(transfer.getTransferTime()); //流转时间
			taskTransferRecord.setOperatorId(transfer.getOperatorId()); //操作员
			taskTransferRecord.setOperatorDept(transfer.getOperatorDept());
			taskTransferRecord.setCallResult(transfer.getCallResult());
			taskTransferRecord.setCallMemo(transfer.getCallMemo());
			taskTransferRecord.setCdrId(transfer.getCdrId());
			taskTransferRecord.setRecordTime(transferTime);
			taskTransferRecord.setRoperatePersonId(transfer.getRoperatePersonId());//受理员
			taskTransferRecord.setRoperateDeptId(transfer.getRoperateDeptId());//受理部门
			taskTransferRecord.setRecordOperatorId(transfer.getRoperatePersonId());//受理员
			taskTransferRecord.setRecordOperatorDept(transfer.getRoperateDeptId());//受理部门
			taskTransferRecord.setDomainId(transfer.getDomainId());
			taskTransferRecordList.add(taskTransferRecord);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (taskRecordList != null && taskRecordList.size() > 0) {
			// 批量插入归档表
			map.put("list", taskRecordList);
			taskRecordMapper.insertGroup(map);

			// 删除任务表
			taskDetailMapper.deleteWithListId(taskRecordList);
		}

		if(taskTransferRecordList != null && taskTransferRecordList.size() > 0){
			// 批量插入任务流转归档表
			map.put("list", taskTransferRecordList);

			taskTransferRecordMapper.insertGroup(map);

			// 删除流转表
			taskTransferMapper.deleteWithListId(taskTransferRecordList);
		}
		taskDetailList = taskDetailMapper.selectTaskStatusByEndDate(search);
		return updateTaskStatusByEndDate(search, taskDetailList, deptId, userId, callResult);
	}

	@Override
	@Transactional
	public int updateTaskStatusByEndDate() throws Exception {

		logger.info("Start to execute end date task...");
		TaskDetailSearch search = new TaskDetailSearch();
		search.setEndDateBeforeNow("1");

		List<TaskDetail> taskDetailList = taskDetailMapper.selectTaskStatusByEndDate(search);

		this.updateTaskStatusByEndDate(search, taskDetailList, null, null, "1");
		return 0;
	}

	@Override
	@Transactional
	public int updateTaskStatusByIds(TaskDetailSearch search) throws Exception {
		logger.info("Start to execute update task status by ids...");

		if(search.getUserId() == null || "".equals(search.getUserId())){
			logger.warn("Login user id is empty.");
			return 0;
		}
		//List<UccDepts> ngtDeptList = uccDeptsService.selectByUserId(search.getUserId());
		//String deptId = ngtDeptList.get(0).getDeptId().toString();
		// 部门
		//String deptId = taskDetailMapper.selDeptByUserIdFromTaskDetail(search);
		//search.setRoperateDeptId(deptId);

		List<TaskDetail> taskDetailList = taskDetailMapper.selectTaskStatusByEndDate(search);

		this.updateTaskStatusByEndDate(search, taskDetailList, null, search.getUserId().toString(), "6");
		return 0;
	}

}
