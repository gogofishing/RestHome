package com.resthome.vo;

import com.panjun.annotation.Name;

public class CheckInVo {

	private String uuid;
	private String empNo;
	private String empName;
	private String checkNo;
	private String beginTime;
	private String endTime;
	private String beginDate;
	private String endDate;
	private String tiemLength;
	private String reson;
	private String resonType;
	private String insertTime;
	
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

	@Name(name="checkNo")
	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	@Name(name="beginTime")
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
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

	@Name(name="tiemLength")
	public String getTiemLength() {
		return tiemLength;
	}

	public void setTiemLength(String tiemLength) {
		this.tiemLength = tiemLength;
	}

	@Name(name="reson")
	public String getReson() {
		return reson;
	}

	public void setReson(String reson) {
		this.reson = reson;
	}

	@Name(name="resonType")
	public String getResonType() {
		return resonType;
	}

	public void setResonType(String resonType) {
		this.resonType = resonType;
	}

	@Name(name="insertTime")
	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	

}
