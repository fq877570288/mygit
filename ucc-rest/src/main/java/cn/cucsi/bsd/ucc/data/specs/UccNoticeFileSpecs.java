package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccNoticeFileCriteria;
import cn.cucsi.bsd.ucc.data.domain.*;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/16.
 */
public class UccNoticeFileSpecs {

    public static Specification<UccNoticeFile> uccNoticeFileFileNameLike(final String fileName) {
        return new Specification<UccNoticeFile>() {
            @Override
            public Predicate toPredicate(Root<UccNoticeFile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("fileName"), fileName);
            }
        };
    }

    public static Specification<UccNoticeFile> uccNoticeFileUploadTimeThanOrEqualTo(final Date uploadTimeFrom) {
        return new Specification<UccNoticeFile>() {
            @Override
            public Predicate toPredicate(Root<UccNoticeFile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("uploadTime"), uploadTimeFrom);
            }
        };
    }

    public static Specification<UccNoticeFile> uccNoticeFileUploadTimeLessOrEqualTo(final Date uploadTimeTo) {
        return new Specification<UccNoticeFile>() {
            @Override
            public Predicate toPredicate(Root<UccNoticeFile> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("uploadTime"), uploadTimeTo);
            }
        };
    }

    public static Specification<UccNoticeFile> createSpec(final UccNoticeFileCriteria criteria) {
        Specification<UccNoticeFile> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getFileName())){
            specs = specs.and(uccNoticeFileFileNameLike(criteria.getFileName()));
        }

        if(null != criteria.getUploadTimeFrom()){
            specs = specs.and(uccNoticeFileUploadTimeThanOrEqualTo(criteria.getUploadTimeFrom()));
        }
        if(null != criteria.getUploadTimeTo()){
            specs = specs.and(uccNoticeFileUploadTimeLessOrEqualTo(criteria.getUploadTimeTo()));
        }

        return specs;
    }
}
