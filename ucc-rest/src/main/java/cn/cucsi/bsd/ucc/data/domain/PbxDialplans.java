package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "pbx_dialplans", schema = "ucc", catalog = "")
public class PbxDialplans {
    private String dialplanId;
    private String phoneNumber;
    private Integer type;
    private Integer mode;
    private String destination;
    private Integer status;
    private String memo;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time time1;
    @JsonFormat(pattern = "HH:mm:ss")
    private Time time2;
    private Integer mode2;
    private String destination2;
    private String extGroupId;
    private String outPattern;
    private String domainId;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private UccDomain uccDomain;


    private String destinationForMode; // add zhangyu 前台界面显示的模式

    @Transient
    public String getDestinationForMode() {
        return destinationForMode;
    }

    public void setDestinationForMode(String destinationForMode) {
        this.destinationForMode = destinationForMode;
    }

    @Id
    @Column(name = "dialplan_id", nullable = false, length = 80)
    public String getDialplanId() {
        return dialplanId;
    }

    public void setDialplanId(String dialplanId) {
        this.dialplanId = dialplanId;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 32)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    @Column(name = "mode", nullable = true)
    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    @Basic
    @Column(name = "destination", nullable = true, length = 255)
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 255)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "time1", nullable = true)
    public Time getTime1() {
        return time1;
    }

    public void setTime1(Time time1) {
        this.time1 = time1;
    }

    @Basic
    @Column(name = "time2", nullable = true)
    public Time getTime2() {
        return time2;
    }

    public void setTime2(Time time2) {
        this.time2 = time2;
    }

    @Basic
    @Column(name = "mode2", nullable = true)
    public Integer getMode2() {
        return mode2;
    }

    public void setMode2(Integer mode2) {
        this.mode2 = mode2;
    }

    @Basic
    @Column(name = "destination2", nullable = true, length = 255)
    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    @Basic
    @Column(name = "ext_group_id", nullable = true)
    public String getExtGroupId() {
        return extGroupId;
    }

    public void setExtGroupId(String extGroupId) {
        this.extGroupId = extGroupId;
    }

    @Basic
    @Column(name = "out_pattern", nullable = true, length = 100)
    public String getOutPattern() {
        return outPattern;
    }

    public void setOutPattern(String outPattern) {
        this.outPattern = outPattern;
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

        PbxDialplans that = (PbxDialplans) o;

        if (dialplanId != null ? !dialplanId.equals(that.dialplanId) : that.dialplanId != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (mode != null ? !mode.equals(that.mode) : that.mode != null) return false;
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (time1 != null ? !time1.equals(that.time1) : that.time1 != null) return false;
        if (time2 != null ? !time2.equals(that.time2) : that.time2 != null) return false;
        if (mode2 != null ? !mode2.equals(that.mode2) : that.mode2 != null) return false;
        if (destination2 != null ? !destination2.equals(that.destination2) : that.destination2 != null) return false;
        if (extGroupId != null ? !extGroupId.equals(that.extGroupId) : that.extGroupId != null) return false;
        if (outPattern != null ? !outPattern.equals(that.outPattern) : that.outPattern != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dialplanId != null ? dialplanId.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (time1 != null ? time1.hashCode() : 0);
        result = 31 * result + (time2 != null ? time2.hashCode() : 0);
        result = 31 * result + (mode2 != null ? mode2.hashCode() : 0);
        result = 31 * result + (destination2 != null ? destination2.hashCode() : 0);
        result = 31 * result + (extGroupId != null ? extGroupId.hashCode() : 0);
        result = 31 * result + (outPattern != null ? outPattern.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
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

    @Override
    public String toString() {
        return "PbxDialplans{" +
                "dialplanId='" + dialplanId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", type=" + type +
                ", mode=" + mode +
                ", destination='" + destination + '\'' +
                ", status=" + status +
                ", memo='" + memo + '\'' +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", mode2=" + mode2 +
                ", destination2='" + destination2 + '\'' +
                ", extGroupId='" + extGroupId + '\'' +
                ", outPattern='" + outPattern + '\'' +
                ", domainId='" + domainId + '\'' +
                ", createdUserId='" + createdUserId + '\'' +
                ", createdUserName='" + createdUserName + '\'' +
                ", createdNickName='" + createdNickName + '\'' +
                ", updatedUserId='" + updatedUserId + '\'' +
                ", updatedUserName='" + updatedUserName + '\'' +
                ", updatedNickName='" + updatedNickName + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", uccDomain=" + uccDomain +
                ", destinationForMode='" + destinationForMode + '\'' +
                '}';
    }

}
