package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by tianyuwei on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class SkillGroupUserCriteria extends BasicCriteria{
    String skillGroupId;
    String userId;
    private  String userName;


    public String getSkillGroupId() {
        return skillGroupId;
    }

    public void setSkillGroupId(String skillGroupId) {
        this.skillGroupId = skillGroupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
