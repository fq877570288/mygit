package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.UserExt;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserExtMapper {
    int deleteByPrimaryKey(UserExt userExt);

    int insert(UserExt record);

    int insertSelective(UserExt record);

    UserExt selectByPrimaryKey(String key);

    int updateByPrimaryKeySelective(UserExt record);

    int updateByPrimaryKey(UserExt record);

    List<UserExt> findByUserId(UserExt userExt);
}