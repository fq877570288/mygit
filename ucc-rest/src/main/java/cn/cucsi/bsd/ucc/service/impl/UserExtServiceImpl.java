package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.common.mapper.UserExtMapper;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
import cn.cucsi.bsd.ucc.data.specs.UserExtSpecs;
import cn.cucsi.bsd.ucc.service.UserExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mk on 2017/10/16.
 */
@Service
public class UserExtServiceImpl  implements UserExtService{
    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public UserExt findOne(UserExt userExt) {
        return userExtMapper.selectByPrimaryKey(userExt);
    }

    @Override
    public  List<UserExt> findByUserId(UserExt userExt) {
        return userExtMapper.findByUserId(userExt);
    }
    @Override
    public int del(UserExt userExt) {
        return userExtMapper.deleteByPrimaryKey(userExt);
    }

    @Override
    public int insert(UserExt userExt) {
        return userExtMapper.insert(userExt);
    }

    @Override
    public List<UserExt> selectByUserId(String userId) {
        return userExtMapper.selectByUserId(userId);
    }

    @Override
    public int delete(UserExt userExt) {
        return userExtMapper.deleteByPrimaryKey(userExt);
    }
}
