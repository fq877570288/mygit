package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "ucc_notice", schema = "ucc", catalog = "")
public class UccNotice {
    private String noticeId; //公告主键
    private String noticeCode; //公告编码
    private String noticeTitle; //公告标题
    private String noticeContent; //公告内容
    private String noticeType; //公告类型(0:公告 1：通知)add by wangxiaoyu
    private String releaseTime; //发布时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate; //公告有效期开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate; //公告有效期结束日期
    private String noticeStatus; //公告状态
    private String memo; //备注
    private String userId;
    private String domainId;
    private String flag;//已读标识(未读：0,已读：1)

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
    private UccUsers uccUser;
    @JsonIgnore
    private UccDomain uccDomain;
    @JsonIgnore
    private Collection<UccNoticeFile> uccNoticeFiles;
    @JsonIgnore
    private Collection<UccNoticeTrace> uccNoticeTraces;

    @Id
    @Column(name = "notice_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @Basic
    @Column(name = "notice_code", nullable = false, length = 50)
    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    @Basic
    @Column(name = "notice_title", nullable = true, length = 100)
    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    @Basic
    @Column(name = "notice_content", nullable = true, length = 2000)
    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    @Basic
    @Column(name = "notice_type", nullable = true, length = 50)
    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    @Basic
    @Column(name = "release_time", nullable = true, length = 19)
    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "notice_status", nullable = true, length = 1)
    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 1000)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
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

        UccNotice uccNotice = (UccNotice) o;

        if (noticeId != null ? !noticeId.equals(uccNotice.noticeId) : uccNotice.noticeId != null) return false;
        if (noticeCode != null ? !noticeCode.equals(uccNotice.noticeCode) : uccNotice.noticeCode != null) return false;
        if (noticeTitle != null ? !noticeTitle.equals(uccNotice.noticeTitle) : uccNotice.noticeTitle != null)
            return false;
        if (noticeContent != null ? !noticeContent.equals(uccNotice.noticeContent) : uccNotice.noticeContent != null)
            return false;
        if (noticeType != null ? !noticeType.equals(uccNotice.noticeType) : uccNotice.noticeType != null) return false;
        if (releaseTime != null ? !releaseTime.equals(uccNotice.releaseTime) : uccNotice.releaseTime != null)
            return false;
        if (startDate != null ? !startDate.equals(uccNotice.startDate) : uccNotice.startDate != null) return false;
        if (endDate != null ? !endDate.equals(uccNotice.endDate) : uccNotice.endDate != null) return false;
        if (noticeStatus != null ? !noticeStatus.equals(uccNotice.noticeStatus) : uccNotice.noticeStatus != null)
            return false;
        if (memo != null ? !memo.equals(uccNotice.memo) : uccNotice.memo != null) return false;
        if (userId != null ? !userId.equals(uccNotice.userId) : uccNotice.userId != null) return false;
        if (domainId != null ? !domainId.equals(uccNotice.domainId) : uccNotice.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noticeId != null ? noticeId.hashCode() : 0;
        result = 31 * result + (noticeCode != null ? noticeCode.hashCode() : 0);
        result = 31 * result + (noticeTitle != null ? noticeTitle.hashCode() : 0);
        result = 31 * result + (noticeContent != null ? noticeContent.hashCode() : 0);
        result = 31 * result + (noticeType != null ? noticeType.hashCode() : 0);
        result = 31 * result + (releaseTime != null ? releaseTime.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (noticeStatus != null ? noticeStatus.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public UccUsers getUccUser() {
        return uccUser;
    }

    public void setUccUser(UccUsers uccUser) {
        this.uccUser = uccUser;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }

    @OneToMany(mappedBy = "uccNotice")
    public Collection<UccNoticeFile> getUccNoticeFiles() {
        return uccNoticeFiles;
    }

    public void setUccNoticeFiles(Collection<UccNoticeFile> uccNoticeFiles) {
        this.uccNoticeFiles = uccNoticeFiles;
    }

    @OneToMany(mappedBy = "uccNotice")
    public Collection<UccNoticeTrace> getUccNoticeTraces() {
        return uccNoticeTraces;
    }

    public void setUccNoticeTraces(Collection<UccNoticeTrace> uccNoticeTraces) {
        this.uccNoticeTraces = uccNoticeTraces;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
