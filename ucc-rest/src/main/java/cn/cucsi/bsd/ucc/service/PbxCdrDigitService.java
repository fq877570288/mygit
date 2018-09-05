package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrDigitCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrDigit;
import org.springframework.data.domain.Page;

/**
 * Created by Song on 2017/10/16.
 */
public interface PbxCdrDigitService {
    Page<PbxCdrDigit> findAll(PbxCdrDigitCriteria PbxCdrDigitCriteria);
    PbxCdrDigit findOne(String cdrDigitId);
    PbxCdrDigit save(PbxCdrDigit PbxCdrDigit);
    Boolean delete(String cdrDigitId);
}
