package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxDialplansCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxDialplans;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Song on 2017/10/16.
 */
public class PbxDialplansSpecs {

    public static Specification<PbxDialplans> phoneNumberLike(final String phoneNumber) {
        return new Specification<PbxDialplans>() {
            @Override
            public Predicate toPredicate(Root<PbxDialplans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return  criteriaBuilder.like(root.<String>get("phoneNumber"), "%"+phoneNumber+"%");
            }
        };
    }
    public static Specification<PbxDialplans> domainIdEqual(final String domainId) {
        return new Specification<PbxDialplans>() {
            @Override
            public Predicate toPredicate(Root<PbxDialplans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return  criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }
    public static Specification<PbxDialplans> memoLike(final String memo) {
        return new Specification<PbxDialplans>() {
            @Override
            public Predicate toPredicate(Root<PbxDialplans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return  criteriaBuilder.like(root.<String>get("memo"), "%"+memo+"%");
            }
        };
    }

    public static Specification<PbxDialplans> destinationLike(final String destination) {
        return new Specification<PbxDialplans>() {
            @Override
            public Predicate toPredicate(Root<PbxDialplans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("destination"), "%"+destination+"%");
            }
        };
    }
    public static Specification<PbxDialplans> typeEqual(final Integer type) {
        return new Specification<PbxDialplans>() {
            @Override
            public Predicate toPredicate(Root<PbxDialplans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("type"), type);
            }
        };
    }
    public static Specification<PbxDialplans> modeEqual(final Integer mdoe) {
        return new Specification<PbxDialplans>() {
            @Override
            public Predicate toPredicate(Root<PbxDialplans> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("mode"), mdoe);
            }
        };
    }
    public static Specification<PbxDialplans> createSpec(final PbxDialplansCriteria PbxDialplansCriteria) {
        Specification<PbxDialplans> spec = null;
        if (PbxDialplansCriteria == null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(PbxDialplansCriteria.getPhoneNumber())){
            specs = specs.and(phoneNumberLike(PbxDialplansCriteria.getPhoneNumber()));
        }
        if(!Strings.isNullOrEmpty(PbxDialplansCriteria.getDestination())){
            specs = specs.and(destinationLike(PbxDialplansCriteria.getDestination()));
        }
        if(!Strings.isNullOrEmpty(PbxDialplansCriteria.getMemo())){
            specs = specs.and(memoLike(PbxDialplansCriteria.getMemo()));
        }
        if(!Strings.isNullOrEmpty(PbxDialplansCriteria.getDomainId())){
            specs = specs.and(domainIdEqual(PbxDialplansCriteria.getDomainId()));
        }
        if(PbxDialplansCriteria.getType()!=null && PbxDialplansCriteria.getType()!=-1 && PbxDialplansCriteria.getType()!=0){
            specs = specs.and(typeEqual(PbxDialplansCriteria.getType()));
        }
        if(PbxDialplansCriteria.getMode()!=null && PbxDialplansCriteria.getMode()!=-1 && PbxDialplansCriteria.getMode()!=0){
            specs = specs.and(modeEqual(PbxDialplansCriteria.getMode()));
        }
        return specs;
    }


}
