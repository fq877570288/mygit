package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PermissionGroupsCriteria;
import cn.cucsi.bsd.ucc.common.beans.UccCustomersCriteria;
import cn.cucsi.bsd.ucc.data.domain.PermissionGroups;
import cn.cucsi.bsd.ucc.data.domain.UccCustomers;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.Date;

import static cn.cucsi.bsd.ucc.data.specs.PermissionGroupsSpecs.permissionGroupNameLike;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by tianyuwei on 2017/10/13.
 */
public class UccCustomersSpecs {

    public static Specification<UccCustomers> uccCustomersCustCodeEqual(final String custCode) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("custCode"), custCode);
            }
        };
    }


    public static Specification<UccCustomers> uccCustomersCustNameLike(final String custName) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("custName"), custName);
            }
        };
    }

    public static Specification<UccCustomers> uccCustomersCustTypeEqual(final Integer type) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("type"), type);
            }
        };
    }

    public static Specification<UccCustomers> uccCustomersCustPhoneLike(final String phone) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("phone"), phone);
            }
        };
    }

    public static Specification<UccCustomers> uccCustomersCustCreatetimeFromeGreater(final Date createtimeFrome) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createtime"), createtimeFrome);
            }
        };
    }

    public static Specification<UccCustomers> uccCustomersCustCreatetimeFromeLess(final Date createtimeTo) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createtime"), createtimeTo);
            }
        };
    }
    public static Specification<UccCustomers> uccCustomersCustDomainIdEq(final String domainId) {
        return new Specification<UccCustomers>() {
            @Override
            public Predicate toPredicate(Root<UccCustomers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("domainId"), domainId);
            }
        };
    }

    public static Specification<UccCustomers> createSpec(final UccCustomersCriteria criteria) {
        Specification<UccCustomers> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getCustCode())){
            specs = specs.and(uccCustomersCustCodeEqual(criteria.getCustCode()));
        }

        if(!Strings.isNullOrEmpty(criteria.getCustName())){
            specs = specs.and(uccCustomersCustNameLike(criteria.getCustName()));
        }
        if(null != criteria.getType()){
            specs = specs.and(uccCustomersCustTypeEqual(criteria.getType()));
        }
        if(!Strings.isNullOrEmpty(criteria.getPhone())){
            specs = specs.and(uccCustomersCustPhoneLike(criteria.getPhone()));
        }
        if(null != criteria.getCreatetimeFrome()){
            specs = specs.and(uccCustomersCustCreatetimeFromeGreater(criteria.getCreatetimeFrome()));
        }
        if(null != criteria.getCreatetimeTo()){
            specs = specs.and(uccCustomersCustCreatetimeFromeLess(criteria.getCreatetimeTo()));
        }
        if(null != criteria.getDomainId()){
            specs = specs.and(uccCustomersCustDomainIdEq(criteria.getDomainId()));
        }
        return specs;
    }
}
