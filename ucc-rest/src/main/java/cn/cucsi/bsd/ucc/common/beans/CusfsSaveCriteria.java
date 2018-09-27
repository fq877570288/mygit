package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/****
 * 保存用户自定义显示字段——入参
 */
@ApiModel
public class CusfsSaveCriteria {

	@ApiModelProperty(value = "用户ID",required = true)
	private String userId; //用户ID

	@ApiModelProperty(value = "",required = true)
	private String customfieldNames;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomfieldNames() {
		return customfieldNames;
	}

	public void setCustomfieldNames(String customfieldNames) {
		this.customfieldNames = customfieldNames;
	}
}
