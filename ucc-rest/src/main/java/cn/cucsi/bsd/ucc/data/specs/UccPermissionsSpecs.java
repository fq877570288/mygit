package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccPermissionsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccPermissions;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by mk on 2017/10/13.
 */
public class UccPermissionsSpecs {
    public static Specification<UccPermissions> permissionNameLike(final String permissionName) {
        return new Specification<UccPermissions>() {
            @Override
            public Predicate toPredicate(Root<UccPermissions> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("permissionName"), permissionName);
            }
        };
    }
    public static Specification<UccPermissions> permissionIdIn(final List<String> permissionIds) {
        return new Specification<UccPermissions>() {
            @Override
            public Predicate toPredicate(Root<UccPermissions> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                CriteriaBuilder.In<String> in = criteriaBuilder.in(root.get("permissionId"));
                for (String id: permissionIds
                        ) {
                    in.value(id);
                }
                return in;
            }
        };
    }
    public static Specification<UccPermissions> createSpec(final UccPermissionsCriteria criteria) {
        Specification<UccPermissions> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);
        if(criteria.getPermissionIds()!=null && criteria.getPermissionIds().size()>0){
            specs = specs.and(permissionIdIn(criteria.getPermissionIds()));
        }
        if(!Strings.isNullOrEmpty(criteria.getPermissionName())){
            specs = specs.and(permissionNameLike(criteria.getPermissionName()));
        }
        return specs;
    }
}
