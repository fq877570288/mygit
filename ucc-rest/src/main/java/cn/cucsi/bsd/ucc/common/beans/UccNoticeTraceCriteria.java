package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/13.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UccNoticeTraceCriteria extends  BasicCriteria {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
