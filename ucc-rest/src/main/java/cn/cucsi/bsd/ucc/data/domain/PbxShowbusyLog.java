package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
/*
*示忙记录
* */
@Entity
@Table(name = "pbx_showbusy_log", schema = "ucc", catalog = "")
public class PbxShowbusyLog {
    private String showbusyLogId;//示忙主键
    private Integer type;//示忙类型
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date busyTime;//示忙时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date cancelBusyTime;//取消示忙时间
    private Time dif;//dif
    private String userId;//用户ID
    private String operatorId;//操作员ID
    private String extId;//分机ID
    private String domainId;//域ID
    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    @JsonView(JSONView.Summary.class)
    private String createdUserId;//创建人ID
    @JsonView(JSONView.Summary.class)
    private String createdUserName;//创建人姓名
    @JsonView(JSONView.Summary.class)
    private String createdNickName;//创建人昵称
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;//修改人ID
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;//修改人姓名
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;//修改人昵称

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;//创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;//修改时间
    @JsonIgnore
    private UccUsers uccUser;//
    @JsonIgnore
    private UccUsers uccOperator;//
    @JsonIgnore
    private PbxExts pbxExt;//
    @JsonIgnore
    private UccDomain uccDomain;//

    @Id
    @Column(name = "showbusy_log_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getShowbusyLogId() {
        return showbusyLogId;
    }

    public void setShowbusyLogId(String showbusyLogId) {
        this.showbusyLogId = showbusyLogId;
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
    @Column(name = "busy_time", nullable = false)
    public Date getBusyTime() {
        return busyTime;
    }

    public void setBusyTime(Date busyTime) {
        this.busyTime = busyTime;
    }

    @Basic
    @Column(name = "cancel_busy_time", nullable = false)
    public Date getCancelBusyTime() {
        return cancelBusyTime;
    }

    public void setCancelBusyTime(Date cancelBusyTime) {
        this.cancelBusyTime = cancelBusyTime;
    }

    @Basic
    @Column(name = "dif", nullable = true)
    public Time getDif() {
        return dif;
    }

    public void setDif(Time dif) {
        this.dif = dif;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "operator_id", nullable = false, length = 32)
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    @Basic
    @Column(name = "ext_id", nullable = false, length = 32)
    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
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

        PbxShowbusyLog that = (PbxShowbusyLog) o;

        if (showbusyLogId != null ? !showbusyLogId.equals(that.showbusyLogId) : that.showbusyLogId != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (busyTime != null ? !busyTime.equals(that.busyTime) : that.busyTime != null) return false;
        if (cancelBusyTime != null ? !cancelBusyTime.equals(that.cancelBusyTime) : that.cancelBusyTime != null)
            return false;
        if (dif != null ? !dif.equals(that.dif) : that.dif != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (operatorId != null ? !operatorId.equals(that.operatorId) : that.operatorId != null) return false;
        if (extId != null ? !extId.equals(that.extId) : that.extId != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = showbusyLogId != null ? showbusyLogId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (busyTime != null ? busyTime.hashCode() : 0);
        result = 31 * result + (cancelBusyTime != null ? cancelBusyTime.hashCode() : 0);
        result = 31 * result + (dif != null ? dif.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (operatorId != null ? operatorId.hashCode() : 0);
        result = 31 * result + (extId != null ? extId.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccUsers getUccUser() {
        return uccUser;
    }

    public void setUccUser(cn.cucsi.bsd.ucc.data.domain.UccUsers uccUser) {
        this.uccUser = uccUser;
    }

    @ManyToOne
    @JoinColumn(name = "operator_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccUsers getUccOperator() {
        return uccOperator;
    }

    public void setUccOperator(cn.cucsi.bsd.ucc.data.domain.UccUsers uccOperator) {
        this.uccOperator = uccOperator;
    }

    @ManyToOne
    @JoinColumn(name = "ext_id", referencedColumnName = "ext_id", nullable = false, updatable = false, insertable = false)
    public PbxExts getPbxExt() {
        return pbxExt;
    }

    public void setPbxExt(PbxExts pbxExt) {
        this.pbxExt = pbxExt;
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
}
