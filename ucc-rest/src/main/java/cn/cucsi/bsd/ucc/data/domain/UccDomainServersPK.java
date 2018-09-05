package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UccDomainServersPK implements Serializable {
    private String serverName;
    private String domainId;

    @Column(name = "server_name", nullable = false, length = 50)
    @Id
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Column(name = "domain_id", nullable = false, length = 32)
    @Id
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

        UccDomainServersPK that = (UccDomainServersPK) o;

        if (serverName != null ? !serverName.equals(that.serverName) : that.serverName != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverName != null ? serverName.hashCode() : 0;
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }
}
