package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.data.domain.Paging;
import java.util.List;

public class TaskDetailSearch  extends Paging {
	
	private String businessCode; //业务号码

    private String taskTypeName; //任务类型名称

    private String deptMeshName; //网给名称

    private String deptAreaName; //包区名称
    
    private String developmentDeptName; //发展部门
    
    private String roperateDeptId; // 受理部门
    
    private String taskStatus; // 流转操作
	private String taskStatusList; // 流转操作
    
    private String userId;
    
    private String taskCode; //任务编码
    
    private String taskDetailId; //任务主键

	private String taskDetailIds;
	private List<String> taskDetailIdInList;
    
    private String userName; //联系人姓名
    
    private String taskTypeId; //任务类型ID
    
    private String defultPhone; //联系电话
    
    private String tariffName; //资费名称
    
    private String status; //客户状态
    
    private String ponLogo; //接入方式
    
    private String deptLevel; //部门等级
    
    private String beginTime;
    
    private String endTime;
    
    private String importPersonId;
    
    private String operatorId; //操作员
    
    private String importBatch; //导入批次
    
    private String outCallRecord;//本月是否有外呼记录

	private String deptIdAndChildIds; //部门ID以及自部门ID字符串，逗号分隔

	private String endDateBeforeNow;

	private String completeMode;

	private Integer taskNumberStart;

	private Integer taskNumberEnd;

	public Integer getTaskNumberStart() {
		return taskNumberStart;
	}

	public void setTaskNumberStart(Integer taskNumberStart) {
		this.taskNumberStart = taskNumberStart;
	}

	public Integer getTaskNumberEnd() {
		return taskNumberEnd;
	}

	public void setTaskNumberEnd(Integer taskNumberEnd) {
		this.taskNumberEnd = taskNumberEnd;
	}

	public String getCompleteMode() {
		return completeMode;
	}

	public void setCompleteMode(String completeMode) {
		this.completeMode = completeMode;
	}

	public String getEndDateBeforeNow() {
		return endDateBeforeNow;
	}

	public void setEndDateBeforeNow(String endDateBeforeNow) {
		this.endDateBeforeNow = endDateBeforeNow;
	}

	public String getDeptIdAndChildIds() {
		return deptIdAndChildIds;
	}

	public void setDeptIdAndChildIds(String deptIdAndChildIds) {
		this.deptIdAndChildIds = deptIdAndChildIds;
	}

	public String getOutCallRecord() {
		return outCallRecord;
	}

	public void setOutCallRecord(String outCallRecord) {
		this.outCallRecord = outCallRecord;
	}

	public String getImportBatch() {
		return importBatch;
	}

	public void setImportBatch(String importBatch) {
		this.importBatch = importBatch;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getImportPersonId() {
		return importPersonId;
	}

	public void setImportPersonId(String importPersonId) {
		this.importPersonId = importPersonId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(String deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPonLogo() {
		return ponLogo;
	}

	public void setPonLogo(String ponLogo) {
		this.ponLogo = ponLogo;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public String getDefultPhone() {
		return defultPhone;
	}

	public void setDefultPhone(String defultPhone) {
		this.defultPhone = defultPhone;
	}

	public String getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoperateDeptId() {
		return roperateDeptId;
	}

	public void setRoperateDeptId(String roperateDeptId) {
		this.roperateDeptId = roperateDeptId;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public String getDeptMeshName() {
		return deptMeshName;
	}

	public void setDeptMeshName(String deptMeshName) {
		this.deptMeshName = deptMeshName;
	}

	public String getDeptAreaName() {
		return deptAreaName;
	}

	public void setDeptAreaName(String deptAreaName) {
		this.deptAreaName = deptAreaName;
	}

	public String getDevelopmentDeptName() {
		return developmentDeptName;
	}

	public void setDevelopmentDeptName(String developmentDeptName) {
		this.developmentDeptName = developmentDeptName;
	}

	public String getTaskDetailId() {
		return taskDetailId;
	}

	public void setTaskDetailId(String taskDetailId) {
		this.taskDetailId = taskDetailId;
	}

	public String getTaskDetailIds() {
		return taskDetailIds;
	}

	public void setTaskDetailIds(String taskDetailIds) {
		this.taskDetailIds = taskDetailIds;
	}

	public List<String> getTaskDetailIdInList() {
		return taskDetailIdInList;
	}

	public void setTaskDetailIdInList(List<String> taskDetailIdInList) {
		this.taskDetailIdInList = taskDetailIdInList;
	}

	public String getTaskStatusList() {
		return taskStatusList;
	}

	public void setTaskStatusList(String taskStatusList) {
		this.taskStatusList = taskStatusList;
	}
}