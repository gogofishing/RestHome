package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CommunityActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "community_activity", catalog = "rest_home")
public class CommunityActivity implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private String activityName;	
	private String activityContent;
	private String activityType;
	private String activityPlace;
	private String startTime;
	private String endTime;
	private String beginDate;
	private String endDate;
	private String activityNumber;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public CommunityActivity() {
	}

	/** minimal constructor */
	public CommunityActivity(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public CommunityActivity(String uuid, Employee employee,
			String activityName, String activityContent, String activityPlace,
			String activityType,String startTime, String endTime,
			String beginDate,String endDate,
			String activityNumber,String insertTime, 
			String status) {
		this.uuid = uuid;
		this.employee = employee;
		this.activityName = activityName;
		this.activityContent=activityContent;
		this.activityPlace = activityPlace;
		this.activityType = activityType;
		this.startTime=startTime;
		this.endTime=endTime;
		this.beginDate=beginDate;
		this.endDate=endDate;
		this.activityNumber = activityNumber;
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

	@Column(name = "activity_name", length = 100)
	public String getActivityName() {
		return this.activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}	

	@Column(name = "activity_place", length = 65535)
	public String getActivityPlace() {
		return this.activityPlace;
	}

	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}

	@Column(name = "activity_type", length = 45)
	public String getActivityType() {
		return this.activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	@Column(name = "activity_content", length = 100)
	public String getActivityContent() {
		return activityContent;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	@Column(name = "start_time", length = 45)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "end_time", length = 45)
	public String getEndTime() {
		return endTime;
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

	@Column(name = "activity_number", length = 45)
	public String getActivityNumber() {
		return this.activityNumber;
	}

	public void setActivityNumber(String activityNumber) {
		this.activityNumber = activityNumber;
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