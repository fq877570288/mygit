package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxShowbusyLogCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxShowbusyLog;
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
public class PbxShowbusyLogSpecs {
    public static Specification<PbxShowbusyLog> typeEqual(final Integer type) {

        return new Specification<PbxShowbusyLog>() {
            @Override
            public Predicate toPredicate(Root<PbxShowbusyLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("type"), type);
            }
        };
    }
    public static Specification<PbxShowbusyLog> createSpec(final PbxShowbusyLogCriteria criteria) {
        Specification<PbxShowbusyLog> spec = null;
        if(criteria==null) return spec;
        Specifications specs = where(spec);

        if(criteria.getType()!=null){
            specs = specs.and(typeEqual(criteria.getType()));
        }

        return specs;
    }
}
