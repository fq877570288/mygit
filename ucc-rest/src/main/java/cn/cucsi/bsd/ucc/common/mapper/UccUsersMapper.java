package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.UserLoginForAPPCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UccUsersMapper {

    int deleteByPrimaryKey(String userId);

    int insert(UccUsers record);

    int insertSelective(UccUsers record);

    UccUsers selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UccUsers record);

    int updateByPrimaryKey(UccUsers record);

    /***
     * 根据用户名、密码获取用户列表（APP登录用）
     * add by wangxiaoyu
     * 2018-09-10
     */
    UccUsers userLoginForAPP(UserLoginForAPPCriteria userLoginForAPPCriteria);
}