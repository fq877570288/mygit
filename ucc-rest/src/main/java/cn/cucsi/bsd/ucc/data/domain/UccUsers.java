package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.common.untils.MyUtils;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ucc_users", schema = "ucc", catalog = "")
public class UccUsers {

    @JsonView(JSONView.Summary.class)
    private String userId;

    @JsonView(JSONView.Summary.class)
    private Integer userType;

    @JsonView(JSONView.Summary.class)
    private int userStatus;
    @JsonView(JSONView.Summary.class)
    private String mobile;
    @JsonView(JSONView.Summary.class)
    private String email;
    @JsonView(JSONView.Summary.class)
    private String userName;
    @JsonView(JSONView.Summary.class)
    private String password;
    @JsonView(JSONView.Summary.class)
    private Integer errorCount;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lockTime;
    @JsonView(JSONView.Summary.class)
    private String nickName;
    @JsonView(JSONView.Summary.class)
    private Integer sex;
    @JsonView(JSONView.Summary.class)
    private Integer age;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy年MM月dd日")
    private Date birthday;
    @JsonView(JSONView.Summary.class)
    private String tel;
    @JsonView(JSONView.Summary.class)
    private String qq;
    @JsonView(JSONView.Summary.class)
    private String address1;
    @JsonView(JSONView.Summary.class)
    private String address2;
    @JsonView(JSONView.Summary.class)
    private String address3;
    @JsonView(JSONView.Summary.class)
    private String address4;
    @JsonView(JSONView.Summary.class)
    private String memo;
    @JsonView(JSONView.Summary.class)
    private String sign;
    @JsonView(JSONView.Summary.class)
    private Date regTime;
    @JsonView(JSONView.Summary.class)
    private String regIp;
    @JsonView(JSONView.Summary.class)
    private Integer regFrom;
    @JsonView(JSONView.Summary.class)
    private String regArea;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    @JsonView(JSONView.Summary.class)
    private String lastLoginIp;
    @JsonView(JSONView.Summary.class)
    private Integer loginCount;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastOperateTime;
    @JsonView(JSONView.Summary.class)
    private Integer lastOperateUser;
    @JsonView(JSONView.Summary.class)
    private String lastOperateDesc;
    @JsonView(JSONView.Summary.class)
    private String customfieldnames;
    @JsonView(JSONView.Summary.class)
    private String domainId;
    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    @JsonView(JSONView.Summary.class)
    private String createdUserId;
    @JsonView(JSONView.Summary.class)
    private String createdUserName;
    @JsonView(JSONView.Summary.class)
    private String createdNickName;
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;

    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    private Collection<LoginLog> loginLogs;

    private Collection<PbxShowbusyLog> pbxShowbusyLogsByUserId;
    @JsonView(JSONView.Summary.class)
    private Collection<PbxShowbusyLog> pbxShowbusyLogsByOperator;
    @JsonView(JSONView.Summary.class)
    private Collection<SkillGroupUser> skillGroupUsers;
    @JsonIgnore
    private Collection<TeamUsers> teamUsers;
//    @JsonIgnore
//    private Collection<UccDepts> uccDeptsByUserId_0;
    @JsonIgnore
    private Collection<UccNotice> uccNotices;
    @JsonIgnore
    private Collection<UccNoticeTrace> uccNoticeTraces;
    @JsonView(JSONView.Summary.class)
    private UccDomain uccDomain;

    private Collection<PermissionGroups> permissionGroupss;

    private Collection<UccPermissions> uccPermissionss;
    @JsonView(JSONView.UccUserWithDept.class)
    private Collection<UserDept> userDepts;

    @JsonView(JSONView.UccUserWithRole.class)
    @JsonIgnore
    private Collection<UserRole> userRoles;

    @JsonView(JSONView.UccUserWithRole.class)
    private Collection<UccRoles> roles;
    @JsonView(JSONView.UccUserWithDept.class)
    private Collection<UccDepts> depts;
//    @JsonView(JSONView.UccUserWithExt.class)
    private Collection<PbxExts> ext;

