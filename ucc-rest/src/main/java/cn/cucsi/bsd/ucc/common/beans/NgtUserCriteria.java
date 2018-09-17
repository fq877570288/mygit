package cn.cucsi.bsd.ucc.common.beans;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户搜索类
 */
public class NgtUserCriteria extends BasicPageCriteria{
	
	private Long deptId;
	
	private Integer type;
	
	private Integer status;
	
	private String mobile;
	
	private String email;
	
	private String nickName;
	
	private Integer sex;
	
	private Integer ageBegin;
	
	private Integer ageEnd;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthdayBegin;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date birthdayEnd;
	
	private String tel;
	
	private String qq;
	
	private Integer address1;
	
	private Integer address2;
	
	private Integer address3;
	
	private Integer roleId;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date regTimeBegin;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date regTimeEnd;
	
	  private String userRoleName;
	  private String userName;
	  private Integer userType;
	  
	  
	private String deptMeshName; // 部门名称 Add by bli 2016-07-29
	
	private String deptIdAndChildIds; //部门IDs Add by bli 2017-02-15
	
	private String extNum; //分机 Add by bli 2017-03-24
	
	
	public String getExtNum() {
		return extNum;
	}

	public void setExtNum(String extNum) {
		this.extNum = extNum;
	}

	public String getDeptIdAndChildIds() {
		return deptIdAndChildIds;
	}

	public void setDeptIdAndChildIds(String deptIdAndChildIds) {
		this.deptIdAndChildIds = deptIdAndChildIds;
	}

	public String getDeptMeshName() {
		return deptMeshName;
	}

	public void setDeptMeshName(String deptMeshName) {
		this.deptMeshName = deptMeshName;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAgeBegin() {
		return ageBegin;
	}

	public void setAgeBegin(Integer ageBegin) {
		this.ageBegin = ageBegin;
	}

	public Integer getAgeEnd() {
		return ageEnd;
	}

	public void setAgeEnd(Integer ageEnd) {
		this.ageEnd = ageEnd;
	}

	public Date getBirthdayBegin() {
		return birthdayBegin;
	}

	public void setBirthdayBegin(Date birthdayBegin) {
		this.birthdayBegin = birthdayBegin;
	}

	public Date getBirthdayEnd() {
		return birthdayEnd;
	}

	public void setBirthdayEnd(Date birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Integer getAddress1() {
		return address1;
	}

	public void setAddress1(Integer address1) {
		this.address1 = address1;
	}

	public Integer getAddress2() {
		return address2;
	}

	public void setAddress2(Integer address2) {
		this.address2 = address2;
	}

	public Integer getAddress3() {
		return address3;
	}

	public void setAddress3(Integer address3) {
		this.address3 = address3;
	}

	public Date getRegTimeBegin() {
		return regTimeBegin;
	}

	public void setRegTimeBegin(Date regTimeBegin) {
		this.regTimeBegin = regTimeBegin;
	}

	public Date getRegTimeEnd() {
		return regTimeEnd;
	}

	public void setRegTimeEnd(Date regTimeEnd) {
		this.regTimeEnd = regTimeEnd;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserRoleName() {
		return userRoleName;
	}

	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
}
