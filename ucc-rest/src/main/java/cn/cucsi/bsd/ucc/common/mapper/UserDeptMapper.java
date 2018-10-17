package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UserDeptKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDeptMapper {
    int deleteByPrimaryKey(UserDeptKey key);

    int insert(UserDeptKey record);

    int insertSelective(UserDeptKey record);

    void deleteByUserId(UserDeptKey record);

}