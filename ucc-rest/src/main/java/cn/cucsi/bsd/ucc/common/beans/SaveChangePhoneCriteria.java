package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * 保存变更号码——入参
 */
@ApiModel
public class SaveChangePhoneCriteria {

	@ApiModelProperty(value = "租户ID",required = true)
	private String domainId; //租户ID

	@ApiModelProperty(value = "业务编码",required = true)
	private String businessCode; //业务编码

	@ApiModelProperty(value = "",required = true)
	private String changePhone;

	@ApiModelProperty(value = "",required = true)
	private String fPhone;

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

	public String getChangePhone() {
		return changePhone;
	}

	public void setChangePhone(String changePhone) {
		this.changePhone = changePhone;
	}

	public String getfPhone() {
		return fPhone;
	}

	public void setfPhone(String fPhone) {
		this.fPhone = fPhone;
	}
}
