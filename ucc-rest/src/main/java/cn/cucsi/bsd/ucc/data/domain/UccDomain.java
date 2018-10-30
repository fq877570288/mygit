package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
/*
* 域
* */
@Entity
@Table(name = "ucc_domain", schema = "ucc", catalog = "")
public class UccDomain {
    @JsonView(JSONView.Summary.class)
    private String domainId;//域ID
    @JsonView(JSONView.Summary.class)
    private String domainCode;//域ID
    @JsonView(JSONView.Summary.class)
    private String tel1;//联系电话1
    @JsonView(JSONView.Summary.class)
    private String tel2;//联系电话2
    @JsonView(JSONView.Summary.class)
    private String domainEmail;//邮箱
    @JsonView(JSONView.Summary.class)
    private String domainName;//域名城
    @JsonView(JSONView.Summary.class)
    private String domainDesc;//域描述
    @JsonView(JSONView.Summary.class)
    private String status;//状态
    @JsonView(JSONView.Summary.class)
    private String addr1;//地址1
    @JsonView(JSONView.Summary.class)
    private String addr2;//地址2
    //以下六个字段，作为创建和更新 使用，不再使用关联关系

    @JsonView(JSONView.Summary.class)
    private String createdUserId;//创建人ID
    @JsonView(JSONView.Summary.class)
    private String createdUserName;//创建人姓名
    @JsonView(JSONView.Summary.class)
    private String createdNickName;//创建人昵称
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;//修改人ID
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;//修改人姓名
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;//修改人昵称
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private Collection<DomainWeekendException> domainWeekendExceptions;
    @JsonIgnore
    private Collection<DomainWeekendRule> domainWeekendRules;
    @JsonIgnore
    private Collection<LoginLog> loginLogs;
    @JsonIgnore
    private Collection<PbxCallTransfer> pbxCallTransfers;
    @JsonIgnore
    private Collection<PbxCdrs> pbxCdrs;
    @JsonIgnore
    private Collection<PbxDialplans> pbxDialplans;
    @JsonIgnore
    private Collection<PbxExtGroups> pbxExtGroups;
    @JsonIgnore
    private Collection<PbxExts> pbxExts;
    @JsonIgnore
    private Collection<PbxGateways> pbxGateways;
    @JsonIgnore
    private Collection<PbxIvrs> pbxIvrs;
    @JsonIgnore
    private Collection<PbxQueues> pbxQueues;
    @JsonIgnore
    private Collection<PbxRecords> pbxRecords;
    @JsonIgnore
    private Collection<PbxShowbusyLog> pbxShowbusyLogs;
    @JsonIgnore
    private Collection<PermissionGroups> permissionGroups;
    @JsonIgnore
    private Collection<UccCustomers> uccCustomers;
    @JsonIgnore
    private Collection<UccDepts> uccDepts;
    @JsonIgnore
    private Collection<UccDomainClients> uccDomainClients;
    @JsonIgnore
    private Collection<UccDomainServers> uccDomainServers;
    @JsonIgnore
    private Collection<UccNotice> uccNotices;
    @JsonIgnore
    private Collection<UccRoles> uccRoles;
    @JsonIgnore
    private Collection<UccSkillGroup> uccSkillGroups;
    @JsonIgnore
    private Collection<UccTeams> uccTeams;
    @JsonIgnore
    private Collection<UccUsers> uccUsers;

/*
    @JsonView(JSONView.DomainWithUser.class)
    private UccUsers uccUsersByCreatePerson;

    @JsonView(JSONView.DomainWithUser.class)
    private UccUsers uccUsersByUpdatePerson;
*/


