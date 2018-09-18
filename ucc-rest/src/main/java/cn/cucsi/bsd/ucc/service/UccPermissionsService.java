package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UccPermissionsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccPermissions;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by mk on 2017/10/13.
 */
public interface UccPermissionsService {
    Page<UccPermissions> findWhole(UccPermissionsCriteria search);
    Page<UccPermissions> findAllTree(UccPermissionsCriteria search);
    List<UccPermissions> findAll(UccPermissionsCriteria search);
    UccPermissions findOne(String permissionId );
    UccPermissions save(UccPermissions uccPermissions);
    Boolean delete(String permissionId);
}
