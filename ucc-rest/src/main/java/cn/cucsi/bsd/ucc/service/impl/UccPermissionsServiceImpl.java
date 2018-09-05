package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean;
import cn.cucsi.bsd.ucc.common.beans.UccPermissionsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccPermissions;
import cn.cucsi.bsd.ucc.data.repo.UccPermissionsRepository;
import cn.cucsi.bsd.ucc.data.specs.UccPermissionsSpecs;
import cn.cucsi.bsd.ucc.service.UccPermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mk on 2017/10/13.
 */
@Service
public class UccPermissionsServiceImpl implements UccPermissionsService {
    @Autowired
    UccPermissionsRepository uccPermissionsRepository;
    
    
    @Override
    public Page<UccPermissions> findWhole(UccPermissionsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "updatedTime");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return uccPermissionsRepository.findAll(UccPermissionsSpecs.createSpec(criteria),pageable);
    }
    @Override
    public List<UccPermissions> findAll(UccPermissionsCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return uccPermissionsRepository.findAll(UccPermissionsSpecs.createSpec(criteria),sort);
    }

    @Override
    public UccPermissions findOne(String permissionId) {
        return uccPermissionsRepository.findOne(permissionId);
    }

    @Override
    public UccPermissions save(UccPermissions uccPermissions) {
        return uccPermissionsRepository.save(uccPermissions);
    }

    @Override
    public Boolean delete(String permissionId) {
        this.uccPermissionsRepository.delete(permissionId);
        return true;
    }
}
