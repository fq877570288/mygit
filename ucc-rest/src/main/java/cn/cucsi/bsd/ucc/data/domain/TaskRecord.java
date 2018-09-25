package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import cn.cucsi.bsd.ucc.component.PbxUtils;
import java.util.Date;

public class TaskRecord {

	private String taskRecordId;

	private String importBatch; // 导入批次

	private String importPersonId; // 导入人主键
	
    private String importPersonName;

	private Date importTime; // 导入时间
	
	private String trTime;  // 导入时间字符串（虽然没用，但是也得有）
	
	private String taskTypeName; // 任务类型名称

	private String deptMeshName; // 网给名称

	private String deptAreaName; // 包区名称

	private String developmentDeptName; // 发展部门

	private String businessCode; // 业务号码

	private String userName; // 用户名称

	private String productType; // 产品类型

	private String tariffName; // 资费名称

	private String installedAddress; // 装机地址

	private String responsible; // 责任体

	private String netStop; // 网别

	private String packageName; // 套餐名称

	private String contractName; // 合约名称

	private String contractStartTime; // 合约开始时间

	private String contractEndTime; // 合约结束时间

	private String activationTime; // 激活时间

	private String contacts; // 联系人

	private String phoneNumber1; // 联系电话1

	private String phoneNumber2; // 联系电话2

	private String phoneNumber; // 联系电话

	private String ponLogo; // PON标识

	private String velocity; // 速率

	private String status; // 状态

	private String customfields1; // 自定义字段1

	private String customfields2;

	private String customfields3;

	private String customfields4;

	private String customfields5;

	private String customfields6;

	private String customfields7;

	private String customfields8;

	private String customfields9;

	private String customfields10;

	private String customfields11;

	private String customfields12;

	private String customfields13;

	private String customfields14;

	private String customfields15;

	private String deptMeshId;
	private String deptAreaId;
	private String developmentId;
	private String cdrId;

	private Date recordTime;

	private String operatorId;
	
    private String operatorName;
	 
	private String operatorDept;
	
	 private String operatorDName;
	
    private String roperateDeptId; //任务处理部门

	private String callMemo;

	private String callResult;
	
	private String callResultName;
	
	private String taskTypeId;
	
	private String taskCode;
	
	private String initDevelopment; 
    
    private String initMeshId; 

    private String initAreaId; 
	
	private String nickName;
	
    private Date transferTime; //流转时间
    
    private String defultPhone; //默认显示联系电话
    
    private String rate; //速率
    
    private String totalTime; //通话时长
    
    private String destinationNumber; //呼叫号码
    
    private Integer callTime; //接通时长
    
    private Date createdTime; //开始时间
    
    private Date answeredTime; //应答时间
    
    private Date hangupTime; //挂机时间
    
    private String hangupCauseStr; //挂机原因
    
    private String hangupCause;

	private Date endDate;  //截止日期  Add by bli 2017-01-18

	private String domainId;//租户ID

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getHangupCauseStr() {
		return hangupCauseStr;
	}

	public void setHangupCauseStr(String hangupCauseStr) {
		this.hangupCauseStr = hangupCauseStr;
	}

	public String getHangupCause() {
		return hangupCause;
	}

