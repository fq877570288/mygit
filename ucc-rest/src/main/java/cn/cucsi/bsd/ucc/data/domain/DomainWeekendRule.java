package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "domain_weekend_rule", schema = "ucc", catalog = "")
@IdClass(DomainWeekendRulePK.class)
public class DomainWeekendRule {
    private String weekendRule; //1111100 周一到周五上班，周六周日休息
    private String workTimeRule; //0800-1130,1230-1700
    private String domainId;
    private String teamId;

    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    @JsonView(JSONView.Summary.class)
    private String createdUserId;
    @JsonView(JSONView.Summary.class)
    private String createdUserName;
    @JsonView(JSONView.Summary.class)
    private String createdNickName;
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private UccDomain uccDomain;
    @JsonIgnore
    private UccTeams uccTeam;

    @Basic
    @Column(name = "weekend_rule", nullable = true, length = 15)
    public String getWeekendRule() {
        return weekendRule;
    }

    public void setWeekendRule(String weekendRule) {
        this.weekendRule = weekendRule;
    }

    @Basic
    @Column(name = "work_time_rule", nullable = true, length = 200)
    public String getWorkTimeRule() {
        return workTimeRule;
    }

    public void setWorkTimeRule(String workTimeRule) {
        this.workTimeRule = workTimeRule;
    }

    @Id
    @Column(name = "domain_id", nullable = false, length = 32)
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
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

        DomainWeekendRule that = (DomainWeekendRule) o;

        if (weekendRule != null ? !weekendRule.equals(that.weekendRule) : that.weekendRule != null) return false;
        if (workTimeRule != null ? !workTimeRule.equals(that.workTimeRule) : that.workTimeRule != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;
        if (teamId != null ? !teamId.equals(that.teamId) : that.teamId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = weekendRule != null ? weekendRule.hashCode() : 0;
        result = 31 * result + (workTimeRule != null ? workTimeRule.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        result = 31 * result + (teamId != null ? teamId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccTeams getUccTeam() {
        return uccTeam;
    }

    public void setUccTeam(cn.cucsi.bsd.ucc.data.domain.UccTeams uccTeam) {
        this.uccTeam = uccTeam;
    }

    @Basic
    @Column(name = "created_user_id", nullable = true, length = 32)
    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
    @Basic
    @Column(name = "created_user_name", nullable = true, length = 32)
    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }
    @Basic
    @Column(name = "created_nick_name", nullable = true)
    public String getCreatedNickName() {
        return createdNickName;
    }

    public void setCreatedNickName(String createdNickName) {
        this.createdNickName = createdNickName;
    }
    @Basic
    @Column(name = "updated_user_id", nullable = true, length = 32)
    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
    @Basic
    @Column(name = "updated_user_name", nullable = true, length = 32)
    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }
    @Basic
    @Column(name = "updated_nick_name", nullable = true)
    public String getUpdatedNickName() {
        return updatedNickName;
    }

    public void setUpdatedNickName(String updatedNickName) {
        this.updatedNickName = updatedNickName;
    }

    @Basic
    @Column(nullable = true)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(nullable = true)
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
