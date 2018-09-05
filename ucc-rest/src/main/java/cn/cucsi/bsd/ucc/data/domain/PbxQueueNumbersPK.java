package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class PbxQueueNumbersPK implements Serializable {
    private String queueId;
    private String extId;

    @Column(name = "queue_id", nullable = false, length = 32)
    @Id
    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    @Column(name = "ext_id", nullable = false, length = 32)
    @Id
    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PbxQueueNumbersPK that = (PbxQueueNumbersPK) o;

        if (queueId != null ? !queueId.equals(that.queueId) : that.queueId != null) return false;
        if (extId != null ? !extId.equals(that.extId) : that.extId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = queueId != null ? queueId.hashCode() : 0;
        result = 31 * result + (extId != null ? extId.hashCode() : 0);
        return result;
    }

}
