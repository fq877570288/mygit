package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccRolesCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccRoles;
import cn.cucsi.bsd.ucc.data.domain.UserRole;
import com.google.common.base.Strings;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by mk on 2017/10/16.
 */
public class UccRolesSpecs {
    public static Specification<UccRoles> roleNameLike(final String roleName) {
        return new Specification<UccRoles>() {
            @Override
            public Predicate toPredicate(Root<UccRoles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("roleName"), "%" + roleName + "%");
            }
        };
    }

    public static Specification<UccRoles> roleNameEqual(final String roleName) {
        return new Specification<UccRoles>() {
            @Override
            public Predicate toPredicate(Root<UccRoles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("roleName"), roleName);
            }
        };
    }


    public static Specification<UccRoles> domainIdEqual(final String domainId) {
        return new Specification<UccRoles>() {
            @Override
            public Predicate toPredicate(Root<UccRoles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }

    public static Specification<UccRoles> createTimeThanOrEqualTo(final Date from) {
        return new Specification<UccRoles>() {
            @Override
            public Predicate toPredicate(Root<UccRoles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<UccRoles> createTimeLessOrEqualTo(final Date to) {
        return new Specification<UccRoles>() {
            @Override
            public Predicate toPredicate(Root<UccRoles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }
    public static Specification<UccRoles> createSpec(final UccRolesCriteria criteria) {
        Specification<UccRoles> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);
        if(!Strings.isNullOrEmpty(criteria.getRoleName())){
            specs = specs.and(roleNameLike(criteria.getRoleName()));
        }
        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
        }
        if(null != criteria.getCreatedTimeFrom()){
            specs = specs.and(createTimeThanOrEqualTo(criteria.getCreatedTimeFrom()));
        }
        if(null != criteria.getCreatedTimeTo()){
            specs = specs.and(createTimeLessOrEqualTo(criteria.getCreatedTimeTo()));
        }
        return specs;
    }

    public static Specification<UccRoles> createSpecByRoleName(final UccRolesCriteria criteria) {
        Specification<UccRoles> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);
        if(!Strings.isNullOrEmpty(criteria.getRoleName())){
            specs = specs.and(roleNameEqual(criteria.getRoleName()));
        }
        if(null != criteria.getCreatedTimeFrom()){
            specs = specs.and(createTimeThanOrEqualTo(criteria.getCreatedTimeFrom()));
        }
        if(null != criteria.getCreatedTimeTo()){
            specs = specs.and(createTimeLessOrEqualTo(criteria.getCreatedTimeTo()));
        }
        return specs;
    }
}
