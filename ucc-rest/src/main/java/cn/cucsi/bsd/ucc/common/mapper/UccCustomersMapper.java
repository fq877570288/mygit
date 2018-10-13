package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.TaskDetailSearch;
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

    Integer selectOngoingBySearchCount(TaskDetailSearch search) throws Exception;

    List<UccCustomers> selectOngoingBySearch(TaskDetailSearch search) throws Exception;

    int updateDefultPhone(UccCustomers uccCustomers) throws Exception;

    int updateChangePhone(UccCustomers uccCustomers) throws Exception;

    String custmIsBlack(String businesscode,String domainId) throws Exception;

    List<UccCustomers> selectNextTaskBySearch(TaskDetailSearch search) throws Exception;

    UccCustomers selectUccCustomersByCode(Map<String, Object> whereMap) throws Exception;

    //int inBlackListByBusinessCodeWEB(UccCustomers customer) throws Exception;
}