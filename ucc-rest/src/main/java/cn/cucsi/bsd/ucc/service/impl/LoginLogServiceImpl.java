package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.LoginLogCriteria;
import cn.cucsi.bsd.ucc.data.domain.LoginLog;
import cn.cucsi.bsd.ucc.data.repo.LoginLogRepository;
import cn.cucsi.bsd.ucc.data.specs.LoginLogSpecs;
import cn.cucsi.bsd.ucc.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by Song on 2017/10/16.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService{


    @Autowired
    private LoginLogRepository loginLogRepository;

    @Override
    public Page<LoginLog> findAll(LoginLogCriteria loginLogCriteria) {
        Pageable pageable = new PageRequest(loginLogCriteria.getPage(), loginLogCriteria.getSize());
        return loginLogRepository.findAll(LoginLogSpecs.createSpec(loginLogCriteria), pageable);
    }

    @Override
    public LoginLog findOne(String LogId) {
        return this.loginLogRepository.findOne(LogId);
    }

    @Override
    public LoginLog save(LoginLog loginLog) {
        return this.loginLogRepository.save(loginLog);
    }

    @Override
    public Boolean delete(String LogId) {
        this.loginLogRepository.delete(LogId);
        return true;
    }

}
