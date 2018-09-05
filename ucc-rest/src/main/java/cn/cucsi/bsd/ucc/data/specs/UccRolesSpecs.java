package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccRolesCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccRoles;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by mk on 2017/10/16.
 */
public class UccRolesSpecs {
    public static Specification<UccRoles> roleNameLike(final String roleName) {
        return new Specification<UccRoles>() {
            @Override
            public Predicate toPredicate(Root<UccRoles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("roleName"), roleName);
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

        return specs;
    }
}
