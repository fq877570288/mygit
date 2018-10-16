package cn.cucsi.bsd.ucc.data.specs;


import cn.cucsi.bsd.ucc.common.beans.UccTeamsCriteria;
import cn.cucsi.bsd.ucc.data.domain.TeamUsers;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
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
public class UccTeamsSpecs {
    public static Specification<UccTeams> teamNameLike(final String teamName) {
        return new Specification<UccTeams>() {
            @Override
            public Predicate toPredicate(Root<UccTeams> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("teamName"),"%" + teamName + "%");
            }
        };
    }


    public static Specification<UccTeams> teamDomainId(final String domainId) {
        return new Specification<UccTeams>() {
            @Override
            public Predicate toPredicate(Root<UccTeams> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }
    public static Specification<UccTeams> createTimeThanOrEqualTo(final Date from) {
        return new Specification<UccTeams>() {
            @Override
            public Predicate toPredicate(Root<UccTeams> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<UccTeams> createTimeLessOrEqualTo(final Date to) {
        return new Specification<UccTeams>() {
            @Override
            public Predicate toPredicate(Root<UccTeams> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }

    public static Specification<UccTeams> createSpec(final UccTeamsCriteria criteria) {
        Specification<UccTeams> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(teamDomainId(criteria.getDomainId()));
        }

        if(!Strings.isNullOrEmpty(criteria.getTeamName())){
            specs = specs.and(teamNameLike(criteria.getTeamName()));
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
