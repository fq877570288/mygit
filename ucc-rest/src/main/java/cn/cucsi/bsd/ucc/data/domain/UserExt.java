package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Transient;
import java.util.Date;
/*
* 用户号码关系
* */
public class UserExt {
    @JsonView(JSONView.Summary.class)
    private String extId;

    @JsonView(JSONView.Summary.class)
    private String extNum;

    @JsonView(JSONView.Summary.class)
    private String domainId;

    @JsonView(JSONView.Summary.class)
    private String userId;

    @JsonView(JSONView.Summary.class)
    private String createdNickName;

    @JsonView(JSONView.Summary.class)
    private Date createdTime;

    @JsonView(JSONView.Summary.class)
    private String createdUserId;

    @JsonView(JSONView.Summary.class)
    private String createdUserName;

    @JsonView(JSONView.Summary.class)
    private String updatedNickName;

    @JsonView(JSONView.Summary.class)
    private Date updatedTime;

    @JsonView(JSONView.Summary.class)
    private String updatedUserId;

    @JsonView(JSONView.Summary.class)
    private String updatedUserName;

    @JsonView(JSONView.Summary.class)
    private String isdefault;
    @JsonView(JSONView.Summary.class)
    @Transient
    private String extPwd;

    public String getCreatedNickName() {
        return createdNickName;
    }

    public void setCreatedNickName(String createdNickName) {
        this.createdNickName = createdNickName == null ? null : createdNickName.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId == null ? null : createdUserId.trim();
    }

    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName == null ? null : createdUserName.trim();
    }

    public String getUpdatedNickName() {
        return updatedNickName;
    }

    public void setUpdatedNickName(String updatedNickName) {
        this.updatedNickName = updatedNickName == null ? null : updatedNickName.trim();
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId == null ? null : updatedUserId.trim();
    }

    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName == null ? null : updatedUserName.trim();
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault == null ? null : isdefault.trim();
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Transient
    public String getExtPwd() {
        return extPwd;
    }

    public void setExtPwd(String extPwd) {
        this.extPwd = extPwd;
    }
}
