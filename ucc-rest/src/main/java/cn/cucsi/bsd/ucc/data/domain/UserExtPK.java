package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
* 用户号码关系
* */
public class UserExtPK  implements Serializable {
    private String userId;//用户ID
    private String extId;//分机号

    @Column(name = "user_id", nullable = false, length = 32)
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "ext_id", nullable = false, length = 30)
    @Id
    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserExtPK that = (UserExtPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (extId != null ? !extId.equals(that.extId) : that.extId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (extId != null ? extId.hashCode() : 0);
        return result;
    }
}
