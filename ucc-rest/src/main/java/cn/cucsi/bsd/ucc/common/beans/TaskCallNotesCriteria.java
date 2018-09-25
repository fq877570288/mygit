package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 根据业务编码查询呼出记录--入参封装实体
 * add by wangxiaoyu
 * 2018-08-31
 */
@ApiModel
public class TaskCallNotesCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "业务编码",required = true)
    private String businesscode;// 业务编码

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }
}
