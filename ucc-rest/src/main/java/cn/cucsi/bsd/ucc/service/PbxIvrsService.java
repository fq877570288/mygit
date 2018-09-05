package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxIvrsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxIvrs;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by jjjjj on 2017-10-16.
 */
public interface PbxIvrsService {
    Page<PbxIvrs> findAll(PbxIvrsCriteria criteria);
    PbxIvrs findOne(String ivrId);
    PbxIvrs save(PbxIvrs pbxIvrs);
    Boolean delete(String ivrId);
    List<PbxIvrs> findAllOfNoPage(PbxIvrsCriteria criteria);
}