	public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause == null ? null : hangupCause.trim();
        this.hangupCauseStr = PbxUtils.hangupCause2cn(hangupCause);
    }
	
	
	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDefultPhone() {
		return defultPhone;
	}

	public void setDefultPhone(String defultPhone) {
		this.defultPhone = defultPhone;
	}

	public String getOperatorDName() {
		return operatorDName;
	}

	public void setOperatorDName(String operatorDName) {
		this.operatorDName = operatorDName;
	}

	public String getImportPersonName() {
		return importPersonName;
	}

	public void setImportPersonName(String importPersonName) {
		this.importPersonName = importPersonName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getTransTime(){
		String s=null;
		if(transferTime!=null){
			s = MyUtils.formatDate(transferTime);
		}
    	return s;
    }
	
	public String getRoperateDeptId() {
		return roperateDeptId;
	}

	public void setRoperateDeptId(String roperateDeptId) {
		this.roperateDeptId = roperateDeptId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInitDevelopment() {
		return initDevelopment;
	}

	public void setInitDevelopment(String initDevelopment) {
		this.initDevelopment = initDevelopment;
	}

	public String getInitMeshId() {
		return initMeshId;
	}

	public void setInitMeshId(String initMeshId) {
		this.initMeshId = initMeshId;
	}

	public String getInitAreaId() {
		return initAreaId;
	}

	public void setInitAreaId(String initAreaId) {
		this.initAreaId = initAreaId;
	}

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getTrTime() {
		String s =null;
		if(importTime!=null){
			s =  MyUtils.formatDate(importTime);
		}
		return s;
	}

	public String getReTime() {
		String s =null;
		if(recordTime!=null){
		 s = MyUtils.formatDate(recordTime);
		}
		return s ;
	}

	public String getTaskRecordId() {
		return taskRecordId;
	}

	public void setTaskRecordId(String taskRecordId) {
		this.taskRecordId = taskRecordId == null ? null : taskRecordId.trim();
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName == null ? null : tariffName.trim();
	}

	public String getImportBatch() {
		return importBatch;
	}

	public void setImportBatch(String importBatch) {
		this.importBatch = importBatch == null ? null : importBatch.trim();
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public String getImportPersonId() {
		return importPersonId;
	}

	public void setImportPersonId(String importPersonId) {
		this.importPersonId = importPersonId == null ? null : importPersonId.trim();
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode == null ? null : businessCode.trim();
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName == null ? null : taskTypeName.trim();
	}

	public String getDeptMeshId() {
		return deptMeshId;
	}

	public void setDeptMeshId(String deptMeshId) {
		this.deptMeshId = deptMeshId == null ? null : deptMeshId.trim();
	}

	public String getDeptAreaId() {
		return deptAreaId;
	}

	public void setDeptAreaId(String deptAreaId) {
		this.deptAreaId = deptAreaId == null ? null : deptAreaId.trim();
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType == null ? null : productType.trim();
	}

	public String getPhoneNumber1() {
		return phoneNumber1;
	}

	public void setPhoneNumber1(String phoneNumber1) {
		this.phoneNumber1 = phoneNumber1 == null ? null : phoneNumber1.trim();
	}

	public String getPhoneNumber2() {
		return phoneNumber2;
	}

	public void setPhoneNumber2(String phoneNumber2) {
		this.phoneNumber2 = phoneNumber2 == null ? null : phoneNumber2.trim();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity == null ? null : velocity.trim();
	}

	public String getCustomfields1() {
		return customfields1;
	}

	public void setCustomfields1(String customfields1) {
		this.customfields1 = customfields1 == null ? null : customfields1.trim();
	}

	public String getCustomfields2() {
		return customfields2;
	}

	public void setCustomfields2(String customfields2) {
		this.customfields2 = customfields2 == null ? null : customfields2.trim();
	}

	public String getCustomfields3() {
		return customfields3;
	}

	public void setCustomfields3(String customfields3) {
		this.customfields3 = customfields3 == null ? null : customfields3.trim();
	}

	public String getCustomfields4() {
		return customfields4;
	}

	public void setCustomfields4(String customfields4) {
		this.customfields4 = customfields4 == null ? null : customfields4.trim();
	}

	public String getCustomfields5() {
		return customfields5;
	}

	public void setCustomfields5(String customfields5) {
		this.customfields5 = customfields5 == null ? null : customfields5.trim();
	}

	public String getCustomfields6() {
		return customfields6;
	}

	public void setCustomfields6(String customfields6) {
		this.customfields6 = customfields6 == null ? null : customfields6.trim();
	}

	public String getCustomfields7() {
		return customfields7;
	}

	public void setCustomfields7(String customfields7) {
		this.customfields7 = customfields7 == null ? null : customfields7.trim();
	}

	public String getCustomfields8() {
		return customfields8;
	}

	public void setCustomfields8(String customfields8) {
		this.customfields8 = customfields8 == null ? null : customfields8.trim();
	}

	public String getCustomfields9() {
		return customfields9;
	}

	public void setCustomfields9(String customfields9) {
		this.customfields9 = customfields9 == null ? null : customfields9.trim();
	}

	public String getCustomfields10() {
		return customfields10;
	}

	public void setCustomfields10(String customfields10) {
		this.customfields10 = customfields10 == null ? null : customfields10.trim();
	}

	public String getCustomfields11() {
		return customfields11;
	}

	public void setCustomfields11(String customfields11) {
		this.customfields11 = customfields11 == null ? null : customfields11.trim();
	}

	public String getCustomfields12() {
		return customfields12;
	}

	public void setCustomfields12(String customfields12) {
		this.customfields12 = customfields12 == null ? null : customfields12.trim();
	}

	public String getCustomfields13() {
		return customfields13;
	}

	public void setCustomfields13(String customfields13) {
		this.customfields13 = customfields13 == null ? null : customfields13.trim();
	}

	public String getCustomfields14() {
		return customfields14;
	}

	public void setCustomfields14(String customfields14) {
		this.customfields14 = customfields14 == null ? null : customfields14.trim();
	}

	public String getCustomfields15() {
		return customfields15;
	}

	public void setCustomfields15(String customfields15) {
		this.customfields15 = customfields15 == null ? null : customfields15.trim();
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible == null ? null : responsible.trim();
	}

	public String getNetStop() {
		return netStop;
	}

	public void setNetStop(String netStop) {
		this.netStop = netStop == null ? null : netStop.trim();
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName == null ? null : packageName.trim();
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName == null ? null : contractName.trim();
	}

	public String getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(String contractStartTime) {
		this.contractStartTime = contractStartTime == null ? null : contractStartTime.trim();
	}

	public String getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(String contractEndTime) {
		this.contractEndTime = contractEndTime == null ? null : contractEndTime.trim();
	}

	public String getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(String activationTime) {
		this.activationTime = activationTime == null ? null : activationTime.trim();
	}

	public String getDevelopmentId() {
		return developmentId;
	}

	public void setDevelopmentId(String developmentId) {
		this.developmentId = developmentId == null ? null : developmentId.trim();
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts == null ? null : contacts.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getInstalledAddress() {
		return installedAddress;
	}

	public void setInstalledAddress(String installedAddress) {
		this.installedAddress = installedAddress == null ? null : installedAddress.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getPonLogo() {
		return ponLogo;
	}

	public void setPonLogo(String ponLogo) {
		this.ponLogo = ponLogo == null ? null : ponLogo.trim();
	}

	public String getCdrId() {
		return cdrId;
	}

	public void setCdrId(String cdrId) {
		this.cdrId = cdrId == null ? null : cdrId.trim();
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
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

	public String getCallResultName() {
		return callResultName;
	}

	public void setCallResultName(String callResultName) {
		this.callResultName = callResultName;
	}

	public void setTrTime(String trTime) {
		this.trTime = trTime;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
}