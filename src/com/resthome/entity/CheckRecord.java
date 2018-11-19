/*package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

*//**
 * CheckRecord entity. @author MyEclipse Persistence Tools
 *//*
@Entity
@Table(name = "check_record", catalog = "rest_home")
public class CheckRecord implements java.io.Serializable {

	// Fields

	private String uuid;
	private OldPeople oldPeople;
	private String checkInfoHtmlSource;
	private String checkProject;
	private String checkResultHtmlSource;
	private String nextCheckTime;
	private String workPeople;
	private String insertTime;
	private String status;

	// Constructors

	*//** default constructor *//*
	public CheckRecord() {
	}

	*//** minimal constructor *//*
	public CheckRecord(String uuid) {
		this.uuid = uuid;
	}

	*//** full constructor *//*
	public CheckRecord(String uuid, OldPeople oldPeople,
			String checkInfoHtmlSource, String checkProject,
			String checkResultHtmlSource, String nextCheckTime,
			String workPeople, String insertTime, String status) {
		this.uuid = uuid;
		this.oldPeople = oldPeople;
		this.checkInfoHtmlSource = checkInfoHtmlSource;
		this.checkProject = checkProject;
		this.checkResultHtmlSource = checkResultHtmlSource;
		this.nextCheckTime = nextCheckTime;
		this.workPeople = workPeople;
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

	@ManyToOne(fetch = FetchType.LAZY)
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

	@Column(name = "work_people", length = 45)
	public String getWorkPeople() {
		return this.workPeople;
	}

	public void setWorkPeople(String workPeople) {
		this.workPeople = workPeople;
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

}*/