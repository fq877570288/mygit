package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendExceptionCriteria;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendException;
import cn.cucsi.bsd.ucc.data.repo.DomainWeekendExceptionRepository;
import cn.cucsi.bsd.ucc.data.specs.DomainWeekendExceptionSpecs;
import cn.cucsi.bsd.ucc.service.DomainWeekendExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by home on 2017/10/16.
 */
@Transactional
@Service
public class DomainWeekendExceptionServiceImpl implements DomainWeekendExceptionService {

    @Autowired
    private DomainWeekendExceptionRepository domainWeekendExceptionRepository;


    @Override
    public Page<DomainWeekendException> findAll(DomainWeekendExceptionCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC,"exceptionId");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return domainWeekendExceptionRepository.findAll(DomainWeekendExceptionSpecs.createSpec(criteria),pageable);
    }

    @Override
    public DomainWeekendException findOne(String exceptionId) {
        return domainWeekendExceptionRepository.findOne(exceptionId);
    }

    @Override
    public DomainWeekendException save(DomainWeekendException domainWeekendException) {
        return domainWeekendExceptionRepository.save(domainWeekendException);
    }

    @Override
    public Boolean delete(String exceptionId) {
        domainWeekendExceptionRepository.delete(exceptionId);
        return true;
    }
}
