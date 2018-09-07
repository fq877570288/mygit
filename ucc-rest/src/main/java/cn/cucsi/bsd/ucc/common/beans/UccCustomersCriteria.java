package cn.cucsi.bsd.ucc.common.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by tianyuwei on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccCustomersCriteria extends BasicCriteria {
    String custCode;
    String custName;
    private String phone;
    private Integer type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtimeFrome;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtimeTo;
    private String domainId;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatetimeFrome() {
        return createtimeFrome;
    }

    public void setCreatetimeFrome(Date createtimeFrome) {
        this.createtimeFrome = createtimeFrome;
    }

    public Date getCreatetimeTo() {
        return createtimeTo;
    }

    public void setCreatetimeTo(Date createtimeTo) {
        this.createtimeTo = createtimeTo;
    }
}
