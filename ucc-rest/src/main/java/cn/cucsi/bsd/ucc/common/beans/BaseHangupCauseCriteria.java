package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by home on 2017/10/16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class BaseHangupCauseCriteria extends BasicCriteria{
    private String causeEn;
    private String causeCn;

    public String getCauseEn() {
        return causeEn;
    }

    public void setCauseEn(String causeEn) {
        this.causeEn = causeEn;
    }

    public String getCauseCn() {
        return causeCn;
    }

    public void setCauseCn(String causeCn) {
        this.causeCn = causeCn;
    }
}
