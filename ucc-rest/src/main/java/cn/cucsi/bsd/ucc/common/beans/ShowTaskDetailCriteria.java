package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 查询客户任务详情--入参封装实体
 * add by wangxiaoyu
 * 2018-08-31
 */
@ApiModel
public class ShowTaskDetailCriteria{

    @ApiModelProperty(value = "业务编码",required = true)
    private String businessCode;

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
