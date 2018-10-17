package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserRoleCriteria;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
import cn.cucsi.bsd.ucc.data.domain.UserRole;
import cn.cucsi.bsd.ucc.data.domain.UserRolePK;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
public interface UserRoleService {
    List<UserRole> findAll(UserRoleCriteria search);
    UserRole findOne(UserRolePK userId);
    UserRole save(UserRole userRole);
    Boolean delete(UserRolePK userId);

    void deleteByUserId(String userId);

    void insert(UserRole userRole);
}
