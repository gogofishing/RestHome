package com.resthome.vo;

import com.panjun.annotation.Name;
import com.resthome.entity.Employee;

public class EmployeeCertificateVo {
	private String uuid;
	private String empUuid;
	private String certificateName;
	private String certificateGrade;
	private String getTime;
	private String endTime;
	private String certificateNo;
	private String publishPart;
	private String status;
	private String insertTime;
	private String certificateStatus;//是否过期
	@Name(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Name(name="employee",className="com.resthome.entity.Employee",relativeName="uuid")
	public String getEmpUuid() {
		return empUuid;
	}
	public void setEmpUuid(String empUuid) {
		this.empUuid = empUuid;
	}
	@Name(name="certificateName")
	public String getCertificateName() {
		return certificateName;
	}
	public void setCertificateName(String certificateName) {
		this.certificateName = certificateName;
	}
	@Name(name="certificateGrade")
	public String getCertificateGrade() {
		return certificateGrade;
	}
	public void setCertificateGrade(String certificateGrade) {
		this.certificateGrade = certificateGrade;
	}
	@Name(name="getTime")
	public String getGetTime() {
		return getTime;
	}
	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
	@Name(name="endTime")
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	@Name(name="certificateNo")
	public String getCertificateNo() {
		return certificateNo;
	}
	
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	@Name(name="publishPart")
	public String getPublishPart() {
		return publishPart;
	}
	public void setPublishPart(String publishPart) {
		this.publishPart = publishPart;
	}
	@Name(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Name(name="insertTime")
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public String getCertificateStatus() {
		return certificateStatus;
	}
	public void setCertificateStatus(String certificateStatus) {
		this.certificateStatus = certificateStatus;
	}
	
	

}
