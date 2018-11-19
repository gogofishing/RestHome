package com.resthome.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Volunteer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "volunteer", catalog = "rest_home")
public class Volunteer implements java.io.Serializable {

	// Fields

	private String uuid;
	private String volunteerNo;
	private String volunteerName;
	private String idCard;
	private String sex;
	private String phone;
	private String email;
	private String address;
	private Integer workHours;
	private String insertTime;
	private String status;
	private Set<VolunteerActivity> volunteerActivities = new HashSet<VolunteerActivity>(0);

	// Constructors

	/** default constructor */
	public Volunteer() {
	}

	/** minimal constructor */
	public Volunteer(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public Volunteer(String uuid, String volunteerNo, String volunteerName,
			String idCard, String sex, String phone, String email,
			String address, Integer workHours, String insertTime,
			String status, Set volunteerActivities) {
		this.uuid = uuid;
		this.volunteerNo = volunteerNo;
		this.volunteerName = volunteerName;
		this.idCard = idCard;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.workHours = workHours;
		this.insertTime = insertTime;
		this.status = status;
		this.volunteerActivities = volunteerActivities;
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

	@Column(name = "volunteer_no", length = 100)
	public String getVolunteerNo() {
		return this.volunteerNo;
	}

	public void setVolunteerNo(String volunteerNo) {
		this.volunteerNo = volunteerNo;
	}

	@Column(name = "volunteer_name", length = 100)
	public String getVolunteerName() {
		return this.volunteerName;
	}

	public void setVolunteerName(String volunteerName) {
		this.volunteerName = volunteerName;
	}

	@Column(name = "id_card", length = 60)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "sex", length = 45)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "work_hours", length = 11)
	public Integer getWorkHours() {
		return this.workHours;
	}

	public void setWorkHours(Integer workHours) {
		this.workHours = workHours;
	}

	@Column(name = "insert_time", length = 45)
	public String getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "volunteer")
	public Set<VolunteerActivity> getVolunteerActivities() {
		return this.volunteerActivities;
	}

	public void setVolunteerActivities(Set<VolunteerActivity> volunteerActivities) {
		this.volunteerActivities = volunteerActivities;
	}

}