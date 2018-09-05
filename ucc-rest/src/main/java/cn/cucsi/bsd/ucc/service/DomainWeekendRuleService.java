package cn.cucsi.bsd.ucc.service;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendRuleCriteria;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRule;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRulePK;
import org.springframework.data.domain.Page;

/**
 * Created by home on 2017/10/16.
 */
public interface DomainWeekendRuleService {
    Page<DomainWeekendRule> findAll(DomainWeekendRuleCriteria criteria);
    DomainWeekendRule findOne(DomainWeekendRulePK domainWeekendRulePK);
    DomainWeekendRule save(DomainWeekendRule domainWeekendRule);
    Boolean delete(DomainWeekendRulePK domainWeekendRulePK);
}
