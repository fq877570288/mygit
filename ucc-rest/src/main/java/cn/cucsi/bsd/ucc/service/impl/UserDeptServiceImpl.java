package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UserDeptCriteria;
import cn.cucsi.bsd.ucc.common.mapper.UserDeptMapper;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UserDept;
import cn.cucsi.bsd.ucc.data.domain.UserDeptKey;
import cn.cucsi.bsd.ucc.data.domain.UserDeptPK;
import cn.cucsi.bsd.ucc.data.repo.UserDeptRepository;
import cn.cucsi.bsd.ucc.data.specs.UserDeptSpecs;
import cn.cucsi.bsd.ucc.service.UserDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by mk on 2017/10/16.
 */
@Service
public class UserDeptServiceImpl implements UserDeptService {
    @Autowired
    UserDeptRepository userDeptRepository;
    @Autowired
    UserDeptMapper userDeptMapper;
    @Override
    public Page<UserDept> findAll(UserDeptCriteria search) {
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(search.getPage(), search.getSize(), sort);
        return userDeptRepository.findAll(UserDeptSpecs.createSpec(search),pageable);
    }

    @Override
    public UserDept findOne(UserDeptPK userDeptPK) {
        return userDeptRepository.findOne(userDeptPK);
    }

    @Override
    public UserDept save(UserDept uccUsers) {
        return userDeptRepository.save(uccUsers);
    }

    @Override
    public Boolean delete(UserDeptPK userDeptPK) {
        this.userDeptRepository.delete(userDeptPK);
        return true;
    }

    @Override
    public void deleteByUserId(String userId) {
            UserDeptKey userDeptKey = new UserDeptKey();
            userDeptKey.setUserId(userId);
            userDeptMapper.deleteByUserId(userDeptKey);
    }

    @Override
    public void insert(UserDeptKey userDept) {
        userDeptMapper.insert(userDept);
    }
}