    @Id
    @Column(name = "domain_id", nullable = false, length = 32)
//    @GenericGenerator(name="idGenerator", strategy="uuid")
//    @GeneratedValue(generator="idGenerator")
    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }



    @Basic
    @Column(name = "domain_code", nullable = true, length = 1)
    public String getDomainCode() {
        return domainCode;
    }

    public void setDomainCode(String domainCode) {
        this.domainCode = domainCode;
    }



    @Basic
    @Column(name = "status", nullable = true, length = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "tel1", nullable = true, length = 20)
    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    @Basic
    @Column(name = "tel2", nullable = true, length = 20)
    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    @Basic
    @Column(name = "domain_email", nullable = true, length = 10)
    public String getDomainEmail() {
        return domainEmail;
    }

    public void setDomainEmail(String domainEmail) {
        this.domainEmail = domainEmail;
    }

    @Basic
    @Column(name = "domain_name", nullable = false, length = 200)
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Basic
    @Column(name = "domain_desc", nullable = true, length = 500)
    public String getDomainDesc() {
        return domainDesc;
    }

    public void setDomainDesc(String domainDesc) {
        this.domainDesc = domainDesc;
    }

    @Basic
    @Column(name = "addr1", nullable = true, length = 300)
    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    @Basic
    @Column(name = "addr2", nullable = true, length = 300)
    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

        /*
  @ManyToOne
    @JoinColumn(name = "createdPerson", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    @NotFound(action=NotFoundAction.IGNORE)
    public UccUsers getUccUsersByCreatePerson() {
        return uccUsersByCreatePerson;
    }

    public void setUccUsersByCreatePerson(UccUsers uccUsersByCreatePerson) {
        this.uccUsersByCreatePerson = uccUsersByCreatePerson;
    }

    @ManyToOne()
    @JoinColumn(name = "updatedPerson", referencedColumnName = "user_id", nullable = false, updatable = false, insertable = false)
    @NotFound(action=NotFoundAction.IGNORE)
    public UccUsers getUccUsersByUpdatePerson() {
        return uccUsersByUpdatePerson;
    }

    public void setUccUsersByUpdatePerson(UccUsers uccUsersByUpdatePerson) {
        this.uccUsersByUpdatePerson = uccUsersByUpdatePerson;
    }
*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UccDomain uccDomain = (UccDomain) o;

        if (domainId != null ? !domainId.equals(uccDomain.domainId) : uccDomain.domainId != null) return false;
        if (tel1 != null ? !tel1.equals(uccDomain.tel1) : uccDomain.tel1 != null) return false;
        if (tel2 != null ? !tel2.equals(uccDomain.tel2) : uccDomain.tel2 != null) return false;
        if (domainCode != null ? !domainCode.equals(uccDomain.domainCode) : uccDomain.domainCode != null) return false;
        if (domainEmail != null ? !domainEmail.equals(uccDomain.domainEmail) : uccDomain.domainEmail != null)
            return false;
        if (domainName != null ? !domainName.equals(uccDomain.domainName) : uccDomain.domainName != null) return false;
        if (domainDesc != null ? !domainDesc.equals(uccDomain.domainDesc) : uccDomain.domainDesc != null) return false;
        if (addr1 != null ? !addr1.equals(uccDomain.addr1) : uccDomain.addr1 != null) return false;
        if (addr2 != null ? !addr2.equals(uccDomain.addr2) : uccDomain.addr2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = domainId != null ? domainId.hashCode() : 0;
        result = 31 * result + (tel1 != null ? tel1.hashCode() : 0);
        result = 31 * result + (domainCode != null ? domainCode.hashCode() : 0);
        result = 31 * result + (tel2 != null ? tel2.hashCode() : 0);
        result = 31 * result + (domainEmail != null ? domainEmail.hashCode() : 0);
        result = 31 * result + (domainName != null ? domainName.hashCode() : 0);
        result = 31 * result + (domainDesc != null ? domainDesc.hashCode() : 0);
        result = 31 * result + (addr1 != null ? addr1.hashCode() : 0);
        result = 31 * result + (addr2 != null ? addr2.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<DomainWeekendException> getDomainWeekendExceptions() {
        return domainWeekendExceptions;
    }

    public void setDomainWeekendExceptions(Collection<DomainWeekendException> domainWeekendExceptions) {
        this.domainWeekendExceptions = domainWeekendExceptions;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<DomainWeekendRule> getDomainWeekendRules() {
        return domainWeekendRules;
    }

    public void setDomainWeekendRules(Collection<DomainWeekendRule> domainWeekendRules) {
        this.domainWeekendRules = domainWeekendRules;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<LoginLog> getLoginLogs() {
        return loginLogs;
    }

    public void setLoginLogs(Collection<LoginLog> loginLogs) {
        this.loginLogs = loginLogs;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxCallTransfer> getPbxCallTransfers() {
        return pbxCallTransfers;
    }

    public void setPbxCallTransfers(Collection<PbxCallTransfer> pbxCallTransfers) {
        this.pbxCallTransfers = pbxCallTransfers;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxCdrs> getPbxCdrs() {
        return pbxCdrs;
    }

    public void setPbxCdrs(Collection<PbxCdrs> pbxCdrs) {
        this.pbxCdrs = pbxCdrs;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxDialplans> getPbxDialplans() {
        return pbxDialplans;
    }

    public void setPbxDialplans(Collection<PbxDialplans> pbxDialplans) {
        this.pbxDialplans = pbxDialplans;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxExtGroups> getPbxExtGroups() {
        return pbxExtGroups;
    }

    public void setPbxExtGroups(Collection<PbxExtGroups> pbxExtGroups) {
        this.pbxExtGroups = pbxExtGroups;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxExts> getPbxExts() {
        return pbxExts;
    }

    public void setPbxExts(Collection<PbxExts> pbxExts) {
        this.pbxExts = pbxExts;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxGateways> getPbxGateways() {
        return pbxGateways;
    }

    public void setPbxGateways(Collection<PbxGateways> pbxGateways) {
        this.pbxGateways = pbxGateways;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxIvrs> getPbxIvrs() {
        return pbxIvrs;
    }

    public void setPbxIvrs(Collection<PbxIvrs> pbxIvrs) {
        this.pbxIvrs = pbxIvrs;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxQueues> getPbxQueues() {
        return pbxQueues;
    }

    public void setPbxQueues(Collection<PbxQueues> pbxQueues) {
        this.pbxQueues = pbxQueues;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxRecords> getPbxRecords() {
        return pbxRecords;
    }

    public void setPbxRecords(Collection<PbxRecords> pbxRecords) {
        this.pbxRecords = pbxRecords;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PbxShowbusyLog> getPbxShowbusyLogs() {
        return pbxShowbusyLogs;
    }

    public void setPbxShowbusyLogs(Collection<PbxShowbusyLog> pbxShowbusyLogs) {
        this.pbxShowbusyLogs = pbxShowbusyLogs;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<PermissionGroups> getPermissionGroups() {
        return permissionGroups;
    }

    public void setPermissionGroups(Collection<PermissionGroups> permissionGroups) {
        this.permissionGroups = permissionGroups;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccCustomers> getUccCustomers() {
        return uccCustomers;
    }

    public void setUccCustomers(Collection<UccCustomers> uccCustomers) {
        this.uccCustomers = uccCustomers;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccDepts> getUccDepts() {
        return uccDepts;
    }

    public void setUccDepts(Collection<UccDepts> uccDepts) {
        this.uccDepts = uccDepts;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccDomainClients> getUccDomainClients() {
        return uccDomainClients;
    }

    public void setUccDomainClients(Collection<UccDomainClients> uccDomainClients) {
        this.uccDomainClients = uccDomainClients;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccDomainServers> getUccDomainServers() {
        return uccDomainServers;
    }

    public void setUccDomainServers(Collection<UccDomainServers> uccDomainServers) {
        this.uccDomainServers = uccDomainServers;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccNotice> getUccNotices() {
        return uccNotices;
    }

    public void setUccNotices(Collection<UccNotice> uccNotices) {
        this.uccNotices = uccNotices;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccRoles> getUccRoles() {
        return uccRoles;
    }

    public void setUccRoles(Collection<UccRoles> uccRoles) {
        this.uccRoles = uccRoles;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccSkillGroup> getUccSkillGroups() {
        return uccSkillGroups;
    }

    public void setUccSkillGroups(Collection<UccSkillGroup> uccSkillGroups) {
        this.uccSkillGroups = uccSkillGroups;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccTeams> getUccTeams() {
        return uccTeams;
    }

    public void setUccTeams(Collection<UccTeams> uccTeams) {
        this.uccTeams = uccTeams;
    }

    @OneToMany(mappedBy = "uccDomain")
    public Collection<UccUsers> getUccUsers() {
        return uccUsers;
    }

    public void setUccUsers(Collection<UccUsers> uccUsers) {
        this.uccUsers = uccUsers;
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
