package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * 在办任务保存并继续——入参
 */
@ApiModel
public class SaveAndGoonDetailCriteria {

	@ApiModelProperty(value = "租户ID",required = true)
	private String domainId; //租户ID

	@ApiModelProperty(value = "",required = true)
	private String callinfo;

	@ApiModelProperty(value = "通话记录ID",required = false)
	private String cdrId;

	@ApiModelProperty(value = "用户ID",required = true)
	private String userId; //用户ID

	@ApiModelProperty(value = "任务类型ID",required = true)
	private String taskTypeId; //任务类型ID

	@ApiModelProperty(value = "业务编码",required = true)
	private String businessCode; //业务编码

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCallinfo() {
		return callinfo;
	}

	public void setCallinfo(String callinfo) {
		this.callinfo = callinfo;
	}

	public String getCdrId() {
		return cdrId;
	}

	public void setCdrId(String cdrId) {
		this.cdrId = cdrId;
	}

	public String getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

}
