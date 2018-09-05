package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserDeptPK implements Serializable {
    private String userId;
    private String deptId;

    @Column(name = "user_id", nullable = false, length = 32)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "dept_id", nullable = false, length = 32)
    @Id
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDeptPK that = (UserDeptPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (deptId != null ? !deptId.equals(that.deptId) : that.deptId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
        return result;
    }
}
