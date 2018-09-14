package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.TeamUsers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeamUsersMapper {
    int deleteByPrimaryKey(TeamUsers key);

    int insert(TeamUsers record);

    int insertSelective(TeamUsers record);
}