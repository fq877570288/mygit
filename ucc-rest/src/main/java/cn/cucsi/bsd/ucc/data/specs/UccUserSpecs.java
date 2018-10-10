package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.data.domain.UccDepts;
import cn.cucsi.bsd.ucc.data.domain.UccUsers;
import cn.cucsi.bsd.ucc.common.beans.UccUserCriteria;
import com.google.common.base.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.*;

public class UccUserSpecs {
    public static Specification<UccUsers> userNameEqual(final String userName) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("userName"), userName);
            }
        };
    }

    public static Specification<UccUsers> userNameLike(final String userName) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("userName"), "%"+userName+"%");
            }
        };
    }

    public static Specification<UccUsers> passwordLike(final String password) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("password"), password);
            }
        };
    }


    public static Specification<UccUsers> mobileEqual(final String mobile) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("mobile"), mobile);
            }
        };
    }

    public static Specification<UccUsers> domainIdEqual(final String domainId) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("domainId"), domainId);
            }
        };
    }

    public static Specification<UccUsers> nickNameLike(final String nickName) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("nickName"), '%'+nickName+'%');
            }
        };
    }

    public static Specification<UccUsers> emailLike(final String email) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("email"), '%'+email+'%');
            }
        };
    }

    public static Specification<UccUsers> lastLoginTimeThanOrEqualTo(final Date from) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("lastLoginTime"), from);
            }
        };
    }

    public static Specification<UccUsers> lastLoginTimeLessOrEqualTo(final Date to) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("lastLoginTime"), to);
            }
        };
    }

    public static Specification<UccUsers> createTimeThanOrEqualTo(final Date from) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), from);
            }
        };
    }

    public static Specification<UccUsers> createTimeLessOrEqualTo(final Date to) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), to);
            }
        };
    }

    public static Specification<UccUsers> deptEqual(final String dept) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.join("depts").get("deptId");
                return criteriaBuilder.equal(path, dept);
            }
        };
    }

//    public static Specification<UccUsers> rolesIn(final List<String> roles) {
//
//        return new Specification<UccUsers>() {
//            @Override
//            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                Path<Object> path = root.join("roles").get("roleId");
//                CriteriaBuilder.In<Object> in = criteriaBuilder.in(path);
//
//                for (int i = 0;i < roles.size(); i++) {
//                    in.value(roles.get(i).toString());
//                }
//
//                return criteriaBuilder.in(in);
//            }
//        };
//    }

    public static Specification<UccUsers> rolesEqual(final String roles) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.join("roles").get("roleId");
                return criteriaBuilder.equal(path, roles);
            }
        };
    }


    public static Specification<UccUsers> extIdEqual(final String extId) {
        return new Specification<UccUsers>() {
            @Override
            public Predicate toPredicate(Root<UccUsers> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> path = root.join("ext").get("extId");
                return criteriaBuilder.equal(path, extId);
            }
        };
    }

    public static Specification<UccUsers> createSpec(final UccUserCriteria criteria) {
        Specification<UccUsers> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getUserName())){
            specs = specs.and(userNameLike(criteria.getUserName()));
        }
        if(!Strings.isNullOrEmpty(criteria.getPassword())){
            specs = specs.and(passwordLike(criteria.getPassword()));
        }

        if(!Strings.isNullOrEmpty(criteria.getMobile())){
            specs = specs.and(mobileEqual(criteria.getMobile()));
        }

        if(!Strings.isNullOrEmpty(criteria.getDomainId())){
            specs = specs.and(domainIdEqual(criteria.getDomainId()));
        }

        if(!Strings.isNullOrEmpty(criteria.getEmail())){
            specs = specs.and(emailLike(criteria.getEmail()));
        }

        if(!Strings.isNullOrEmpty(criteria.getNickName())){
            specs = specs.and(nickNameLike(criteria.getNickName()));
        }

        if(null != criteria.getCreatedTimeFrom()){
            specs = specs.and(createTimeThanOrEqualTo(criteria.getCreatedTimeFrom()));
        }

        if(null != criteria.getCreatedTimeTo()){
            specs = specs.and(createTimeLessOrEqualTo(criteria.getCreatedTimeTo()));
        }

        if(null != criteria.getLastLoginTimeFrom()){
            specs = specs.and(lastLoginTimeThanOrEqualTo(criteria.getLastLoginTimeFrom()));
        }

        if(null != criteria.getLastLoginTimeTo()){
            specs = specs.and(lastLoginTimeLessOrEqualTo(criteria.getLastLoginTimeTo()));
        }

        if(null != criteria.getUccDepts() && criteria.getUccDepts().size() > 0){
            for(int i = 0; i < criteria.getUccDepts().size(); i++){
                if(i==0) {
                    specs = specs.and(deptEqual(criteria.getUccDepts().get(i)));
                }else {
                    specs = specs.or(deptEqual(criteria.getUccDepts().get(i)));
                }
            }
        }

        if(null != criteria.getUserRoles() && criteria.getUserRoles().size() > 0){
            for(int i = 0; i < criteria.getUserRoles().size(); i++){
                if(i==0) {
                    specs = specs.and(rolesEqual(criteria.getUserRoles().get(i)));
                }else{
                    specs = specs.or(rolesEqual(criteria.getUserRoles().get(i)));
                }
            }
        }
//4028bb81657df6e7016584346a740001
        if(null != criteria.getExtId() && criteria.getExtId().size() > 0){
            for(int i = 0; i < criteria.getExtId().size(); i++){
                if(i==0){
                    specs = specs.and(extIdEqual(criteria.getExtId().get(i)));
                }else{
                    specs = specs.or(extIdEqual(criteria.getExtId().get(i)));
                }

            }
        }
        return specs;
    }

    public static Specification<UccUsers> loginCreateSpec(final UccUserCriteria criteria) {
        Specification<UccUsers> spec = null;
        if(criteria==null) return spec;

        Specifications specs = where(spec);

        if(!Strings.isNullOrEmpty(criteria.getUserName())){
            specs = specs.and(userNameEqual(criteria.getUserName()));
        }
        if(!Strings.isNullOrEmpty(criteria.getPassword())){
            specs = specs.and(passwordLike(criteria.getPassword()));
        }
        return specs;
    }
}
