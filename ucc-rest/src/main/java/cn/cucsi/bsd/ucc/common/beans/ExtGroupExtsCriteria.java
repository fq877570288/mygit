package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by Song on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class ExtGroupExtsCriteria  extends BasicCriteria{
    private String groupId;
    private String extId;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }
}