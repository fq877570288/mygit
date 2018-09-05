package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
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
public class PbxCdrsSpecs {

    public static Specification<PbxCdrs> firstCallerLike(final String firstCaller) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("firstCaller"), firstCaller);
            }
        };
    }


    public static Specification<PbxCdrs> callerJobNumberLike(final String callerJobNumber) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("callerJobNumber"), callerJobNumber);
            }
        };
    }

    public static Specification<PbxCdrs> createSpec(final PbxCdrsCriteria PbxCdrsCriteria) {
        Specification<PbxCdrs> spec = null;
        if (PbxCdrsCriteria == null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getfirstCaller())){
            specs = specs.and(firstCallerLike(PbxCdrsCriteria.getfirstCaller()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getcallerJobNumber())){
            specs = specs.and(callerJobNumberLike(PbxCdrsCriteria.getcallerJobNumber()));
        }

        return specs;
    }
}
