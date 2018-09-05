package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxRecordsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxRecords;
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
public class PbxRecordsSpecs {
    public static Specification<PbxRecords> recordNameEqual(final String recordName) {

        return new Specification<PbxRecords>() {
            @Override
            public Predicate toPredicate(Root<PbxRecords> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("recordName"), recordName);
            }
        };
    }
    public static Specification<PbxRecords> createSpec(final PbxRecordsCriteria criteria) {
        Specification<PbxRecords> spec = null;
        if(criteria==null) return spec;
        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getRecordName())){
            specs = specs.and(recordNameEqual(criteria.getRecordName()));
        }

        return specs;
    }
}
