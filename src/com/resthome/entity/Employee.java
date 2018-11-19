package com.resthome.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee", catalog = "rest_home")
public class Employee implements java.io.Serializable {

	// Fields

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
	//private Set<EmployeeCheck> employeeChecksForChargeUuid = new HashSet<EmployeeCheck>(0);
	private Set<OldPeople> oldPeoplesForCarePeople1Uuid = new HashSet<OldPeople>(0);
	//private Set<EmployeeCheck> employeeChecksForEmployeeUuid = new HashSet<EmployeeCheck>(0);
	private Set<Goods> goodses = new HashSet<Goods>(0);
	private Set<CheckIn> checkIns = new HashSet<CheckIn>(0);
	private Set<InStorage> inStorages = new HashSet<InStorage>(0);
	//private Set<EmployeeTraning> employeeTranings = new HashSet<EmployeeTraning>(0);
	private Set<OldPeople> oldPeoplesForCarePeople2Uuid = new HashSet<OldPeople>(0);
	private Set<OutStorage> outStorages = new HashSet<OutStorage>(0);
	//private Set<EmployeeCheck> employeeChecksForManagerUuid = new HashSet<EmployeeCheck>(0);
	private Set<VolunteerActivity> volunteerActivity=new HashSet<VolunteerActivity>(0);
	private Set<CommunityActivity> communityActivity=new HashSet<CommunityActivity>(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String uuid,String employeeName,String employeeNo,String department,String grade,String workStatus,String workName,String status){
		this.uuid = uuid;
		this.employeeName = employeeName;
		this.employeeNo = employeeNo;
		this.department = department;
		this.grade = grade;
		this.workStatus = workStatus;
		this.workName = workName;
		this.status = status;
	}

	/** full constructor */
	public Employee(String uuid, String employeeName, String employeeNo,
			String department, String grade, String idCard,
			String idCardImageA, String idCardImageB, String headImage,
			String workStatus, String birthday, String sex, String nation,
			String workName, String party, String marriage, String phone,
			String homeTel, String email, String address, String idCardAddress,
			String familyHtmlSource, String emergencyPeopleHtmlSource,
			String beginWorkTime, String documentHtmlSource,
			String personnelHtmlSource, String mianWorkHtmlSource,
			String workExperienceHtmlSource,
			String educationExperienceHtmlSource, String moreHtmlSource,
			String insertTime, String status, String baoxianjishuUp,
			String baoxianjishuDown, String baoxianjishu,
			String zhufangjishuUp, String zhufangjishuDown,
			String zhufangjishu, String baoxianbuchangjin, String basicSalary,
			String positionSalary,
			Set<OldPeople> oldPeoplesForCarePeople1Uuid,
			Set<Goods> goodses,
			Set<CheckIn> checkIns, Set<InStorage> inStorages,
			Set<OldPeople> oldPeoplesForCarePeople2Uuid,
			Set<OutStorage> outStorages,
			Set<VolunteerActivity> volunteerActivity,
			Set<CommunityActivity> communityActivity) {
		this.uuid = uuid;
		this.employeeName = employeeName;
		this.employeeNo = employeeNo;
		this.department = department;
		this.grade = grade;
		this.idCard = idCard;
		this.idCardImageA = idCardImageA;
		this.idCardImageB = idCardImageB;
		this.headImage = headImage;
		this.workStatus = workStatus;
		this.birthday = birthday;
		this.sex = sex;
		this.nation = nation;
		this.workName = workName;
		this.party = party;
		this.marriage = marriage;
		this.phone = phone;
		this.homeTel = homeTel;
		this.email = email;
		this.address = address;
		this.idCardAddress = idCardAddress;
		this.familyHtmlSource = familyHtmlSource;
		this.emergencyPeopleHtmlSource = emergencyPeopleHtmlSource;
		this.beginWorkTime = beginWorkTime;
		this.documentHtmlSource = documentHtmlSource;
		this.personnelHtmlSource = personnelHtmlSource;
		this.mianWorkHtmlSource = mianWorkHtmlSource;
		this.workExperienceHtmlSource = workExperienceHtmlSource;
		this.educationExperienceHtmlSource = educationExperienceHtmlSource;
		this.moreHtmlSource = moreHtmlSource;
		this.insertTime = insertTime;
		this.status = status;
		this.baoxianjishuUp = baoxianjishuUp;
		this.baoxianjishuDown = baoxianjishuDown;
		this.baoxianjishu = baoxianjishu;
		this.zhufangjishuUp = zhufangjishuUp;
		this.zhufangjishuDown = zhufangjishuDown;
		this.zhufangjishu = zhufangjishu;
		this.baoxianbuchangjin = baoxianbuchangjin;
		this.basicSalary = basicSalary;
		this.positionSalary = positionSalary;
		//this.employeeChecksForChargeUuid = employeeChecksForChargeUuid;
		this.oldPeoplesForCarePeople1Uuid = oldPeoplesForCarePeople1Uuid;
		//this.employeeChecksForEmployeeUuid = employeeChecksForEmployeeUuid;
		this.goodses = goodses;
		this.checkIns = checkIns;
		this.inStorages = inStorages;
		//this.employeeTranings = employeeTranings;
		this.oldPeoplesForCarePeople2Uuid = oldPeoplesForCarePeople2Uuid;
		this.outStorages = outStorages;
		//this.employeeChecksForManagerUuid = employeeChecksForManagerUuid;
		this.volunteerActivity=volunteerActivity;
		this.communityActivity=communityActivity;
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

	@Column(name = "employee_name", length = 100)
	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Column(name = "employee_no", length = 100)
	public String getEmployeeNo() {
		return this.employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	@Column(name = "department", length = 100)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Column(name = "grade", length = 100)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "id_card", length = 60)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "id_card_image_a")
	public String getIdCardImageA() {
		return this.idCardImageA;
	}

