package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class UccSkillGroupCriteria extends  BasicCriteria {
    private String skillGroupName;
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeTo;

    public String getSkillGroupName() {
        return skillGroupName;
    }

    public void setSkillGroupName(String skillGroupName) {
        this.skillGroupName = skillGroupName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
