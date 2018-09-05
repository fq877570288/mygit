package cn.cucsi.bsd.ucc.common.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by tianyuwei on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccDeptsCriteria extends BasicCriteria {
    String createdBy;
    String deptName;
    String deptPid;
    String domainId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date deptCreateTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date deptCreateTimeTo;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptPid() {
        return deptPid;
    }

    public void setDeptPid(String deptPid) {
        this.deptPid = deptPid;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public Date getDeptCreateTimeFrom() {
        return deptCreateTimeFrom;
    }

    public void setDeptCreateTimeFrom(Date deptCreateTimeFrom) {
        this.deptCreateTimeFrom = deptCreateTimeFrom;
    }

    public Date getDeptCreateTimeTo() {
        return deptCreateTimeTo;
    }

    public void setDeptCreateTimeTo(Date deptCreateTimeTo) {
        this.deptCreateTimeTo = deptCreateTimeTo;
    }
}
