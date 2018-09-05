package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.ExtGroupExtsCriteria;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
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
public class ExtGroupExtsSpecs {

    public static Specification<ExtGroupExts> groupIdLike(final String groupId) {
        return new Specification<ExtGroupExts>() {
            @Override
            public Predicate toPredicate(Root<ExtGroupExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("groupId"), groupId);
            }
        };
    }


    public static Specification<ExtGroupExts> extIdLike(final String extId) {
        return new Specification<ExtGroupExts>() {
            @Override
            public Predicate toPredicate(Root<ExtGroupExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("extId"), extId);
            }
        };
    }

    public static Specification<ExtGroupExts> createSpec(final ExtGroupExtsCriteria ExtGroupExtsCriteria) {
        Specification<ExtGroupExts> spec = null;
        if (ExtGroupExtsCriteria == null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(ExtGroupExtsCriteria.getGroupId())){
            specs = specs.and(groupIdLike(ExtGroupExtsCriteria.getGroupId()));
        }
        if(!Strings.isNullOrEmpty(ExtGroupExtsCriteria.getExtId())){
            specs = specs.and(extIdLike(ExtGroupExtsCriteria.getExtId()));
        }

        return specs;
    }

}
