package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 生成任务 导入数据批次列表--入参封装实体
 * add by wangxiaoyu
 * 2018-09-14
 */
@ApiModel
public class CreateTaskCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
