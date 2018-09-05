package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by mk on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class UserExtCriteria  extends  BasicCriteria{
    private String extNum;

    public String getExtNum() {
        return extNum;
    }

    public void setExtNum(String extNum) {
        this.extNum = extNum;
    }
}
