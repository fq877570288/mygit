package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/*
* 用户号码关系
* */
@Entity
@Table(name = "user_ext", schema = "ucc", catalog = "")
@IdClass(UserExtPK.class)
public class UserExt {
    private String userId;//用户ID
    private String extId;//分机号
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;//创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;//修改时间
    @JsonIgnore
    private UccUsers uccUser;
    @JsonIgnore
    private PbxExts pbxExts;

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "ext_id", nullable = false, length = 32)
    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }


    @ManyToOne
    @JoinColumn(name = "ext_id", referencedColumnName = "ext_id", nullable = false, updatable = false, insertable = false)
    public PbxExts getPbxExts() {
        return pbxExts;
    }

    public void setPbxExts(PbxExts pbxExts) {
        this.pbxExts = pbxExts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExt userRole = (UserExt) o;

        if (userId != null ? !userId.equals(userRole.userId) : userRole.userId != null) return false;
        if (extId != null ? !extId.equals(userRole.extId) : userRole.extId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (extId != null ? extId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccUsers getUccUser() {
        return uccUser;
    }

    public void setUccUser(cn.cucsi.bsd.ucc.data.domain.UccUsers uccUser) {
        this.uccUser = uccUser;
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
}
