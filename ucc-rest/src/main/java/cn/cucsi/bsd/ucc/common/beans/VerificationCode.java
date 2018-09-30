package cn.cucsi.bsd.ucc.common.beans;

public class VerificationCode {
    private String imgId;//唯一标识
    private String imgBase64;//图片
    private String text;//内容

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getImgBase64() {
        return imgBase64;
    }

    public void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
