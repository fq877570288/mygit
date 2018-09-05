package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UccDomainClientsPK implements Serializable {
    private String name;
    private String domainId;

    @Column(name = "name", nullable = false, length = 50)
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        UccDomainClientsPK that = (UccDomainClientsPK) o;

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
}
