package cn.cucsi.bsd.ucc.common.beans;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Song on 2017/10/16.
 */


public class PbxCdrsCriteria extends BasicCriteria{
    private String firstCaller;
    
    private String callerJobNumber;

    private String cdrId;	        
    private String extsNum;

    private String aftergwId;

    private String afterivrId;

    private String afterqueueId;

    private String hangupCauseEn;

    private String isRecord1;

    private String isRecord2;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeTo;

    private Integer totalTimeFrom;

    private Integer totalTimeTo;

    private Integer callTimeBegin;

    private Integer callTimeEnd;

    private Integer satisfaction;//满意度qc

    private Integer type;
    
   
    
    public void setfirstCaller(String firstCaller) {
        this.firstCaller = firstCaller;
    }

    public String getfirstCaller() {
        return firstCaller;
    }
    public void setcallerJobNumber(String callerJobNumber) {
        this.callerJobNumber = callerJobNumber;
    }

    public String getcallerJobNumber() {
        return callerJobNumber;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public void setHangupCauseEn(String hangupCauseEn) {
        this.hangupCauseEn = hangupCauseEn;
    }

    public void setIsRecord1(String isRecord1) {
        this.isRecord1 = isRecord1;
    }

    public void setIsRecord2(String isRecord2) {
        this.isRecord2 = isRecord2;
    }

    public void setExtsNum(String extsNum) {
        this.extsNum = extsNum;
    }

    public void setSatisfaction(Integer satisfaction) {
        this.satisfaction = satisfaction;
    }

    public void setTotalTimeFrom(Integer totalTimeFrom) {
        this.totalTimeFrom = totalTimeFrom;
    }

    public void setTotalTimeTo(Integer totalTimeTo) {
        this.totalTimeTo = totalTimeTo;
    }

    public void setCreateTimeTo(Date createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
    }

    public void setCreateTimeFrom(Date createTimeFrom) {
        this.createdTimeFrom = createTimeFrom;
    }

    public void setCdrId(String cdrId) {
        this.cdrId = cdrId;
    }

    public void setCallTimeTo(Integer callTimeEnd) {
        this.callTimeEnd = callTimeEnd;
    }

    public void setAfterqueueId(String afterqueueId) {
        this.afterqueueId = afterqueueId;
    }

    public void setCallTimeFrom(Integer callTimeBegin) {
        this.callTimeBegin = callTimeBegin;
    }

    public void setAfterivrId(String afterivrId) {
        this.afterivrId = afterivrId;
    }

    public void setAftergwId(String aftergwId) {
        this.aftergwId = aftergwId;
    }

    public Integer getType() {
        return type;
    }

    public Integer getTotalTimeTo() {
        return totalTimeTo;
    }

    public Integer getTotalTimeFrom() {
        return totalTimeFrom;
    }

    public Integer getSatisfaction() {
        return satisfaction;
    }

    public String getExtsNum() {
        return extsNum;
    }

    public String getIsRecord2() {
        return isRecord2;
    }

    public String getIsRecord1() {
        return isRecord1;
    }

    public String getHangupCauseEn() {
        return hangupCauseEn;
    }

    public Date getCreateTimeTo() {
        return createdTimeTo;
    }

    public String getCdrId() {
        return cdrId;
    }
    

    public Date getCreateTimeFrom() {
        return createdTimeFrom;
    }

    public Integer getCallTimeTo() {
        return callTimeEnd;
    }

    public String getAfterqueueId() {
        return afterqueueId;
    }

    public Integer getCallTimeFrom() {
        return callTimeBegin;
    }

    public String getAfterivrId() {
        return afterivrId;
    }

    public String getAftergwId() {
        return aftergwId;
    }
}
