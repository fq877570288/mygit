package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.RolesPermissionsCriteria;
import cn.cucsi.bsd.ucc.data.domain.RolesPermissions;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/13.
 */
public class RolesPermissionsSpecs {
    public static Specification<RolesPermissions> permissionGroupNameLike(final String groupName) {
        return new Specification<RolesPermissions>() {
            @Override
            public Predicate toPredicate(Root<RolesPermissions> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("userPermissionGroupName"), groupName);
            }
        };
    }

    public static Specification<RolesPermissions> domainIdEqual(final String domainId) {
        return new Specification<RolesPermissions>() {
            @Override
            public Predicate toPredicate(Root<RolesPermissions> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }


    public static Specification<RolesPermissions> createSpec(final RolesPermissionsCriteria criteria) {
        Specification<RolesPermissions> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getRoleId())){
            specs = specs.and(permissionGroupNameLike(criteria.getRoleId()));
        }

        if(!Strings.isNullOrEmpty(criteria.getPermissionId())){
            specs = specs.and(domainIdEqual(criteria.getPermissionId()));
        }
        return specs;
    }
}
