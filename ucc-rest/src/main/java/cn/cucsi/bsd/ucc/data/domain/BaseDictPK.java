package cn.cucsi.bsd.ucc.data.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class BaseDictPK implements Serializable {
    private String dictType;
    private int dictKey;

    @Column(name = "dict_type", nullable = false, length = 20)
    @Id
    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    @Column(name = "dict_key", nullable = false)
    @Id
    public int getDictKey() {
        return dictKey;
    }

    public void setDictKey(int dictKey) {
        this.dictKey = dictKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseDictPK that = (BaseDictPK) o;

        if (dictKey != that.dictKey) return false;
        if (dictType != null ? !dictType.equals(that.dictType) : that.dictType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dictType != null ? dictType.hashCode() : 0;
        result = 31 * result + dictKey;
        return result;
    }
}
