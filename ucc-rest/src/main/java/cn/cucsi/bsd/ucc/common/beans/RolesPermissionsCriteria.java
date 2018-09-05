package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by jjjjj on 2017-10-16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class RolesPermissionsCriteria  extends BasicCriteria   {
    private String roleId;
    private String permissionId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
