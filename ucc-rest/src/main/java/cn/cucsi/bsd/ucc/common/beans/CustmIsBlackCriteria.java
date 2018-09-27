package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * WEB端查询客户黑名单信息——入参
 */
@ApiModel
public class CustmIsBlackCriteria {

	@ApiModelProperty(value = "业务编码",required = true)
	private String businessCode; //业务编码

	@ApiModelProperty(value = "租户ID",required = true)
	private String domainId;//租户ID

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
}
