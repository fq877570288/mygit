package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UserDeptCriteria extends  BasicCriteria {
    private String deptId;
    private String userId;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
