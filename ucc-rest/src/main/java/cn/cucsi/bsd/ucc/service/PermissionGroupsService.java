package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */
public interface PermissionGroupsService {
    List<PermissionGroups> findAll(PermissionGroupsCriteria criteria);
    PermissionGroups findOne(String permissionGroupId);
    PermissionGroups save(PermissionGroups permissionGroups);
    Boolean delete(String permissionGroupId);
}
