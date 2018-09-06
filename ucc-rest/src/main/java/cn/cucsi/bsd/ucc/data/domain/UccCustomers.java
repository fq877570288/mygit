package cn.cucsi.bsd.ucc.data.domain;

import cn.cucsi.bsd.ucc.common.JSONView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ucc_customers", schema = "ucc", catalog = "")
public class UccCustomers {
    private String custId;
    private String custCode;
    private String custName;
    private Integer sex;
    private String phone;
    private String phone2;
    private String phone3;
    private String fax;
    private Integer address1;
    private Integer address2;
    private Integer address3;
    private String address4;
    private Integer type;
    private Integer msgResource;
    private String memo;
    private String idcard;
    private Date birthday;
    private String qq;
    private String email;
    private String companyname;
    private Integer companynature;
    private Integer industrytype;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date createtime;
    private String changephone1;
    private String changephone2;
    private String changephone3;
    private String businesscode;
    private String contacts;
    private String status;
    private String installedaddress;
    private String ponlogo;
    private String tariffname;
    private String defultPhone;
    private String productType;
    private String rate;
    private String pullBlackReason;
    private String domainId;
    //不是这张表的字段它是关联表字段
    private String domainName;
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
    private UccDomain uccDomain;

    @Id
    @Column(name = "cust_id", nullable = false, length = 32)
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Basic
    @Column(name = "cust_code", nullable = true, length = 50)
    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    @Basic
    @Column(name = "cust_name", nullable = true, length = 30)
    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    @Basic
    @Column(name = "sex", nullable = true)
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "phone2", nullable = true, length = 30)
    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Basic
    @Column(name = "phone3", nullable = true, length = 30)
    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    @Basic
    @Column(name = "fax", nullable = true, length = 30)
    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Basic
    @Column(name = "address1", nullable = true)
    public Integer getAddress1() {
        return address1;
    }

    public void setAddress1(Integer address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "address2", nullable = true)
    public Integer getAddress2() {
        return address2;
    }

    public void setAddress2(Integer address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "address3", nullable = true)
    public Integer getAddress3() {
        return address3;
    }

    public void setAddress3(Integer address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "address4", nullable = true, length = 10)
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
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
    @Column(name = "msg_resource", nullable = true)
    public Integer getMsgResource() {
        return msgResource;
    }

    public void setMsgResource(Integer msgResource) {
        this.msgResource = msgResource;
    }

    @Basic
    @Column(name = "memo", nullable = true, length = 10)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Basic
    @Column(name = "idcard", nullable = true, length = 30)
    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 20)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "companyname", nullable = true, length = 50)
    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Basic
    @Column(name = "companynature", nullable = true)
    public Integer getCompanynature() {
        return companynature;
    }

    public void setCompanynature(Integer companynature) {
        this.companynature = companynature;
    }

    @Basic
    @Column(name = "industrytype", nullable = true)
    public Integer getIndustrytype() {
        return industrytype;
    }

    public void setIndustrytype(Integer industrytype) {
        this.industrytype = industrytype;
    }



    @Basic
    @Column(name = "changephone1", nullable = true, length = 30)
    public String getChangephone1() {
        return changephone1;
    }

    public void setChangephone1(String changephone1) {
        this.changephone1 = changephone1;
    }

    @Basic
    @Column(name = "changephone2", nullable = true, length = 30)
    public String getChangephone2() {
        return changephone2;
    }

    public void setChangephone2(String changephone2) {
        this.changephone2 = changephone2;
    }

    @Basic
    @Column(name = "changephone3", nullable = true, length = 30)
    public String getChangephone3() {
        return changephone3;
    }

    public void setChangephone3(String changephone3) {
        this.changephone3 = changephone3;
    }

    @Basic
    @Column(name = "businesscode", nullable = true, length = 45)
    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }

    @Basic
    @Column(name = "contacts", nullable = true, length = 30)
    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "installedaddress", nullable = true, length = 500)
    public String getInstalledaddress() {
        return installedaddress;
    }

    public void setInstalledaddress(String installedaddress) {
        this.installedaddress = installedaddress;
    }

    @Basic
    @Column(name = "ponlogo", nullable = true, length = 45)
    public String getPonlogo() {
        return ponlogo;
    }

    public void setPonlogo(String ponlogo) {
        this.ponlogo = ponlogo;
    }

    @Basic
    @Column(name = "tariffname", nullable = true, length = 200)
    public String getTariffname() {
        return tariffname;
    }

    public void setTariffname(String tariffname) {
        this.tariffname = tariffname;
    }

    @Basic
    @Column(name = "defult_phone", nullable = true, length = 45)
    public String getDefultPhone() {
        return defultPhone;
    }

    public void setDefultPhone(String defultPhone) {
        this.defultPhone = defultPhone;
    }

    @Basic
    @Column(name = "product_type", nullable = true, length = 45)
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Basic
    @Column(name = "rate", nullable = true, length = 45)
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Basic
    @Column(name = "pull_black_reason", nullable = true, length = 500)
    public String getPullBlackReason() {
        return pullBlackReason;
    }

    public void setPullBlackReason(String pullBlackReason) {
        this.pullBlackReason = pullBlackReason;
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

        UccCustomers that = (UccCustomers) o;

        if (custId != null ? !custId.equals(that.custId) : that.custId != null) return false;
        if (custCode != null ? !custCode.equals(that.custCode) : that.custCode != null) return false;
        if (custName != null ? !custName.equals(that.custName) : that.custName != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null) return false;
        if (phone3 != null ? !phone3.equals(that.phone3) : that.phone3 != null) return false;
        if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
        if (address1 != null ? !address1.equals(that.address1) : that.address1 != null) return false;
        if (address2 != null ? !address2.equals(that.address2) : that.address2 != null) return false;
        if (address3 != null ? !address3.equals(that.address3) : that.address3 != null) return false;
        if (address4 != null ? !address4.equals(that.address4) : that.address4 != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (msgResource != null ? !msgResource.equals(that.msgResource) : that.msgResource != null) return false;
        if (memo != null ? !memo.equals(that.memo) : that.memo != null) return false;
        if (idcard != null ? !idcard.equals(that.idcard) : that.idcard != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (qq != null ? !qq.equals(that.qq) : that.qq != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (companyname != null ? !companyname.equals(that.companyname) : that.companyname != null) return false;
        if (companynature != null ? !companynature.equals(that.companynature) : that.companynature != null)
            return false;
        if (industrytype != null ? !industrytype.equals(that.industrytype) : that.industrytype != null) return false;
//        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (changephone1 != null ? !changephone1.equals(that.changephone1) : that.changephone1 != null) return false;
        if (changephone2 != null ? !changephone2.equals(that.changephone2) : that.changephone2 != null) return false;
        if (changephone3 != null ? !changephone3.equals(that.changephone3) : that.changephone3 != null) return false;
        if (businesscode != null ? !businesscode.equals(that.businesscode) : that.businesscode != null) return false;
        if (contacts != null ? !contacts.equals(that.contacts) : that.contacts != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (installedaddress != null ? !installedaddress.equals(that.installedaddress) : that.installedaddress != null)
            return false;
        if (ponlogo != null ? !ponlogo.equals(that.ponlogo) : that.ponlogo != null) return false;
        if (tariffname != null ? !tariffname.equals(that.tariffname) : that.tariffname != null) return false;
        if (defultPhone != null ? !defultPhone.equals(that.defultPhone) : that.defultPhone != null) return false;
        if (productType != null ? !productType.equals(that.productType) : that.productType != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        if (pullBlackReason != null ? !pullBlackReason.equals(that.pullBlackReason) : that.pullBlackReason != null)
            return false;
        if (domainId != null ? !domainId.equals(that.domainId) : that.domainId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = custId != null ? custId.hashCode() : 0;
        result = 31 * result + (custCode != null ? custCode.hashCode() : 0);
        result = 31 * result + (custName != null ? custName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
        result = 31 * result + (phone3 != null ? phone3.hashCode() : 0);
        result = 31 * result + (fax != null ? fax.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (address3 != null ? address3.hashCode() : 0);
        result = 31 * result + (address4 != null ? address4.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (msgResource != null ? msgResource.hashCode() : 0);
        result = 31 * result + (memo != null ? memo.hashCode() : 0);
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (companyname != null ? companyname.hashCode() : 0);
        result = 31 * result + (companynature != null ? companynature.hashCode() : 0);
        result = 31 * result + (industrytype != null ? industrytype.hashCode() : 0);
//        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (changephone1 != null ? changephone1.hashCode() : 0);
        result = 31 * result + (changephone2 != null ? changephone2.hashCode() : 0);
        result = 31 * result + (changephone3 != null ? changephone3.hashCode() : 0);
        result = 31 * result + (businesscode != null ? businesscode.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (installedaddress != null ? installedaddress.hashCode() : 0);
        result = 31 * result + (ponlogo != null ? ponlogo.hashCode() : 0);
        result = 31 * result + (tariffname != null ? tariffname.hashCode() : 0);
        result = 31 * result + (defultPhone != null ? defultPhone.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (pullBlackReason != null ? pullBlackReason.hashCode() : 0);
        result = 31 * result + (domainId != null ? domainId.hashCode() : 0);
        return result;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
