package cn.cucsi.bsd.ucc.data.domain;

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
    private String roperatePersonId; //任务受理人

    @JsonIgnore
    private String roperateDeptId; //任务受理部门

    @ApiModelProperty(value = "外呼备注",required = true)
    private String callMemo; //外呼备注

    @ApiModelProperty(value = "外呼结果（成功或失败）",required = true)
    private String callResult; //外呼结果

    @JsonIgnore
    private String transfeRoperate; //流转操作  0:创建、1：分派、2：接收、3：回退

    @JsonIgnore
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
}