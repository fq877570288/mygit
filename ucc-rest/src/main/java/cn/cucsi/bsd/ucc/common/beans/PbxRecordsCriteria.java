package cn.cucsi.bsd.ucc.common.beans;

import java.util.Date;

/**
 * Created by jjjjj on 2017-10-16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxRecordsCriteria  extends BasicCriteria   {
    private String recordName;
    private String contentType;
    private String type;
    private String recordFrom;
    private Date createdTime;
    private String domainId;

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecordFrom() {
        return recordFrom;
    }

    public void setRecordFrom(String recordFrom) {
        this.recordFrom = recordFrom;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
}
