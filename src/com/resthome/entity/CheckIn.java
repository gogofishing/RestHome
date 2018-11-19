package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CheckIn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "check_in", catalog = "rest_home")
public class CheckIn implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private String checkNo;
	private String beginTime;
	private String endTime;
	private String beginDate;
	private String endDate;
	private String tiemLength;
	private String reson;
	private String resonType;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public CheckIn() {
	}

	/** minimal constructor */
	public CheckIn(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public CheckIn(String uuid, Employee employee, String checkNo,
			String beginTime, String endTime, String beginDate, String endDate,
			String tiemLength, String reson, String resonType,
			String insertTime, String status) {
		this.uuid = uuid;
		this.employee = employee;
		this.checkNo = checkNo;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.tiemLength = tiemLength;
		this.reson = reson;
		this.resonType = resonType;
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

	@Column(name = "check_no", length = 45)
	public String getCheckNo() {
		return this.checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
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

	@Column(name = "begin_date", length = 45)
	public String getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "end_date", length = 45)
	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Column(name = "tiem_length", length = 45)
	public String getTiemLength() {
		return this.tiemLength;
	}

	public void setTiemLength(String tiemLength) {
		this.tiemLength = tiemLength;
	}

	@Column(name = "reson", length = 65535)
	public String getReson() {
		return this.reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	@Column(name = "reson_type", length = 100)
	public String getResonType() {
		return this.resonType;
	}

	public void setResonType(String resonType) {
		this.resonType = resonType;
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