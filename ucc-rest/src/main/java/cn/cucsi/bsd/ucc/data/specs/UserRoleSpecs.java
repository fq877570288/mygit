package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserRoleCriteria;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
import cn.cucsi.bsd.ucc.data.domain.UserRole;
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
public class UserRoleSpecs {
    public static Specification<UserRole> extNumEqual(final String roleId) {
        return new Specification<UserRole>() {
            @Override
            public Predicate toPredicate(Root<UserRole> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("roleId"), roleId);
            }
        };
    }

    public static Specification<UserRole> userIdEqual(final String userId) {
        return new Specification<UserRole>() {
            @Override
            public Predicate toPredicate(Root<UserRole> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("userId"), userId);
            }
        };
    }

    public static Specification<UserRole> createSpec(final UserRoleCriteria criteria) {
        Specification<UserRole> spec = null;
        if(criteria==null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getRoleId())){
            specs = specs.and(extNumEqual(criteria.getRoleId()));
        }
        if(!Strings.isNullOrEmpty(criteria.getUserId())){
            specs = specs.and(userIdEqual(criteria.getUserId()));
        }
        return specs;
    }
}
