package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "pbx_ivrs", schema = "ucc", catalog = "")
public class PbxIvrs {

    @JsonView(JSONView.Summary.class)
    private String ivrId;

    @JsonView(JSONView.Summary.class)
    private String ivrName;

    @JsonView(JSONView.Summary.class)
    private Integer keyTimeout;

    @JsonView(JSONView.Summary.class)
    private Integer setupType;

    @JsonView(JSONView.Summary.class)
    private String setupContent;

    @JsonView(JSONView.Summary.class)
    private Integer type;

    @JsonView(JSONView.Summary.class)
    private Integer status;

    @JsonView(JSONView.Summary.class)
    private String memo;

    @JsonView(JSONView.Summary.class)
    private Integer key0Type;

    @JsonView(JSONView.Summary.class)
    private String key0Content;

    @JsonView(JSONView.Summary.class)
    private Integer key1Type;

    @JsonView(JSONView.Summary.class)
    private String key1Content;

    @JsonView(JSONView.Summary.class)
    private Integer key2Type;

    @JsonView(JSONView.Summary.class)
    private String key2Content;

    @JsonView(JSONView.Summary.class)
    private Integer key3Type;

    @JsonView(JSONView.Summary.class)
    private String key3Content;

    @JsonView(JSONView.Summary.class)
    private Integer key4Type;

    @JsonView(JSONView.Summary.class)
    private String key4Content;

    @JsonView(JSONView.Summary.class)
    private Integer key5Type;

    @JsonView(JSONView.Summary.class)
    private String key5Content;

    @JsonView(JSONView.Summary.class)
    private Integer key6Type;

    @JsonView(JSONView.Summary.class)
    private String key6Content;

    @JsonView(JSONView.Summary.class)
    private Integer key7Type;

    @JsonView(JSONView.Summary.class)
    private String key7Content;

    @JsonView(JSONView.Summary.class)
    private Integer key8Type;

    @JsonView(JSONView.Summary.class)
    private String key8Content;

    @JsonView(JSONView.Summary.class)
    private Integer key9Type;

    @JsonView(JSONView.Summary.class)
    private String key9Content;

    @JsonView(JSONView.Summary.class)
    private Integer keyxType;

    @JsonView(JSONView.Summary.class)
    private String keyxContent;

    @JsonView(JSONView.Summary.class)
    private String keyaContent;

    @JsonView(JSONView.Summary.class)
    private Integer keyaType;

    @JsonView(JSONView.Summary.class)
    private String domainId;

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


    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdTime;

    @JsonView(JSONView.Summary.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedTime;

    @JsonView(JSONView.Summary.class)
    private Collection<PbxCdrDigit> pbxCdrDigits;

    @JsonView(JSONView.PbxIvrsWithDomain.class)
    private UccDomain uccDomain;


    @Id
    @Column(name = "ivr_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getIvrId() {
        return ivrId;
    }

    public void setIvrId(String ivrId) {
        this.ivrId = ivrId;
    }

    @Basic
    @Column(name = "ivr_name", nullable = false, length = 50)
    public String getIvrName() {
        return ivrName;
    }

    public void setIvrName(String ivrName) {
        this.ivrName = ivrName;
    }

    @Basic
    @Column(name = "key_timeout", nullable = true)
    public Integer getKeyTimeout() {
        return keyTimeout;
    }

    public void setKeyTimeout(Integer keyTimeout) {
        this.keyTimeout = keyTimeout;
    }

    @Basic
    @Column(name = "setup_type", nullable = true)
    public Integer getSetupType() {
        return setupType;
    }

    public void setSetupType(Integer setupType) {
        this.setupType = setupType;
    }

    @Basic
    @Column(name = "setup_content", nullable = true, length = 255)
    public String getSetupContent() {
        return setupContent;
    }

    public void setSetupContent(String setupContent) {
        this.setupContent = setupContent;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 255)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "key0_type", nullable = true)
    public Integer getKey0Type() {
        return key0Type;
    }

    public void setKey0Type(Integer key0Type) {
        this.key0Type = key0Type;
    }

    @Basic
    @Column(name = "key0_content", nullable = true, length = 255)
    public String getKey0Content() {
        return key0Content;
    }

