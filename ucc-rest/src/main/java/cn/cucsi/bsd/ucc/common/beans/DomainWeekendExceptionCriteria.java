package cn.cucsi.bsd.ucc.common.beans;


/**
 * Created by home on 2017/10/16.
 */

import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class DomainWeekendExceptionCriteria extends BasicCriteria {

    private String exceptionId;

    private String domainId;

    private String teamId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exceptionDate;

    public Date getExceptionDate() {
        return exceptionDate;
    }

    public void setExceptionDate(Date exceptionDate) {
        this.exceptionDate = exceptionDate;
    }

    /*
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exceptionDateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exceptionDateTo;*/

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

/*    public Date getExceptionDateFrom() {
        return exceptionDateFrom;
    }

    public void setExceptionDateFrom(Date exceptionDateFrom) {
        this.exceptionDateFrom = exceptionDateFrom;
    }

    public Date getExceptionDateTo() {
        return exceptionDateTo;
    }

    public void setExceptionDateTo(Date exceptionDateTo) {
        this.exceptionDateTo = exceptionDateTo;
    }*/

    public String getExceptionId() {
        return exceptionId;
    }

    public void setExceptionId(String exceptionId) {
        this.exceptionId = exceptionId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
