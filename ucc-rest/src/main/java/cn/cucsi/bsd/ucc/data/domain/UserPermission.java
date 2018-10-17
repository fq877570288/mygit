package cn.cucsi.bsd.ucc.data.domain;

public class UserPermission {
	
    @Override
	public String toString() {
		return "{\"userPermissionId\":\"" + userPermissionId + "\", \"userPermissionName\":\"" + userPermissionName + "\", \"userPermissionGroup\":\"" + userPermissionGroup + "\"}";
	}

	private Integer userPermissionId;

    private String userPermissionName;

    private Integer userPermissionGroup;
    
    private String userPermissionGroupName;
    
    private Boolean checked;

    public Integer getUserPermissionId() {
        return userPermissionId;
    }

    public void setUserPermissionId(Integer userPermissionId) {
        this.userPermissionId = userPermissionId;
    }

    public String getUserPermissionName() {
        return userPermissionName;
    }

    public void setUserPermissionName(String userPermissionName) {
        this.userPermissionName = userPermissionName == null ? null : userPermissionName.trim();
    }

    public Integer getUserPermissionGroup() {
        return userPermissionGroup;
    }

    public void setUserPermissionGroup(Integer userPermissionGroup) {
        this.userPermissionGroup = userPermissionGroup;
    }

	public String getUserPermissionGroupName() {
		return userPermissionGroupName;
	}

	public void setUserPermissionGroupName(String userPermissionGroupName) {
		this.userPermissionGroupName = userPermissionGroupName;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}