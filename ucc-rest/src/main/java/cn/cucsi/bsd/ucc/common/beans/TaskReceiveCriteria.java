package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/***
 * 生成任务 导入数据批次列表--入参封装实体
 * add by wangxiaoyu
 * 2018-09-14
 */
@ApiModel
public class TaskReceiveCriteria {

    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    @ApiModelProperty(value = "任务ID",required = true)
    private String  taskDetailIds;

    @ApiModelProperty(value = "任务ID",required = false)
    private List<String> taskDetailIdListForWait;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskDetailIds() {
        return taskDetailIds;
    }

    public void setTaskDetailIds(String taskDetailIds) {
        this.taskDetailIds = taskDetailIds;
    }

    public List<String> getTaskDetailIdListForWait() {
        return taskDetailIdListForWait;
    }

    public void setTaskDetailIdListForWait(List<String> taskDetailIdListForWait) {
        this.taskDetailIdListForWait = taskDetailIdListForWait;
    }
}
