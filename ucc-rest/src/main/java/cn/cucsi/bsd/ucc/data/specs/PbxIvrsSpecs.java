package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxIvrsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxIvrs;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by jjjjj on 2017-10-16.
 */
public class PbxIvrsSpecs {
    public static Specification<PbxIvrs> ivrNameEqual(final String ivrName) {

        return new Specification<PbxIvrs>() {
            @Override
            public Predicate toPredicate(Root<PbxIvrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("ivrName"), ivrName);
            }
        };
    }

    public static Specification<PbxIvrs> domainIdEqual(final String domainId) {

        return new Specification<PbxIvrs>() {
            @Override
            public Predicate toPredicate(Root<PbxIvrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }

    public static Specification<PbxIvrs> createSpec(final PbxIvrsCriteria criteria) {
        Specification<PbxIvrs> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getIvrName())){
            specs = specs.and(ivrNameEqual(criteria.getIvrName()));
        }
        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
        }
        return specs;
    }
}
