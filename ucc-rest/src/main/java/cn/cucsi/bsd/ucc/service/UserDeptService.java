package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.UserDeptCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UserDept;
import cn.cucsi.bsd.ucc.data.domain.UserDeptKey;
import cn.cucsi.bsd.ucc.data.domain.UserDeptPK;
import org.springframework.data.domain.Page;

import java.util.Collection;

/**
 * Created by mk on 2017/10/16.
 */
public interface UserDeptService {
    Page<UserDept> findAll(UserDeptCriteria search);
    UserDept findOne(UserDeptPK userDeptPK);
    UserDept save(UserDept uccUsers);
    Boolean delete(UserDeptPK userDeptPK);

    void deleteByUserId(String userId);

    void insert(UserDeptKey userDept);
}
