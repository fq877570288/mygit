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
@Table(name = "pbx_gateways", schema = "ucc", catalog = "")
public class PbxGateways {
    private String gwId;
    private String gwName;
    private Integer type;
    private String account;
    private String pwd;
    private String serverIp;
    private Integer serverPort;
    private String clientIp;
    private String reCaller;
    private Integer status;
    private String memo;
    private String realm;
    private String registerProxy;
    private String fromUser;
    private String fromDomain;
    private String registerTransport;
    private String domainId;
    private String[] gwNumbersStr;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;
    @JsonIgnore
    private UccDomain uccDomain;
    private Collection<PbxGwCalleeRewriteRules> pbxGwCalleeRewriteRules;
    private Collection<PbxGwNumbers> pbxGwNumbers;

    @Id
    @Column(name = "gw_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getGwId() {
        return gwId;
    }

    public void setGwId(String gwId) {
        this.gwId = gwId;
    }

    @Basic
    @Column(name = "gw_name", nullable = true, length = 50)
    public String getGwName() {
        return gwName;
    }

    public void setGwName(String gwName) {
        this.gwName = gwName;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "account", nullable = true, length = 50)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "pwd", nullable = true, length = 50)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "server_ip", nullable = true, length = 50)
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    @Basic
    @Column(name = "server_port", nullable = true)
    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    @Basic
    @Column(name = "client_ip", nullable = true, length = 50)
    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    @Basic
    @Column(name = "re_caller", nullable = true, length = 50)
    public String getReCaller() {
        return reCaller;
    }

    public void setReCaller(String reCaller) {
        this.reCaller = reCaller;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 255)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }


    @Basic
    @Column(name = "realm", nullable = true, length = 45)
    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    @Basic
    @Column(name = "register_proxy", nullable = true, length = 45)
    public String getRegisterProxy() {
        return registerProxy;
    }

    public void setRegisterProxy(String registerProxy) {
        this.registerProxy = registerProxy;
    }

    @Basic
    @Column(name = "from_user", nullable = true, length = 45)
    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    @Basic
    @Column(name = "from_domain", nullable = true, length = 45)
    public String getFromDomain() {
        return fromDomain;
    }

    public void setFromDomain(String fromDomain) {
        this.fromDomain = fromDomain;
    }

    @Basic
    @Column(name = "register_transport", nullable = true, length = 45)
    public String getRegisterTransport() {
        return registerTransport;
    }

    public void setRegisterTransport(String registerTransport) {
        this.registerTransport = registerTransport;
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

        PbxGateways that = (PbxGateways) o;

        if (gwId != null ? !gwId.equals(that.gwId) : that.gwId != null) return false;
        if (gwName != null ? !gwName.equals(that.gwName) : that.gwName != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;
        if (serverIp != null ? !serverIp.equals(that.serverIp) : that.serverIp != null) return false;
        if (serverPort != null ? !serverPort.equals(that.serverPort) : that.serverPort != null) return false;
        if (clientIp != null ? !clientIp.equals(that.clientIp) : that.clientIp != null) return false;
        if (reCaller != null ? !reCaller.equals(that.reCaller) : that.reCaller != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (createdTime != null ? !createdTime.equals(that.createdTime) : that.createdTime != null) return false;
        if (realm != null ? !realm.equals(that.realm) : that.realm != null) return false;
        if (registerProxy != null ? !registerProxy.equals(that.registerProxy) : that.registerProxy != null)
            return false;
        if (fromUser != null ? !fromUser.equals(that.fromUser) : that.fromUser != null) return false;
        if (fromDomain != null ? !fromDomain.equals(that.fromDomain) : that.fromDomain != null) return false;
        if (registerTransport != null ? !registerTransport.equals(that.registerTransport) : that.registerTransport != null)
            return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gwId != null ? gwId.hashCode() : 0;
        result = 31 * result + (gwName != null ? gwName.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (serverIp != null ? serverIp.hashCode() : 0);
        result = 31 * result + (serverPort != null ? serverPort.hashCode() : 0);
        result = 31 * result + (clientIp != null ? clientIp.hashCode() : 0);
        result = 31 * result + (reCaller != null ? reCaller.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        result = 31 * result + (realm != null ? realm.hashCode() : 0);
        result = 31 * result + (registerProxy != null ? registerProxy.hashCode() : 0);
        result = 31 * result + (fromUser != null ? fromUser.hashCode() : 0);
        result = 31 * result + (fromDomain != null ? fromDomain.hashCode() : 0);
        result = 31 * result + (registerTransport != null ? registerTransport.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
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

    @OneToMany(mappedBy = "pbxGateways")
    public Collection<PbxGwCalleeRewriteRules> getPbxGwCalleeRewriteRules() {
        return pbxGwCalleeRewriteRules;
    }

    public void setPbxGwCalleeRewriteRules(Collection<PbxGwCalleeRewriteRules> pbxGwCalleeRewriteRules) {
        this.pbxGwCalleeRewriteRules = pbxGwCalleeRewriteRules;
    }

    @OneToMany(mappedBy = "pbxGateway")
    public Collection<PbxGwNumbers> getPbxGwNumbers() {
        return pbxGwNumbers;
    }

    public void setPbxGwNumbers(Collection<PbxGwNumbers> pbxGwNumbers) {
        this.pbxGwNumbers = pbxGwNumbers;
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

    @Transient
    public String[] getGwNumbersStr() {
        return gwNumbersStr;
    }

    public void setGwNumbersStr(String[] gwNumbersStr) {
        this.gwNumbersStr = gwNumbersStr;
    }
}
