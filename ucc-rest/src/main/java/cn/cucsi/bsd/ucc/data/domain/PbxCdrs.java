package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import cn.cucsi.bsd.ucc.component.PbxUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "pbx_cdrs", schema = "ucc", catalog = "")
public class PbxCdrs {
    private String cdrId;
    private String cdr;
    private String firstCaller; //主叫
    private String firstCallee;
    private String sipNetworkIp;
    private String callerJobNumber;
    private String calleeJobNumber;
    private String callerIdNumber;
    private String destinationNumber; //被叫
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date answeredTime; //应答时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date hangupTime;//挂机时间
    private Integer totalTime;//总计时长(秒)
    private Integer callTime;//接通时长
    private String hangupCause; //挂机原因
    private String hangupCauseStr;
    private Integer aftergw;
    private Integer afterivr;
    private Integer afterqueue;
    private String recordid1;//通话或者质检
    private String recordid2;//通话或者质检
    private Integer qc;//通话质检
    private Integer type;//类型
    private String trsUserNumber;
    private Integer queueSize;
    private String domainId;
    private String custId;//客户ID add by wangxiaoyu 2018-09-04
    private String custName;//客户姓名(仅用于APP端通话记录展示用) add by wangxiaoyu 2018-09-04

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    @JsonView(JSONView.Summary.class)
    private String createdUserId;
    @JsonView(JSONView.Summary.class)
    private String createdUserName;
    @JsonView(JSONView.Summary.class)
    private String createdNickName;
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;


    
    private Collection<PbxCdrDigit> pbxCdrDigits;
    private PbxRecords pbxRecords1;
    private PbxRecords pbxRecords2;
    private BaseHangupCause baseHangupCause;
    @JsonIgnore
    private UccDomain uccDomain;

    @Id
    @Column(name = "cdr_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getCdrId() {
        return cdrId;
    }

    public void setCdrId(String cdrId) {
        this.cdrId = cdrId;
    }

