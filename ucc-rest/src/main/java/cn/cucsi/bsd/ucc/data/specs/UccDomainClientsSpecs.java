package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccDomainClientsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomain;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClients;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public class UccDomainClientsSpecs {

    public static Specification<UccDomainClients> uccDomainClientsDomainIdEqual(final String domainId) {
        return new Specification<UccDomainClients>() {
            @Override
            public Predicate toPredicate(Root<UccDomainClients> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }

    public static Specification<UccDomainClients> uccDomainClientsNameEqual(final String name) {
        return new Specification<UccDomainClients>() {
            @Override
            public Predicate toPredicate(Root<UccDomainClients> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }


    public static Specification<UccDomainClients> createSpec(final UccDomainClientsCriteria criteria) {
        Specification<UccDomainClients> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(uccDomainClientsDomainIdEqual(criteria.getDomainId()));
        }

        if(!Strings.isNullOrEmpty(criteria.getClients())){
            specs = specs.and(uccDomainClientsNameEqual(criteria.getClients()));
        }

        return specs;
    }
}
