package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admin", catalog = "rest_home")
public class Admin implements java.io.Serializable {

	// Fields

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

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public Admin(String uuid, String adminName, String adminPwd,
			String identify, String oldpeopleAuthority,
			String employeeAuthority, String communityAuthority,
			String storageAuthority, String systemMetaAuthority, String status,
			String insertTime) {
		this.uuid = uuid;
		this.adminName = adminName;
		this.adminPwd = adminPwd;
		this.identify = identify;
		this.oldpeopleAuthority = oldpeopleAuthority;
		this.employeeAuthority = employeeAuthority;
		this.communityAuthority = communityAuthority;
		this.storageAuthority = storageAuthority;
		this.systemMetaAuthority = systemMetaAuthority;
		this.status = status;
		this.insertTime = insertTime;
	}

	// Property accessors
	@Id
	@Column(name = "uuid", unique = true, nullable = false, length = 36)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "admin_name", length = 100)
	public String getAdminName() {
		return this.adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	@Column(name = "admin_pwd", length = 100)
	public String getAdminPwd() {
		return this.adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	@Column(name = "identify", length = 45)
	public String getIdentify() {
		return this.identify;
	}

	public void setIdentify(String identify) {
		this.identify = identify;
	}

	@Column(name = "oldpeople_authority", length = 20)
	public String getOldpeopleAuthority() {
		return this.oldpeopleAuthority;
	}

	public void setOldpeopleAuthority(String oldpeopleAuthority) {
		this.oldpeopleAuthority = oldpeopleAuthority;
	}

	@Column(name = "employee_authority", length = 20)
	public String getEmployeeAuthority() {
		return this.employeeAuthority;
	}

	public void setEmployeeAuthority(String employeeAuthority) {
		this.employeeAuthority = employeeAuthority;
	}

	@Column(name = "community_authority", length = 20)
	public String getCommunityAuthority() {
		return this.communityAuthority;
	}

	public void setCommunityAuthority(String communityAuthority) {
		this.communityAuthority = communityAuthority;
	}

	@Column(name = "storage_authority", length = 20)
	public String getStorageAuthority() {
		return this.storageAuthority;
	}

	public void setStorageAuthority(String storageAuthority) {
		this.storageAuthority = storageAuthority;
	}

	@Column(name = "system_meta_authority", length = 20)
	public String getSystemMetaAuthority() {
		return this.systemMetaAuthority;
	}

	public void setSystemMetaAuthority(String systemMetaAuthority) {
		this.systemMetaAuthority = systemMetaAuthority;
	}

	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "insert_time", length = 45)
	public String getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

}