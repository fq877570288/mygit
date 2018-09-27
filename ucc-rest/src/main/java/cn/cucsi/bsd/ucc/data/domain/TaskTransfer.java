package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/****
 * 任务流转表
 * add by wangxiaoyu
 * 2018-08-30
 */
public class TaskTransfer {

    public static final String CREATE = "0"; // 创建
    public static final String ALLOCATION = "1"; // 分派
    public static final String RECEIVE = "2"; // 接收
    public static final String BACK = "3"; // 回退

    @JsonIgnore
    private String taskTransferId; //主键

    @ApiModelProperty(value = "任务明细主键",required = true)
    private String taskDetailId; //任务明细表主键

    @ApiModelProperty(value = "流转状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退",required = true)
    private String transferStatus; //流转状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退

    @JsonIgnore
    private String roperatePersonId; //任务受理人ID
    private String userName; //任务受理人

    @JsonIgnore
    private String roperateDeptId; //任务受理部门ID

    private String roperateDept; //任务受理部门

    @ApiModelProperty(value = "外呼备注",required = true)
    private String callMemo; //外呼备注

    @ApiModelProperty(value = "外呼结果（成功或失败）",required = true)
    private String callResult; //外呼结果

    @JsonIgnore
    private String transfeRoperate; //流转操作  0:创建、1：分派、2：接收、3：回退

    private Date transferTime; //流转时间

    @JsonIgnore
    private String cdrId; //通话记录ID

    @JsonIgnore
    private String backMemo; //回退备注

    @JsonIgnore
    private String operatorId; //操作员

    @JsonIgnore
    private String operatorDept; //操作部门

    @JsonIgnore
    private String domainId;//租户ID

    private String destinationNumber; //被叫
    private Integer callTime;//接通时长
    private String taskCode;//任务编码
    private String callTimeStr;//通话时长（*天*小时*分*秒个格式）

    private String totalTime; //通话时长
    private Date createdTime; //开始时间
    private Date answeredTime; //应答时间
    private Date hangupTime; //挂机时间
    private String recordid1; //录音
    private String recordid2; //留言
    private Integer qc; //通话质检
    private String hangupCause;
    private String receiveDeptName; //任务接收部门名称

    public String getTaskTransferId() {
        return taskTransferId;
    }

    public void setTaskTransferId(String taskTransferId) {
        this.taskTransferId = taskTransferId == null ? null : taskTransferId.trim();
    }

    public String getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(String taskDetailId) {
        this.taskDetailId = taskDetailId == null ? null : taskDetailId.trim();
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus == null ? null : transferStatus.trim();
    }

    public String getRoperatePersonId() {
        return roperatePersonId;
    }

    public void setRoperatePersonId(String roperatePersonId) {
        this.roperatePersonId = roperatePersonId == null ? null : roperatePersonId.trim();
    }

    public String getRoperateDeptId() {
        return roperateDeptId;
    }

    public void setRoperateDeptId(String roperateDeptId) {
        this.roperateDeptId = roperateDeptId == null ? null : roperateDeptId.trim();
    }

    public String getCallMemo() {
        return callMemo;
    }

    public void setCallMemo(String callMemo) {
        this.callMemo = callMemo == null ? null : callMemo.trim();
    }

    public String getCallResult() {
        return callResult;
    }

    public void setCallResult(String callResult) {
        this.callResult = callResult == null ? null : callResult.trim();
    }

    public String getTransfeRoperate() {
        return transfeRoperate;
    }

    public void setTransfeRoperate(String transfeRoperate) {
        this.transfeRoperate = transfeRoperate == null ? null : transfeRoperate.trim();
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
        this.cdrId = cdrId == null ? null : cdrId.trim();
    }

    public String getBackMemo() {
        return backMemo;
    }

    public void setBackMemo(String backMemo) {
        this.backMemo = backMemo == null ? null : backMemo.trim();
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId == null ? null : operatorId.trim();
    }

    public String getOperatorDept() {
        return operatorDept;
    }

    public void setOperatorDept(String operatorDept) {
        this.operatorDept = operatorDept == null ? null : operatorDept.trim();
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getRoperateDept() {
        return roperateDept;
    }

    public void setRoperateDept(String roperateDept) {
        this.roperateDept = roperateDept;
    }

    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCallTimeStr() {
        return callTimeStr;
    }

    public void setCallTimeStr(String callTimeStr) {
        this.callTimeStr = callTimeStr;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
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

    public Integer getQc() {
        return qc;
    }

    public void setQc(Integer qc) {
        this.qc = qc;
    }

    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause;
    }

    public String getReceiveDeptName() {
        return receiveDeptName;
    }

    public void setReceiveDeptName(String receiveDeptName) {
        this.receiveDeptName = receiveDeptName;
    }
}