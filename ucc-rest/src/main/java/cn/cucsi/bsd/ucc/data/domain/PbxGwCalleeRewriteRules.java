package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pbx_gw_callee_rewrite_rules", schema = "ucc", catalog = "")
public class PbxGwCalleeRewriteRules {
    private String rewriteId;
    private String gatewayId;
    private String prefix;
    private Integer ruleRm;
    private String ruleAdd;
    //以下六个字段，作为创建和更新 使用，不再使用关联关系
    @JsonView(JSONView.Summary.class)
    private String createdUserId;
    @JsonView(JSONView.Summary.class)
    private String createdUserName;
    @JsonView(JSONView.Summary.class)
    private String createdNickName;
    @JsonView(JSONView.Summary.class)
    private String updatedUserId;
    @JsonView(JSONView.Summary.class)
    private String updatedUserName;
    @JsonView(JSONView.Summary.class)
    private String updatedNickName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;
    @JsonIgnore
    private PbxGateways pbxGateways;

    @Id
    @Column(name = "rewrite_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getRewriteId() {
        return rewriteId;
    }

    public void setRewriteId(String rewriteId) {
        this.rewriteId = rewriteId;
    }

    @Basic
    @Column(name = "gateway_id", nullable = true, length = 32)
    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    @Basic
    @Column(name = "prefix", nullable = true, length = 32)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Basic
    @Column(name = "rule_rm", nullable = true)
    public Integer getRuleRm() {
        return ruleRm;
    }

    public void setRuleRm(Integer ruleRm) {
        this.ruleRm = ruleRm;
    }

    @Basic
    @Column(name = "rule_add", nullable = true, length = 32)
    public String getRuleAdd() {
        return ruleAdd;
    }

    public void setRuleAdd(String ruleAdd) {
        this.ruleAdd = ruleAdd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PbxGwCalleeRewriteRules that = (PbxGwCalleeRewriteRules) o;

        if (rewriteId != null ? !rewriteId.equals(that.rewriteId) : that.rewriteId != null) return false;
        if (gatewayId != null ? !gatewayId.equals(that.gatewayId) : that.gatewayId != null) return false;
        if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
        if (ruleRm != null ? !ruleRm.equals(that.ruleRm) : that.ruleRm != null) return false;
        if (ruleAdd != null ? !ruleAdd.equals(that.ruleAdd) : that.ruleAdd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rewriteId != null ? rewriteId.hashCode() : 0;
        result = 31 * result + (gatewayId != null ? gatewayId.hashCode() : 0);
        result = 31 * result + (prefix != null ? prefix.hashCode() : 0);
        result = 31 * result + (ruleRm != null ? ruleRm.hashCode() : 0);
        result = 31 * result + (ruleAdd != null ? ruleAdd.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "gateway_id", referencedColumnName = "gw_id", updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.PbxGateways getPbxGateways() {
        return pbxGateways;
    }

    public void setPbxGateways(cn.cucsi.bsd.ucc.data.domain.PbxGateways pbxGateways) {
        this.pbxGateways = pbxGateways;
    }

    @Basic
    @Column(name = "created_user_id", nullable = true, length = 32)
    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
    @Basic
    @Column(name = "created_user_name", nullable = true, length = 32)
    public String getCreatedUserName() {
        return createdUserName;
    }

    public void setCreatedUserName(String createdUserName) {
        this.createdUserName = createdUserName;
    }
    @Basic
    @Column(name = "created_nick_name", nullable = true)
    public String getCreatedNickName() {
        return createdNickName;
    }

    public void setCreatedNickName(String createdNickName) {
        this.createdNickName = createdNickName;
    }
    @Basic
    @Column(name = "updated_user_id", nullable = true, length = 32)
    public String getUpdatedUserId() {
        return updatedUserId;
    }

    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId;
    }
    @Basic
    @Column(name = "updated_user_name", nullable = true, length = 32)
    public String getUpdatedUserName() {
        return updatedUserName;
    }

    public void setUpdatedUserName(String updatedUserName) {
        this.updatedUserName = updatedUserName;
    }
    @Basic
    @Column(name = "updated_nick_name", nullable = true)
    public String getUpdatedNickName() {
        return updatedNickName;
    }

    public void setUpdatedNickName(String updatedNickName) {
        this.updatedNickName = updatedNickName;
    }

    @Basic
    @Column(nullable = true)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Basic
    @Column(nullable = true)
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
