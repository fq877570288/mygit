package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.RolesPermissionsCriteria;
import cn.cucsi.bsd.ucc.data.domain.RolesPermissions;
import cn.cucsi.bsd.ucc.data.repo.RolesPermissionsRepository;
import cn.cucsi.bsd.ucc.data.specs.RolesPermissionsSpecs;
import cn.cucsi.bsd.ucc.service.RolesPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tianyuwei on 2017/10/13.
 */

@Service
public class RolesPermissionsServiceImpl implements RolesPermissionsService{

    @Autowired
    private RolesPermissionsRepository rolesPermissionsRepository;
/*
    @Override
    public List<RolesPermissions> findAll(RolesPermissionsCriteria criteria) {
        return RolesPermissionsRepository.findAll(RolesPermissionsSpecs.createSpec(criteria));
    }

    @Override
    public RolesPermissions findOne(String permissionGroupId) {
        return this.RolesPermissionsRepository.findOne(permissionGroupId);
    }
*/
    @Override
    public RolesPermissions save(RolesPermissions permissionGroups) {
        return this.rolesPermissionsRepository.save(permissionGroups);
    }

    @Override
    public Boolean delete(String permissionGroupId) {
        this.rolesPermissionsRepository.delete(permissionGroupId);
        return true;
    }
}
