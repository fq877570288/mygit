package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 将客户移至黑名单
 * add by wangxiaoyu
 * 2018-08-24
 */
@ApiModel
public class UccToBlackCriteria{

    @ApiModelProperty(value = "拉黑原因",required = true)
    private String pullBlackReason;
    @ApiModelProperty(value = "坐席员ID",required = false)
    private String userId;
    @ApiModelProperty(value = "客户ID",required = false)
    private String custId;
    @ApiModelProperty(value = "业务ID",required = false)
    private String businessCode;
    @ApiModelProperty(value = "释放时间",required = true)
    private String releaseTime;

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getPullBlackReason() {
        return pullBlackReason;
    }

    public void setPullBlackReason(String pullBlackReason) {
        this.pullBlackReason = pullBlackReason;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }
}
