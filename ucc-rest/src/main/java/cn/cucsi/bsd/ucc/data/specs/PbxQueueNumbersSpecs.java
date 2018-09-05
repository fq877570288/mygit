package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxQueueNumbersCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxQueueNumbers;
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
public class PbxQueueNumbersSpecs {
    public static Specification<PbxQueueNumbers> extIdEqual(final String extId) {

        return new Specification<PbxQueueNumbers>() {
            @Override
            public Predicate toPredicate(Root<PbxQueueNumbers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("extId"), extId);
            }
        };
    }
    public static Specification<PbxQueueNumbers> queueIdEqual(final String queueId) {

        return new Specification<PbxQueueNumbers>() {
            @Override
            public Predicate toPredicate(Root<PbxQueueNumbers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("queueId"), queueId);
            }
        };
    }

    public static Specification<PbxQueueNumbers> createSpec(final PbxQueueNumbersCriteria criteria) {
        Specification<PbxQueueNumbers> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getExtId())){
            specs = specs.and(extIdEqual(criteria.getExtId()));
        }
        if(!Strings.isNullOrEmpty(criteria.getQueueId())){
            specs = specs.and(queueIdEqual(criteria.getQueueId()));
        }
        return specs;
    }
}