    @Basic
    @Column(name = "cdr", nullable = false, length = 50)
    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }

    @Basic
    @Column(name = "first_caller", nullable = true, length = 50)
    public String getFirstCaller() {
        return firstCaller;
    }

    public void setFirstCaller(String firstCaller) {
        this.firstCaller = firstCaller;
    }

    @Basic
    @Column(name = "first_callee", nullable = true, length = 50)
    public String getFirstCallee() {
        return firstCallee;
    }

    public void setFirstCallee(String firstCallee) {
        this.firstCallee = firstCallee;
    }

    @Basic
    @Column(name = "sip_network_ip", nullable = true, length = 50)
    public String getSipNetworkIp() {
        return sipNetworkIp;
    }

    public void setSipNetworkIp(String sipNetworkIp) {
        this.sipNetworkIp = sipNetworkIp;
    }

    @Basic
    @Column(name = "caller_job_number", nullable = true, length = 50)
    public String getCallerJobNumber() {
        return callerJobNumber;
    }

    public void setCallerJobNumber(String callerJobNumber) {
        this.callerJobNumber = callerJobNumber;
    }

    @Basic
    @Column(name = "callee_job_number", nullable = true, length = 50)
    public String getCalleeJobNumber() {
        return calleeJobNumber;
    }

    public void setCalleeJobNumber(String calleeJobNumber) {
        this.calleeJobNumber = calleeJobNumber;
    }

    @Basic
    @Column(name = "caller_id_number", nullable = true, length = 50)
    public String getCallerIdNumber() {
        return callerIdNumber;
    }

    public void setCallerIdNumber(String callerIdNumber) {
        this.callerIdNumber = callerIdNumber;
    }

    @Basic
    @Column(name = "destination_number", nullable = true, length = 50)
    public String getDestinationNumber() {
        return destinationNumber;
    }

    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    @Basic
    @Column(name = "answered_time", nullable = false)
    public Date getAnsweredTime() {
        return answeredTime;
    }

    public void setAnsweredTime(Date answeredTime) {
        this.answeredTime = answeredTime;
    }

    @Basic
    @Column(name = "hangup_time", nullable = false)
    public Date getHangupTime() {
        return hangupTime;
    }

    public void setHangupTime(Date hangupTime) {
        this.hangupTime = hangupTime;
    }

    @Basic
    @Column(name = "total_time", nullable = true)
    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    @Basic
    @Column(name = "call_time", nullable = true)
    public Integer getCallTime() {
        return callTime;
    }

    public void setCallTime(Integer callTime) {
        this.callTime = callTime;
    }

    @Basic
    @Column(name = "hangup_cause", nullable = true, length = 50)
    public String getHangupCause() {
        return hangupCause;
    }

    public void setHangupCause(String hangupCause) {
        this.hangupCause = hangupCause == null ? null : hangupCause.trim();
        this.hangupCauseStr = PbxUtils.hangupCause2cn(hangupCause);
        this.hangupCause = hangupCause;
    }

    @Basic
    @Column(name = "aftergw", nullable = true)
    public Integer getAftergw() {
        return aftergw;
    }

    public void setAftergw(Integer aftergw) {
        this.aftergw = aftergw;
    }

    @Basic
    @Column(name = "afterivr", nullable = true)
    public Integer getAfterivr() {
        return afterivr;
    }

    public void setAfterivr(Integer afterivr) {
        this.afterivr = afterivr;
    }

    @Basic
    @Column(name = "afterqueue", nullable = true)
    public Integer getAfterqueue() {
        return afterqueue;
    }

    public void setAfterqueue(Integer afterqueue) {
        this.afterqueue = afterqueue;
    }

    @Basic
    @Column(name = "recordid1", nullable = true, length = 50)
    public String getRecordid1() {
        return recordid1;
    }

    public void setRecordid1(String recordid1) {
        this.recordid1 = recordid1;
    }

    @Basic
    @Column(name = "recordid2", nullable = true, length = 50)
    public String getRecordid2() {
        return recordid2;
    }

    public void setRecordid2(String recordid2) {
        this.recordid2 = recordid2;
    }

    @Basic
    @Column(name = "qc", nullable = true)
    public Integer getQc() {
        return qc;
    }

    public void setQc(Integer qc) {
        this.qc = qc;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "trs_user_number", nullable = true, length = 16)
    public String getTrsUserNumber() {
        return trsUserNumber;
    }

    public void setTrsUserNumber(String trsUserNumber) {
        this.trsUserNumber = trsUserNumber;
    }

    @Basic
    @Column(name = "queue_size", nullable = true)
    public Integer getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    @Basic
    @Column(name = "domain_id", nullable = false, length = 32)
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PbxCdrs pbxCdrs = (PbxCdrs) o;

        if (cdrId != null ? !cdrId.equals(pbxCdrs.cdrId) : pbxCdrs.cdrId != null) return false;
        if (cdr != null ? !cdr.equals(pbxCdrs.cdr) : pbxCdrs.cdr != null) return false;
        if (firstCaller != null ? !firstCaller.equals(pbxCdrs.firstCaller) : pbxCdrs.firstCaller != null) return false;
        if (firstCallee != null ? !firstCallee.equals(pbxCdrs.firstCallee) : pbxCdrs.firstCallee != null) return false;
        if (sipNetworkIp != null ? !sipNetworkIp.equals(pbxCdrs.sipNetworkIp) : pbxCdrs.sipNetworkIp != null)
            return false;
        if (callerJobNumber != null ? !callerJobNumber.equals(pbxCdrs.callerJobNumber) : pbxCdrs.callerJobNumber != null)
            return false;
        if (calleeJobNumber != null ? !calleeJobNumber.equals(pbxCdrs.calleeJobNumber) : pbxCdrs.calleeJobNumber != null)
            return false;
        if (callerIdNumber != null ? !callerIdNumber.equals(pbxCdrs.callerIdNumber) : pbxCdrs.callerIdNumber != null)
            return false;
        if (destinationNumber != null ? !destinationNumber.equals(pbxCdrs.destinationNumber) : pbxCdrs.destinationNumber != null)
            return false;
        if (createdTime != null ? !createdTime.equals(pbxCdrs.createdTime) : pbxCdrs.createdTime != null) return false;
        if (answeredTime != null ? !answeredTime.equals(pbxCdrs.answeredTime) : pbxCdrs.answeredTime != null)
            return false;
        if (hangupTime != null ? !hangupTime.equals(pbxCdrs.hangupTime) : pbxCdrs.hangupTime != null) return false;
        if (totalTime != null ? !totalTime.equals(pbxCdrs.totalTime) : pbxCdrs.totalTime != null) return false;
        if (callTime != null ? !callTime.equals(pbxCdrs.callTime) : pbxCdrs.callTime != null) return false;
        if (hangupCause != null ? !hangupCause.equals(pbxCdrs.hangupCause) : pbxCdrs.hangupCause != null) return false;
        if (aftergw != null ? !aftergw.equals(pbxCdrs.aftergw) : pbxCdrs.aftergw != null) return false;
        if (afterivr != null ? !afterivr.equals(pbxCdrs.afterivr) : pbxCdrs.afterivr != null) return false;
        if (afterqueue != null ? !afterqueue.equals(pbxCdrs.afterqueue) : pbxCdrs.afterqueue != null) return false;
        if (recordid1 != null ? !recordid1.equals(pbxCdrs.recordid1) : pbxCdrs.recordid1 != null) return false;
        if (recordid2 != null ? !recordid2.equals(pbxCdrs.recordid2) : pbxCdrs.recordid2 != null) return false;
        if (qc != null ? !qc.equals(pbxCdrs.qc) : pbxCdrs.qc != null) return false;
        if (type != null ? !type.equals(pbxCdrs.type) : pbxCdrs.type != null) return false;
        if (trsUserNumber != null ? !trsUserNumber.equals(pbxCdrs.trsUserNumber) : pbxCdrs.trsUserNumber != null)
            return false;
        if (queueSize != null ? !queueSize.equals(pbxCdrs.queueSize) : pbxCdrs.queueSize != null) return false;
        if (domainId != null ? !domainId.equals(pbxCdrs.domainId) : pbxCdrs.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cdrId != null ? cdrId.hashCode() : 0;
        result = 31 * result + (cdr != null ? cdr.hashCode() : 0);
        result = 31 * result + (firstCaller != null ? firstCaller.hashCode() : 0);
        result = 31 * result + (firstCallee != null ? firstCallee.hashCode() : 0);
        result = 31 * result + (sipNetworkIp != null ? sipNetworkIp.hashCode() : 0);
        result = 31 * result + (callerJobNumber != null ? callerJobNumber.hashCode() : 0);
        result = 31 * result + (calleeJobNumber != null ? calleeJobNumber.hashCode() : 0);
        result = 31 * result + (callerIdNumber != null ? callerIdNumber.hashCode() : 0);
        result = 31 * result + (destinationNumber != null ? destinationNumber.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (answeredTime != null ? answeredTime.hashCode() : 0);
        result = 31 * result + (hangupTime != null ? hangupTime.hashCode() : 0);
        result = 31 * result + (totalTime != null ? totalTime.hashCode() : 0);
        result = 31 * result + (callTime != null ? callTime.hashCode() : 0);
        result = 31 * result + (hangupCause != null ? hangupCause.hashCode() : 0);
        result = 31 * result + (aftergw != null ? aftergw.hashCode() : 0);
        result = 31 * result + (afterivr != null ? afterivr.hashCode() : 0);
        result = 31 * result + (afterqueue != null ? afterqueue.hashCode() : 0);
        result = 31 * result + (recordid1 != null ? recordid1.hashCode() : 0);
        result = 31 * result + (recordid2 != null ? recordid2.hashCode() : 0);
        result = 31 * result + (qc != null ? qc.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (trsUserNumber != null ? trsUserNumber.hashCode() : 0);
        result = 31 * result + (queueSize != null ? queueSize.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pbxCdr")
    public Collection<PbxCdrDigit> getPbxCdrDigits() {
        return pbxCdrDigits;
    }

    public void setPbxCdrDigits(Collection<PbxCdrDigit> pbxCdrDigits) {
        this.pbxCdrDigits = pbxCdrDigits;
    }
    
    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }
    @ManyToOne
    @JoinColumn(name = "recordid2", referencedColumnName = "record_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.PbxRecords getPbxRecords2() {
        return pbxRecords2;
    }

    public void setPbxRecords2(cn.cucsi.bsd.ucc.data.domain.PbxRecords pbxRecords2) {
        this.pbxRecords2 = pbxRecords2;
    }
    @ManyToOne
    @JoinColumn(name = "recordid1", referencedColumnName = "record_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.PbxRecords getPbxRecords1() {
        return pbxRecords1;
    }

    public void setPbxRecords1(cn.cucsi.bsd.ucc.data.domain.PbxRecords pbxRecords1) {
        this.pbxRecords1 = pbxRecords1;
    }
    @ManyToOne
    @JoinColumn(name = "hangup_cause", referencedColumnName = "cause_en", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.BaseHangupCause getBaseHangupCause() {
        return baseHangupCause;
    }

    public void setBaseHangupCause(cn.cucsi.bsd.ucc.data.domain.BaseHangupCause baseHangupCause) {
        this.baseHangupCause = baseHangupCause;
    }
    

    @Basic
    @Column(nullable = true)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(nullable = true)
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    @Basic
    @Column(nullable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Transient
    public String getHangupCauseStr() {
        return hangupCauseStr;
    }

    public void setHangupCauseStr(String hangupCauseStr) {
        this.hangupCauseStr = hangupCauseStr;
    }

    //------------------------
    @Basic
    @Column(name = "created_user_id", nullable = true, length = 32)
    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
    @Basic
    @Column(name = "created_user_name", nullable = true, length = 32)
    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }
    @Basic
    @Column(name = "created_nick_name", nullable = true)
    public String getCreatedNickName() {
        return createdNickName;
    }

    public void setCreatedNickName(String createdNickName) {
        this.createdNickName = createdNickName;
    }
    @Basic
    @Column(name = "updated_user_id", nullable = true, length = 32)
    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
    @Basic
    @Column(name = "updated_user_name", nullable = true, length = 32)
    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }
    @Basic
    @Column(name = "updated_nick_name", nullable = true)
    public String getUpdatedNickName() {
        return updatedNickName;
    }

    public void setUpdatedNickName(String updatedNickName) {
        this.updatedNickName = updatedNickName;
    }


    //----------------------

    @Basic
    @Column(name = "cust_id", nullable = true, length = 32)
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }
}
