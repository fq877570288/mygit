package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/16.
 */
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class UccTeamsCriteria extends  BasicCriteria  {
    private String teamName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimeTo;

    @JsonFormat(pattern="yyyy-MM-ddHH:mm:ss")
    public Date getCreatedTimeFrom() {
        return createdTimeFrom;
    }

    public void setCreatedTimeFrom(Date createdTimeFrom) {
        this.createdTimeFrom = createdTimeFrom;
    }
    @JsonFormat(pattern="yyyy-MM-ddHH:mm:ss")
    public Date getCreatedTimeTo() {
        return createdTimeTo;
    }

    public void setCreatedTimeTo(Date createdTimeTo) {
        this.createdTimeTo = createdTimeTo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
