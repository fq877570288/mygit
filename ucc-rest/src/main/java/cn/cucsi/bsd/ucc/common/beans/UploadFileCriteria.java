package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 导入--入参封装实体
 * add by wangxiaoyu
 * 2018-09-17
 */
@ApiModel
public class UploadFileCriteria {

    @ApiModelProperty(value = "",required = true)
    private String DeptIdAndChildIds;

    public String getDeptIdAndChildIds() {
        return DeptIdAndChildIds;
    }

    public void setDeptIdAndChildIds(String deptIdAndChildIds) {
        DeptIdAndChildIds = deptIdAndChildIds;
    }
}
