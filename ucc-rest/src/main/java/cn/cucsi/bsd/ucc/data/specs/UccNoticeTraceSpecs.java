package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccNoticeTraceCriteria;

import cn.cucsi.bsd.ucc.data.domain.UccNoticeTrace;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by mk on 2017/10/13.
 */
public class UccNoticeTraceSpecs {
    public static Specification<UccNoticeTrace> userIdEqual(final String userId) {
        return new Specification<UccNoticeTrace>() {
            @Override
            public Predicate toPredicate(Root<UccNoticeTrace> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("userId"),userId );
            }
        };
    }
    public static Specification<UccNoticeTrace> createSpec(final UccNoticeTraceCriteria criteria) {
        Specification<UccNoticeTrace> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getUserId())){
            specs = specs.and(userIdEqual(criteria.getUserId()));
        }
        return specs;
    }
}
