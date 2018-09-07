package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.DomainWeekendExceptionCriteria;
import cn.cucsi.bsd.ucc.data.domain.DomainWeekendException;
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
public class DomainWeekendExceptionSpecs {

    public static Specification<DomainWeekendException> exceptionIdEqual(final String exceptionId) {
        return new Specification<DomainWeekendException>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendException> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("exceptionId"), exceptionId);
            }
        };
    }


    public static Specification<DomainWeekendException> exceptionDateEqual(final Date exceptionDate) {
        return new Specification<DomainWeekendException>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendException> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("exceptionDate"), exceptionDate);
            }
        };
    }

/*
    public static Specification<DomainWeekendException> exceptionDateThanOrEqualTo(final Date from) {
        return new Specification<DomainWeekendException>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendException> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("exceptionDate"), from);
            }
        };
    }

    public static Specification<DomainWeekendException> exceptionDateLessOrEqualTo(final Date to) {
        return new Specification<DomainWeekendException>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendException> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("exceptionDate"), to);
            }
        };
    }*/


    public static Specification<DomainWeekendException> teamIdLike(final String teamId) {
        return new Specification<DomainWeekendException>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendException> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("teamId"), teamId);
            }
        };

    }

    public static Specification<DomainWeekendException> domainIdLike(final String domainId) {
        return new Specification<DomainWeekendException>() {
            @Override
            public Predicate toPredicate(Root<DomainWeekendException> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("domainId"), domainId);
            }
        };

    }

    ;


    public static Specification<DomainWeekendException> createSpec(final DomainWeekendExceptionCriteria criteria) {

        Specification<DomainWeekendException> spec = null;
        if (criteria == null) return spec;

        Specifications specs = where(spec);

        if (!Strings.isNullOrEmpty(criteria.getExceptionId())) {
            specs = specs.and(exceptionIdEqual(criteria.getExceptionId()));
        }

        if (!Strings.isNullOrEmpty(criteria.getTeamId())) {
            specs = specs.and(teamIdLike(criteria.getTeamId()));
        }

     /*   if (criteria.getExceptionDateFrom() != null) {
            specs = specs.and(exceptionDateThanOrEqualTo(criteria.getExceptionDateFrom()));
        }

        if (null != criteria.getExceptionDateTo()) {
            specs = specs.and(exceptionDateLessOrEqualTo(criteria.getExceptionDateTo()));
        }*/
        if (null != criteria.getExceptionDate()) {
            specs = specs.and(exceptionDateEqual(criteria.getExceptionDate()));
        }


        if (!Strings.isNullOrEmpty(criteria.getDomainId())) {
            specs = specs.and(domainIdLike(criteria.getDomainId()));
        }


        return specs;
    }

}
