package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by home on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseDictCriteria extends BasicCriteria{

    private String dictType;

    private Integer dictKey;

    private String dictValue;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getDictKey() {
        return dictKey;
    }

    public void setDictKey(Integer dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
}
