package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccServersCriteria;

import cn.cucsi.bsd.ucc.data.domain.UccServers;

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
public class UccServersSpecs {
    public static Specification<UccServers> serverIpEqual(final String serverIp) {
        return new Specification<UccServers>() {
            @Override
            public Predicate toPredicate(Root<UccServers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("serverIp"), serverIp);
            }
        };
    }

    public static Specification<UccServers> createSpec(final UccServersCriteria criteria) {
        Specification<UccServers> spec = null;
        if(criteria==null) return spec;
        Specifications specs = where(spec);
        if(!Strings.isNullOrEmpty(criteria.getServerIp())){
            specs = specs.and(serverIpEqual(criteria.getServerIp()));
        }
        return specs;
    }

}
