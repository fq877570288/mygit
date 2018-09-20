package cn.cucsi.bsd.ucc.common.mapper;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsForAPPCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PbxCdrsMapper {

    //根据条件查询通话记录列表（移动端查询通话记录） by wangxiaoyu 2018-09-04
    List<PbxCdrs> showListByTel(PbxCdrsForAPPCriteria pbxCdrsForAPPCriteria);
    //根据条件查询通话记录列表（web端查询通话记录） by zhaolei 2018-09-04
    List<PbxCdrs> selectByPrimary(PbxCdrsCriteria pbxCdrsCriteria);
    //根据条件查询通话记录列表（web端查询通话记录） by zhoalie 2018-09-04
    List<PbxCdrs> selectByPrimaryExcel(PbxCdrsCriteria pbxCdrsCriteria);  
}