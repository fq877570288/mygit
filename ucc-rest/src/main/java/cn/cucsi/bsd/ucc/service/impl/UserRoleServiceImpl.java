package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UserRoleCriteria;
import cn.cucsi.bsd.ucc.data.domain.UserRole;
import cn.cucsi.bsd.ucc.data.domain.UserRolePK;
import cn.cucsi.bsd.ucc.data.repo.UserRoleRepository;
import cn.cucsi.bsd.ucc.data.specs.UserRoleSpecs;
import cn.cucsi.bsd.ucc.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Override
    public List<UserRole> findAll(UserRoleCriteria search) {
        return userRoleRepository.findAll(UserRoleSpecs.createSpec(search));
    }

    @Override
    public UserRole findOne(UserRolePK  userRolePK) {
        return userRoleRepository.findOne(userRolePK);
    }

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public Boolean delete(UserRolePK userRolePK) {
        this.userRoleRepository.delete(userRolePK);
        return true;
    }
}
