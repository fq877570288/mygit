package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.BaseHangupCauseCriteria;
import cn.cucsi.bsd.ucc.data.domain.BaseHangupCause;
import org.springframework.data.domain.Page;

/**
 * Created by home on 2017/10/16.
 */
public interface BaseHangupCauseService {
    Page<BaseHangupCause> findAll(BaseHangupCauseCriteria search);
    BaseHangupCause findOne(String causeEn);
    BaseHangupCause save(BaseHangupCause baseHangupCause);
    Boolean delete(String causeEn);
}
