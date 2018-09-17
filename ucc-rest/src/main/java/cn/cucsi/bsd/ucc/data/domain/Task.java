package cn.cucsi.bsd.ucc.data.domain;

/****
 * 任务表
 */
public class Task {
	
    private String taskId; //主键
    private String businessCode; //业务号码
    
    public static final String CREATENEW = "createNew"; // 新建任务
    public static final String CREATEOLD = "createOld"; // 替换现有任务
    
    public static final String ALLOCDEFULT = "allocdefult"; // 默认分派
    public static final String ALLOCMESH = "allocmesh"; // 分派到网格
    public static final String ALLOCAREA = "allocarea"; // 分派到包区

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }
}