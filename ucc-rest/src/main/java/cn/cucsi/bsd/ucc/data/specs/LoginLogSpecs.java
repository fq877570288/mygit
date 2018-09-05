package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.LoginLogCriteria;
import cn.cucsi.bsd.ucc.data.domain.LoginLog;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Song on 2017/10/16.
 */
public class LoginLogSpecs {
    public static Specification<LoginLog> loginIpLike(final String loginIp) {
        return new Specification<LoginLog>() {
            @Override
            public Predicate toPredicate(Root<LoginLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("loginIp"), loginIp);
            }
        };
    }


    public static Specification<LoginLog> logCriteriaLike(final String logCriteria) {
        return new Specification<LoginLog>() {
            @Override
            public Predicate toPredicate(Root<LoginLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("logCriteria"), logCriteria);
            }
        };
    }

    public static Specification<LoginLog> createSpec(final LoginLogCriteria loginLogCriteria) {
        Specification<LoginLog> spec = null;
        if (loginLogCriteria == null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(loginLogCriteria.getLoginIp())){
            specs = specs.and(loginIpLike(loginLogCriteria.getLoginIp()));
        }
        if(!Strings.isNullOrEmpty(loginLogCriteria.getLoginArea())){
            specs = specs.and(logCriteriaLike(loginLogCriteria.getLoginArea()));
        }

        return specs;
    }


}
