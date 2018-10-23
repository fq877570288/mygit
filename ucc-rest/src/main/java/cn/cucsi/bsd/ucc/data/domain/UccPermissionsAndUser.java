package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ucc_permissions", schema = "ucc", catalog = "")
public class UccPermissionsAndUser {
    @Transient
    private String permissionId;
    @Transient
    private String mpid;
    @JsonView(JSONView.Summary.class)
    private String name;

    @JsonView(JSONView.Summary.class)
    private String icon;

    @JsonView(JSONView.Summary.class)
    private String path;

    @JsonView(JSONView.Summary.class)
    private String isLeftMenu;

    @Transient
    @JsonView(JSONView.Summary.class)
    private List<UccPermissionsAndUserSecound> children;

    @Id
    @Column(name = "permission_id", nullable = false, length = 32)
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "mpid", nullable = true, length = 32)
    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
    }

    @Basic
    @Column(name = "permission_name", nullable = true, length = 48)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Basic
    @Column(name = "icon", nullable = true, length = 32)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    @Basic
    @Column(name = "route", nullable = true, length = 32)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Transient
    public List<UccPermissionsAndUserSecound> getChildren() {
        return children;
    }

    public void setChildren(List<UccPermissionsAndUserSecound> children) {
        this.children = children;
    }

    @Basic
    @Column(name = "is_left_menu", nullable = true, length = 32)
    public String getIsLeftMenu() {
        return isLeftMenu;
    }

    public void setIsLeftMenu(String isLeftMenu) {
        this.isLeftMenu = isLeftMenu;
    }
}
