package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
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
public class PermissionGroupsSpecs {
    public static Specification<PermissionGroups> permissionGroupNameLike(final String groupName) {
        return new Specification<PermissionGroups>() {
            @Override
            public Predicate toPredicate(Root<PermissionGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("userPermissionGroupName"), groupName);
            }
        };
    }

    public static Specification<PermissionGroups> domainIdEqual(final String domainId) {
        return new Specification<PermissionGroups>() {
            @Override
            public Predicate toPredicate(Root<PermissionGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }


    public static Specification<PermissionGroups> createSpec(final PermissionGroupsCriteria criteria) {
        Specification<PermissionGroups> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getPermissionGroupName())){
            specs = specs.and(permissionGroupNameLike(criteria.getPermissionGroupName()));
        }

        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
        }
        return specs;
    }
}
