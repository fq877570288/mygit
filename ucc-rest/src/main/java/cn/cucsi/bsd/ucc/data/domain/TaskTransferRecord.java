package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.component.PbxUtils;

import java.util.Date;

public class TaskTransferRecord {
	
	private String taskTransferRecordId; //主键

    private String taskRecordId; //任务归档表主键

    private String transferStatus; //流转状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退

    private String roperatePersonId; //任务受理人

    private String roperateDeptId; //任务受理部门

    private String callMemo; //外呼备注

    private String callResult; //外呼结果

    private String transfeRoperate; //流转操作  0:创建、1：分派、2：接收、3：回退

    private Date transferTime; //流转时间

    private String cdrId; //通话记录ID

    private String backMemo; //回退备注

    private String operatorId; //操作员
    
    private String operatorDept; //操作部门
    
    private String deptMeshId;
    
    private String deptAreaId;
    
    private String developmentDept;
    
    private String totalTime; //通话时长
    
    private String destinationNumber; //呼叫号码
    
    private String taskCode; //任务编号
    
    private Integer callTime; //接通时长
    
    private Date createdTime; //开始时间
    
    private Date answeredTime; //应答时间
    
    private Date hangupTime; //挂机时间
    
    private String hangupCauseStr; //挂机原因
    
    private Integer qc; //通话质检
    
    private String recordid1; //录音
    
    private String recordid2; //留言
    
    private String hangupCause;
    
    private Date recordTime; //归档时间
    
    private String recordOperatorId; //归档人
    
    private String recordOperatorDept; //归档人部门

	private String domainId;//租户ID
    
    public String getTrTime(){
    	return MyUtils.formatDate(transferTime);
    }

	public String getTaskTransferRecordId() {
		return taskTransferRecordId;
	}

	public void setTaskTransferRecordId(String taskTransferRecordId) {
		this.taskTransferRecordId = taskTransferRecordId;
	}

	public String getTaskRecordId() {
		return taskRecordId;
	}

	public void setTaskRecordId(String taskRecordId) {
		this.taskRecordId = taskRecordId;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getRoperatePersonId() {
		return roperatePersonId;
	}

	public void setRoperatePersonId(String roperatePersonId) {
		this.roperatePersonId = roperatePersonId;
	}

	public String getRoperateDeptId() {
		return roperateDeptId;
	}

	public void setRoperateDeptId(String roperateDeptId) {
		this.roperateDeptId = roperateDeptId;
	}

	public String getCallMemo() {
		return callMemo;
	}

	public void setCallMemo(String callMemo) {
		this.callMemo = callMemo;
	}

	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public String getTransfeRoperate() {
		return transfeRoperate;
	}

	public void setTransfeRoperate(String transfeRoperate) {
		this.transfeRoperate = transfeRoperate;
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	public String getCdrId() {
		return cdrId;
	}

	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}

	public String getBackMemo() {
		return backMemo;
	}

	public void setBackMemo(String backMemo) {
		this.backMemo = backMemo;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorDept() {
		return operatorDept;
	}

	public void setOperatorDept(String operatorDept) {
		this.operatorDept = operatorDept;
	}

	public String getDeptMeshId() {
		return deptMeshId;
	}

	public void setDeptMeshId(String deptMeshId) {
		this.deptMeshId = deptMeshId;
	}

	public String getDeptAreaId() {
		return deptAreaId;
	}

	public void setDeptAreaId(String deptAreaId) {
		this.deptAreaId = deptAreaId;
	}

	public String getDevelopmentDept() {
		return developmentDept;
	}

	public void setDevelopmentDept(String developmentDept) {
		this.developmentDept = developmentDept;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getDestinationNumber() {
		return destinationNumber;
	}

	public void setDestinationNumber(String destinationNumber) {
		this.destinationNumber = destinationNumber;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public Integer getCallTime() {
		return callTime;
	}

	public void setCallTime(Integer callTime) {
		this.callTime = callTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getAnsweredTime() {
		return answeredTime;
	}

	public void setAnsweredTime(Date answeredTime) {
		this.answeredTime = answeredTime;
	}

	public Date getHangupTime() {
		return hangupTime;
	}

	public void setHangupTime(Date hangupTime) {
		this.hangupTime = hangupTime;
	}

	public String getHangupCauseStr() {
		return hangupCauseStr;
	}

	public void setHangupCauseStr(String hangupCauseStr) {
		this.hangupCauseStr = hangupCauseStr;
	}

	public Integer getQc() {
		return qc;
	}

	public void setQc(Integer qc) {
		this.qc = qc;
	}

	public String getRecordid1() {
		return recordid1;
	}

	public void setRecordid1(String recordid1) {
		this.recordid1 = recordid1;
	}

	public String getRecordid2() {
		return recordid2;
	}

	public void setRecordid2(String recordid2) {
		this.recordid2 = recordid2;
	}

	public String getHangupCause() {
		return hangupCause;
	}

	public void setHangupCause(String hangupCause) {
		this.hangupCause = hangupCause == null ? null : hangupCause.trim();
        this.hangupCauseStr = PbxUtils.hangupCause2cn(hangupCause);
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getRecordOperatorId() {
		return recordOperatorId;
	}

	public void setRecordOperatorId(String recordOperatorId) {
		this.recordOperatorId = recordOperatorId;
	}

	public String getRecordOperatorDept() {
		return recordOperatorDept;
	}

	public void setRecordOperatorDept(String recordOperatorDept) {
		this.recordOperatorDept = recordOperatorDept;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
}