    public void setKey0Content(String key0Content) {
        this.key0Content = key0Content;
    }

    @Basic
    @Column(name = "key1_type", nullable = true)
    public Integer getKey1Type() {
        return key1Type;
    }

    public void setKey1Type(Integer key1Type) {
        this.key1Type = key1Type;
    }

    @Basic
    @Column(name = "key1_content", nullable = true, length = 255)
    public String getKey1Content() {
        return key1Content;
    }

    public void setKey1Content(String key1Content) {
        this.key1Content = key1Content;
    }

    @Basic
    @Column(name = "key2_type", nullable = true)
    public Integer getKey2Type() {
        return key2Type;
    }

    public void setKey2Type(Integer key2Type) {
        this.key2Type = key2Type;
    }

    @Basic
    @Column(name = "key2_content", nullable = true, length = 255)
    public String getKey2Content() {
        return key2Content;
    }

    public void setKey2Content(String key2Content) {
        this.key2Content = key2Content;
    }

    @Basic
    @Column(name = "key3_type", nullable = true)
    public Integer getKey3Type() {
        return key3Type;
    }

    public void setKey3Type(Integer key3Type) {
        this.key3Type = key3Type;
    }

    @Basic
    @Column(name = "key3_content", nullable = true, length = 255)
    public String getKey3Content() {
        return key3Content;
    }

    public void setKey3Content(String key3Content) {
        this.key3Content = key3Content;
    }

    @Basic
    @Column(name = "key4_type", nullable = true)
    public Integer getKey4Type() {
        return key4Type;
    }

    public void setKey4Type(Integer key4Type) {
        this.key4Type = key4Type;
    }

    @Basic
    @Column(name = "key4_content", nullable = true, length = 255)
    public String getKey4Content() {
        return key4Content;
    }

    public void setKey4Content(String key4Content) {
        this.key4Content = key4Content;
    }

    @Basic
    @Column(name = "key5_type", nullable = true)
    public Integer getKey5Type() {
        return key5Type;
    }

    public void setKey5Type(Integer key5Type) {
        this.key5Type = key5Type;
    }

    @Basic
    @Column(name = "key5_content", nullable = true, length = 255)
    public String getKey5Content() {
        return key5Content;
    }

    public void setKey5Content(String key5Content) {
        this.key5Content = key5Content;
    }

    @Basic
    @Column(name = "key6_type", nullable = true)
    public Integer getKey6Type() {
        return key6Type;
    }

    public void setKey6Type(Integer key6Type) {
        this.key6Type = key6Type;
    }

    @Basic
    @Column(name = "key6_content", nullable = true, length = 255)
    public String getKey6Content() {
        return key6Content;
    }

    public void setKey6Content(String key6Content) {
        this.key6Content = key6Content;
    }

    @Basic
    @Column(name = "key7_type", nullable = true)
    public Integer getKey7Type() {
        return key7Type;
    }

    public void setKey7Type(Integer key7Type) {
        this.key7Type = key7Type;
    }

    @Basic
    @Column(name = "key7_content", nullable = true, length = 255)
    public String getKey7Content() {
        return key7Content;
    }

    public void setKey7Content(String key7Content) {
        this.key7Content = key7Content;
    }

    @Basic
    @Column(name = "key8_type", nullable = true)
    public Integer getKey8Type() {
        return key8Type;
    }

    public void setKey8Type(Integer key8Type) {
        this.key8Type = key8Type;
    }

    @Basic
    @Column(name = "key8_content", nullable = true, length = 255)
    public String getKey8Content() {
        return key8Content;
    }

    public void setKey8Content(String key8Content) {
        this.key8Content = key8Content;
    }

    @Basic
    @Column(name = "key9_type", nullable = true)
    public Integer getKey9Type() {
        return key9Type;
    }

    public void setKey9Type(Integer key9Type) {
        this.key9Type = key9Type;
    }

    @Basic
    @Column(name = "key9_content", nullable = true, length = 255)
    public String getKey9Content() {
        return key9Content;
    }

    public void setKey9Content(String key9Content) {
        this.key9Content = key9Content;
    }

