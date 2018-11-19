package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SickRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sick_record", catalog = "rest_home")
public class SickRecord implements java.io.Serializable {

	// Fields

	private String uuid;
	private OldPeople oldPeople;
	private String checkDate;
	private String inHospitalDate;
	private String outHospitalDate;
	private String sickResonHtmlSource;
	private String sickType;
	private String doctor;
	private String cureInfoHtmlSource;
	private String carePeopleInfoHtmlSource;
	private String checkProjectInfoHtmlSource;
	private String checkResultHtmlSource;
	private String checkInfoHtmlSource;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public SickRecord() {
	}

	/** minimal constructor */
	public SickRecord(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SickRecord(String uuid, OldPeople oldPeople, String checkDate,
			String inHospitalDate, String outHospitalDate,
			String sickResonHtmlSource, String sickType, String doctor,
			String cureInfoHtmlSource, String carePeopleInfoHtmlSource,
			String checkProjectInfoHtmlSource, String checkResultHtmlSource,
			String checkInfoHtmlSource, String insertTime, String status) {
		this.uuid = uuid;
		this.oldPeople = oldPeople;
		this.checkDate = checkDate;
		this.inHospitalDate = inHospitalDate;
		this.outHospitalDate = outHospitalDate;
		this.sickResonHtmlSource = sickResonHtmlSource;
		this.sickType = sickType;
		this.doctor = doctor;
		this.cureInfoHtmlSource = cureInfoHtmlSource;
		this.carePeopleInfoHtmlSource = carePeopleInfoHtmlSource;
		this.checkProjectInfoHtmlSource = checkProjectInfoHtmlSource;
		this.checkResultHtmlSource = checkResultHtmlSource;
		this.checkInfoHtmlSource = checkInfoHtmlSource;
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
	@JoinColumn(name = "old_people_uuid")
	public OldPeople getOldPeople() {
		return this.oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}

	@Column(name = "check_date", length = 45)
	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "in_hospital_date", length = 45)
	public String getInHospitalDate() {
		return this.inHospitalDate;
	}

	public void setInHospitalDate(String inHospitalDate) {
		this.inHospitalDate = inHospitalDate;
	}

	@Column(name = "out_hospital_date", length = 45)
	public String getOutHospitalDate() {
		return this.outHospitalDate;
	}

	public void setOutHospitalDate(String outHospitalDate) {
		this.outHospitalDate = outHospitalDate;
	}

	@Column(name = "sick_reson_html_source", length = 65535)
	public String getSickResonHtmlSource() {
		return this.sickResonHtmlSource;
	}

	public void setSickResonHtmlSource(String sickResonHtmlSource) {
		this.sickResonHtmlSource = sickResonHtmlSource;
	}

	@Column(name = "sick_type", length = 65535)
	public String getSickType() {
		return this.sickType;
	}

	public void setSickType(String sickType) {
		this.sickType = sickType;
	}

	@Column(name = "doctor")
	public String getDoctor() {
		return this.doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	@Column(name = "cure_info_html_source", length = 65535)
	public String getCureInfoHtmlSource() {
		return this.cureInfoHtmlSource;
	}

	public void setCureInfoHtmlSource(String cureInfoHtmlSource) {
		this.cureInfoHtmlSource = cureInfoHtmlSource;
	}

	@Column(name = "care_people_info_html_source", length = 65535)
	public String getCarePeopleInfoHtmlSource() {
		return this.carePeopleInfoHtmlSource;
	}

	public void setCarePeopleInfoHtmlSource(String carePeopleInfoHtmlSource) {
		this.carePeopleInfoHtmlSource = carePeopleInfoHtmlSource;
	}

	@Column(name = "check_project_info_html_source", length = 65535)
	public String getCheckProjectInfoHtmlSource() {
		return this.checkProjectInfoHtmlSource;
	}

	public void setCheckProjectInfoHtmlSource(String checkProjectInfoHtmlSource) {
		this.checkProjectInfoHtmlSource = checkProjectInfoHtmlSource;
	}

	@Column(name = "check_result_html_source", length = 65535)
	public String getCheckResultHtmlSource() {
		return this.checkResultHtmlSource;
	}

	public void setCheckResultHtmlSource(String checkResultHtmlSource) {
		this.checkResultHtmlSource = checkResultHtmlSource;
	}

	@Column(name = "check_info_html_source", length = 65535)
	public String getCheckInfoHtmlSource() {
		return this.checkInfoHtmlSource;
	}

	public void setCheckInfoHtmlSource(String checkInfoHtmlSource) {
		this.checkInfoHtmlSource = checkInfoHtmlSource;
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