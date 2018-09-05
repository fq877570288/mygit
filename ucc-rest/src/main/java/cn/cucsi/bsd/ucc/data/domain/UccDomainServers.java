package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ucc_domain_servers", schema = "ucc", catalog = "")
@IdClass(UccDomainServersPK.class)
public class UccDomainServers {
    private String serverName;
    private String domainId;
    private UccServers uccServer;

    @JsonIgnore
    private UccDomain uccDomain;

    @Id
    @Column(name = "server_name", nullable = false, length = 50)
    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    @Id
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

        UccDomainServers that = (UccDomainServers) o;

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

    @ManyToOne
    @JoinColumn(name = "server_name", referencedColumnName = "server_name", nullable = false, updatable = false, insertable = false)
    public UccServers getUccServer() {
        return uccServer;
    }

    public void setUccServer(UccServers uccServer) {
        this.uccServer = uccServer;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
    }


}