	public void setIdCardImageA(String idCardImageA) {
		this.idCardImageA = idCardImageA;
	}

	@Column(name = "id_card_image_b")
	public String getIdCardImageB() {
		return this.idCardImageB;
	}

	public void setIdCardImageB(String idCardImageB) {
		this.idCardImageB = idCardImageB;
	}

	@Column(name = "head_image")
	public String getHeadImage() {
		return this.headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@Column(name = "work_status", length = 45)
	public String getWorkStatus() {
		return this.workStatus;
	}

	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}

	@Column(name = "birthday", length = 100)
	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(name = "sex", length = 45)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "nation", length = 45)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "work_name", length = 100)
	public String getWorkName() {
		return this.workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	@Column(name = "party", length = 100)
	public String getParty() {
		return this.party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	@Column(name = "marriage", length = 45)
	public String getMarriage() {
		return this.marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	@Column(name = "phone", length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "home_tel", length = 45)
	public String getHomeTel() {
		return this.homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address", length = 65535)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "id_card_address", length = 65535)
	public String getIdCardAddress() {
		return this.idCardAddress;
	}

	public void setIdCardAddress(String idCardAddress) {
		this.idCardAddress = idCardAddress;
	}

	@Column(name = "family_html_source", length = 65535)
	public String getFamilyHtmlSource() {
		return this.familyHtmlSource;
	}

	public void setFamilyHtmlSource(String familyHtmlSource) {
		this.familyHtmlSource = familyHtmlSource;
	}

	@Column(name = "emergency_people_html_source", length = 65535)
	public String getEmergencyPeopleHtmlSource() {
		return this.emergencyPeopleHtmlSource;
	}

	public void setEmergencyPeopleHtmlSource(String emergencyPeopleHtmlSource) {
		this.emergencyPeopleHtmlSource = emergencyPeopleHtmlSource;
	}

	@Column(name = "begin_work_time", length = 60)
	public String getBeginWorkTime() {
		return this.beginWorkTime;
	}

	public void setBeginWorkTime(String beginWorkTime) {
		this.beginWorkTime = beginWorkTime;
	}

	@Column(name = "document_html_source", length = 65535)
	public String getDocumentHtmlSource() {
		return this.documentHtmlSource;
	}

	public void setDocumentHtmlSource(String documentHtmlSource) {
		this.documentHtmlSource = documentHtmlSource;
	}

	@Column(name = "personnel_html_source", length = 65535)
	public String getPersonnelHtmlSource() {
		return this.personnelHtmlSource;
	}

	public void setPersonnelHtmlSource(String personnelHtmlSource) {
		this.personnelHtmlSource = personnelHtmlSource;
	}

	@Column(name = "mian_work_html_source", length = 65535)
	public String getMianWorkHtmlSource() {
		return this.mianWorkHtmlSource;
	}

	public void setMianWorkHtmlSource(String mianWorkHtmlSource) {
		this.mianWorkHtmlSource = mianWorkHtmlSource;
	}

	@Column(name = "work_experience_html_source", length = 65535)
	public String getWorkExperienceHtmlSource() {
		return this.workExperienceHtmlSource;
	}

	public void setWorkExperienceHtmlSource(String workExperienceHtmlSource) {
		this.workExperienceHtmlSource = workExperienceHtmlSource;
	}

	@Column(name = "education_experience_html_source", length = 65535)
	public String getEducationExperienceHtmlSource() {
		return this.educationExperienceHtmlSource;
	}

	public void setEducationExperienceHtmlSource(
			String educationExperienceHtmlSource) {
		this.educationExperienceHtmlSource = educationExperienceHtmlSource;
	}

	@Column(name = "more_html_source", length = 65535)
	public String getMoreHtmlSource() {
		return this.moreHtmlSource;
	}

	public void setMoreHtmlSource(String moreHtmlSource) {
		this.moreHtmlSource = moreHtmlSource;
	}

	@Column(name = "insert_time", length = 60)
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

	@Column(name = "baoxianjishu_up")
	public String getBaoxianjishuUp() {
		return this.baoxianjishuUp;
	}

	public void setBaoxianjishuUp(String baoxianjishuUp) {
		this.baoxianjishuUp = baoxianjishuUp;
	}

	@Column(name = "baoxianjishu_down")
	public String getBaoxianjishuDown() {
		return this.baoxianjishuDown;
	}

	public void setBaoxianjishuDown(String baoxianjishuDown) {
		this.baoxianjishuDown = baoxianjishuDown;
	}

	@Column(name = "baoxianjishu")
	public String getBaoxianjishu() {
		return this.baoxianjishu;
	}

	public void setBaoxianjishu(String baoxianjishu) {
		this.baoxianjishu = baoxianjishu;
	}

	@Column(name = "zhufangjishu_up")
	public String getZhufangjishuUp() {
		return this.zhufangjishuUp;
	}

	public void setZhufangjishuUp(String zhufangjishuUp) {
		this.zhufangjishuUp = zhufangjishuUp;
	}

	@Column(name = "zhufangjishu_down")
	public String getZhufangjishuDown() {
		return this.zhufangjishuDown;
	}

	public void setZhufangjishuDown(String zhufangjishuDown) {
		this.zhufangjishuDown = zhufangjishuDown;
	}

	public String getZhufangjishu() {
		return this.zhufangjishu;
	}

	@Column(name = "zhufangjishu")
	public void setZhufangjishu(String zhufangjishu) {
		this.zhufangjishu = zhufangjishu;
	}

	@Column(name = "baoxianbuchangjin")
	public String getBaoxianbuchangjin() {
		return this.baoxianbuchangjin;
	}
	
	public void setBaoxianbuchangjin(String baoxianbuchangjin) {
		this.baoxianbuchangjin = baoxianbuchangjin;
	}

	@Column(name = "basic_salary")
	public String getBasicSalary() {
		return this.basicSalary;
	}

	public void setBasicSalary(String basicSalary) {
		this.basicSalary = basicSalary;
	}

	@Column(name = "position_salary")
	public String getPositionSalary() {
		return this.positionSalary;
	}

	public void setPositionSalary(String positionSalary) {
		this.positionSalary = positionSalary;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByChargeUuid")
	public Set<EmployeeCheck> getEmployeeChecksForChargeUuid() {
		return this.employeeChecksForChargeUuid;
	}

	public void setEmployeeChecksForChargeUuid(Set<EmployeeCheck> employeeChecksForChargeUuid) {
		this.employeeChecksForChargeUuid = employeeChecksForChargeUuid;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByCarePeople1Uuid")
	public Set<OldPeople> getOldPeoplesForCarePeople1Uuid() {
		return this.oldPeoplesForCarePeople1Uuid;
	}

	public void setOldPeoplesForCarePeople1Uuid(Set<OldPeople> oldPeoplesForCarePeople1Uuid) {
		this.oldPeoplesForCarePeople1Uuid = oldPeoplesForCarePeople1Uuid;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByEmployeeUuid")
	public Set<EmployeeCheck> getEmployeeChecksForEmployeeUuid() {
		return this.employeeChecksForEmployeeUuid;
	}

	public void setEmployeeChecksForEmployeeUuid(
			Set<EmployeeCheck> employeeChecksForEmployeeUuid) {
		this.employeeChecksForEmployeeUuid = employeeChecksForEmployeeUuid;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<CheckIn> getCheckIns() {
		return this.checkIns;
	}

	public void setCheckIns(Set<CheckIn> checkIns) {
		this.checkIns = checkIns;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<InStorage> getInStorages() {
		return this.inStorages;
	}

	public void setInStorages(Set<InStorage> inStorages) {
		this.inStorages = inStorages;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<EmployeeTraning> getEmployeeTranings() {
		return this.employeeTranings;
	}

	public void setEmployeeTranings(Set<EmployeeTraning> employeeTranings) {
		this.employeeTranings = employeeTranings;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByCarePeople2Uuid")
	public Set<OldPeople> getOldPeoplesForCarePeople2Uuid() {
		return this.oldPeoplesForCarePeople2Uuid;
	}

	public void setOldPeoplesForCarePeople2Uuid(Set<OldPeople> oldPeoplesForCarePeople2Uuid) {
		this.oldPeoplesForCarePeople2Uuid = oldPeoplesForCarePeople2Uuid;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<OutStorage> getOutStorages() {
		return this.outStorages;
	}

	public void setOutStorages(Set<OutStorage> outStorages) {
		this.outStorages = outStorages;
	}


	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employeeByManagerUuid")
	public Set<EmployeeCheck> getEmployeeChecksForManagerUuid() {
		return this.employeeChecksForManagerUuid;
	}

	public void setEmployeeChecksForManagerUuid(Set<EmployeeCheck> employeeChecksForManagerUuid) {
		this.employeeChecksForManagerUuid = employeeChecksForManagerUuid;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<VolunteerActivity> getVolunteerActivity() {
		return volunteerActivity;
	}

	public void setVolunteerActivity(Set<VolunteerActivity> volunteerActivity) {
		this.volunteerActivity = volunteerActivity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<CommunityActivity> getCommunityActivity() {
		return communityActivity;
	}

	public void setCommunityActivity(Set<CommunityActivity> communityActivity) {
		this.communityActivity = communityActivity;
	}
	
	

}