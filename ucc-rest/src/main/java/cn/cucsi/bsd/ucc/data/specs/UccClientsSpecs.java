package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.SystemConfigCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccClientsCriteria;
import cn.cucsi.bsd.ucc.data.domain.SystemConfig;
import cn.cucsi.bsd.ucc.data.domain.UccClients;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/13.
 */
public class UccClientsSpecs {

    public static Specification<UccClients> uccClientsNameLike(final String name) {
        return new Specification<UccClients>() {
            @Override
            public Predicate toPredicate(Root<UccClients> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("name"), name);
            }
        };
    }

    public static Specification<UccClients> uccClientsIpLike(final String ip) {
        return new Specification<UccClients>() {
            @Override
            public Predicate toPredicate(Root<UccClients> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("ip"), ip);
            }
        };
    }

    public static Specification<UccClients> createSpec(final UccClientsCriteria criteria) {
        Specification<UccClients> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getName())){
            specs = specs.and(uccClientsNameLike(criteria.getName()));
        }

        if(!Strings.isNullOrEmpty(criteria.getIp())){
            specs = specs.and(uccClientsIpLike(criteria.getIp()));
        }
        return specs;
    }
}
