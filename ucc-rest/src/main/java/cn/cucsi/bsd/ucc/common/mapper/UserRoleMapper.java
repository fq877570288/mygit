package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRole key);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    void deleteByUserId(UserRole record);
}