package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by jjjjj on 2017-10-13.
 */
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel
public class PbxExtsCriteria  extends BasicCriteria  {
    private String extId;
    private String extNum;
    private String domainId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeTo;

    private String extGroupsId;

    private List<String> extIds;

    public  List<String> getExtIds() {
        return extIds;
    }

    public void setExtIds( List<String> extIds) {
        this.extIds = extIds;
    }

    public String getExtGroupsId() {
        return extGroupsId;
    }

    public void setExtGroupsId(String extGroupsId) {
        this.extGroupsId = extGroupsId;
    }

    public Date getCreatedTimeFrom() {
        return createdTimeFrom;
    }

    public void setCreatedTimeFrom(Date createdTimeFrom) {
        this.createdTimeFrom = createdTimeFrom;
    }

    public Date getCreatedTimeTo() {
        return createdTimeTo;
    }

    public void setCreatedTimeTo(Date createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
}
