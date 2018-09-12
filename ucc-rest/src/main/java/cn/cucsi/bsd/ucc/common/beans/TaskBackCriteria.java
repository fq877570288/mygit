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

    @ApiModelProperty(value = "任务ID列表",required = true)
    private List<String> taskDetailIdList = new ArrayList<>();

    public List<String> getTaskDetailIdList() {
        return taskDetailIdList;
    }

    public void setTaskDetailIdList(List<String> taskDetailIdList) {
        this.taskDetailIdList = taskDetailIdList;
    }
}
