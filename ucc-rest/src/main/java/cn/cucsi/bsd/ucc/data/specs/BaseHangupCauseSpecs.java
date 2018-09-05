package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.BaseHangupCauseCriteria;
import cn.cucsi.bsd.ucc.data.domain.BaseHangupCause;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by home on 2017/10/16.
 */
public class BaseHangupCauseSpecs {
    public static Specification<BaseHangupCause> causeEnEqual(final String causeEn){
        return new Specification<BaseHangupCause>() {
            @Override
            public Predicate toPredicate(Root<BaseHangupCause> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("causeEn"), causeEn);
            }
        };

    }

    public static Specification<BaseHangupCause> causeCnLike(final String causeCn){
        return new Specification<BaseHangupCause>() {
            @Override
            public Predicate toPredicate(Root<BaseHangupCause> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("causeCn"),causeCn);
            }
        };
    }

    public static Specification<BaseHangupCause> createSpec(final BaseHangupCauseCriteria criteria){
        Specification<BaseHangupCause> spec = null;
        if(criteria==null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getCauseCn())){
            specs = specs.and(causeCnLike(criteria.getCauseCn()));
        }
        if (!Strings.isNullOrEmpty(criteria.getCauseEn())){
            specs = specs.and(causeEnEqual(criteria.getCauseEn()));
        }
        return specs;
    }
}
