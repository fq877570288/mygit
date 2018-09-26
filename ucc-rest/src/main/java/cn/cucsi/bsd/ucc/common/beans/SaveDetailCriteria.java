package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * 保存任务明细——入参
 */
@ApiModel
public class SaveDetailCriteria {

	@ApiModelProperty(value = "租户ID",required = false)
	private String domainId; //租户ID

	@ApiModelProperty(value = "",required = false)
	private String callinfo;

	@ApiModelProperty(value = "",required = false)
	private String cdrId;

	@ApiModelProperty(value = "用户ID",required = false)
	private String userId; //用户ID

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
}
