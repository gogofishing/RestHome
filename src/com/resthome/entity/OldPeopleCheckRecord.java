package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OldPeopleCheckRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "old_people_check_record", catalog = "rest_home")
public class OldPeopleCheckRecord implements java.io.Serializable {

	// Fields

	private String uuid;
	private OldPeople oldPeople;
	private String checkInfoHtmlSource;
	private String checkProject;
	private String checkResultHtmlSource;
	private String nextCheckTime;
	private String workPeopleName;
	private String insertTime;
	private String status;
	private String workPeopleNo;

	// Constructors

	/** default constructor */
	public OldPeopleCheckRecord() {
	}

	/** minimal constructor */
	public OldPeopleCheckRecord(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public OldPeopleCheckRecord(String uuid, OldPeople oldPeople,
			String checkInfoHtmlSource, String checkProject,
			String checkResultHtmlSource, String nextCheckTime,
			String workPeopleName, String insertTime, String status,
			String workPeopleNo) {
		this.uuid = uuid;
		this.oldPeople = oldPeople;
		this.checkInfoHtmlSource = checkInfoHtmlSource;
		this.checkProject = checkProject;
		this.checkResultHtmlSource = checkResultHtmlSource;
		this.nextCheckTime = nextCheckTime;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "old_people_uuid")
	public OldPeople getOldPeople() {
		return this.oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}

	@Column(name = "check_info_html_source", length = 65535)
	public String getCheckInfoHtmlSource() {
		return this.checkInfoHtmlSource;
	}

	public void setCheckInfoHtmlSource(String checkInfoHtmlSource) {
		this.checkInfoHtmlSource = checkInfoHtmlSource;
	}

	@Column(name = "check_project", length = 65535)
	public String getCheckProject() {
		return this.checkProject;
	}

	public void setCheckProject(String checkProject) {
		this.checkProject = checkProject;
	}

	@Column(name = "check_result_html_source", length = 65535)
	public String getCheckResultHtmlSource() {
		return this.checkResultHtmlSource;
	}

	public void setCheckResultHtmlSource(String checkResultHtmlSource) {
		this.checkResultHtmlSource = checkResultHtmlSource;
	}

	@Column(name = "next_check_time", length = 45)
	public String getNextCheckTime() {
		return this.nextCheckTime;
	}

	public void setNextCheckTime(String nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
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