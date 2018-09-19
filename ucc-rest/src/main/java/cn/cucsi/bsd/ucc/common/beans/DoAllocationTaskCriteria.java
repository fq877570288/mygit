package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 分派任务--入参封装实体
 * add by wangxiaoyu
 * 2018-09-19
 */
@ApiModel
public class DoAllocationTaskCriteria{

    @ApiModelProperty(value = "",required = true)
    private String alloc;

    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    @ApiModelProperty(value = "",required = true)
    private String barchs;

    @ApiModelProperty(value = "",required = true)
    private String endDate;

    public String getAlloc() {
        return alloc;
    }

    public void setAlloc(String alloc) {
        this.alloc = alloc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBarchs() {
        return barchs;
    }

    public void setBarchs(String barchs) {
        this.barchs = barchs;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
