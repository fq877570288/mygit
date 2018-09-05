package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccTeamsCriteria extends  BasicCriteria  {
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
