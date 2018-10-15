package cn.cucsi.bsd.ucc.data.specs;

import cn.cucsi.bsd.ucc.common.beans.PbxCdrsCriteria;
import cn.cucsi.bsd.ucc.data.domain.PbxCdrs;
import com.google.common.base.Strings;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
                return criteriaBuilder.like(root.<String>get("firstCaller"), "%"  + firstCaller + "%" );
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
    
    public static Specification<PbxCdrs> cdrIdEqual(final String cdrId) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("cdrId"), cdrId);
            }
        };
    }
    
    public static Specification<PbxCdrs> firstCalleeLike(final String extNum) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.<String>get("firstCallee"),  "%" + extNum + "%");
            }
        };
    }
    public static Specification<PbxCdrs> aftergwEqual(final String aftergw) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("aftergw"),aftergw);
            }
        };
    }
    public static Specification<PbxCdrs> afterivrEqual(final String afterivr) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("afterivr"),afterivr);
            }
        };
    }
    public static Specification<PbxCdrs> afterqueueEqual(final String afterqueue) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("afterqueue"),afterqueue);
            }
        };
    }
    public static Specification<PbxCdrs> hangupCauseEqual(final String hangupCause) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<String>get("hangupCause"),hangupCause);
            }
        };
    }
    public static Specification<PbxCdrs> createTimeThanOrEqualFrom(final Date from) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                    String strDate = sdf.format(from);
                    Date sDate=sdf.parse(strDate);
                    return criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createdTime"), sDate);
                }
                catch(Exception e){
                    e.printStackTrace();
                System.out.println("根据条件查询通话记录列表发生异常！");
                return null;
                }
            }
        };
    }
    
  
    
    public static Specification<PbxCdrs> createTimeLessOrEqualTo(final Date to) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                    String strDate = sdf.format(to);
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(sdf.parse(strDate));
                    calendar.add(calendar.DATE,1);
                    Date sDate=calendar.getTime();
                    return criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createdTime"), sDate);
                }
                catch(Exception e){
                    e.printStackTrace();
                System.out.println("根据条件查询通话记录列表发生异常！");
                return null;
                }
            }
        };
    }
    public static Specification<PbxCdrs> totalTimeThanOrEqualFrom(final Integer from) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Integer>get("totalTime"), from);
            }
        };
    }
    public static Specification<PbxCdrs> totalTimeThanOrEqualTo(final Integer to) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Integer>get("totalTime"), to);
            }
        };
    }
    
    public static Specification<PbxCdrs> callTimeThanOrEqualFrom(final Integer from) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.<Integer>get("callTime"), from);
            }
        };
    }
    public static Specification<PbxCdrs> callTimeThanOrEqualTo(final Integer to) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThanOrEqualTo(root.<Integer>get("callTime"), to);
            }
        };
    }
    public static Specification<PbxCdrs> qcEqual(final Integer qc) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("qc"), qc);
            }
        };
    }
    public static Specification<PbxCdrs> typeEqual(final Integer type) {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Integer>get("type"), type);
            }
        };
    }
    public static Specification<PbxCdrs> recordid1EqualNull() {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isNull(root.<String>get("recordid1"));
            }
        };
    }
    public static Specification<PbxCdrs> recordid1EqualNotNull() {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isNotNull(root.<String>get("recordid1"));
            }
        };
    }
    public static Specification<PbxCdrs> recordid2EqualNull() {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isNull(root.<String>get("recordid2"));
            }
        };
    }
    public static Specification<PbxCdrs> recordid2EqualNotNull() {
        return new Specification<PbxCdrs>() {
            @Override
            public Predicate toPredicate(Root<PbxCdrs> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isNotNull(root.<String>get("recordid2"));
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
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getCdrId())){
            specs = specs.and(cdrIdEqual(PbxCdrsCriteria.getCdrId()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getCdrId())){
            specs = specs.and(cdrIdEqual(PbxCdrsCriteria.getCdrId()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getExtsNum())){
            specs = specs.and(firstCalleeLike(PbxCdrsCriteria.getExtsNum()));
            specs = specs.or(firstCallerLike(PbxCdrsCriteria.getExtsNum()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getAftergwId())){
            specs = specs.and(aftergwEqual(PbxCdrsCriteria.getAftergwId()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getAfterivrId())){
            specs = specs.and(afterivrEqual(PbxCdrsCriteria.getAfterivrId()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getAfterqueueId())){
            specs = specs.and(afterqueueEqual(PbxCdrsCriteria.getAfterqueueId()));
        }
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getHangupCauseEn())){
            specs = specs.and(hangupCauseEqual(PbxCdrsCriteria.getHangupCauseEn()));
        }
        /*
        if(!Strings.isNullOrEmpty(PbxCdrsCriteria.getHangupCauseEn())){
            specs = specs.and(afterqueueEqual(PbxCdrsCriteria.getHangupCauseEn()));
        }
*/
        if(null != PbxCdrsCriteria.getCreatedTimeFrom()){
            specs = specs.and(createTimeThanOrEqualFrom(PbxCdrsCriteria.getCreatedTimeFrom()));
        }
        if(null != PbxCdrsCriteria.getCreateTimeTo()){
            specs = specs.and(createTimeLessOrEqualTo(PbxCdrsCriteria.getCreateTimeTo()));
        }
        if(null != PbxCdrsCriteria.getTotalTimeFrom()){
            specs = specs.and(totalTimeThanOrEqualFrom(PbxCdrsCriteria.getTotalTimeFrom()));
        }
        if(null != PbxCdrsCriteria.getTotalTimeTo()){
            specs = specs.and(totalTimeThanOrEqualTo(PbxCdrsCriteria.getTotalTimeTo()));
        }
        if(null != PbxCdrsCriteria.getCallTimeBegin()){
            specs = specs.and(callTimeThanOrEqualFrom(PbxCdrsCriteria.getCallTimeBegin()));
        }
        if(null != PbxCdrsCriteria.getCallTimeEnd()){
            specs = specs.and(callTimeThanOrEqualTo(PbxCdrsCriteria.getCallTimeEnd()));
        }
        if(null != PbxCdrsCriteria.getSatisfaction()){
            specs = specs.and(qcEqual(PbxCdrsCriteria.getSatisfaction()));
        }
        if(null != PbxCdrsCriteria.getType()){
            specs = specs.and(typeEqual(PbxCdrsCriteria.getType()));
        }
        if(null != PbxCdrsCriteria.getIsRecord1() && PbxCdrsCriteria.getIsRecord1().equals("1"))
        {
            specs = specs.and(recordid1EqualNull());
        }
        else if(null != PbxCdrsCriteria.getIsRecord1() && PbxCdrsCriteria.getIsRecord1().equals("2"))
        {
            specs = specs.and(recordid1EqualNotNull());
        }
        if(null != PbxCdrsCriteria.getIsRecord2() && PbxCdrsCriteria.getIsRecord2().equals("1"))
        {
            specs = specs.and(recordid2EqualNull());
        }
        else if(null != PbxCdrsCriteria.getIsRecord2() && PbxCdrsCriteria.getIsRecord2().equals("2"))
        {
            specs = specs.and(recordid2EqualNotNull());
        }

        return specs;
    }
}
