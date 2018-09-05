package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.BaseDictCriteria;
import cn.cucsi.bsd.ucc.common.beans.DomainWeekendRuleCriteria;
import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendRule;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by home on 2017/10/16.
 */
public class DomainWeekendRuleSpecs {

    public static Specification<DomainWeekendRule> weekendRuleEqual(final String weekendRule){
        return new Specification<DomainWeekendRule>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendRule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Date>get("weekendRule"), weekendRule);
            }
        };
    }

    public static Specification<DomainWeekendRule> teamIdLike(final String teamId){
        return new Specification<DomainWeekendRule>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendRule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("teamId"),teamId);
            }
        };
    }


    public static Specification<DomainWeekendRule> domainIdLike(final String domainId){
        return new Specification<DomainWeekendRule>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendRule> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("domainId"),domainId);
            }
        };
    }

    public static Specification<DomainWeekendRule> createSpec(final DomainWeekendRuleCriteria criteria){

        Specification<DomainWeekendRule> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getWeekendRule())){
            specs = specs.and(weekendRuleEqual(criteria.getWeekendRule()));
        }

        if (!Strings.isNullOrEmpty(criteria.getTeamId())){
            specs = specs.and(teamIdLike(criteria.getTeamId()));
        }
        if (!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdLike(criteria.getDomainId()));
        }
        return specs;
    }
}
