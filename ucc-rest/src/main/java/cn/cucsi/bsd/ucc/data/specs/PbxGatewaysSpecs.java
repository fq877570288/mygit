package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxGatewaysCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxGateways;
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
public class PbxGatewaysSpecs {
    public static Specification<PbxGateways> gwNameEqual(final String gwName) {

        return new Specification<PbxGateways>() {
            @Override
            public Predicate toPredicate(Root<PbxGateways> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("gwName"), gwName);
            }
        };
    }
    public static Specification<PbxGateways> domainIdEqual(final String domainId) {

        return new Specification<PbxGateways>() {
            @Override
            public Predicate toPredicate(Root<PbxGateways> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }
    public static Specification<PbxGateways> typeEqual(final Integer type) {

        return new Specification<PbxGateways>() {
            @Override
            public Predicate toPredicate(Root<PbxGateways> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("type"), type);
            }
        };
    }
    public static Specification<PbxGateways> statusEqual(final Integer status) {

        return new Specification<PbxGateways>() {
            @Override
            public Predicate toPredicate(Root<PbxGateways> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("status"), status);
            }
        };
    }
    public static Specification<PbxGateways> createSpec(final PbxGatewaysCriteria criteria) {
        Specification<PbxGateways> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getGwName())){
            specs = specs.and(gwNameEqual(criteria.getGwName()));
        }
        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
        }
        if(criteria.getType()!=null && criteria.getType()!=-1){
            specs = specs.and(typeEqual(criteria.getType()));
        }
        if(criteria.getStatus()!=null ){
            specs = specs.and(statusEqual(criteria.getStatus()));
        }
        return specs;
    }

}