    @JsonView(JSONView.UccUserWithTeams.class)
    private Collection<UccTeams> teams;
    @JsonView(JSONView.UccUserWithSkillGroup.class)
    private Collection<UccSkillGroup> skillGroup;
    @JsonView(JSONView.Summary.class)
    private List<UccPermissionsAndUser> uccPermissions;
    @JsonView(JSONView.Summary.class)
    private JSONObject result;

    @ManyToMany
    @JoinTable(name="user_role",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="role_id", referencedColumnName="role_id")
    )
    public Collection<UccRoles> getRoles() {
        return roles;
    }

    public void setRoles(Collection<UccRoles> roles) {
        this.roles = roles;
    }


    @ManyToMany
    @JoinTable(name="user_dept",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="dept_id", referencedColumnName="dept_id")
    )
    public Collection<UccDepts> getDepts() {
        return depts;
    }

    public void setDepts(Collection<UccDepts> depts) {
        this.depts = depts;
    }

    @ManyToMany
    @JoinTable(name="team_users",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="team_id", referencedColumnName="team_id")
    )
    public Collection<UccTeams> getTeams() {
        return teams;
    }

    public void setTeams(Collection<UccTeams> teams) {
        this.teams = teams;
    }

    @OneToMany
    @JoinTable(name="user_ext",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="ext_id", referencedColumnName="ext_id")
    )
    public Collection<PbxExts> getExt() {
        return ext;
    }

    public void setExt(Collection<PbxExts> ext) {
        this.ext = ext;
    }

    @ManyToMany
    @JoinTable(name="skill_group_user",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="user_id"),
            inverseJoinColumns=
            @JoinColumn(name="skill_group_id", referencedColumnName="skill_group_id")
    )
    public Collection<UccSkillGroup> getSkillGroup() {
        return skillGroup;
    }

    public void setSkillGroup(Collection<UccSkillGroup> skillGroup) {
        this.skillGroup = skillGroup;
    }

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_type", nullable = true)
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "user_status", nullable = false)
    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 16)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 48)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 48)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 48)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "error_count", nullable = true)
    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    @Basic
    @Column(name = "lock_time", nullable = false)
    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    @Basic
    @Column(name = "nick_name", nullable = true, length = 16)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "tel", nullable = true, length = 32)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 32)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "address1", nullable = true)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "address2", nullable = true)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "address3", nullable = true)
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "address4", nullable = true, length = 255)
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 255)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "sign", nullable = true, length = 255)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Basic
    @Column(name = "reg_time", nullable = false)
    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Basic
    @Column(name = "reg_ip", nullable = true, length = 24)
    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    @Basic
    @Column(name = "reg_from", nullable = true)
    public Integer getRegFrom() {
        return regFrom;
    }

    public void setRegFrom(Integer regFrom) {
        this.regFrom = regFrom;
    }

    @Basic
    @Column(name = "reg_area", nullable = true, length = 50)
    public String getRegArea() {
        return regArea;
    }

    public void setRegArea(String regArea) {
        this.regArea = regArea;
    }

    @Basic
    @Column(name = "last_login_time", nullable = false)
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Basic
    @Column(name = "last_login_ip", nullable = true, length = 24)
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    @Basic
    @Column(name = "login_count", nullable = true)
    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Basic
    @Column(name = "last_operate_time", nullable = false)
    public Date getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(Date lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }

    @Basic
    @Column(name = "last_operate_user", nullable = true)
    public Integer getLastOperateUser() {
        return lastOperateUser;
    }

    public void setLastOperateUser(Integer lastOperateUser) {
        this.lastOperateUser = lastOperateUser;
    }

    @Basic
    @Column(name = "last_operate_desc", nullable = true, length = 255)
    public String getLastOperateDesc() {
        return lastOperateDesc;
    }

    public void setLastOperateDesc(String lastOperateDesc) {
        this.lastOperateDesc = lastOperateDesc;
    }

    @Basic
    @Column(name = "customfieldnames", nullable = true, length = 1000)
    public String getCustomfieldnames() {
        return customfieldnames;
    }

    public void setCustomfieldnames(String customfieldnames) {
        this.customfieldnames = customfieldnames;
    }

    @Basic
    @Column(name = "domain_id", nullable = false, length = 32)
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UccUsers uccUsers = (UccUsers) o;

        if (userStatus != uccUsers.userStatus) return false;
        if (userId != null ? !userId.equals(uccUsers.userId) : uccUsers.userId != null) return false;
        if (userType != null ? !userType.equals(uccUsers.userType) : uccUsers.userType != null) return false;
        if (mobile != null ? !mobile.equals(uccUsers.mobile) : uccUsers.mobile != null) return false;
        if (email != null ? !email.equals(uccUsers.email) : uccUsers.email != null) return false;
        if (userName != null ? !userName.equals(uccUsers.userName) : uccUsers.userName != null) return false;
        if (password != null ? !password.equals(uccUsers.password) : uccUsers.password != null) return false;
        if (errorCount != null ? !errorCount.equals(uccUsers.errorCount) : uccUsers.errorCount != null) return false;
        if (lockTime != null ? !lockTime.equals(uccUsers.lockTime) : uccUsers.lockTime != null) return false;
        if (nickName != null ? !nickName.equals(uccUsers.nickName) : uccUsers.nickName != null) return false;
        if (sex != null ? !sex.equals(uccUsers.sex) : uccUsers.sex != null) return false;
        if (age != null ? !age.equals(uccUsers.age) : uccUsers.age != null) return false;
        if (birthday != null ? !birthday.equals(uccUsers.birthday) : uccUsers.birthday != null) return false;
        if (tel != null ? !tel.equals(uccUsers.tel) : uccUsers.tel != null) return false;
        if (qq != null ? !qq.equals(uccUsers.qq) : uccUsers.qq != null) return false;
        if (address1 != null ? !address1.equals(uccUsers.address1) : uccUsers.address1 != null) return false;
        if (address2 != null ? !address2.equals(uccUsers.address2) : uccUsers.address2 != null) return false;
        if (address3 != null ? !address3.equals(uccUsers.address3) : uccUsers.address3 != null) return false;
        if (address4 != null ? !address4.equals(uccUsers.address4) : uccUsers.address4 != null) return false;
        if (memo != null ? !memo.equals(uccUsers.memo) : uccUsers.memo != null) return false;
        if (sign != null ? !sign.equals(uccUsers.sign) : uccUsers.sign != null) return false;
        if (regTime != null ? !regTime.equals(uccUsers.regTime) : uccUsers.regTime != null) return false;
        if (regIp != null ? !regIp.equals(uccUsers.regIp) : uccUsers.regIp != null) return false;
        if (regFrom != null ? !regFrom.equals(uccUsers.regFrom) : uccUsers.regFrom != null) return false;
        if (regArea != null ? !regArea.equals(uccUsers.regArea) : uccUsers.regArea != null) return false;
        if (lastLoginTime != null ? !lastLoginTime.equals(uccUsers.lastLoginTime) : uccUsers.lastLoginTime != null)
            return false;
        if (lastLoginIp != null ? !lastLoginIp.equals(uccUsers.lastLoginIp) : uccUsers.lastLoginIp != null)
            return false;
        if (loginCount != null ? !loginCount.equals(uccUsers.loginCount) : uccUsers.loginCount != null) return false;
        if (lastOperateTime != null ? !lastOperateTime.equals(uccUsers.lastOperateTime) : uccUsers.lastOperateTime != null)
            return false;
        if (lastOperateUser != null ? !lastOperateUser.equals(uccUsers.lastOperateUser) : uccUsers.lastOperateUser != null)
            return false;
        if (lastOperateDesc != null ? !lastOperateDesc.equals(uccUsers.lastOperateDesc) : uccUsers.lastOperateDesc != null)
            return false;
        if (customfieldnames != null ? !customfieldnames.equals(uccUsers.customfieldnames) : uccUsers.customfieldnames != null)
            return false;
        if (domainId != null ? !domainId.equals(uccUsers.domainId) : uccUsers.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + userStatus;
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (errorCount != null ? errorCount.hashCode() : 0);
        result = 31 * result + (lockTime != null ? lockTime.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (address4 != null ? address4.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        result = 31 * result + (regTime != null ? regTime.hashCode() : 0);
        result = 31 * result + (regIp != null ? regIp.hashCode() : 0);
        result = 31 * result + (regFrom != null ? regFrom.hashCode() : 0);
        result = 31 * result + (regArea != null ? regArea.hashCode() : 0);
        result = 31 * result + (lastLoginTime != null ? lastLoginTime.hashCode() : 0);
        result = 31 * result + (lastLoginIp != null ? lastLoginIp.hashCode() : 0);
        result = 31 * result + (loginCount != null ? loginCount.hashCode() : 0);
        result = 31 * result + (lastOperateTime != null ? lastOperateTime.hashCode() : 0);
        result = 31 * result + (lastOperateUser != null ? lastOperateUser.hashCode() : 0);
        result = 31 * result + (lastOperateDesc != null ? lastOperateDesc.hashCode() : 0);
        result = 31 * result + (customfieldnames != null ? customfieldnames.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<LoginLog> getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(Collection<LoginLog> loginLogs) {
        this.loginLogs = loginLogs;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<PbxShowbusyLog> getPbxShowbusyLogsByUserId() {
        return pbxShowbusyLogsByUserId;
    }

    public void setPbxShowbusyLogsByUserId(Collection<PbxShowbusyLog> pbxShowbusyLogsByUserId) {
        this.pbxShowbusyLogsByUserId = pbxShowbusyLogsByUserId;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<PbxShowbusyLog> getPbxShowbusyLogsByOperator() {
        return pbxShowbusyLogsByOperator;
    }

    public void setPbxShowbusyLogsByOperator(Collection<PbxShowbusyLog> pbxShowbusyLogsByOperator) {
        this.pbxShowbusyLogsByOperator = pbxShowbusyLogsByOperator;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<SkillGroupUser> getSkillGroupUsers() {
        return skillGroupUsers;
    }

    public void setSkillGroupUsers(Collection<SkillGroupUser> skillGroupUsers) {
        this.skillGroupUsers = skillGroupUsers;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<TeamUsers> getTeamUsers() {
        return teamUsers;
    }

    public void setTeamUsers(Collection<TeamUsers> teamUsers) {
        this.teamUsers = teamUsers;
    }

//    @OneToMany(mappedBy = "uccUser")
//    public Collection<UccDepts> getUccDeptsByUserId_0() {
//        return uccDeptsByUserId_0;
//    }
//
//    public void setUccDeptsByUserId_0(Collection<UccDepts> uccDeptsByUserId_0) {
//        this.uccDeptsByUserId_0 = uccDeptsByUserId_0;
//    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<UccNotice> getUccNotices() {
        return uccNotices;
    }

    public void setUccNotices(Collection<UccNotice> uccNotices) {
        this.uccNotices = uccNotices;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<UccNoticeTrace> getUccNoticeTraces() {
        return uccNoticeTraces;
    }

    public void setUccNoticeTraces(Collection<UccNoticeTrace> uccNoticeTraces) {
        this.uccNoticeTraces = uccNoticeTraces;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<UserDept> getUserDepts() {
        return userDepts;
    }

    public void setUserDepts(Collection<UserDept> userDepts) {
        this.userDepts = userDepts;
    }

    @OneToMany(mappedBy = "uccUser")
    public Collection<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Collection<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Basic
    @Column(name = "created_user_id", nullable = true, length = 32)
    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
    @Basic
    @Column(name = "created_user_name", nullable = true, length = 32)
    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }
    @Basic
    @Column(name = "created_nick_name", nullable = true)
    public String getCreatedNickName() {
        return createdNickName;
    }

    public void setCreatedNickName(String createdNickName) {
        this.createdNickName = createdNickName;
    }
    @Basic
    @Column(name = "updated_user_id", nullable = true, length = 32)
    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
    @Basic
    @Column(name = "updated_user_name", nullable = true, length = 32)
    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }
    @Basic
    @Column(name = "updated_nick_name", nullable = true)
    public String getUpdatedNickName() {
        return updatedNickName;
    }

    public void setUpdatedNickName(String updatedNickName) {
        this.updatedNickName = updatedNickName;
    }

    @Basic
    @Column(nullable = true)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(nullable = true)
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Transient
    public List<UccPermissionsAndUser> getUccPermissions() {
        return uccPermissions;
    }

    public void setUccPermissions(List<UccPermissionsAndUser> uccPermissions) {
        this.uccPermissions = uccPermissions;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }
}
