package com.resthome.vo;


public class OldPeopleOutInRecordVo {
	private String uuid;
	private String oldPeopleNo;
	private String oldPeopleName;
	private String empNo;
	private String empName;
	private String beginTime;
	private String endTime;
	private String recordDate;
	
	private String reason;
	private String familyInfo;
	
	


	public OldPeopleOutInRecordVo(){}
	

	public OldPeopleOutInRecordVo(String uuid, String oldPeopleNo,
			String oldPeopleName, String empNo, String empName,
			String beginTime, String endTime) {
		super();
		this.uuid = uuid;
		this.oldPeopleNo = oldPeopleNo;
		this.oldPeopleName = oldPeopleName;
		this.empNo = empNo;
		this.empName = empName;
		this.beginTime = beginTime;
		this.endTime = endTime;
	}
	
	

	public OldPeopleOutInRecordVo(String uuid, String oldPeopleNo,
			String oldPeopleName, String empNo, String empName,
			String beginTime, String endTime, String recordDate, String reason,
			String familyInfo) {
		super();
		this.uuid = uuid;
		this.oldPeopleNo = oldPeopleNo;
		this.oldPeopleName = oldPeopleName;
		this.empNo = empNo;
		this.empName = empName;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.recordDate = recordDate;
		this.reason = reason;
		this.familyInfo = familyInfo;
	}


	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOldPeopleNo() {
		return oldPeopleNo;
	}
	public void setOldPeopleNo(String oldPeopleNo) {
		this.oldPeopleNo = oldPeopleNo;
	}
	public String getOldPeopleName() {
		return oldPeopleName;
	}
	public void setOldPeopleName(String oldPeopleName) {
		this.oldPeopleName = oldPeopleName;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
}
