package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxDialplansCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxDialplans;
import org.springframework.data.domain.Page;

/**
 * Created by Song on 2017/10/16.
 */
public interface PbxDialplansService {
    Page<PbxDialplans> findAll(PbxDialplansCriteria PbxDialplansCriteria);
    PbxDialplans findOne(String dialplanId);
    PbxDialplans save(PbxDialplans PbxDialplans);
    Boolean delete(String dialplanId);
}
