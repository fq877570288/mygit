package cn.cucsi.bsd.ucc.common.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
public class UccUserCriteria extends BasicCriteria {
    private String userName;
    private String password;
    private String mobile;
    private String domainId;
    private String email;
    private String nickName;
    private String extNum;
    private String teamId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeFrom;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeTo;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date createdTimeStart;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date createdTimeEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTimeTo;
    private List<String> uccDepts = new ArrayList<>();
    private List<String> userRoles = new ArrayList<>();
    private List<String> extId = new ArrayList<>();
    private String userStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreatedTimeFrom() {
        return createdTimeFrom;
    }

    public void setCreatedTimeFrom(Date createdTimeFrom) {
        this.createdTimeFrom = createdTimeFrom;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreatedTimeTo() {
        return createdTimeTo;
    }

    public void setCreatedTimeTo(Date createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getCreatedTimeStart() {
        return createdTimeStart;
    }

    public void setCreatedTimeStart(Date createdTimeStart) {
        this.createdTimeStart = createdTimeStart;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getCreatedTimeEnd() {
        return createdTimeEnd;
    }

    public void setCreatedTimeEnd(Date createdTimeEnd) {
        this.createdTimeEnd = createdTimeEnd;
    }

    public Date getLastLoginTimeFrom() {
        return lastLoginTimeFrom;
    }

    public void setLastLoginTimeFrom(Date lastLoginTimeFrom) {
        this.lastLoginTimeFrom = lastLoginTimeFrom;
    }

    public Date getLastLoginTimeTo() {
        return lastLoginTimeTo;
    }

    public void setLastLoginTimeTo(Date lastLoginTimeTo) {
        this.lastLoginTimeTo = lastLoginTimeTo;
    }

    public List<String> getUccDepts() {
        return uccDepts;
    }

    public void setUccDepts(List<String> uccDepts) {
        this.uccDepts = uccDepts;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getExtId() {
        return extId;
    }

    public void setExtId(List<String> extId) {
        this.extId = extId;
    }

    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
