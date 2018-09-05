package cn.cucsi.bsd.ucc.common.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/***
 * 将客户移至黑名单
 * add by wangxiaoyu
 * 2018-08-24
 */
@ApiModel
public class UccToBlackCriteria{

    @ApiModelProperty(value = "用户类型",required = true)
    private Integer type;
    @ApiModelProperty(value = "业务编码",required = true)
    private String businesscode;
    @ApiModelProperty(value = "拉黑原因",required = true)
    private String pullBlackReason;
    @ApiModelProperty(value = "坐席员ID",required = true)
    private String userId;
    @ApiModelProperty(value = "更新时间",required = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

    public String getBusinesscode() {
        return businesscode;
    }

    public void setBusinesscode(String businesscode) {
        this.businesscode = businesscode;
    }
    public String getPullBlackReason() {
        return pullBlackReason;
    }

    public void setPullBlackReason(String pullBlackReason) {
        this.pullBlackReason = pullBlackReason;
    }
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
