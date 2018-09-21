package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;

import java.util.Collection;
import java.util.Date;
/*
*分机
* */
@Entity
@Table(name = "pbx_exts", schema = "ucc", catalog = "")
public class PbxExts {
    @JsonView(JSONView.Summary.class)
    private String extId;//分机ID
    @JsonView(JSONView.Summary.class)
    private String extNum;//分机号
    private String extPwd;//分机密码
    private String domainId;//域ID
    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    @JsonView(JSONView.Summary.class)
    private String createdUserId;//创建人ID
    @JsonView(JSONView.Summary.class)
    private String createdUserName;//创建人姓名
    @JsonView(JSONView.Summary.class)
    private String createdNickName;//创建人昵称
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;//修改人ID
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;//修改人姓名
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;//修改人昵称
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private Collection<ExtGroupExts> extGroupExts;
    @JsonIgnore
    private PbxCallTransfer pbxCallTransfer;
    @JsonIgnore
    private UccDomain uccDomain;
    @JsonIgnore
    private Collection<PbxQueueNumbers> pbxQueueNumbers;
    @JsonIgnore
    private Collection<PbxShowbusyLog> pbxShowbusyLogs;
    @JsonIgnore
    private UserExt userExt;

    @Transient
    private String username;
    @Transient
    private String userNickName;
    @Transient
    private String extGroupId;
    @Transient
    private String extGroupName;
    @Transient
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Transient
    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
    @Transient
    public String getExtGroupId() {
        return extGroupId;
    }

    public void setExtGroupId(String extGroupId) {
        this.extGroupId = extGroupId;
    }
    @Transient
    public String getExtGroupName() {
        return extGroupName;
    }

    public void setExtGroupName(String extGroupName) {
        this.extGroupName = extGroupName;
    }

    private String extGroupsByExtId;

    @Id
    @Column(name = "ext_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    @Basic
    @Column(name = "ext_num", nullable = false, length = 32)
    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }

    @Basic
    @Column(name = "ext_pwd", nullable = true, length = 20)
    public String getExtPwd() {
        return extPwd;
    }

    public void setExtPwd(String extPwd) {
        this.extPwd = extPwd;
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

        PbxExts pbxExts = (PbxExts) o;

        if (extId != null ? !extId.equals(pbxExts.extId) : pbxExts.extId != null) return false;
        if (extNum != null ? !extNum.equals(pbxExts.extNum) : pbxExts.extNum != null) return false;
        if (extPwd != null ? !extPwd.equals(pbxExts.extPwd) : pbxExts.extPwd != null) return false;
        if (domainId != null ? !domainId.equals(pbxExts.domainId) : pbxExts.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = extId != null ? extId.hashCode() : 0;
        result = 31 * result + (extNum != null ? extNum.hashCode() : 0);
        result = 31 * result + (extPwd != null ? extPwd.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pbxExt")
    public Collection<ExtGroupExts> getExtGroupExts() {
        return extGroupExts;
    }

    public void setExtGroupExts(Collection<ExtGroupExts> extGroupExts) {
        this.extGroupExts = extGroupExts;
    }

    @OneToOne(mappedBy = "pbxExt")
    public PbxCallTransfer getPbxCallTransfer() {
        return pbxCallTransfer;
    }

    public void setPbxCallTransfer(PbxCallTransfer pbxCallTransfer) {
        this.pbxCallTransfer = pbxCallTransfer;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }

    @OneToMany(mappedBy = "pbxExt")
    public Collection<PbxQueueNumbers> getPbxQueueNumbers() {
        return pbxQueueNumbers;
    }

    public void setPbxQueueNumbers(Collection<PbxQueueNumbers> pbxQueueNumbers) {
        this.pbxQueueNumbers = pbxQueueNumbers;
    }

    @OneToMany(mappedBy = "pbxExt")
    public Collection<PbxShowbusyLog> getPbxShowbusyLogs() {
        return pbxShowbusyLogs;
    }

    public void setPbxShowbusyLogs(Collection<PbxShowbusyLog> pbxShowbusyLogs) {
        this.pbxShowbusyLogs = pbxShowbusyLogs;
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

    @OneToOne(mappedBy = "pbxExts")
    public UserExt getUserExt() {
        return userExt;
    }

    public void setUserExt(UserExt userExt) {
        this.userExt = userExt;
    }

    @Transient
    public String getExtGroupsByExtId() {
        return extGroupsByExtId;
    }

    public void setExtGroupsByExtId(String extGroupsByExtId) {
        this.extGroupsByExtId = extGroupsByExtId;
    }


}
