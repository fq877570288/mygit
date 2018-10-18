package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by Song on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxCallTransferCriteria  extends BasicCriteria{
    private String num1;
    private String num2;
    private String extId;
    private String domainId;
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getExtId() {
        return extId;
    }

    public String getDomainId() {
        return domainId;
    }
    
    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }
}
