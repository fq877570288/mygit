package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class DomainWeekendRulePK implements Serializable {
    private String domainId;
    private String teamId;

    @Column(name = "domain_id", nullable = false, length = 32)
    @Id
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Column(name = "team_id", nullable = false, length = 32)
    @Id
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DomainWeekendRulePK that = (DomainWeekendRulePK) o;

        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domainId != null ? domainId.hashCode() : 0;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }


}
