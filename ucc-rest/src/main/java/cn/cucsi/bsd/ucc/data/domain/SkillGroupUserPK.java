package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class SkillGroupUserPK implements Serializable {
    private String skillGroupId;
    private String userId;

    @Column(name = "skill_group_id", nullable = false, length = 32)
    @Id
    public String getSkillGroupId() {
        return skillGroupId;
    }

    public void setSkillGroupId(String skillGroupId) {
        this.skillGroupId = skillGroupId;
    }

    @Column(name = "user_id", nullable = false, length = 32)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkillGroupUserPK that = (SkillGroupUserPK) o;

        if (skillGroupId != null ? !skillGroupId.equals(that.skillGroupId) : that.skillGroupId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = skillGroupId != null ? skillGroupId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
