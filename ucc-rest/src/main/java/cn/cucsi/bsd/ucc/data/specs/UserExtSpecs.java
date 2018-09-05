package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UserDeptCriteria;
import cn.cucsi.bsd.ucc.common.beans.UserExtCriteria;
import cn.cucsi.bsd.ucc.data.domain.UserDept;
import cn.cucsi.bsd.ucc.data.domain.UserExt;
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
public class UserExtSpecs {
    public static Specification<UserExt>extNumEqual(final String extNum) {
        return new Specification<UserExt>() {
            @Override
            public Predicate toPredicate(Root<UserExt> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("extNum"), extNum);
            }
        };
    }

    public static Specification<UserExt> createSpec(final UserExtCriteria criteria) {
        Specification<UserExt> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getExtNum())){
            specs = specs.and(extNumEqual(criteria.getExtNum()));
        }
        return specs;
    }
}
