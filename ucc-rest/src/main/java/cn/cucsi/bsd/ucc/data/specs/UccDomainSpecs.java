package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccDeptsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccDomainCriteria;
import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UccDomain;
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
 * Created by tianyuwei on 2017/10/16.
 */
public class UccDomainSpecs {

    public static Specification<UccDomain> uccDomainDomainEmailLike(final String domainEmail) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("domainEmail"), domainEmail);
            }
        };
    }

    public static Specification<UccDomain> uccDomainDomainNameLike(final String domainName) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("domainName"), "%" + domainName + "%");
            }
        };
    }


    public static Specification<UccDomain> uccDomainstatusLike(final String status) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("status"), status);
            }
        };
    }

    public static Specification<UccDomain> uccDomainDomainAddrLike(final String addr) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("addr1"), "%" + addr + "%");
            }
        };
    }

    public static Specification<UccDomain> uccDomainDomainTelLike(final String tel) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("tel1"), tel);
            }
        };
    }

    public static Specification<UccDomain> uccDomainDomainTelLike2(final String tel) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("tel2"), tel);
            }
        };
    }



    public static Specification<UccDomain> createTimeThanOrEqualTo(final Date from) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<UccDomain> createTimeLessOrEqualTo(final Date to) {
        return new Specification<UccDomain>() {
            @Override
            public Predicate toPredicate(Root<UccDomain> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }



    public static Specification<UccDomain> createSpec(final UccDomainCriteria criteria) {
        Specification<UccDomain> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getDomainEmail())){
            specs = specs.and(uccDomainDomainEmailLike(criteria.getDomainEmail()));
        }

        if(!Strings.isNullOrEmpty(criteria.getStatus())){
            specs = specs.and(uccDomainstatusLike(criteria.getStatus()));
        }

        if(!Strings.isNullOrEmpty(criteria.getDomainName())){
            specs = specs.and(uccDomainDomainNameLike(criteria.getDomainName()));
        }

        if(!Strings.isNullOrEmpty(criteria.getAddr())){
            specs = specs.and(uccDomainDomainAddrLike(criteria.getAddr()));
        }

        if(!Strings.isNullOrEmpty(criteria.getTel())){
            specs = specs.and(uccDomainDomainTelLike(criteria.getTel()));
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
