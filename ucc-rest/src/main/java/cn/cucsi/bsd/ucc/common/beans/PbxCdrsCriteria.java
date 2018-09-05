package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by Song on 2017/10/16.
 */

public class PbxCdrsCriteria extends BasicCriteria{
    private String firstCaller;
    private String callerJobNumber;

    public String getfirstCaller() {
        return firstCaller;
    }

    public void setfirstCaller(String firstCaller) {
        this.firstCaller = firstCaller;
    }

    public String getcallerJobNumber() {
        return callerJobNumber;
    }

    public void setcallerJobNumber(String callerJobNumber) {
        this.callerJobNumber = callerJobNumber;
    }
}
