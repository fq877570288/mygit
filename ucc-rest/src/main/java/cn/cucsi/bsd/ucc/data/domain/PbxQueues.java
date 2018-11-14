package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "pbx_queues", schema = "ucc", catalog = "")
public class PbxQueues {
    private String queueId;
    private String queueName;
    private Integer type;
    private Integer status;
    private String memo;
    private String domainId;
    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    private String createdUserId;
    private String createdUserName;
    private String createdNickName;

    private String updatedUserId;
    private String updatedUserName;
    private String updatedNickName;

    private String[] extGroupExts;
    private String[] numbers;

    private String exts;

    @Transient
    public String[] getExtGroupExts() {
        return extGroupExts;
    }

    public void setExtGroupExts(String[] extGroupExts) {
        this.extGroupExts = extGroupExts;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private Collection<PbxQueueNumbers> pbxQueueNumbers;
    @JsonIgnore
    private UccDomain uccDomain;


    @Id
    @Column(name = "queue_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    @Basic
    @Column(name = "queue_name", nullable = true, length = 50)
    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
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

        PbxQueues pbxQueues = (PbxQueues) o;

        if (queueId != null ? !queueId.equals(pbxQueues.queueId) : pbxQueues.queueId != null) return false;
        if (queueName != null ? !queueName.equals(pbxQueues.queueName) : pbxQueues.queueName != null) return false;
        if (type != null ? !type.equals(pbxQueues.type) : pbxQueues.type != null) return false;
        if (status != null ? !status.equals(pbxQueues.status) : pbxQueues.status != null) return false;
        if (memo != null ? !memo.equals(pbxQueues.memo) : pbxQueues.memo != null) return false;
        if (domainId != null ? !domainId.equals(pbxQueues.domainId) : pbxQueues.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queueId != null ? queueId.hashCode() : 0;
        result = 31 * result + (queueName != null ? queueName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pbxQueue")
    public Collection<PbxQueueNumbers> getPbxQueueNumbers() {
        return pbxQueueNumbers;
    }

    public void setPbxQueueNumbers(Collection<PbxQueueNumbers> pbxQueueNumbers) {
        this.pbxQueueNumbers = pbxQueueNumbers;
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

    public String getExts() {
        return exts;
    }

    public void setExts(String exts) {
        this.exts = exts;
    }

    @Transient
    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }
}
