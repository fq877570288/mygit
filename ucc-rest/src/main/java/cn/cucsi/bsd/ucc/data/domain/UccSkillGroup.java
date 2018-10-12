package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ucc_skill_group", schema = "ucc", catalog = "")
public class UccSkillGroup {
    private String skillGroupId;
    private String skillGroupName;
    private String skillGroupCode;
    private String skillGroupPid;
    private String domainId;
    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    private String createdUserId;
    private String createdUserName;
    private String createdNickName;
    private String updatedUserId;
    private String updatedUserName;
    private String updatedNickName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    private String status;

    @Transient
    @JsonView(JSONView.Summary.class)
    private List<UccSkillGroup> uccSkillGroups;

    @Basic
    @Column(name = "status", nullable = false, length = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    private Collection<SkillGroupUser> skillGroupUsers;
    @JsonIgnore
    private UccDomain uccDomain;

    @Id
    @Column(name = "skill_group_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getSkillGroupId() {
        return skillGroupId;
    }

    public void setSkillGroupId(String skillGroupId) {
        this.skillGroupId = skillGroupId;
    }

    @Basic
    @Column(name = "skill_group_name", nullable = false, length = 45)
    public String getSkillGroupName() {
        return skillGroupName;
    }

    public void setSkillGroupName(String skillGroupName) {
        this.skillGroupName = skillGroupName;
    }

    @Basic
    @Column(name = "skill_group_pid", nullable = false, length = 32)
    public String getSkillGroupPid() {
        return skillGroupPid;
    }

    public void setSkillGroupPid(String skillGroupPid) {
        this.skillGroupPid = skillGroupPid;
    }

    @Basic
    @Column(name = "skill_group_code", nullable = true, length = 45)
    public String getSkillGroupCode() {
        return skillGroupCode;
    }

    public void setSkillGroupCode(String skillGroupCode) {
        this.skillGroupCode = skillGroupCode;
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

        UccSkillGroup that = (UccSkillGroup) o;

        if (skillGroupId != null ? !skillGroupId.equals(that.skillGroupId) : that.skillGroupId != null) return false;
        if (skillGroupName != null ? !skillGroupName.equals(that.skillGroupName) : that.skillGroupName != null)
            return false;
        if (skillGroupCode != null ? !skillGroupCode.equals(that.skillGroupCode) : that.skillGroupCode != null)
            return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = skillGroupId != null ? skillGroupId.hashCode() : 0;
        result = 31 * result + (skillGroupName != null ? skillGroupName.hashCode() : 0);
        result = 31 * result + (skillGroupCode != null ? skillGroupCode.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uccSkillGroup")
    public Collection<SkillGroupUser> getSkillGroupUsers() {
        return skillGroupUsers;
    }

    public void setSkillGroupUsers(Collection<SkillGroupUser> skillGroupUsers) {
        this.skillGroupUsers = skillGroupUsers;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
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
    public List<UccSkillGroup> getUccSkillGroups() {
        return uccSkillGroups;
    }

    public void setUccSkillGroups(List<UccSkillGroup> uccSkillGroups) {
        this.uccSkillGroups = uccSkillGroups;
    }
}
