package cn.cucsi.bsd.ucc.data.specs;


import cn.cucsi.bsd.ucc.common.beans.SkillGroupUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.SkillGroupUser;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/17.
 */
public class SkillGroupUserSpecs {
    public static Specification<SkillGroupUser> skillGoupUserIdEqual(final String userId) {
        return new Specification<SkillGroupUser>() {
            @Override
            public Predicate toPredicate(Root<SkillGroupUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("userId"), userId);
            }
        };
    }

    public static Specification<SkillGroupUser> skillGoupSkillGroupIdEqual(final String skillGroupId) {
        return new Specification<SkillGroupUser>() {
            @Override
            public Predicate toPredicate(Root<SkillGroupUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("skillGroupId"), skillGroupId);
            }
        };
    }


    public static Specification<SkillGroupUser> userNameEqual(final String userName) {
        return new Specification<SkillGroupUser>() {
            @Override
            public Predicate toPredicate(Root<SkillGroupUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("userName"),userName);
            }
        };
    }

    public static Specification<SkillGroupUser> groupBy() {
        return new Specification<SkillGroupUser>() {
            @Override
            public Predicate toPredicate(Root<SkillGroupUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                criteriaQuery.groupBy(root.get("userId"));


                return criteriaQuery.getRestriction();
            }
        };
    }

    public static Specification<SkillGroupUser> createSpec(final SkillGroupUserCriteria criteria) {
        Specification<SkillGroupUser> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getSkillGroupId())){
            specs = specs.and(skillGoupSkillGroupIdEqual(criteria.getSkillGroupId()));
        }

        if(!Strings.isNullOrEmpty(criteria.getUserId())){
            specs = specs.and(skillGoupUserIdEqual(criteria.getUserId()));
        }
        if(!Strings.isNullOrEmpty(criteria.getUserName())){
            specs = specs.and(userNameEqual(criteria.getUserName()));
        }
        specs = specs.and(groupBy());
        return specs;
    }

}
