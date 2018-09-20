package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.UccBlackListCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UccCustomersMapper {
    int deleteByPrimaryKey(String custId);

    int insert(UccCustomers record);

    int insertSelective(UccCustomers record);

    List<UccCustomers> selectByPrimaryKey(UccBlackListCriteria uccBlackListCriteria);

    int updateByPrimaryKeySelective(UccCustomers record);

    int updateByPrimaryKey(UccCustomers record);

    List<UccCustomers> selectByBusinessCodes(Map<String, String> businesscodeMap) throws Exception;

    int deleteByBusinessCodes(Map<String, String> businesscodeMap) throws Exception;

    int insertGroup(Map<String, Object> taskMap) throws Exception;
}