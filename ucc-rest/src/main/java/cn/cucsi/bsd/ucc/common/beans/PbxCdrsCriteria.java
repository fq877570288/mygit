package cn.cucsi.bsd.ucc.common.beans;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by Song on 2017/10/16.
 */


public class PbxCdrsCriteria extends BasicPageCriteria{
    private String firstCaller;
    
    private String callerJobNumber;

    private String cdrId;	        
    private String number;

    private String aftergwId;

    private String afterivrId;

    private String afterqueueId;

    private String hangupCauseEn;

    private Integer isRecord1;

    private Integer isRecord2;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimeTo;

    private Integer totalTimeFrom;

    private Integer totalTimeTo;

    private Integer callTimeFrom;

    private Integer callTimeTo;

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

    public void setIsRecord1(Integer isRecord1) {
        this.isRecord1 = isRecord1;
    }

    public void setIsRecord2(Integer isRecord2) {
        this.isRecord2 = isRecord2;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public void setCreateTimeTo(Date createTimeTo) {
        this.createTimeTo = createTimeTo;
    }

    public void setCreateTimeFrom(Date createTimeFrom) {
        this.createTimeFrom = createTimeFrom;
    }

    public void setCdrId(String cdrId) {
        this.cdrId = cdrId;
    }

    public void setCallTimeTo(Integer callTimeTo) {
        this.callTimeTo = callTimeTo;
    }

    public void setAfterqueueId(String afterqueueId) {
        this.afterqueueId = afterqueueId;
    }

    public void setCallTimeFrom(Integer callTimeFrom) {
        this.callTimeFrom = callTimeFrom;
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

    public String getNumber() {
        return number;
    }

    public Integer getIsRecord2() {
        return isRecord2;
    }

    public Integer getIsRecord1() {
        return isRecord1;
    }

    public String getHangupCauseEn() {
        return hangupCauseEn;
    }

    public Date getCreateTimeTo() {
        return createTimeTo;
    }

    public String getCdrId() {
        return cdrId;
    }
    

    public Date getCreateTimeFrom() {
        return createTimeFrom;
    }

    public Integer getCallTimeTo() {
        return callTimeTo;
    }

    public String getAfterqueueId() {
        return afterqueueId;
    }

    public Integer getCallTimeFrom() {
        return callTimeFrom;
    }

    public String getAfterivrId() {
        return afterivrId;
    }

    public String getAftergwId() {
        return aftergwId;
    }
}
