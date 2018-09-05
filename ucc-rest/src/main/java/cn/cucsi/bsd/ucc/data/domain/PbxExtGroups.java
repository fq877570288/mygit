package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "pbx_ext_groups", schema = "ucc", catalog = "")
public class PbxExtGroups {
    @JsonView(JSONView.Summary.class)
    private String groupId;
    @JsonView(JSONView.Summary.class)
    private String groupName;
    @JsonView(JSONView.Summary.class)
    private String groupDesc;
    @JsonView(JSONView.Summary.class)
    private Integer groupOrder;
    @JsonView(JSONView.Summary.class)
    private String domainId;
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

    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;

    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    @JsonIgnore
    private Collection<ExtGroupExts> extGroupExts;
    @JsonIgnore
    private UccDomain uccDomain;
//    @JsonView(JSONView.PbxPbxExtGroupsWithUser.class)
//    private UccUsers uccUsersByCreatePerson;
//    @JsonView(JSONView.PbxPbxExtGroupsWithUser.class)
//    private UccUsers uccUsersByUpdatePerson;
    @JsonView(JSONView.Summary.class)
    private String status = "1"; //1在用，0删除，默认是1




    @Id
    @Column(name = "group_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "group_name", nullable = false, length = 200)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "group_desc", nullable = true, length = 500)
    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    @Basic
    @Column(name = "group_order", nullable = true)
    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    @Basic
    @Column(name = "domain_id", nullable = false, length = 32)
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PbxExtGroups that = (PbxExtGroups) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (groupDesc != null ? !groupDesc.equals(that.groupDesc) : that.groupDesc != null) return false;
        if (groupOrder != null ? !groupOrder.equals(that.groupOrder) : that.groupOrder != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (groupDesc != null ? groupDesc.hashCode() : 0);
        result = 31 * result + (groupOrder != null ? groupOrder.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pbxExtGroup")
    public Collection<ExtGroupExts> getExtGroupExts() {
        return extGroupExts;
    }

    public void setExtGroupExts(Collection<ExtGroupExts> extGroupExts) {
        this.extGroupExts = extGroupExts;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }

/*    @ManyToOne
    @JoinColumn(name = "createdPerson", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public UccUsers getUccUsersByCreatePerson() {
        return uccUsersByCreatePerson;
    }

    public void setUccUsersByCreatePerson(UccUsers uccUsersByCreatePerson) {
        this.uccUsersByCreatePerson = uccUsersByCreatePerson;
    }
    @ManyToOne
    @JoinColumn(name = "updatedPerson", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public UccUsers getUccUsersByUpdatePerson() {
        return uccUsersByUpdatePerson;
    }

    public void setUccUsersByUpdatePerson(UccUsers uccUsersByUpdatePerson) {
        this.uccUsersByUpdatePerson = uccUsersByUpdatePerson;
    }*/

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
