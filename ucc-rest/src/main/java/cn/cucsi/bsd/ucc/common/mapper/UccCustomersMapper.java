package cn.cucsi.bsd.ucc.common.mapper;


import cn.cucsi.bsd.ucc.common.beans.UccBlackListCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UccCustomersMapper {
    int deleteByPrimaryKey(String custId);

    int insert(UccCustomers record);

    int insertSelective(UccCustomers record);

    List<UccCustomers> selectByPrimaryKey(UccBlackListCriteria uccBlackListCriteria);

    int updateByPrimaryKeySelective(UccCustomers record);

    int updateByPrimaryKey(UccCustomers record);
}