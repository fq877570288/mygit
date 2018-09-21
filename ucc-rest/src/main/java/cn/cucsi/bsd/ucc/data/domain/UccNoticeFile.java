package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ucc_notice_file", schema = "ucc", catalog = "")
public class UccNoticeFile {
    private String noticeFileId;
    private String fileName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    private Integer fileOrder;
    private String noticeId;
    private String contentType;
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
    private UccNotice uccNotice;

    @Id
    @Column(name = "notice_file_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getNoticeFileId() {
        return noticeFileId;
    }

    public void setNoticeFileId(String noticeFileId) {
        this.noticeFileId = noticeFileId;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = 45)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "upload_time", nullable = true)
    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Basic
    @Column(name = "file_order", nullable = true)
    public Integer getFileOrder() {
        return fileOrder;
    }

    public void setFileOrder(Integer fileOrder) {
        this.fileOrder = fileOrder;
    }

    @Basic
    @Column(name = "notice_id", nullable = true, length = 32)
    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UccNoticeFile that = (UccNoticeFile) o;

        if (noticeFileId != null ? !noticeFileId.equals(that.noticeFileId) : that.noticeFileId != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (uploadTime != null ? !uploadTime.equals(that.uploadTime) : that.uploadTime != null) return false;
        if (fileOrder != null ? !fileOrder.equals(that.fileOrder) : that.fileOrder != null) return false;
        if (noticeId != null ? !noticeId.equals(that.noticeId) : that.noticeId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noticeFileId != null ? noticeFileId.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (uploadTime != null ? uploadTime.hashCode() : 0);
        result = 31 * result + (fileOrder != null ? fileOrder.hashCode() : 0);
        result = 31 * result + (noticeId != null ? noticeId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "notice_id", referencedColumnName = "notice_id", updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccNotice getUccNotice() {
        return uccNotice;
    }

    public void setUccNotice(cn.cucsi.bsd.ucc.data.domain.UccNotice uccNotice) {
        this.uccNotice = uccNotice;
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
    @Basic
    @Column(name = "content_type", nullable = true, length = 50)
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
