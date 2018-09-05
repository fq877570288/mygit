package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxQueuesCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxQueues;
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
public class PbxQueuesSpecs {
    public static Specification<PbxQueues> queueNameLike(final String queueName) {

        return new Specification<PbxQueues>() {
            @Override
            public Predicate toPredicate(Root<PbxQueues> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("queueName"), "%"+queueName+"%");
            }
        };
    }
    public static Specification<PbxQueues> domainIdEqual(final String domainId) {

        return new Specification<PbxQueues>() {
            @Override
            public Predicate toPredicate(Root<PbxQueues> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }
    public static Specification<PbxQueues> typeEqual(final Integer type) {

        return new Specification<PbxQueues>() {
            @Override
            public Predicate toPredicate(Root<PbxQueues> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("type"), type);

            }
        };
    }
    public static Specification<PbxQueues> createSpec(final PbxQueuesCriteria criteria) {
        Specification<PbxQueues> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getQueueName())){
            specs = specs.and(queueNameLike(criteria.getQueueName()));
        }
        if(criteria.getType()!=null&&criteria.getType()!=-1){
            specs = specs.and(typeEqual(criteria.getType()));

        }
        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));

        }
        return specs;
    }
}
