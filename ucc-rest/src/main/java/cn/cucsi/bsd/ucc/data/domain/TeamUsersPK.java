package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TeamUsersPK implements Serializable {
    private String userId;
    private String teamId;

    @Column(name = "user_id", nullable = false, length = 32)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

        TeamUsersPK that = (TeamUsersPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }
}
