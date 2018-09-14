package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PageResultBean_New;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
import cn.cucsi.bsd.ucc.common.beans.PbxCdrsForAPPCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Song on 2017/10/16.
 */
public interface PbxCdrsService {
    PageResultBean_New<List<PbxCdrs>> findAll(PbxCdrsCriteria PbxCdrsCriteria);
    PbxCdrs findOne(String cdrId);
    PbxCdrs save(PbxCdrs PbxCdrs);
    Boolean delete(String cdrId);

    //根据条件查询通话记录列表（移动端查询通话记录） by wangxiaoyu 2018-09-04
    PageResultBean_New<List<PbxCdrs>> showListByTel(PbxCdrsForAPPCriteria pbxCdrsForAPPCriteria);
    
    //PageResultBean_New<List<PbxCdrs>> selectByPrimary(PbxCdrsCriteria pbxCdrsCriteria);
}
