package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 自定义显示字段--入参封装实体
 * add by wangxiaoyu
 * 2018-09-17
 */
@ApiModel
public class CustomFieldsSaveCriteria {

    @ApiModelProperty(value = "自定义字段 ",required = true)
    private String customfieldNames;

    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    public String getCustomfieldNames() {
        return customfieldNames;
    }

    public void setCustomfieldNames(String customfieldNames) {
        this.customfieldNames = customfieldNames;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
