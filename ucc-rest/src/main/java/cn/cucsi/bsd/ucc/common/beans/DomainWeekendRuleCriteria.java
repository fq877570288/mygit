package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by home on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class DomainWeekendRuleCriteria extends BasicCriteria{
    private String weekendRule;

    private String teamId;
    private  String domainId;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getWeekendRule() {
        return weekendRule;
    }

    public void setWeekendRule(String weekendRule) {
        this.weekendRule = weekendRule;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
