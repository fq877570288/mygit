package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.data.repo.UccPermissionsRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "ucc_roles", schema = "ucc", catalog = "")
public class UccRoles {
    @JsonView(JSONView.Summary.class)
    private String roleId;
    @JsonView(JSONView.Summary.class)
    private String roleName;
    @JsonView(JSONView.Summary.class)
    private String blacklistFlag;
    private String sensitiveFlag;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    @JsonIgnore
    private Collection<RolesPermissions> rolesPermissions;

    @JsonView(JSONView.UccRolesWithUccPermissions.class)
    private Collection<UccPermissions> uccPermissions;
    @JsonIgnore
    private UccDomain uccDomain;
    @JsonIgnore
    private Collection<UserRole> userRoles;

    //角色功能点
    @ManyToMany
    @JoinTable(name="roles_permissions",
            joinColumns=
            @JoinColumn(name="role_id", referencedColumnName="role_id"),
            inverseJoinColumns=
            @JoinColumn(name="permission_id", referencedColumnName="permission_id")
    )
    public Collection<UccPermissions> getUccPermissions() {
        return uccPermissions;
    }

    public void setUccPermissions(Collection<UccPermissions> uccPermissions) {
        this.uccPermissions = uccPermissions;
    }
    //角色功能点 end
    @Id
    @Column(name = "role_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = true, length = 48)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "blacklist_flag", nullable = true, length = 1)
    public String getBlacklistFlag() {
        return blacklistFlag;
    }

    public void setBlacklistFlag(String blacklistFlag) {
        this.blacklistFlag = blacklistFlag;
    }

    @Basic
    @Column(name = "sensitive_flag", nullable = true, length = 1)
    public String getSensitiveFlag() {
        return sensitiveFlag;
    }

    public void setSensitiveFlag(String sensitiveFlag) {
        this.sensitiveFlag = sensitiveFlag;
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

        UccRoles uccRoles = (UccRoles) o;

        if (roleId != null ? !roleId.equals(uccRoles.roleId) : uccRoles.roleId != null) return false;
        if (roleName != null ? !roleName.equals(uccRoles.roleName) : uccRoles.roleName != null) return false;
        if (blacklistFlag != null ? !blacklistFlag.equals(uccRoles.blacklistFlag) : uccRoles.blacklistFlag != null)
            return false;
        if (sensitiveFlag != null ? !sensitiveFlag.equals(uccRoles.sensitiveFlag) : uccRoles.sensitiveFlag != null)
            return false;
        if (domainId != null ? !domainId.equals(uccRoles.domainId) : uccRoles.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (blacklistFlag != null ? blacklistFlag.hashCode() : 0);
        result = 31 * result + (sensitiveFlag != null ? sensitiveFlag.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uccRole")
    public Collection<RolesPermissions> getRolesPermissions() {
        return rolesPermissions;
    }

    public void setRolesPermissions(Collection<RolesPermissions> rolesPermissions) {
        this.rolesPermissions = rolesPermissions;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }

    @OneToMany(mappedBy = "uccRole")
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
}
