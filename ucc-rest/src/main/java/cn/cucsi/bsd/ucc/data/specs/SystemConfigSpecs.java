package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.common.beans.SystemConfigCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
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
public class SystemConfigSpecs {
    public static Specification<SystemConfig> systemConfigNameLike(final String name) {
        return new Specification<SystemConfig>() {
            @Override
            public Predicate toPredicate(Root<SystemConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("name"), name);
            }
        };
    }

    public static Specification<SystemConfig> systemConfigValueLike(final String value) {
        return new Specification<SystemConfig>() {
            @Override
            public Predicate toPredicate(Root<SystemConfig> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("name"), value);
            }
        };
    }

    public static Specification<SystemConfig> createSpec(final SystemConfigCriteria criteria) {
        Specification<SystemConfig> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getName())){
            specs = specs.and(systemConfigNameLike(criteria.getName()));
        }

        if(!Strings.isNullOrEmpty(criteria.getValue())){
            specs = specs.and(systemConfigValueLike(criteria.getValue()));
        }
        return specs;
    }
}
