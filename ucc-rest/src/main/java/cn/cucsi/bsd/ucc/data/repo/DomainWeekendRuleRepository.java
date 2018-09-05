package cn.cucsi.bsd.ucc.data.repo;

import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRule;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRulePK;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by home on 2017/10/16.
 */
public interface DomainWeekendRuleRepository extends PagingAndSortingRepository<DomainWeekendRule,DomainWeekendRulePK>,JpaSpecificationExecutor {
}
