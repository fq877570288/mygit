package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxExtsCriteria;
import cn.cucsi.bsd.ucc.data.domain.ExtGroupExts;
import cn.cucsi.bsd.ucc.data.domain.PbxExtGroups;
import cn.cucsi.bsd.ucc.data.domain.PbxExts;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by jjjjj on 2017-10-13.
 */
public class PbxExtsSpecs {

    public static Specification<PbxExts> extNumEqual(final String extNum) {

        return new Specification<PbxExts>() {
            @Override
            public Predicate toPredicate(Root<PbxExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("extNum"), extNum);
            }
        };
    }
    public static Specification<PbxExts> domainIdEqual(final String domainId) {

        return new Specification<PbxExts>() {
            @Override
            public Predicate toPredicate(Root<PbxExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }
    public static Specification<PbxExts> createTimeThanOrEqualTo(final Date from) {
        return new Specification<PbxExts>() {
            @Override
            public Predicate toPredicate(Root<PbxExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<PbxExts> createTimeLessOrEqualTo(final Date to) {
        return new Specification<PbxExts>() {
            @Override
            public Predicate toPredicate(Root<PbxExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }
    public static Specification<PbxExts> extIdIn(List<String> extId) {

        return new Specification<PbxExts>() {
            @Override
            public Predicate toPredicate(Root<PbxExts> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               In<String> in = criteriaBuilder.in(root.get("extId"));
                for (String id: extId
                     ) {
                    in.value(id);
                }
                return in;
            }
        };
    }

    public static Specification<PbxExts> createSpec(final PbxExtsCriteria criteria) {
        Specification<PbxExts> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getExtNum())){
            specs = specs.and(extNumEqual(criteria.getExtNum()));
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
        if(null != criteria.getExtIds()){
            specs =specs.and(extIdIn(criteria.getExtIds()));
        }
        return specs;
    }
}
