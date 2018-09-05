package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccDomainClientsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccDomainServersCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDomainClients;
import cn.cucsi.bsd.ucc.data.domain.UccDomainServers;
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
public class UccDomainServersSpecs {
    public static Specification<UccDomainServers> uccDomainServersDomainIdEqual(final String domainId) {
        return new Specification<UccDomainServers>() {
            @Override
            public Predicate toPredicate(Root<UccDomainServers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }

    public static Specification<UccDomainServers> uccDomainServersNameEqual(final String serverName) {
        return new Specification<UccDomainServers>() {
            @Override
            public Predicate toPredicate(Root<UccDomainServers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("serverName"), serverName);
            }
        };
    }


    public static Specification<UccDomainServers> createSpec(final UccDomainServersCriteria criteria) {
        Specification<UccDomainServers> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(uccDomainServersDomainIdEqual(criteria.getDomainId()));
        }

        if(!Strings.isNullOrEmpty(criteria.getServerName())){
            specs = specs.and(uccDomainServersNameEqual(criteria.getServerName()));
        }

        return specs;
    }
}
