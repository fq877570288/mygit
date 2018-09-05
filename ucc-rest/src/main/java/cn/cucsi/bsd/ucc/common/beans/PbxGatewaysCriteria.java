package cn.cucsi.bsd.ucc.common.beans;

import java.util.Date;

/**
 * Created by jjjjj on 2017-10-13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxGatewaysCriteria  extends BasicCriteria  {
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
    private Date createdTime;
    private String updatedPerson;
    private String realm;
    private String registerProxy;
    private String fromUser;
    private String fromDomain;
    private String registerTransport;
    private String domainId;

    public String getGwName() {
        return gwName;
    }

    public void setGwName(String gwName) {
        this.gwName = gwName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getReCaller() {
        return reCaller;
    }

    public void setReCaller(String reCaller) {
        this.reCaller = reCaller;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRegisterProxy() {
        return registerProxy;
    }

    public void setRegisterProxy(String registerProxy) {
        this.registerProxy = registerProxy;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromDomain() {
        return fromDomain;
    }

    public void setFromDomain(String fromDomain) {
        this.fromDomain = fromDomain;
    }

    public String getRegisterTransport() {
        return registerTransport;
    }

    public void setRegisterTransport(String registerTransport) {
        this.registerTransport = registerTransport;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getUpdatedPerson() {
        return updatedPerson;
    }

    public void setUpdatedPerson(String updatedPerson) {
        this.updatedPerson = updatedPerson;
    }
}
