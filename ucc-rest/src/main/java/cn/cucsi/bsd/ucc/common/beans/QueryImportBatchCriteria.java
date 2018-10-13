package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 分派任务--入参封装实体
 * add by wangxiaoyu
 * 2018-09-19
 */
@ApiModel
public class QueryImportBatchCriteria {

    @ApiModelProperty(value = "",required = false)
    private String ignoreNotask;

    @ApiModelProperty(value = "",required = false)
    private String taskTypeName;

    @ApiModelProperty(value = "",required = false)
    private String recent;

    public String getIgnoreNotask() {
        return ignoreNotask;
    }

    public void setIgnoreNotask(String ignoreNotask) {
        this.ignoreNotask = ignoreNotask;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }
}
