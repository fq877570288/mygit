package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by tianyuwei on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccDomainServersCriteria extends BasicCriteria {
    String domainId;
    String serverName;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
