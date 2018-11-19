package com.resthome.vo;

import com.panjun.annotation.Name;
import com.resthome.entity.Employee;
import com.resthome.entity.OldPeople;
import com.resthome.entity.Volunteer;

public class VolunteerActivityVo {
	
	private String uuid;
	private String empNo;
	private String empName;
	private String vltNo;
	private String vltName;
	private String oldNo;
	private String oldName;
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
	
	@Name(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Name(name="employee",className="com.resthome.entity.Employee",relativeName="employeeNo")
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	@Name(name="employee",className="com.resthome.entity.Employee",relativeName="employeeName")
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Name(name="volunteer",className="com.resthome.entity.Volunteer",relativeName="volunteerNo")
	public String getVltNo() {
		return vltNo;
	}
	public void setVltNo(String vltNo) {
		this.vltNo = vltNo;
	}
	@Name(name="volunteer",className="com.resthome.entity.Volunteer",relativeName="volunteerName")
	public String getVltName() {
		return vltName;
	}
	public void setVltName(String vltName) {
		this.vltName = vltName;
	}
	@Name(name="oldPeople",className="com.resthome.entity.OldPeople",relativeName="oldPeopleNo")
	public String getOldNo() {
		return oldNo;
	}
	public void setOldNo(String oldNo) {
		this.oldNo = oldNo;
	}
	@Name(name="oldPeople",className="com.resthome.entity.OldPeople",relativeName="oldPeopleName")
	public String getOldName() {
		return oldName;
	}
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	@Name(name="activityContent")
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	@Name(name="activityType")
	public String getActivityType() {
		return activityType;
	}
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	@Name(name="activityPlace")
	public String getActivityPlace() {
		return activityPlace;
	}
	public void setActivityPlace(String activityPlace) {
		this.activityPlace = activityPlace;
	}
	@Name(name="startTime")
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	@Name(name="endTime")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Name(name="beginDate")
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	@Name(name="endDate")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Name(name="hours")
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	@Name(name="insertTime")
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	@Name(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
