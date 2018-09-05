package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 根据条件查询通话记录--入参封装实体
 * add by wangxiaoyu
 * 2018-09-04
 */
@ApiModel
public class PbxCdrsForAPPCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "业务分机号",required = true)
    private String userTel;//业务分机号

    public String getUserTel() {
        return userTel;
    }
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }
}
