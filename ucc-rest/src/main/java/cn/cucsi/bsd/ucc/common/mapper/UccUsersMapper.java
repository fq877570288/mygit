package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserLoginForAPPCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

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

    int insertSample(UccUsers record);

    UccUsers selectByMobileLimit1(String mobile);

    UccUsers selectByEmailLimit1(String email);

    int selectCountByUserName(String userName);

    int selectCountByEmail(String email);

    UccUsers selectByUserName(String userName);

    int clearUserUserRoles(Long id);

    int setErrorCountIncrementById(UccUsers user);

    int resetErrorCountById(UccUsers user);

    int setLockTimeById(UccUsers user);

    int changePassword(UccUsers user);

    int changeEmail(UccUsers user);

    int selectBySearchCount(UccUserCriteria search);

    List<UccUsers> selectBySearch(UccUserCriteria search);

    List<UccUsers> selectAll();

    int updateSampleByPrimaryKey(UccUsers user);

    int updateLastLoginByPrimaryKey(UccUsers user);

    int updateUserStatusByPrimaryKey(UccUsers user);

    void cusfsSave(Map<String, Object> map);

    UccUsers selectByExtNum(Integer extNum);

    UccUsers selectByPrimaryKey2(String userId);
}