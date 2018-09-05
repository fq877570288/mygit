package cn.cucsi.bsd.ucc.data.specs;


import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public class UccDeptsSpecs {
    public static Specification<UccDepts> uccDeptsCreatedByEqual(final String createdBy) {
        return new Specification<UccDepts>() {
            @Override
            public Predicate toPredicate(Root<UccDepts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("createdBy"), createdBy);
            }
        };
    }


    public static Specification<UccDepts> uccDeptsDeptNameLike(final String deptName) {
        return new Specification<UccDepts>() {
            @Override
            public Predicate toPredicate(Root<UccDepts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("deptName"), '%'+deptName+'%');
        }
        };
    }

    public static Specification<UccDepts> uccDeptsDeptPidEqual(final String deptPid) {
        return new Specification<UccDepts>() {
            @Override
            public Predicate toPredicate(Root<UccDepts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("deptPid"), deptPid);
            }
        };
    }

    public static Specification<UccDepts> uccDeptsDomainIdEqual(final String domainId) {
        return new Specification<UccDepts>() {
            @Override
            public Predicate toPredicate(Root<UccDepts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }

    public static Specification<UccDepts> createSpec(final UccDeptsCriteria criteria) {
        Specification<UccDepts> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getCreatedBy())){
            specs = specs.and(uccDeptsCreatedByEqual(criteria.getCreatedBy()));
        }

        if(!Strings.isNullOrEmpty(criteria.getDeptName())){
            specs = specs.and(uccDeptsDeptNameLike(criteria.getDeptName()));
        }

        if(!Strings.isNullOrEmpty(criteria.getDeptPid())){
            specs = specs.and(uccDeptsDeptPidEqual(criteria.getDeptPid()));
        }

        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(uccDeptsDomainIdEqual(criteria.getDomainId()));
        }

        return specs;
    }
}
