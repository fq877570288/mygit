package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class UccPermissionsCriteria  extends  BasicCriteria{

    private String userId;


    private String permissionName;
    private List<String> permissionIds;


    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
}
