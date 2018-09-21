package cn.cucsi.bsd.ucc.common.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by tianyuwei on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccNoticeFileCriteria extends BasicCriteria {
    String fileName;
    String noticeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date uploadTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date uploadTimeTo;

    public String getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getUploadTimeFrom() {
        return uploadTimeFrom;
    }

    public void setUploadTimeFrom(Date uploadTimeFrom) {
        this.uploadTimeFrom = uploadTimeFrom;
    }

    public Date getUploadTimeTo() {
        return uploadTimeTo;
    }

    public void setUploadTimeTo(Date uploadTimeTo) {
        this.uploadTimeTo = uploadTimeTo;
    }
}
