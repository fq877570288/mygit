package cn.cucsi.bsd.ucc.data.specs;


import cn.cucsi.bsd.ucc.common.beans.UccTeamsCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccTeams;
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
public class UccTeamsSpecs {
    public static Specification<UccTeams> teamNameLike(final String teamName) {
        return new Specification<UccTeams>() {
            @Override
            public Predicate toPredicate(Root<UccTeams> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("teamName"), teamName);
            }
        };
    }
    public static Specification<UccTeams> createSpec(final UccTeamsCriteria criteria) {
        Specification<UccTeams> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getTeamName())){
            specs = specs.and(teamNameLike(criteria.getTeamName()));
        }
        return specs;
    }
}
