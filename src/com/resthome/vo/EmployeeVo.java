package com.resthome.vo;

import java.util.List;

import com.panjun.annotation.Name;

public class EmployeeVo {
	private String uuid;
	private String employeeName;
	private String employeeNo;
	private String department;
	private String grade;
	private String idCard;
	private String idCardImageA;
	private String idCardImageB;
	private String headImage;
	private String workStatus;
	private String birthday;
	private String sex;
	private String nation;
	private String workName;
	private String party;
	private String marriage;
	private String phone;
	private String homeTel;
	private String email;
	private String address;
	private String idCardAddress;
	private String familyHtmlSource;
	private String emergencyPeopleHtmlSource;
	private String beginWorkTime;
	private String documentHtmlSource;
	private String personnelHtmlSource;
	private String mianWorkHtmlSource;
	private String workExperienceHtmlSource;
	private String educationExperienceHtmlSource;
	private String moreHtmlSource;
	private String insertTime;
	private String status;
	private String baoxianjishuUp;
	private String baoxianjishuDown;
	private String baoxianjishu;
	private String zhufangjishuUp;
	private String zhufangjishuDown;
	private String zhufangjishu;
	private String baoxianbuchangjin;
	private String basicSalary;
	private String positionSalary;
	private List<EmployeeCertificateVo> ecvList;
	
	
	@Name(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Name(name="employeeName")
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@Name(name="employeeNo")
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	@Name(name="department")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Name(name="grade")
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Name(name="idCard")
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@Name(name="idCardImageA")
	public String getIdCardImageA() {
		return idCardImageA;
	}
	public void setIdCardImageA(String idCardImageA) {
		this.idCardImageA = idCardImageA;
	}
	@Name(name="idCardImageB")
	public String getIdCardImageB() {
		return idCardImageB;
	}
	public void setIdCardImageB(String idCardImageB) {
		this.idCardImageB = idCardImageB;
	}
	@Name(name="headImage")
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	@Name(name="workStatus")
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	@Name(name="birthday")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Name(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Name(name="nation")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	@Name(name="workName")
	public String getWorkName() {
		return workName;
	}
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	@Name(name="party")
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	@Name(name="marriage")
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	@Name(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Name(name="homeTel")
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	@Name(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Name(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Name(name="idCardAddress")
	public String getIdCardAddress() {
		return idCardAddress;
	}
	public void setIdCardAddress(String idCardAddress) {
		this.idCardAddress = idCardAddress;
	}
	@Name(name="familyHtmlSource")
	public String getFamilyHtmlSource() {
		return familyHtmlSource;
	}
	public void setFamilyHtmlSource(String familyHtmlSource) {
		this.familyHtmlSource = familyHtmlSource;
	}
	@Name(name="emergencyPeopleHtmlSource")
	public String getEmergencyPeopleHtmlSource() {
		return emergencyPeopleHtmlSource;
	}
	public void setEmergencyPeopleHtmlSource(String emergencyPeopleHtmlSource) {
		this.emergencyPeopleHtmlSource = emergencyPeopleHtmlSource;
	}
	@Name(name="beginWorkTime")
	public String getBeginWorkTime() {
		return beginWorkTime;
	}
	public void setBeginWorkTime(String beginWorkTime) {
		this.beginWorkTime = beginWorkTime;
	}
	@Name(name="documentHtmlSource")
	public String getDocumentHtmlSource() {
		return documentHtmlSource;
	}
	public void setDocumentHtmlSource(String documentHtmlSource) {
		this.documentHtmlSource = documentHtmlSource;
	}
	@Name(name="personnelHtmlSource")
	public String getPersonnelHtmlSource() {
		return personnelHtmlSource;
	}
	public void setPersonnelHtmlSource(String personnelHtmlSource) {
		this.personnelHtmlSource = personnelHtmlSource;
	}
	@Name(name="mianWorkHtmlSource")
	public String getMianWorkHtmlSource() {
		return mianWorkHtmlSource;
	}
	public void setMianWorkHtmlSource(String mianWorkHtmlSource) {
		this.mianWorkHtmlSource = mianWorkHtmlSource;
	}
	@Name(name="workExperienceHtmlSource")
	public String getWorkExperienceHtmlSource() {
		return workExperienceHtmlSource;
	}
	public void setWorkExperienceHtmlSource(String workExperienceHtmlSource) {
		this.workExperienceHtmlSource = workExperienceHtmlSource;
	}
	@Name(name="educationExperienceHtmlSource")
	public String getEducationExperienceHtmlSource() {
		return educationExperienceHtmlSource;
	}
	public void setEducationExperienceHtmlSource(
			String educationExperienceHtmlSource) {
		this.educationExperienceHtmlSource = educationExperienceHtmlSource;
	}
	@Name(name="moreHtmlSource")
	public String getMoreHtmlSource() {
		return moreHtmlSource;
	}
	public void setMoreHtmlSource(String moreHtmlSource) {
		this.moreHtmlSource = moreHtmlSource;
	}
	@Name(name="insertTime")
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	@Name(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Name(name="baoxianjishuUp")
	public String getBaoxianjishuUp() {
		return baoxianjishuUp;
	}
	public void setBaoxianjishuUp(String baoxianjishuUp) {
		this.baoxianjishuUp = baoxianjishuUp;
	}
	@Name(name="baoxianjishuDown")
	public String getBaoxianjishuDown() {
		return baoxianjishuDown;
	}
	public void setBaoxianjishuDown(String baoxianjishuDown) {
		this.baoxianjishuDown = baoxianjishuDown;
	}
	@Name(name="baoxianjishu")
	public String getBaoxianjishu() {
		return baoxianjishu;
	}
	public void setBaoxianjishu(String baoxianjishu) {
		this.baoxianjishu = baoxianjishu;
	}
	@Name(name="zhufangjishuUp")
	public String getZhufangjishuUp() {
		return zhufangjishuUp;
	}
	public void setZhufangjishuUp(String zhufangjishuUp) {
		this.zhufangjishuUp = zhufangjishuUp;
	}
	@Name(name="zhufangjishuDown")
	public String getZhufangjishuDown() {
		return zhufangjishuDown;
	}
	public void setZhufangjishuDown(String zhufangjishuDown) {
		this.zhufangjishuDown = zhufangjishuDown;
	}
	@Name(name="zhufangjishu")
	public String getZhufangjishu() {
		return zhufangjishu;
	}
	public void setZhufangjishu(String zhufangjishu) {
		this.zhufangjishu = zhufangjishu;
	}
	@Name(name="baoxianbuchangjin")
	public String getBaoxianbuchangjin() {
		return baoxianbuchangjin;
	}
	public void setBaoxianbuchangjin(String baoxianbuchangjin) {
		this.baoxianbuchangjin = baoxianbuchangjin;
	}
	@Name(name="basicSalary")
	public String getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}
	@Name(name="positionSalary")
	public String getPositionSalary() {
		return positionSalary;
	}
	public void setPositionSalary(String positionSalary) {
		this.positionSalary = positionSalary;
	}
	public List<EmployeeCertificateVo> getEcvList() {
		return ecvList;
	}
	public void setEcvList(List<EmployeeCertificateVo> ecvList) {
		this.ecvList = ecvList;
	}
	
	

}
