package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.BaseDictCriteria;

import cn.cucsi.bsd.ucc.data.domain.BaseDict;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by home on 2017/10/13.
 */
public class BaseDictSpecs {
    public static Specification<BaseDict> dictTypeEqual(final String dictType)  {
        return new Specification<BaseDict>() {
            @Override
            public Predicate toPredicate(Root<BaseDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("dictType"), dictType);
            }
        };
    }


    public static Specification<BaseDict> dictValueLike(final String dictValue) {
        return new Specification<BaseDict>() {
            @Override
            public Predicate toPredicate(Root<BaseDict> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("dictValue"),dictValue);
            }
        };
    }

    public static Specification<BaseDict> createSpec(final BaseDictCriteria criteria){

        Specification<BaseDict> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getDictType())){
            specs = specs.and(dictTypeEqual(criteria.getDictType()));
        }

        if (!Strings.isNullOrEmpty(criteria.getDictValue())){
            specs = specs.and(dictValueLike(criteria.getDictValue()));
        }
        return specs;
    }
}
