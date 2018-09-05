package cn.cucsi.bsd.ucc.service.impl;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendRuleCriteria;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRule;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRulePK;
import cn.cucsi.bsd.ucc.data.repo.DomainWeekendRuleRepository;
import cn.cucsi.bsd.ucc.data.specs.DomainWeekendRuleSpecs;
import cn.cucsi.bsd.ucc.service.DomainWeekendRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Created by home on 2017/10/16.
 */
@Service
public class DomainWeekendRuleServiceImpl implements DomainWeekendRuleService {

    @Autowired
    private DomainWeekendRuleRepository domainWeekendRuleRepository;

    @Override
    public Page<DomainWeekendRule> findAll(DomainWeekendRuleCriteria criteria) {
        Sort sort = new Sort(Sort.Direction.DESC,"weekendRule");
        Pageable pageable = new PageRequest(criteria.getPage(), criteria.getSize(), sort);
        return domainWeekendRuleRepository.findAll(DomainWeekendRuleSpecs.createSpec(criteria),pageable);
    }

    @Override
    public DomainWeekendRule findOne(DomainWeekendRulePK domainWeekendRulePK) {
        return domainWeekendRuleRepository.findOne(domainWeekendRulePK);
    }

    @Override
    public DomainWeekendRule save(DomainWeekendRule domainWeekendRule) {
        return domainWeekendRuleRepository.save(domainWeekendRule);
    }

    @Override
    public Boolean delete(DomainWeekendRulePK domainWeekendRulePK) {
        domainWeekendRuleRepository.delete(domainWeekendRulePK);
        return true;
    }
}
