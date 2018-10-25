package cn.cucsi.bsd.ucc.common.beans;


/**
 * Created by tnn on 2018/10/25.
 */
public class OcAcceptInfoCriteria extends BasicCriteria {
    private String taskTypeId;
    private String domainId;
    private String userId;

    public String getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(String taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
