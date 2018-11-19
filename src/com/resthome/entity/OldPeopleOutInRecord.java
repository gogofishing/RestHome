package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OldPeopleOutInRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "old_people_out_in_record", catalog = "rest_home")
public class OldPeopleOutInRecord implements java.io.Serializable {

	// Fields

	private String uuid;
	private OldPeople oldPeople;
	private String recordDate;
	private String reason;
	private String beginTime;
	private String endTime;
	private String familyInfo;
	private String workPeopleName;
	private String insertTime;
	private String status;
	private String workPeopleNo;

	// Constructors

	/** default constructor */
	public OldPeopleOutInRecord() {
	}

	/** minimal constructor */
	public OldPeopleOutInRecord(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public OldPeopleOutInRecord(String uuid, OldPeople oldPeople,
			String recordDate, String reason, String beginTime, String endTime,
			String familyInfo, String workPeopleName, String insertTime,
			String status, String workPeopleNo) {
		this.uuid = uuid;
		this.oldPeople = oldPeople;
		this.recordDate = recordDate;
		this.reason = reason;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.familyInfo = familyInfo;
		this.workPeopleName = workPeopleName;
		this.insertTime = insertTime;
		this.status = status;
		this.workPeopleNo = workPeopleNo;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "old_people_uuid")
	public OldPeople getOldPeople() {
		return this.oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}

	@Column(name = "record_date", length = 45)
	public String getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "reason", length = 65535)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "begin_time", length = 45)
	public String getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "end_time", length = 45)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "family_info", length = 65535)
	public String getFamilyInfo() {
		return this.familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	@Column(name = "work_people_name")
	public String getWorkPeopleName() {
		return this.workPeopleName;
	}

	public void setWorkPeopleName(String workPeopleName) {
		this.workPeopleName = workPeopleName;
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

	@Column(name = "work_people_no")
	public String getWorkPeopleNo() {
		return this.workPeopleNo;
	}

	public void setWorkPeopleNo(String workPeopleNo) {
		this.workPeopleNo = workPeopleNo;
	}

}