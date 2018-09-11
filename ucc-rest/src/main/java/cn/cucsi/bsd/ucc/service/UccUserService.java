package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.ResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.UserLoginForAPPCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UccUserService {
    Page<UccUsers> findAll(UccUserCriteria search);
    UccUsers findOne(String userId);
    UccUsers save(UccUsers uccUsers);
    Boolean delete(String userId);
    Boolean multiDelete(String userIds);
    List<UccUsers> findAllList(UccUserCriteria search);

    /***
     * 根据用户名、密码获取用户列表（APP登录用）
     * add by wangxiaoyu
     * 2018-09-10
     */
    ResultBean_New<UccUsers> userLoginForAPP(UserLoginForAPPCriteria userLoginForAPPCriteria);
}
