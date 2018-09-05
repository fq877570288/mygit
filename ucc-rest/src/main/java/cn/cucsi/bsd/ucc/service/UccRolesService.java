package cn.cucsi.bsd.ucc.service;


import cn.cucsi.bsd.ucc.common.beans.UccRolesCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccRoles;
import org.springframework.data.domain.Page;

/**
 * Created by mk on 2017/10/16.
 */
public interface UccRolesService {
    Page<UccRoles> findAll(UccRolesCriteria search);
    UccRoles findOne(String roleId );
    UccRoles save(UccRoles uccRoles);
    Boolean delete(String roleId);
}
