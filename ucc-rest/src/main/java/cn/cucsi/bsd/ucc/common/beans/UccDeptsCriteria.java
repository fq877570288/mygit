package cn.cucsi.bsd.ucc.common.beans;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * Created by tianyuwei on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccDeptsCriteria extends BasicCriteria {

    private String createdBy;
    private String deptName;
    private String deptPid;
    private String domainId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deptCreateTimeFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deptCreateTimeTo;

    private Integer deptLevel;//部门级别
    private String deptIdAndChildIds; //部门ID以及自部门ID字符串，逗号分隔;

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

    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptIdAndChildIds() {
        return deptIdAndChildIds;
    }

    public void setDeptIdAndChildIds(String deptIdAndChildIds) {
        this.deptIdAndChildIds = deptIdAndChildIds;
    }
}