    @Basic
    @Column(name = "keyx_type", nullable = true)
    public Integer getKeyxType() {
        return keyxType;
    }

    public void setKeyxType(Integer keyxType) {
        this.keyxType = keyxType;
    }

    @Basic
    @Column(name = "keyx_content", nullable = true, length = 255)
    public String getKeyxContent() {
        return keyxContent;
    }

    public void setKeyxContent(String keyxContent) {
        this.keyxContent = keyxContent;
    }

    @Basic
    @Column(name = "keya_content", nullable = true, length = 255)
    public String getKeyaContent() {
        return keyaContent;
    }

    public void setKeyaContent(String keyaContent) {
        this.keyaContent = keyaContent;
    }

    @Basic
    @Column(name = "keya_type", nullable = true)
    public Integer getKeyaType() {
        return keyaType;
    }

    public void setKeyaType(Integer keyaType) {
        this.keyaType = keyaType;
    }

    @Basic
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

        PbxIvrs pbxIvrs = (PbxIvrs) o;

        if (ivrId != null ? !ivrId.equals(pbxIvrs.ivrId) : pbxIvrs.ivrId != null) return false;
        if (ivrName != null ? !ivrName.equals(pbxIvrs.ivrName) : pbxIvrs.ivrName != null) return false;
        if (keyTimeout != null ? !keyTimeout.equals(pbxIvrs.keyTimeout) : pbxIvrs.keyTimeout != null) return false;
        if (setupType != null ? !setupType.equals(pbxIvrs.setupType) : pbxIvrs.setupType != null) return false;
        if (setupContent != null ? !setupContent.equals(pbxIvrs.setupContent) : pbxIvrs.setupContent != null)
            return false;
        if (type != null ? !type.equals(pbxIvrs.type) : pbxIvrs.type != null) return false;
        if (status != null ? !status.equals(pbxIvrs.status) : pbxIvrs.status != null) return false;
        if (memo != null ? !memo.equals(pbxIvrs.memo) : pbxIvrs.memo != null) return false;
        if (key0Type != null ? !key0Type.equals(pbxIvrs.key0Type) : pbxIvrs.key0Type != null) return false;
        if (key0Content != null ? !key0Content.equals(pbxIvrs.key0Content) : pbxIvrs.key0Content != null) return false;
        if (key1Type != null ? !key1Type.equals(pbxIvrs.key1Type) : pbxIvrs.key1Type != null) return false;
        if (key1Content != null ? !key1Content.equals(pbxIvrs.key1Content) : pbxIvrs.key1Content != null) return false;
        if (key2Type != null ? !key2Type.equals(pbxIvrs.key2Type) : pbxIvrs.key2Type != null) return false;
        if (key2Content != null ? !key2Content.equals(pbxIvrs.key2Content) : pbxIvrs.key2Content != null) return false;
        if (key3Type != null ? !key3Type.equals(pbxIvrs.key3Type) : pbxIvrs.key3Type != null) return false;
        if (key3Content != null ? !key3Content.equals(pbxIvrs.key3Content) : pbxIvrs.key3Content != null) return false;
        if (key4Type != null ? !key4Type.equals(pbxIvrs.key4Type) : pbxIvrs.key4Type != null) return false;
        if (key4Content != null ? !key4Content.equals(pbxIvrs.key4Content) : pbxIvrs.key4Content != null) return false;
        if (key5Type != null ? !key5Type.equals(pbxIvrs.key5Type) : pbxIvrs.key5Type != null) return false;
        if (key5Content != null ? !key5Content.equals(pbxIvrs.key5Content) : pbxIvrs.key5Content != null) return false;
        if (key6Type != null ? !key6Type.equals(pbxIvrs.key6Type) : pbxIvrs.key6Type != null) return false;
        if (key6Content != null ? !key6Content.equals(pbxIvrs.key6Content) : pbxIvrs.key6Content != null) return false;
        if (key7Type != null ? !key7Type.equals(pbxIvrs.key7Type) : pbxIvrs.key7Type != null) return false;
        if (key7Content != null ? !key7Content.equals(pbxIvrs.key7Content) : pbxIvrs.key7Content != null) return false;
        if (key8Type != null ? !key8Type.equals(pbxIvrs.key8Type) : pbxIvrs.key8Type != null) return false;
        if (key8Content != null ? !key8Content.equals(pbxIvrs.key8Content) : pbxIvrs.key8Content != null) return false;
        if (key9Type != null ? !key9Type.equals(pbxIvrs.key9Type) : pbxIvrs.key9Type != null) return false;
        if (key9Content != null ? !key9Content.equals(pbxIvrs.key9Content) : pbxIvrs.key9Content != null) return false;
        if (keyxType != null ? !keyxType.equals(pbxIvrs.keyxType) : pbxIvrs.keyxType != null) return false;
        if (keyxContent != null ? !keyxContent.equals(pbxIvrs.keyxContent) : pbxIvrs.keyxContent != null) return false;
        if (keyaContent != null ? !keyaContent.equals(pbxIvrs.keyaContent) : pbxIvrs.keyaContent != null) return false;
        if (keyaType != null ? !keyaType.equals(pbxIvrs.keyaType) : pbxIvrs.keyaType != null) return false;
        if (domainId != null ? !domainId.equals(pbxIvrs.domainId) : pbxIvrs.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ivrId != null ? ivrId.hashCode() : 0;
        result = 31 * result + (ivrName != null ? ivrName.hashCode() : 0);
        result = 31 * result + (keyTimeout != null ? keyTimeout.hashCode() : 0);
        result = 31 * result + (setupType != null ? setupType.hashCode() : 0);
        result = 31 * result + (setupContent != null ? setupContent.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (key0Type != null ? key0Type.hashCode() : 0);
        result = 31 * result + (key0Content != null ? key0Content.hashCode() : 0);
        result = 31 * result + (key1Type != null ? key1Type.hashCode() : 0);
        result = 31 * result + (key1Content != null ? key1Content.hashCode() : 0);
        result = 31 * result + (key2Type != null ? key2Type.hashCode() : 0);
        result = 31 * result + (key2Content != null ? key2Content.hashCode() : 0);
        result = 31 * result + (key3Type != null ? key3Type.hashCode() : 0);
        result = 31 * result + (key3Content != null ? key3Content.hashCode() : 0);
        result = 31 * result + (key4Type != null ? key4Type.hashCode() : 0);
        result = 31 * result + (key4Content != null ? key4Content.hashCode() : 0);
        result = 31 * result + (key5Type != null ? key5Type.hashCode() : 0);
        result = 31 * result + (key5Content != null ? key5Content.hashCode() : 0);
        result = 31 * result + (key6Type != null ? key6Type.hashCode() : 0);
        result = 31 * result + (key6Content != null ? key6Content.hashCode() : 0);
        result = 31 * result + (key7Type != null ? key7Type.hashCode() : 0);
        result = 31 * result + (key7Content != null ? key7Content.hashCode() : 0);
        result = 31 * result + (key8Type != null ? key8Type.hashCode() : 0);
        result = 31 * result + (key8Content != null ? key8Content.hashCode() : 0);
        result = 31 * result + (key9Type != null ? key9Type.hashCode() : 0);
        result = 31 * result + (key9Content != null ? key9Content.hashCode() : 0);
        result = 31 * result + (keyxType != null ? keyxType.hashCode() : 0);
        result = 31 * result + (keyxContent != null ? keyxContent.hashCode() : 0);
        result = 31 * result + (keyaContent != null ? keyaContent.hashCode() : 0);
        result = 31 * result + (keyaType != null ? keyaType.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "pbxIvr")
    public Collection<PbxCdrDigit> getPbxCdrDigits() {
        return pbxCdrDigits;
    }

    public void setPbxCdrDigits(Collection<PbxCdrDigit> pbxCdrDigits) {
        this.pbxCdrDigits = pbxCdrDigits;
    }

    @ManyToOne
    @JoinColumn(name = "domain_id", referencedColumnName = "domain_id", nullable = false, updatable = false, insertable = false)
    public cn.cucsi.bsd.ucc.data.domain.UccDomain getUccDomain() {
        return uccDomain;
    }

    public void setUccDomain(cn.cucsi.bsd.ucc.data.domain.UccDomain uccDomain) {
        this.uccDomain = uccDomain;
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
