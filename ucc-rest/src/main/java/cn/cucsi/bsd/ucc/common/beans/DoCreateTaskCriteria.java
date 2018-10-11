package cn.cucsi.bsd.ucc.common.beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/***
 * 生成任务 导入数据批次列表--入参封装实体
 * add by wangxiaoyu
 * 2018-09-14
 */
@ApiModel
public class DoCreateTaskCriteria {

    @ApiModelProperty(value = "任务操作类型",required = false)
    private String createMode;

    @ApiModelProperty(value = "新任务批次",required = false)
    private String barchs;

    @ApiModelProperty(value = "用户ID",required = true)
    private String userId;

    @ApiModelProperty(value = "用户所属部门ID",required = true)
    private String userDeptID;

    @ApiModelProperty(value = "旧任务批次",required = false)
    private String oldTaskBatch;

    public String getCreateMode() {
        return createMode;
    }

    public void setCreateMode(String createMode) {
        this.createMode = createMode;
    }

    public String getBarchs() {
        return barchs;
    }

    public void setBarchs(String barchs) {
        this.barchs = barchs;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOldTaskBatch() {
        return oldTaskBatch;
    }

    public void setOldTaskBatch(String oldTaskBatch) {
        this.oldTaskBatch = oldTaskBatch;
    }

    public String getUserDeptID() {
        return userDeptID;
    }

    public void setUserDeptID(String userDeptID) {
        this.userDeptID = userDeptID;
    }
}
