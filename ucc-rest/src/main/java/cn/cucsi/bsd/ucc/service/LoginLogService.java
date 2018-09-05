package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.data.domain.LoginLog;
import cn.cucsi.bsd.ucc.common.beans.LoginLogCriteria;
import org.springframework.data.domain.Page;
/**
 * Created by Song on 2017/10/16.
 */
public interface LoginLogService {
    Page<LoginLog> findAll(LoginLogCriteria loginLogCriteria);
    LoginLog findOne(String logId);
    LoginLog save(LoginLog loginLog);
    Boolean delete(String userId);
}
