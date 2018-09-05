package cn.cucsi.bsd.ucc.common.beans;

import cn.cucsi.bsd.ucc.data.domain.TaskTransfer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/***
 * 任务处理结果提交--入参封装实体
 * add by wangxiaoyu
 * 2018-08-31
 */
@ApiModel
public class TaskSubmitCriteria{

    @ApiModelProperty(value = "任务处理结果提交列表",required = true)
    private List<TaskTransfer> taskSubmitList = new ArrayList<TaskTransfer>();

    public List<TaskTransfer> getTaskSubmitList() {
        return taskSubmitList;
    }

    public void setTaskSubmitList(List<TaskTransfer> taskSubmitList) {
        this.taskSubmitList = taskSubmitList;
    }
}
