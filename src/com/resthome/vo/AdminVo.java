package com.resthome.vo;

import com.panjun.annotation.Name;

public class AdminVo {
	private String uuid;
	private String adminName;
	private String adminPwd;
	private String identify;
	private String oldpeopleAuthority;
	private String employeeAuthority;
	private String communityAuthority;
	private String storageAuthority;
	private String systemMetaAuthority;
	private String status;
	private String insertTime;
	
	@Name(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	@Name(name="adminName")
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	@Name(name="adminPwd")
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	
	@Name(name="identify")
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	
	@Name(name="oldpeopleAuthority")
	public String getOldpeopleAuthority() {
		return oldpeopleAuthority;
	}
	public void setOldpeopleAuthority(String oldpeopleAuthority) {
		this.oldpeopleAuthority = oldpeopleAuthority;
	}
	
	@Name(name="employeeAuthority")
	public String getEmployeeAuthority() {
		return employeeAuthority;
	}
	public void setEmployeeAuthority(String employeeAuthority) {
		this.employeeAuthority = employeeAuthority;
	}
	
	@Name(name="communityAuthority")
	public String getCommunityAuthority() {
		return communityAuthority;
	}
	public void setCommunityAuthority(String communityAuthority) {
		this.communityAuthority = communityAuthority;
	}
	
	@Name(name="storageAuthority")
	public String getStorageAuthority() {
		return storageAuthority;
	}
	public void setStorageAuthority(String storageAuthority) {
		this.storageAuthority = storageAuthority;
	}
	
	@Name(name="systemMetaAuthority")
	public String getSystemMetaAuthority() {
		return systemMetaAuthority;
	}
	public void setSystemMetaAuthority(String systemMetaAuthority) {
		this.systemMetaAuthority = systemMetaAuthority;
	}
	
	@Name(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Name(name="insertTime")
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	
}
