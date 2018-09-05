package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UccRolesCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccRoles;
import cn.cucsi.bsd.ucc.data.repo.UccRolesRepository;
import cn.cucsi.bsd.ucc.data.specs.UccRolesSpecs;
import cn.cucsi.bsd.ucc.service.UccRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by mk on 2017/10/16.
 */
@Service
public class UccRolesServiceImpl implements UccRolesService{
    @Autowired
    UccRolesRepository uccRolesRepository;
    @Override
    public Page<UccRoles> findAll(UccRolesCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "roleName");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccRolesRepository.findAll(UccRolesSpecs.createSpec(criteria),pageable);
    }

    @Override
    public UccRoles findOne(String roleId) {
        return uccRolesRepository.findOne(roleId);
    }

    @Override
    public UccRoles save(UccRoles uccRoles) {
        return uccRolesRepository.save(uccRoles);
    }

    @Override
    public Boolean delete(String roleId) {
        this.uccRolesRepository.delete(roleId);
        return true;
    }
}
