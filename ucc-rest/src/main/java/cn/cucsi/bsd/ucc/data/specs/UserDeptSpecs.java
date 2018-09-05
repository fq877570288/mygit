package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserDeptCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.data.domain.UserDept;
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
public class UserDeptSpecs {
    public static Specification<UserDept> deptIdEqual(final String deptId) {
        return new Specification<UserDept>() {
            @Override
            public Predicate toPredicate(Root<UserDept> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("deptId"), deptId);
            }
        };
    }

    public static Specification<UserDept> userIdEqual(final String userId) {
        return new Specification<UserDept>() {
            @Override
            public Predicate toPredicate(Root<UserDept> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("userId"), userId);
            }
        };
    }

    public static Specification<UserDept> createSpec(final UserDeptCriteria criteria) {
        Specification<UserDept> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getDeptId())){
            specs = specs.and(deptIdEqual(criteria.getDeptId()));
        }
        if(!Strings.isNullOrEmpty(criteria.getUserId())){
            specs = specs.and(userIdEqual(criteria.getUserId()));
        }

        return specs;
    }
}
