package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 查询通知详情--入参封装实体
 * add by wangxiaoyu
 * 2018-09-07
 */
@ApiModel
public class ShowNoticeDetailCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "公告主键",required = true)
    private String noticeId;

    @ApiModelProperty(value = "已读标识(未读：0,已读：1)",required = true)
    private String flag;

    @ApiModelProperty(value = "业务员ID",required = true)
    private String userId;

    @ApiModelProperty(value = "公告类型(0:公告 1：通知),APP无需传",required = false)
    private String noticeType;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }
}
