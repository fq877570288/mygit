package cn.cucsi.bsd.ucc.common.beans;

/**
 * Created by jjjjj on 2017-10-16.
 */
import io.swagger.annotations.ApiModel;

@ApiModel
public class PbxMusicsCriteria  extends BasicCriteria   {
    private String musicName;
    private String contentType;
    private String domainId;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }
}
