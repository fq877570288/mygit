package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
/*
* 技能组用户关系
* */
@Entity
@Table(name = "skill_group_user", schema = "ucc", catalog = "")
@IdClass(SkillGroupUserPK.class)
public class SkillGroupUser {
    private String skillGroupId;//技能组ID
    private String userId;//用户ID
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

    private String userName;//用户名
    private String groupName;//技能组名称

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;//创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;//修改时间
    @JsonIgnore
    private UccSkillGroup uccSkillGroup;
    @JsonIgnore
    private UccUsers uccUser;

    @Id
    @Column(name = "skill_group_id", nullable = false, length = 32)
    public String getSkillGroupId() {
        return skillGroupId;
    }

    public void setSkillGroupId(String skillGroupId) {
        this.skillGroupId = skillGroupId;
    }

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillGroupUser that = (SkillGroupUser) o;

        if (skillGroupId != null ? !skillGroupId.equals(that.skillGroupId) : that.skillGroupId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = skillGroupId != null ? skillGroupId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "skill_group_id", referencedColumnName = "skill_group_id", nullable = false, updatable = false, insertable = false)
    public UccSkillGroup getUccSkillGroup() {
        return uccSkillGroup;
    }

    public void setUccSkillGroup(UccSkillGroup uccSkillGroup) {
        this.uccSkillGroup = uccSkillGroup;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public UccUsers getUccUser() {
        return uccUser;
    }

    public void setUccUser(UccUsers uccUser) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
