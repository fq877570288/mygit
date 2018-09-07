package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 根据查询条件获取当前坐席任务外乎列表--入参封装实体
 * add by wangxiaoyu
 * 2018-08-27
 */
@ApiModel
public class NoticeShowListCriteria extends BasicPageCriteria{

    @ApiModelProperty(value = "业务员ID",required = true)
    private String userId;

    @ApiModelProperty(value = "公告类型(0:公告 1：通知),APP无需传",required = false)
    private String noticeType;

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
