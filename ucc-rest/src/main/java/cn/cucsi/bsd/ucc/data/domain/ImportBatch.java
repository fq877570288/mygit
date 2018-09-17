package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import java.util.Date;

/***
 * 导入批次
 */
public class ImportBatch {

    private String importBatch; //导入批次

    private String importPersonId; //导入人主键

    private Date importTime; //导入时间

    private String taskType; //任务类型
    
    private String importPersonName; //导入人姓名
    
    private String batchFlag; // 批次标识    0： 导入数据， 1：已生成任务，2：已分派任务
    
    private String importBatchs; //批次s
    
    public static final String IMPORTBATCH = "importBatch"; 
    public static final String IMPORTPERSONID = "importPersonId";
    public static final String IMPORTTIME = "importTime";
    public static final String TASKTYPE = "taskType"; 
    public static final String IMPORTPERSONNAME = "importPersonName"; 
    public static final String BATCHFLAG = "batchFlag"; 
    
    public static final String BATCHFLAGN = "0"; // 0： 导入数据
    public static final String BATCHFLAGY = "1"; // 1：已生成任务
    public static final String BATCHFLAGA = "2"; // 2：已分派任务
    
	public String getImportBatchs() {
		return importBatchs;
	}

	public void setImportBatchs(String importBatchs) {
		this.importBatchs = importBatchs;
	}

	public String getTrTime(){
    	return MyUtils.formatDate(importTime);
    }
    
	public String getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(String batchFlag) {
		this.batchFlag = batchFlag;
	}

	public String getImportPersonName() {
		return importPersonName;
	}

	public void setImportPersonName(String importPersonName) {
		this.importPersonName = importPersonName;
	}

	public String getImportBatch() {
		return importBatch;
	}

	public void setImportBatch(String importBatch) {
		this.importBatch = importBatch;
	}

	public String getImportPersonId() {
		return importPersonId;
	}

	public void setImportPersonId(String importPersonId) {
		this.importPersonId = importPersonId;
	}

	public Date getImportTime() {
		return importTime;
	}

	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
    
    
    
}
