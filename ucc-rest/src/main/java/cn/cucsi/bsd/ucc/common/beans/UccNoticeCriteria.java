package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by tianyuwei on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel
public class UccNoticeCriteria extends BasicPageCriteriaForBean {
    private String noticeCode;
    private String noticeTitle;
    private String noticeType;
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date noticeTimeTo;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public void setNoticeTimeTo(Date noticeTimeTo) {
        this.noticeTimeTo = noticeTimeTo;
    }

    public void setNoticeTimeFrom(Date noticeTimeFrom) {
        this.noticeTimeFrom = noticeTimeFrom;
    }
    

    public String getNoticeType() {
        return noticeType;
    }

    public String getUserId() {
        return userId;
    }

    public Date getNoticeTimeTo() {
        return noticeTimeTo;
    }

    public Date getNoticeTimeFrom() {
        return noticeTimeFrom;
    }
    
    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
}
