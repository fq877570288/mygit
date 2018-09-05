package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxExtGroupsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;


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
 * Created by jjjjj on 2017-10-13.
 */
public class PbxExtGroupsSpecs {
    public static Specification<PbxExtGroups> groupNameLike(final String groupName) {

        return new Specification<PbxExtGroups>() {
            @Override
            public Predicate toPredicate(Root<PbxExtGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("groupName"), '%'+groupName+'%');
            }
        };
    }


    public static Specification<PbxExtGroups> createTimeThanOrEqualTo(final Date from) {
        return new Specification<PbxExtGroups>() {
            @Override
            public Predicate toPredicate(Root<PbxExtGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<PbxExtGroups> createTimeLessOrEqualTo(final Date to) {
        return new Specification<PbxExtGroups>() {
            @Override
            public Predicate toPredicate(Root<PbxExtGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }

    public static Specification<PbxExtGroups> statusEqual(final String status) {
        return new Specification<PbxExtGroups>() {
            @Override
            public Predicate toPredicate(Root<PbxExtGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("status"),status);
            }
        };
    }
    public static Specification<PbxExtGroups> domainIdEqual(final String domainId) {

        return new Specification<PbxExtGroups>() {
            @Override
            public Predicate toPredicate(Root<PbxExtGroups> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }

    public static Specification<PbxExtGroups> createSpec(final PbxExtGroupsCriteria criteria) {
        Specification<PbxExtGroups> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getGroupName())){
            specs = specs.and(groupNameLike(criteria.getGroupName()));
        }
        if(null != criteria.getCreatedTimeFrom()){
            specs = specs.and(createTimeThanOrEqualTo(criteria.getCreatedTimeFrom()));
        }

        if(null != criteria.getCreatedTimeTo()){
            specs = specs.and(createTimeLessOrEqualTo(criteria.getCreatedTimeTo()));
        }
        if(!Strings.isNullOrEmpty(criteria.getStatus())){
            specs = specs.and(statusEqual(criteria.getStatus()));
        }
        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
        }
        return specs;
    }
    public static Specification<PbxExtGroups> FindAllData() {
        Specification<PbxExtGroups> spec = null;

        return spec;
    }

}
