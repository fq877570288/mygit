package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.*;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UccUserService {

    Page<UccUsers> findAll(UccUserCriteria search);
    UccUsers findOne(String userId);
    UccUsers save(UccUsers uccUsers);
    Boolean delete(String userId);
    Boolean multiDelete(String userIds);
    List<UccUsers> findAllList(UccUserCriteria search);
    List<UccUsers> loginList(UccUserCriteria search);
    List<UccUsers> querySeater(UccUserCriteria uccUserCriteria);
    /***
     * 根据用户名、密码获取用户列表（APP登录用）
     * add by wangxiaoyu
     * 2018-09-10
     */
    ResultBean_New<UccUsers> userLoginForAPP(UserLoginForAPPCriteria userLoginForAPPCriteria);

    void saveMiddleTable(UccUsers uccUsers);

    void cusfsSave(Map<String, Object> map) throws Exception;

    UccUsers selectByPrimaryKey(String userId) throws Exception;


    ResultBean_New<List<UccUserByDept>> userListByDept(List<UserDeptCriteria> userDeptCriterias);

    List<UccUsers> selectSameDeptUserIdByUserId(String userId) throws Exception;

    ResultBean<VerificationCode> VerificationImg()throws IOException;

}
