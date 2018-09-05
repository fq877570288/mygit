package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_dept", schema = "ucc", catalog = "")
@IdClass(UserDeptPK.class)
public class UserDept {
    private String userId;
    private String deptId;

    @JsonIgnore
    private UccUsers uccUser;
    @JsonIgnore
    private UccDepts uccDept;

    @Id
    @Column(name = "user_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "dept_id", nullable = false, length = 32)
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

        UserDept userDept = (UserDept) o;

        if (userId != null ? !userId.equals(userDept.userId) : userDept.userId != null) return false;
        if (deptId != null ? !deptId.equals(userDept.deptId) : userDept.deptId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (deptId != null ? deptId.hashCode() : 0);
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
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDepts getUccDept() {
        return uccDept;
    }

    public void setUccDept(cn.cucsi.bsd.ucc.data.domain.UccDepts uccDept) {
        this.uccDept = uccDept;
    }


}
