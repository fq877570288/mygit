package cn.cucsi.bsd.ucc.data.domain;

import java.util.Date;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;

/****
 * 数据导入表
 * add by wangxiaoyu
 * 2018-09-11
 */
public class DataImport {
	
    private String dataImportId; //主键

    private String importBatch; //导入批次

    private String importPersonId; //导入人主键

    private Date importTime; //导入时间

    private String businessCode; //业务号码

    private String taskTypeName; //任务类型名称

    private String deptMeshName; //网给名称

    private String deptAreaName; //包区名称

    private String deptAgencyName; //代理商名称

    private String productType; //产品类型

    private String phoneNumber3; //联系电话

    private String velocity; //速率

    private String customfields1; //自定义字段1

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

    private String responsible; //责任体

    private String netStop; //网别

    private String packageName; //套餐名称

    private String contractName; //合约名称

    private String contractStartTime; //合约开始时间

    private String contractEndTime; //合约结束时间

    private String activationTime; //激活时间

    private String developmentDept; //发展部门
    
    private String phoneNumber1; //联系电话1
    
    private String phoneNumber2; //联系电话2
    
    private String contacts; //联系人
    
    private String userName; //用户名称
    
    private String tariffName; //资费名称
    
    private String installedAddress; //装机地址
    
    private String status; //状态
    
    private String ponLogo; //PON标识
    
    
    public static final String DATAIMPORTID = "dataImportId";
    public static final String IMPORTBATCH = "importBatch";
    public static final String IMPORTPERSONID = "importPersonId";
    public static final String IMPORTTIME = "importTime";
    public static final String BUSINESSCODE = "businessCode";
    public static final String TASKTYPENAME = "taskTypeName";
    public static final String DEPTMESHNAME = "deptMeshName";
    public static final String DEPTAREANAME = "deptAreaName";
    public static final String DEPTAGENCYNAME = "deptAgencyName";
    public static final String PRODUCTTYPE = "productType";
    public static final String PHONENUMBER3 = "phoneNumber3";
    public static final String VELOCITY = "velocity";
    public static final String CUSTOMFIELDS1 = "customfields1";
    public static final String CUSTOMFIELDS2 = "customfields2";
    public static final String CUSTOMFIELDS3 = "customfields3";
    public static final String CUSTOMFIELDS4 = "customfields4";
    public static final String CUSTOMFIELDS5 = "customfields5";
    public static final String CUSTOMFIELDS6 = "customfields6";
    public static final String CUSTOMFIELDS7 = "customfields7";
    public static final String CUSTOMFIELDS8 = "customfields8";
    public static final String CUSTOMFIELDS9 = "customfields9";
    public static final String CUSTOMFIELDS10 = "customfields10";
    public static final String CUSTOMFIELDS11 = "customfields11";
    public static final String CUSTOMFIELDS12 = "customfields12";
    public static final String CUSTOMFIELDS13 = "customfields13";
    public static final String CUSTOMFIELDS14 = "customfields14";
    public static final String CUSTOMFIELDS15 = "customfields15";
    public static final String RESPONSIBLE = "responsible";
    public static final String NETSTOP = "netStop";
    public static final String PACKAGENAME = "packageName";
    public static final String CONTRACTNAME = "contractName";
    public static final String CONTRACTSTARTTIME = "contractStartTime";
    public static final String CONTRACTENDTIME = "contractEndTime";
    public static final String ACTIVATIONTIME = "activationTime";
    public static final String DEVELOPMENTDEPT = "developmentDept";
    public static final String PHONENUMBER1 = "phoneNumber1";
    public static final String PHONENUMBER2 = "phoneNumber2";
    public static final String CONTACTS = "contacts";
    public static final String USERNAME = "userName";
    public static final String TARIFFNAME = "tariffName";
    public static final String INSTALLEDADDRESS = "installedAddress";
    public static final String STATUS = "status";
    public static final String PONLOGO = "ponLogo";
    // Excel后缀
    public static final String XLS = ".xls";
    public static final String XLSX = ".xlsx";
    
    public String getTrTime(){
    	return MyUtils.formatDate(importTime);
    }
    
    public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String velocity) {
		this.velocity = velocity == null ? null : velocity.trim();
	}

	public String getPonLogo() {
		return ponLogo;
	}

	public void setPonLogo(String ponLogo) {
		this.ponLogo = ponLogo == null ? null : ponLogo.trim();
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

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName == null ? null : tariffName.trim();
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

    public String getDataImportId() {
        return dataImportId;
    }

    public void setDataImportId(String dataImportId) {
        this.dataImportId = dataImportId == null ? null : dataImportId.trim();
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

    public String getDeptMeshName() {
        return deptMeshName;
    }

    public void setDeptMeshName(String deptMeshName) {
        this.deptMeshName = deptMeshName == null ? null : deptMeshName.trim();
    }

    public String getDeptAreaName() {
        return deptAreaName;
    }

    public void setDeptAreaName(String deptAreaName) {
        this.deptAreaName = deptAreaName == null ? null : deptAreaName.trim();
    }

    public String getDeptAgencyName() {
        return deptAgencyName;
    }

    public void setDeptAgencyName(String deptAgencyName) {
        this.deptAgencyName = deptAgencyName == null ? null : deptAgencyName.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getPhoneNumber3() {
        return phoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        this.phoneNumber3 = phoneNumber3 == null ? null : phoneNumber3.trim();
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
}