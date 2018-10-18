package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxCallTransferCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCallTransfer;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
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
public class PbxCallTransferSpecs {

    public static Specification<PbxCallTransfer> num1Like(final String num1) {
        return new Specification<PbxCallTransfer>() {
            @Override
            public Predicate toPredicate(Root<PbxCallTransfer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("num1"), num1);
            }
        };
    }

    public static Specification<PbxCallTransfer> num2Like(final String num2) {
        return new Specification<PbxCallTransfer>() {
            @Override
            public Predicate toPredicate(Root<PbxCallTransfer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("num2"), num2);
            }
        };
    }
      public static Specification<PbxCallTransfer> extIdEqual(final String extId) {
        return new Specification<PbxCallTransfer>() {
            @Override
            public Predicate toPredicate(Root<PbxCallTransfer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("extId"), extId);
            }
        };
    }
      public static Specification<PbxCallTransfer> domainIdEqual(final String domainId) {
        return new Specification<PbxCallTransfer>() {
            @Override
            public Predicate toPredicate(Root<PbxCallTransfer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("domainId"), domainId);
            }
        };
    }
    
       
    public static Specification<PbxCallTransfer> createSpec(final PbxCallTransferCriteria pbxCallTransferCriteria) {
        Specification<PbxCallTransfer> spec = null;
        if (pbxCallTransferCriteria == null) return spec;
        Specifications specs = where(spec);
        if (!Strings.isNullOrEmpty(pbxCallTransferCriteria.getNum1())) {
            specs = specs.and(num1Like(pbxCallTransferCriteria.getNum1()));
        }
        if (!Strings.isNullOrEmpty(pbxCallTransferCriteria.getNum2())) {
            specs = specs.and(num2Like(pbxCallTransferCriteria.getNum2()));
        }
        if (!Strings.isNullOrEmpty(pbxCallTransferCriteria.getDomainId())) {
            specs = specs.and(domainIdEqual(pbxCallTransferCriteria.getDomainId()));
        }
        if (!Strings.isNullOrEmpty(pbxCallTransferCriteria.getExtId())) {
            specs = specs.and(extIdEqual(pbxCallTransferCriteria.getExtId()));
        }
        return specs;
    }


}
