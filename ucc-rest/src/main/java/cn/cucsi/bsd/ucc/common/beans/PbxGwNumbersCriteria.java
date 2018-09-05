package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by jjjjj on 2017-10-16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxGwNumbersCriteria  extends BasicCriteria   {
    private String gwNumber;
    private String gatewayId;

    public String getGwNumber() {
        return gwNumber;
    }

    public void setGwNumber(String gwNumber) {
        this.gwNumber = gwNumber;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
