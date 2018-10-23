package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ucc_permissions", schema = "ucc", catalog = "")
public class UccPermissions {
    @JsonView(JSONView.Summary.class)
    private String permissionId;
    private String id;
    @JsonView(JSONView.Summary.class)
    private String permissionName;
    @JsonView(JSONView.Summary.class)
    private String permissionGroup;

    @JsonView(JSONView.Summary.class)
    private String icon;

    @JsonView(JSONView.Summary.class)
    private String route;

    @JsonView(JSONView.Summary.class)
    private String mpid;

    private String domainId;
    @JsonView(JSONView.Summary.class)
    private String isLeftMenu;



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

    @Transient
    @JsonView(JSONView.Summary.class)
    private List<UccPermissions> uccPermissions;

    @Id
    @Column(name = "permission_id", nullable = false, length = 32)
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission_name", nullable = true, length = 48)
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    @Basic
    @Column(name = "id", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "permission_group", nullable = true, length = 50)
    public String getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(String permissionGroup) {
        this.permissionGroup = permissionGroup;
    }

    @Basic
    @Column(name = "icon", nullable = true, length = 32)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    @Basic
    @Column(name = "route", nullable = true, length = 32)
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
    @Basic
    @Column(name = "mpid", nullable = true, length = 32)
    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UccPermissions that = (UccPermissions) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;
        if (permissionGroup != null ? !permissionGroup.equals(that.permissionGroup) : that.permissionGroup != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId != null ? permissionId.hashCode() : 0;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        result = 31 * result + (permissionGroup != null ? permissionGroup.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uccPermission")
    public Collection<RolesPermissions> getRolesPermissions() {
        return rolesPermissions;
    }

    public void setRolesPermissions(Collection<RolesPermissions> rolesPermissions) {
        this.rolesPermissions = rolesPermissions;
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
    public List<UccPermissions> getUccPermissions() {
        return uccPermissions;
    }

    public void setUccPermissions(List<UccPermissions> uccPermissions) {
        this.uccPermissions = uccPermissions;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Basic
    @Column(name = "is_left_menu", nullable = true, length = 32)
    public String getIsLeftMenu() {
        return isLeftMenu;
    }

    public void setIsLeftMenu(String isLeftMenu) {
        this.isLeftMenu = isLeftMenu;
    }
}
