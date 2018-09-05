package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class ExtGroupExtsPK implements Serializable {
    private String groupId;
    private String extId;

    @Column(name = "group_id", nullable = false, length = 32)
    @Id
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Column(name = "ext_id", nullable = false, length = 32)
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

        ExtGroupExtsPK that = (ExtGroupExtsPK) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (extId != null ? !extId.equals(that.extId) : that.extId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (extId != null ? extId.hashCode() : 0);
        return result;
    }

}
