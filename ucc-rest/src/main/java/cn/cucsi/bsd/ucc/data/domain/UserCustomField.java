package cn.cucsi.bsd.ucc.data.domain;

/****
 * 员工-自定义字段表 关联中间表
 */
public class UserCustomField {
	
    private String userCustomfieldsId; //主键
    private String userId; //员工主键
    private String dataCustomfieldsId; //自定义字段表主键
    private String ieFlag; //导入导出标识（0：导入，1：导出）
    
    public static final String IMPORTFIELD = "0"; // 导入
    public static final String EXPORTFIELD = "1"; // 导出
    
    public String getUserCustomfieldsId() {
        return userCustomfieldsId;
    }

    public void setUserCustomfieldsId(String userCustomfieldsId) {
        this.userCustomfieldsId = userCustomfieldsId == null ? null : userCustomfieldsId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getDataCustomfieldsId() {
        return dataCustomfieldsId;
    }

    public void setDataCustomfieldsId(String dataCustomfieldsId) {
        this.dataCustomfieldsId = dataCustomfieldsId == null ? null : dataCustomfieldsId.trim();
    }

    public String getIeFlag() {
        return ieFlag;
    }

    public void setIeFlag(String ieFlag) {
        this.ieFlag = ieFlag == null ? null : ieFlag.trim();
    }
}