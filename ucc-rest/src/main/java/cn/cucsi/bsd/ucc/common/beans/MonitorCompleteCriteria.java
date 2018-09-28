package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * 批量结束任务——入参
 */
@ApiModel
public class MonitorCompleteCriteria {

	@ApiModelProperty(value = "用户ID",required = true)
	private String userId; //用户ID

	@ApiModelProperty(value = "",required = true)
	private String completeMode;

	@ApiModelProperty(value = "",required = true)
	private String batchs;

	@ApiModelProperty(value = "",required = true)
	private String importBatch;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCompleteMode() {
		return completeMode;
	}

	public void setCompleteMode(String completeMode) {
		this.completeMode = completeMode;
	}

	public String getBatchs() {
		return batchs;
	}

	public void setBatchs(String batchs) {
		this.batchs = batchs;
	}

	public String getImportBatch() {
		return importBatch;
	}

	public void setImportBatch(String importBatch) {
		this.importBatch = importBatch;
	}
}
