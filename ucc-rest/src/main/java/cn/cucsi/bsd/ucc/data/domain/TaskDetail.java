package cn.cucsi.bsd.ucc.data.domain;

import java.util.Date;

/****
 * 任务明细表
 * add by wangxiaoyu
 * 2018-08-30
 */
public class TaskDetail {

    private String transferStatus;//流转状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退
    private String callMemo;//外呼备注
    private String callResult;//外呼结果
    private String custName;//客户姓名
    private String custId;//客户ID

    private String customerPhone;//客户联系电话
    private String taskDetailId;//主键
    private String businessCode;//业务编码
    private String domainId;//租户ID
    private String taskTypeName; //任务类型名称

    private String taskTypeId;//任务类型

    private String deptMeshId;//网格

    private String deptAreaId;//包区

    private String deptAgencyId;//代理商

    private String taskCode;//任务编码

    private String productType;//产品类型

    private String phoneNumber;//默认显示号码

    private String rate;//速率

    private String customfields1;//自定义字段1

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

    private String responsible;//责任体

    private String netStop;//网别

    private String packageName;//套餐名称

    private String contractName;//合约名称

    private String contractStartTime;//合约开始时间

    private String contractEndTime;//合约结束时间

    private String activationTime;//激活时间

    private String developmentDept;//发展部门

    private String importBatch;//导入批次

    private String importPersonId;//导入人主键

    private Date importTime;//导入时间

    private String contacts;//联系人

    private String status;//状态

    private String installedAddress;//装机地址

    private String ponLogo;//PON标识

    private String tariffName;//资费名称

    private String phoneNumber2;//联系电话2

    private String phoneNumber3;//联系电话3

    private String taskStatus;// 任务状态 0:未分派、1：未接收、2：待办、3：在办、4：办结、5：回退

    private String roperateDeptId;//任务处理部

    private String userName; //用户名称

    private String defultPhone;//默认显示联系电话

    private String initDevelopment;//发展部门(初始)

    private String initMeshId;//网格(初始)

    private String initAreaId;//包区(初始)

    private Date endDate;//截止日期

    private String userId;//用户ID

    private String operatorId; //人员主键
    private Date transferTime; //流转时间
    private String nickName; //用户昵称
    private String deptName; //部门名称
    private String cdrId; //通话记录ID
    private String deptMeshName; //网给名称
    private String deptAreaName; //包区名称
    private String developmentDeptName; //发展部门
    private String checkFlag; //用于分派任务避免重复更新客户信息

    public String getTaskDetailId() {
        return taskDetailId;
    }

    public void setTaskDetailId(String taskDetailId) {
        this.taskDetailId = taskDetailId == null ? null : taskDetailId.trim();
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    public String getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(String taskTypeId) {
        this.taskTypeId = taskTypeId == null ? null : taskTypeId.trim();
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

    public String getDeptAgencyId() {
        return deptAgencyId;
    }

    public void setDeptAgencyId(String deptAgencyId) {
        this.deptAgencyId = deptAgencyId == null ? null : deptAgencyId.trim();
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
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

    public String getDevelopmentDept() {
        return developmentDept;
    }

    public void setDevelopmentDept(String developmentDept) {
        this.developmentDept = developmentDept == null ? null : developmentDept.trim();
    }

    public String getImportBatch() {
        return importBatch;
    }

    public void setImportBatch(String importBatch) {
        this.importBatch = importBatch == null ? null : importBatch.trim();
    }

    public String getImportPersonId() {
        return importPersonId;
    }

    public void setImportPersonId(String importPersonId) {
        this.importPersonId = importPersonId == null ? null : importPersonId.trim();
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getInstalledAddress() {
        return installedAddress;
    }

    public void setInstalledAddress(String installedAddress) {
        this.installedAddress = installedAddress == null ? null : installedAddress.trim();
    }

    public String getPonLogo() {
        return ponLogo;
    }

    public void setPonLogo(String ponLogo) {
        this.ponLogo = ponLogo == null ? null : ponLogo.trim();
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName == null ? null : tariffName.trim();
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2 == null ? null : phoneNumber2.trim();
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3 == null ? null : phoneNumber3.trim();
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    public String getRoperateDeptId() {
        return roperateDeptId;
    }

    public void setRoperateDeptId(String roperateDeptId) {
        this.roperateDeptId = roperateDeptId == null ? null : roperateDeptId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getDefultPhone() {
        return defultPhone;
    }

    public void setDefultPhone(String defultPhone) {
        this.defultPhone = defultPhone == null ? null : defultPhone.trim();
    }

    public String getInitDevelopment() {
        return initDevelopment;
    }

    public void setInitDevelopment(String initDevelopment) {
        this.initDevelopment = initDevelopment == null ? null : initDevelopment.trim();
    }

    public String getInitMeshId() {
        return initMeshId;
    }

    public void setInitMeshId(String initMeshId) {
        this.initMeshId = initMeshId == null ? null : initMeshId.trim();
    }

    public String getInitAreaId() {
        return initAreaId;
    }

    public void setInitAreaId(String initAreaId) {
        this.initAreaId = initAreaId == null ? null : initAreaId.trim();
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getCdrId() {
        return cdrId;
    }

    public void setCdrId(String cdrId) {
        this.cdrId = cdrId;
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

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }
}