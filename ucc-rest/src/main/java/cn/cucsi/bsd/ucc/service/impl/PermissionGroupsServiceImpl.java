package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import cn.cucsi.bsd.ucc.data.repo.PermissionGroupsRepository;
import cn.cucsi.bsd.ucc.data.specs.PermissionGroupsSpecs;
import cn.cucsi.bsd.ucc.service.PermissionGroupsService;
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
public class PermissionGroupsServiceImpl implements PermissionGroupsService{

    @Autowired
    private PermissionGroupsRepository permissionGroupsRepository;

    @Override
    public List<PermissionGroups> findAll(PermissionGroupsCriteria criteria) {
        return permissionGroupsRepository.findAll(PermissionGroupsSpecs.createSpec(criteria));
    }

    @Override
    public PermissionGroups findOne(String permissionGroupId) {
        return this.permissionGroupsRepository.findOne(permissionGroupId);
    }

    @Override
    public PermissionGroups save(PermissionGroups permissionGroups) {
        return this.permissionGroupsRepository.save(permissionGroups);
    }

    @Override
    public Boolean delete(String permissionGroupId) {
        this.permissionGroupsRepository.delete(permissionGroupId);
        return true;
    }
}
