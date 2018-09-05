package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ext_group_exts", schema = "ucc", catalog = "")
@IdClass(ExtGroupExtsPK.class)
public class ExtGroupExts {
    private String groupId;
    private String extId;


    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    @JsonIgnore
    private PbxExtGroups pbxExtGroup;
    @JsonIgnore
    private PbxExts pbxExt;

    @Id
    @Column(name = "group_id", nullable = false, length = 32)
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "ext_id", nullable = false, length = 32)
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

        ExtGroupExts that = (ExtGroupExts) o;

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

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.PbxExtGroups getPbxExtGroup() {
        return pbxExtGroup;
    }

    public void setPbxExtGroup(cn.cucsi.bsd.ucc.data.domain.PbxExtGroups pbxExtGroup) {
        this.pbxExtGroup = pbxExtGroup;
    }

    @ManyToOne
    @JoinColumn(name = "ext_id", referencedColumnName = "ext_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.PbxExts getPbxExt() {
        return pbxExt;
    }

    public void setPbxExt(cn.cucsi.bsd.ucc.data.domain.PbxExts pbxExt) {
        this.pbxExt = pbxExt;
    }


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
