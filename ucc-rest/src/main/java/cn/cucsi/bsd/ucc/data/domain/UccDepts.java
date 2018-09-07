package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Collection;

@Entity
@Table(name = "ucc_depts", schema = "ucc", catalog = "")
public class UccDepts {
    @JsonView(JSONView.Summary.class)
    private String deptId;
    @JsonView(JSONView.Summary.class)
    private String createdBy;
    @JsonView(JSONView.Summary.class)
    private String deptName;
    @JsonView(JSONView.Summary.class)
    private String deptPid;
    @JsonView(JSONView.Summary.class)
    private Integer deptLevel;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deptCreateTime;
    @JsonView(JSONView.Summary.class)
    private String deptDesc;
    private String domainId;
    private String deptAdmin;
//    private String userId;
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    @JsonIgnore
    private UccDomain uccDomain;
    @JsonIgnore
    private UccUsers deptAdminByUserId;

    @ManyToOne
    @JoinColumn(name = "dept_admin", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccUsers getDeptAdminByUserId() {
        return deptAdminByUserId;
    }

    public void setDeptAdminByUserId(cn.cucsi.bsd.ucc.data.domain.UccUsers deptAdminByUserId) {
        this.deptAdminByUserId = deptAdminByUserId;
    }

//    private UccUsers uccUser;
    private Collection<UserDept> userDepts;

    @Id
    @Column(name = "dept_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    @Basic
    @Column(name = "created_by", nullable = true, length = 32)
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "dept_name", nullable = false, length = 32)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "dept_pid", nullable = true, length = 32)
    public String getDeptPid() {
        return deptPid;
    }

    public void setDeptPid(String deptPid) {
        this.deptPid = deptPid;
    }

    @Basic
    @Column(name = "dept_level", nullable = true)
    public Integer getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(Integer deptLevel) {
        this.deptLevel = deptLevel;
    }

    @Basic
    @Column(name = "dept_create_time", nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getDeptCreateTime() {
        return deptCreateTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setDeptCreateTime(Date deptCreateTime) {
        this.deptCreateTime = deptCreateTime;
    }

    @Basic
    @Column(name = "dept_desc", nullable = true, length = 255)
    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

    @Basic
    @Column(name = "domain_id", nullable = false, length = 32)
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    @Basic
    @Column(name = "dept_admin", nullable = false, length = 32)
    public String getDeptAdmin() {
        return deptAdmin;
    }

    public void setDeptAdmin(String deptAdmin) {
        this.deptAdmin = deptAdmin;
    }

//    @Basic
//    @Column(name = "user_id", nullable = false, length = 32)
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UccDepts uccDepts = (UccDepts) o;

        if (deptId != null ? !deptId.equals(uccDepts.deptId) : uccDepts.deptId != null) return false;
        if (createdBy != null ? !createdBy.equals(uccDepts.createdBy) : uccDepts.createdBy != null) return false;
        if (deptName != null ? !deptName.equals(uccDepts.deptName) : uccDepts.deptName != null) return false;
        if (deptPid != null ? !deptPid.equals(uccDepts.deptPid) : uccDepts.deptPid != null) return false;
        if (deptLevel != null ? !deptLevel.equals(uccDepts.deptLevel) : uccDepts.deptLevel != null) return false;
        if (deptCreateTime != null ? !deptCreateTime.equals(uccDepts.deptCreateTime) : uccDepts.deptCreateTime != null)
            return false;
        if (deptDesc != null ? !deptDesc.equals(uccDepts.deptDesc) : uccDepts.deptDesc != null) return false;
        if (domainId != null ? !domainId.equals(uccDepts.domainId) : uccDepts.domainId != null) return false;
        if (deptAdmin != null ? !deptAdmin.equals(uccDepts.deptAdmin) : uccDepts.deptAdmin != null) return false;
//        if (userId != null ? !userId.equals(uccDepts.userId) : uccDepts.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deptId != null ? deptId.hashCode() : 0;
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (deptName != null ? deptName.hashCode() : 0);
        result = 31 * result + (deptPid != null ? deptPid.hashCode() : 0);
        result = 31 * result + (deptLevel != null ? deptLevel.hashCode() : 0);
        result = 31 * result + (deptCreateTime != null ? deptCreateTime.hashCode() : 0);
        result = 31 * result + (deptDesc != null ? deptDesc.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        result = 31 * result + (deptAdmin != null ? deptAdmin.hashCode() : 0);
//        result = 31 * result + (userId != null ? userId.hashCode() : 0);
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

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
//    public cn.cucsi.bsd.ucc.data.domain.UccUsers getUccUser() {
//        return uccUser;
//    }

//    public void setUccUser(cn.cucsi.bsd.ucc.data.domain.UccUsers uccUser) {
//        this.uccUser = uccUser;
//    }

    @OneToMany(mappedBy = "uccDept")
    public Collection<UserDept> getUserDepts() {
        return userDepts;
    }

    public void setUserDepts(Collection<UserDept> userDepts) {
        this.userDepts = userDepts;
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
