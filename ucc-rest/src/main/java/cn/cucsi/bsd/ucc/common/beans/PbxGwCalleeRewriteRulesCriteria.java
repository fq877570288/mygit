package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by jjjjj on 2017-10-16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxGwCalleeRewriteRulesCriteria  extends BasicCriteria  {

    private String rewriteId;
    private String gatewayId;
    private String prefix;
    private Integer ruleRm;
    private String ruleAdd;
    private String createdPerson;
    private String updatedPerson;
    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getRuleRm() {
        return ruleRm;
    }

    public void setRuleRm(Integer ruleRm) {
        this.ruleRm = ruleRm;
    }

    public String getRuleAdd() {
        return ruleAdd;
    }

    public void setRuleAdd(String ruleAdd) {
        this.ruleAdd = ruleAdd;
    }

    public String getRewriteId() {
        return rewriteId;
    }

    public void setRewriteId(String rewriteId) {
        this.rewriteId = rewriteId;
    }

    public String getCreatedPerson() {
        return createdPerson;
    }

    public void setCreatedPerson(String createdPerson) {
        this.createdPerson = createdPerson;
    }

    public String getUpdatedPerson() {
        return updatedPerson;
    }

    public void setUpdatedPerson(String updatedPerson) {
        this.updatedPerson = updatedPerson;
    }
}
