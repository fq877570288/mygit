package cn.cucsi.bsd.ucc.common.beans;

public class NgtDeptSearch {
	
	private String deptPid;//上级部门
    
	private Integer deptLevel;//部门级别
    
	private String deptId;//部门编码

	private String deptIdAndChildIds; //部门ID以及自部门ID字符串，逗号分隔;

	private String deptIds; //部门ID字符串，逗号分隔;

	/*
	* 入参传以下这些
	* */
	private String centerID;

	private String meshID;

	private String areaID;

	private String userId;

	private String domainId;

	public String getDeptPid() {
		return deptPid;
	}

	public void setDeptPid(String deptPid) {
		this.deptPid = deptPid;
	}

	public Integer getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(Integer deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptIdAndChildIds() {
		return deptIdAndChildIds;
	}

	public void setDeptIdAndChildIds(String deptIdAndChildIds) {
		this.deptIdAndChildIds = deptIdAndChildIds;
	}

	public String getCenterID() {
		return centerID;
	}

	public void setCenterID(String centerID) {
		this.centerID = centerID;
	}

	public String getMeshID() {
		return meshID;
	}

	public void setMeshID(String meshID) {
		this.meshID = meshID;
	}

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
}
