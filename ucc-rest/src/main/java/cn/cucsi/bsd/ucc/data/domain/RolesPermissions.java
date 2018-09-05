package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "roles_permissions", schema = "ucc", catalog = "")
@IdClass(RolesPermissionsPK.class)
public class RolesPermissions {
    private String roleId;
    private String permissionId;

    @JsonIgnore
    private UccRoles uccRole;
    @JsonIgnore
    private UccPermissions uccPermission;

    @Id
    @Column(name = "role_id", nullable = false, length = 32)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Id
    @Column(name = "permission_id", nullable = false, length = 32)
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesPermissions that = (RolesPermissions) o;

        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;
        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId != null ? roleId.hashCode() : 0;
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccRoles getUccRole() {
        return uccRole;
    }

    public void setUccRole(cn.cucsi.bsd.ucc.data.domain.UccRoles uccRole) {
        this.uccRole = uccRole;
    }

    @ManyToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "permission_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccPermissions getUccPermission() {
        return uccPermission;
    }

    public void setUccPermission(cn.cucsi.bsd.ucc.data.domain.UccPermissions uccPermission) {
        this.uccPermission = uccPermission;
    }


}
