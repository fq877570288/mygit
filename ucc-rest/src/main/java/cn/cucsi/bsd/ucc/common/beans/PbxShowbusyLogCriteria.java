package cn.cucsi.bsd.ucc.common.beans;

import java.sql.Time;
import java.util.Date;

/**
 * Created by jjjjj on 2017-10-16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxShowbusyLogCriteria  extends BasicCriteria   {
    private Integer type;
    private Date busyTime;
    private Date cancelBusyTime;
    private Time dif;
    private String userId;
    private String operatorId;
    private String extId;
    private String domainId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getBusyTime() {
        return busyTime;
    }

    public void setBusyTime(Date busyTime) {
        this.busyTime = busyTime;
    }

    public Date getCancelBusyTime() {
        return cancelBusyTime;
    }

    public void setCancelBusyTime(Date cancelBusyTime) {
        this.cancelBusyTime = cancelBusyTime;
    }

    public Time getDif() {
        return dif;
    }

    public void setDif(Time dif) {
        this.dif = dif;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
}
