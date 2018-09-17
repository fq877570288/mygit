package cn.cucsi.bsd.ucc.data.domain;

import java.io.Serializable;

/****
 * 数据自定义字段表
 * 	
 */
public class DataCustomfield implements Serializable{
	
	private static final long serialVersionUID = -2653956265397449498L;

	private String dataCustomfieldsId; //主键

    private String customfieldsCode; //自定义字段编码

    private String customfieldsName; //自定义字段名称

    private String isDefault; //是否为默认显示字段
    
    
    public static final String HIDE = "0"; // 默认不显示
    public static final String SHOW = "1"; // 默认显示
    

    public String getDataCustomfieldsId() {
        return dataCustomfieldsId;
    }

    public void setDataCustomfieldsId(String dataCustomfieldsId) {
        this.dataCustomfieldsId = dataCustomfieldsId == null ? null : dataCustomfieldsId.trim();
    }

    public String getCustomfieldsCode() {
        return customfieldsCode;
    }

    public void setCustomfieldsCode(String customfieldsCode) {
        this.customfieldsCode = customfieldsCode == null ? null : customfieldsCode.trim();
    }

    public String getCustomfieldsName() {
        return customfieldsName;
    }

    public void setCustomfieldsName(String customfieldsName) {
        this.customfieldsName = customfieldsName == null ? null : customfieldsName.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }
}