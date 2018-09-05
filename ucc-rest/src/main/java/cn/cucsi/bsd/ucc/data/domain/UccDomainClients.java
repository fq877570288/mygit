package cn.cucsi.bsd.ucc.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ucc_domain_clients", schema = "ucc", catalog = "")
@IdClass(UccDomainClientsPK.class)
public class UccDomainClients {
    private String name;
    private String domainId;

    @JsonIgnore
    private UccClients uccClient;
    @JsonIgnore
    private UccDomain uccDomain;

    @Id
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        UccDomainClients that = (UccDomainClients) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "name", referencedColumnName = "name", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccClients getUccClient() {
        return uccClient;
    }

    public void setUccClient(cn.cucsi.bsd.ucc.data.domain.UccClients uccClient) {
        this.uccClient = uccClient;
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
