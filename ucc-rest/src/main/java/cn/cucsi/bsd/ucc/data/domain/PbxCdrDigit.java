package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pbx_cdr_digit", schema = "ucc", catalog = "")
public class PbxCdrDigit {
    private String digits;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date digitTime;
    private String cdrDigitId;
    private String cdrId;
    private String ivrId;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    @JsonIgnore
    private PbxCdrs pbxCdr;
    @JsonIgnore
    private PbxIvrs pbxIvr;

    @Basic
    @Column(name = "digits", nullable = true, length = 50)
    public String getDigits() {
        return digits;
    }

    public void setDigits(String digits) {
        this.digits = digits;
    }

    @Basic
    @Column(name = "digit_time", nullable = false)
    public Date getDigitTime() {
        return digitTime;
    }

    public void setDigitTime(Date digitTime) {
        this.digitTime = digitTime;
    }

    @Id
    @Column(name = "cdr_digit_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getCdrDigitId() {
        return cdrDigitId;
    }

    public void setCdrDigitId(String cdrDigitId) {
        this.cdrDigitId = cdrDigitId;
    }

    @Basic
    @Column(name = "cdr_id", nullable = true, length = 32)
    public String getCdrId() {
        return cdrId;
    }

    public void setCdrId(String cdrId) {
        this.cdrId = cdrId;
    }

    @Basic
    @Column(name = "ivr_id", nullable = false, length = 32)
    public String getIvrId() {
        return ivrId;
    }

    public void setIvrId(String ivrId) {
        this.ivrId = ivrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PbxCdrDigit that = (PbxCdrDigit) o;

        if (digits != null ? !digits.equals(that.digits) : that.digits != null) return false;
        if (digitTime != null ? !digitTime.equals(that.digitTime) : that.digitTime != null) return false;
        if (cdrDigitId != null ? !cdrDigitId.equals(that.cdrDigitId) : that.cdrDigitId != null) return false;
        if (cdrId != null ? !cdrId.equals(that.cdrId) : that.cdrId != null) return false;
        if (ivrId != null ? !ivrId.equals(that.ivrId) : that.ivrId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = digits != null ? digits.hashCode() : 0;
        result = 31 * result + (digitTime != null ? digitTime.hashCode() : 0);
        result = 31 * result + (cdrDigitId != null ? cdrDigitId.hashCode() : 0);
        result = 31 * result + (cdrId != null ? cdrId.hashCode() : 0);
        result = 31 * result + (ivrId != null ? ivrId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cdr_id", referencedColumnName = "cdr_id", updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.PbxCdrs getPbxCdr() {
        return pbxCdr;
    }

    public void setPbxCdr(cn.cucsi.bsd.ucc.data.domain.PbxCdrs pbxCdr) {
        this.pbxCdr = pbxCdr;
    }

    @ManyToOne
    @JoinColumn(name = "ivr_id", referencedColumnName = "ivr_id", nullable = false, updatable = false, insertable = false)
    public PbxIvrs getPbxIvr() {
        return pbxIvr;
    }

    public void setPbxIvr(PbxIvrs pbxIvr) {
        this.pbxIvr = pbxIvr;
    }

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
}
