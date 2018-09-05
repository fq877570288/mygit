package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendExceptionCriteria;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendException;
import org.springframework.data.domain.Page;

/**
 * Created by home on 2017/10/16.
 */
public interface DomainWeekendExceptionService {
    Page<DomainWeekendException> findAll(DomainWeekendExceptionCriteria search);
    DomainWeekendException findOne(String exceptionId);
    DomainWeekendException save(DomainWeekendException domainWeekendException);
    Boolean delete(String exceptionId);
}
