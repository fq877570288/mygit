package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/***
 * 任务撤回--入参封装实体
 * add by wangxiaoyu
 * 2018-08-30
 */
@ApiModel
public class TaskBackCriteria{

    /*@ApiModelProperty(value = "业务员ID",required = true)
    private String userId;

    @ApiModelProperty(value = "任务明细表主键",required = true)
    private String taskDetailId;*/

    @ApiModelProperty(value = "业务编码列表",required = true)
    private List<String> businessCodeList = new ArrayList<>();

    public List<String> getBusinessCodeList() {
        return businessCodeList;
    }

    public void setBusinessCodeList(List<String> businessCodeList) {
        this.businessCodeList = businessCodeList;
    }
}
