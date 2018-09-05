package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.RolesPermissionsCriteria;
import cn.cucsi.bsd.ucc.data.domain.RolesPermissions;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface RolesPermissionsService {
    RolesPermissions save(RolesPermissions rolesPermissions);
    Boolean delete(String rbxQueueNumbers);
    /*
    List<RolesPermissions> findAll(RolesPermissionsCriteria criteria);
    RolesPermissions findOne(String permissionGroupId);
    RolesPermissions save(RolesPermissions rolesPermissions);
    Boolean delete(String permissionGroupId);
*/
}
