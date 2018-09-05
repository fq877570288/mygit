package cn.cucsi.bsd.ucc.data.specs;


import cn.cucsi.bsd.ucc.common.beans.UccSkillGroupCriteria;

import cn.cucsi.bsd.ucc.data.domain.UccSkillGroup;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by mk on 2017/10/16.
 */
public class UccSkillGroupSpecs {
    public static Specification<UccSkillGroup> skillGroupNameLike(final String skillGroupName) {
        return new Specification<UccSkillGroup>() {
            @Override
            public Predicate toPredicate(Root<UccSkillGroup> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("skillGroupName"), skillGroupName);
            }
        };
    }

    public static Specification<UccSkillGroup> createTimeThanOrEqualTo(final Date from) {
        return new Specification<UccSkillGroup>() {
            @Override
            public Predicate toPredicate(Root<UccSkillGroup> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<UccSkillGroup> createTimeLessOrEqualTo(final Date to) {
        return new Specification<UccSkillGroup>() {
            @Override
            public Predicate toPredicate(Root<UccSkillGroup> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }




    public static Specification<UccSkillGroup> createSpec(final UccSkillGroupCriteria criteria) {
        Specification<UccSkillGroup> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getSkillGroupName())){
            specs = specs.and(skillGroupNameLike(criteria.getSkillGroupName()));
        }
        if(null != criteria.getCreatedTimeFrom()){
            specs = specs.and(createTimeThanOrEqualTo(criteria.getCreatedTimeFrom()));
        }

        if(null != criteria.getCreatedTimeTo()){
            specs = specs.and(createTimeLessOrEqualTo(criteria.getCreatedTimeTo()));
        }
        return specs;
    }
}
