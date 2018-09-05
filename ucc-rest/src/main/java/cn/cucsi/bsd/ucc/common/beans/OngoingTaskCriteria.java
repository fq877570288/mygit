package cn.cucsi.bsd.ucc.common.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 根据查询条件获取当前坐席任务外乎列表--入参封装实体
 * add by wangxiaoyu
 * 2018-08-27
 */
@ApiModel
public class OngoingTaskCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "查询条件的关键字",required = false)
    private String keyWords;

    @ApiModelProperty(value = "业务员ID",required = true)
    private String userId;

    @ApiModelProperty(value = "仅用于个人中心（在办：0、待办：1、本月办结：2）判断标识，其他查询无需传",required = false)
    private String taskCountsFlag;

    @ApiModelProperty(value = "该字段无需传",required = false)
    @JsonIgnore
    private String checkFlag;//用于在判断所传查询条件是否是全数字，目的简化sql

    @ApiModelProperty(value = "该字段无需传",required = false)
    @JsonIgnore
    private String dateStart;

    @ApiModelProperty(value = "该字段无需传",required = false)
    @JsonIgnore
    private String dateEnd;

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public String getTaskCountsFlag() {
        return taskCountsFlag;
    }

    public void setTaskCountsFlag(String taskCountsFlag) {
        this.taskCountsFlag = taskCountsFlag;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
