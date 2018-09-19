package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 分配任务列表查询--入参封装实体
 * add by wangxiaoyu
 * 2018-09-19
 */
@ApiModel
public class AllocationTaskCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    private String DeptIdAndChildIds;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptIdAndChildIds() {
        return DeptIdAndChildIds;
    }

    public void setDeptIdAndChildIds(String deptIdAndChildIds) {
        DeptIdAndChildIds = deptIdAndChildIds;
    }
}
