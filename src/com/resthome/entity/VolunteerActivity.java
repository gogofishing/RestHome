package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * VolunteerActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "volunteer_activity", catalog = "rest_home")
public class VolunteerActivity implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private Volunteer volunteer;
	private OldPeople oldPeople;
	private String activityContent;
	private String activityType;
	private String activityPlace;
	private String startTime;
	private String endTime;
	private String beginDate;
	private String endDate;
	private Integer hours;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public VolunteerActivity() {
	}

	/** minimal constructor */
	public VolunteerActivity(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public VolunteerActivity(String uuid, Employee employee,
			Volunteer volunteer, OldPeople oldPeople,String activityContent,
			String activityType,String activityPlace,String startTime,
			String endTime,String beginDate,String endDate,
			Integer hours, String insertTime, String status) {
		this.uuid = uuid;
		this.employee = employee;
		this.volunteer = volunteer;
		this.oldPeople = oldPeople;
		this.activityContent = activityContent;
		this.activityType = activityType;
		this.activityPlace = activityPlace;
		this.startTime = startTime;
		this.endTime = endTime;
		this.beginDate=beginDate;
		this.endDate=endDate;
		this.hours = hours;
		this.insertTime = insertTime;
		this.status = status;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_uuid")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "volunteer_uuid")
	public Volunteer getVolunteer() {
		return this.volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "oldpeople_uuid")
	public OldPeople getOldPeople() {
		return this.oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}

	@Column(name = "activity_content", length = 100)
	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	@Column(name = "activity_type", length = 45)
	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Column(name = "activity_place", length = 65535)
	public String getActivityPlace() {
		return activityPlace;
	}

	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	@Column(name = "start_time", length = 45)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 45)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "begin_date", length = 45)
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "end_date", length = 45)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "hours", length = 11)
	public Integer getHours() {
		return this.hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
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

}