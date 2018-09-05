package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxGwCalleeRewriteRulesCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGwCalleeRewriteRules;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by jjjjj on 2017-10-16.
 */
public class PbxGwCalleeRewriteRulesSpecs {
    public static Specification<PbxGwCalleeRewriteRules> gatewayIdEqual(final String gatewayId) {

        return new Specification<PbxGwCalleeRewriteRules>() {
            @Override
            public Predicate toPredicate(Root<PbxGwCalleeRewriteRules> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("gatewayId"), gatewayId);
            }
        };
    }
    public static Specification<PbxGwCalleeRewriteRules> createSpec(final PbxGwCalleeRewriteRulesCriteria criteria) {
        Specification<PbxGwCalleeRewriteRules> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getGatewayId())){
            specs = specs.and(gatewayIdEqual(criteria.getGatewayId()));
        }
        return specs;
    }
}
