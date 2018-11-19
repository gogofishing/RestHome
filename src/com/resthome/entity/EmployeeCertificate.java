package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * EmployeeCertificate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee_certificate", catalog = "rest_home")
public class EmployeeCertificate implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private String certificateName;
	private String certificateGrade;
	private String getTime;
	private String endTime;
	private String certificateNo;
	private String publishPart;
	private String status;
	private String insertTime;

	// Constructors

	/** default constructor */
	public EmployeeCertificate() {
	}

	/** minimal constructor */
	public EmployeeCertificate(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public EmployeeCertificate(String uuid, Employee employee,
			String certificateName, String certificateGrade, String getTime,
			String endTime, String certificateNo, String publishPart,
			String status, String insertTime) {
		this.uuid = uuid;
		this.employee = employee;
		this.certificateName = certificateName;
		this.certificateGrade = certificateGrade;
		this.getTime = getTime;
		this.endTime = endTime;
		this.certificateNo = certificateNo;
		this.publishPart = publishPart;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_uuid")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "certificate_name", length = 65535)
	public String getCertificateName() {
		return this.certificateName;
	}

	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}

	@Column(name = "certificate_grade", length = 45)
	public String getCertificateGrade() {
		return this.certificateGrade;
	}

	public void setCertificateGrade(String certificateGrade) {
		this.certificateGrade = certificateGrade;
	}

	@Column(name = "get_time", length = 45)
	public String getGetTime() {
		return this.getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	@Column(name = "end_time", length = 45)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "certificate_no")
	public String getCertificateNo() {
		return this.certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	@Column(name = "publish_part", length = 65535)
	public String getPublishPart() {
		return this.publishPart;
	}

	public void setPublishPart(String publishPart) {
		this.publishPart = publishPart;
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