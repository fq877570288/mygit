package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrDigitCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrDigit;
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
public class PbxCdrDigitSpecs {

    public static Specification<PbxCdrDigit> cdrIdLike(final String cdrId) {
        return new Specification<PbxCdrDigit>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrDigit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("cdrId"), cdrId);
            }
        };
    }


    public static Specification<PbxCdrDigit> ivrIdLike(final String ivrId) {
        return new Specification<PbxCdrDigit>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrDigit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("ivrId"), ivrId);
            }
        };
    }

    public static Specification<PbxCdrDigit> createSpec(final PbxCdrDigitCriteria PbxCdrDigitCriteria) {
        Specification<PbxCdrDigit> spec = null;
        if (PbxCdrDigitCriteria == null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(PbxCdrDigitCriteria.getCdrId())){
            specs = specs.and(cdrIdLike(PbxCdrDigitCriteria.getCdrId()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrDigitCriteria.getIvrId())){
            specs = specs.and(ivrIdLike(PbxCdrDigitCriteria.getIvrId()));
        }

        return specs;
    }

}
