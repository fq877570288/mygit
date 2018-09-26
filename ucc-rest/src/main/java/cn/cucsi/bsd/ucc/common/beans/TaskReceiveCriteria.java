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

    @ApiModelProperty(value = "用户ID",required = false)
    private String userId;

    @ApiModelProperty(value = "用户ID列表",required = false)
    private List<String> userIdList;

    @ApiModelProperty(value = "租户ID",required = true)
    private String domainId;

    @ApiModelProperty(value = "任务ID列表",required = false)
    private List<String> taskDetailIdsList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    public List<String> getTaskDetailIdsList() {
        return taskDetailIdsList;
    }

    public void setTaskDetailIdsList(List<String> taskDetailIdsList) {
        this.taskDetailIdsList = taskDetailIdsList;
    }
}
