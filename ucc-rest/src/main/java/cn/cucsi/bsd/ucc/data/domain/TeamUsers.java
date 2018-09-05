package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "team_users", schema = "ucc", catalog = "")
@IdClass(TeamUsersPK.class)
public class TeamUsers {
    private String userId;
    private String teamId;

    @JsonIgnore
    private UccUsers uccUser;
    @JsonIgnore
    private UccTeams uccTeam;

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "team_id", nullable = false, length = 32)
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

        TeamUsers teamUsers = (TeamUsers) o;

        if (userId != null ? !userId.equals(teamUsers.userId) : teamUsers.userId != null) return false;
        if (teamId != null ? !teamId.equals(teamUsers.teamId) : teamUsers.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccUsers getUccUser() {
        return uccUser;
    }

    public void setUccUser(cn.cucsi.bsd.ucc.data.domain.UccUsers uccUser) {
        this.uccUser = uccUser;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccTeams getUccTeam() {
        return uccTeam;
    }

    public void setUccTeam(cn.cucsi.bsd.ucc.data.domain.UccTeams uccTeam) {
        this.uccTeam = uccTeam;
    }

}
