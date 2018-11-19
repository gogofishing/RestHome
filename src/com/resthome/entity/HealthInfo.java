package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * HealthInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "health_info", catalog = "rest_home")
public class HealthInfo implements java.io.Serializable {

	// Fields

	private String uuid;
	private OldPeople oldPeople;
	private String sickName;
	private String sickDate;
	private String cureDate;
	private String cureInfoHtmlSource;
	private String sequela;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public HealthInfo() {
	}

	/** minimal constructor */
	public HealthInfo(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public HealthInfo(String uuid, OldPeople oldPeople, String sickName,
			String sickDate, String cureDate, String cureInfoHtmlSource,
			String sequela, String insertTime, String status) {
		this.uuid = uuid;
		this.oldPeople = oldPeople;
		this.sickName = sickName;
		this.sickDate = sickDate;
		this.cureDate = cureDate;
		this.cureInfoHtmlSource = cureInfoHtmlSource;
		this.sequela = sequela;
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

	@Column(name = "sick_name")
	public String getSickName() {
		return this.sickName;
	}

	public void setSickName(String sickName) {
		this.sickName = sickName;
	}

	@Column(name = "sick_date", length = 45)
	public String getSickDate() {
		return this.sickDate;
	}

	public void setSickDate(String sickDate) {
		this.sickDate = sickDate;
	}

	@Column(name = "cure_date", length = 45)
	public String getCureDate() {
		return this.cureDate;
	}

	public void setCureDate(String cureDate) {
		this.cureDate = cureDate;
	}

	@Column(name = "cure_info_html_source", length = 65535)
	public String getCureInfoHtmlSource() {
		return this.cureInfoHtmlSource;
	}

	public void setCureInfoHtmlSource(String cureInfoHtmlSource) {
		this.cureInfoHtmlSource = cureInfoHtmlSource;
	}

	@Column(name = "sequela", length = 65535)
	public String getSequela() {
		return this.sequela;
	}

	public void setSequela(String sequela) {
		this.sequela = sequela;
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