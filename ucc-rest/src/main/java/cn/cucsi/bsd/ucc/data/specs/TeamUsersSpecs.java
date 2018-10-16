package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.*;
import java.util.Date;

import static org.springframework.data.jpa.domain.Specifications.where;

public class TeamUsersSpecs {

    public static Specification<UccUsers> userNameLike(final String userName) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("userName"), "%" + userName + "%");
            }
        };
    }

    public static Specification<UccUsers> teamIdEqual(final String teamId) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.join("teams").get("teamId");
                return criteriaBuilder.equal(path, teamId);
            }
        };
    }

    public static Specification<UccUsers> domainIdEqual(final String domainId) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"),domainId);
            }
        };
    }

    public static Specification<UccUsers> createTimeThanOrEqualTo(final Date from) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<UccUsers> createTimeLessOrEqualTo(final Date to) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }

    public static Specification<UccUsers> createSpec(final UccUserCriteria criteria) {
        Specification<UccUsers> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getUserName())){
            specs = specs.and(userNameLike(criteria.getUserName()));
        }

        if(!Strings.isNullOrEmpty(criteria.getTeamId())){
            specs = specs.and(teamIdEqual(criteria.getTeamId()));
        }
        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
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
