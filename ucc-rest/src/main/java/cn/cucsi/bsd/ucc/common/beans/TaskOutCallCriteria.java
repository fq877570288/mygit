package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * 跳转到任务外呼页面——入参
 */
@ApiModel
public class TaskOutCallCriteria{

	@ApiModelProperty(value = "租户ID",required = false)
	private String domainId; //租户ID

	@ApiModelProperty(value = "业务编码",required = false)
	private String businessCode; //业务编码

	@ApiModelProperty(value = "用户ID",required = false)
	private String userId; //用户ID

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
