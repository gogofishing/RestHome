package com.resthome.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OldPeople entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "old_people", catalog = "rest_home")
public class OldPeople implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employeeByCarePeople1Uuid;
	private Employee employeeByCarePeople2Uuid;
	private String oldPeopleNo;
	private String oldPeopleName;
	private String idCard;
	private String roomNo;
	private String bedNo;
	private String grade;
	private String headImage;
	private String birthday;
	private String sex;
	private String nation;
	private String party;
	private String marriage;
	private String phone;
	private String homeTel;
	private String email;
	private String bloodType;
	private String allergicHistory;
	private String sugarSick;
	private String bloodPressure;
	private String heartSick;
	private String brainBloodSick;
	private String eyeSick;
	private String getUpEarly;
	private String shitTime;
	private String canNotEat;
	private String oldWorkCompany;
	private String oldWorkName;
	private String oldWork;
	private String oldWorkContent;
	private String liveAddress;
	private String idCardAddress;
	private String familyInfoHtmlSource;
	private String emergencyPeopleHtmlSource;
	private Float walletMoney;
	private String insertTime;
	private String status;
	private String moreHtmlSource;
	private String hobby;
	private String emergencyPeople1;
	private String emergencyPeople2;
	//private Set<OldPeoplePay> oldPeoplePaies = new HashSet<OldPeoplePay>(0);
	private Set<TakeMedicineRecord> takeMedicineRecords = new HashSet<TakeMedicineRecord>(
			0);
	private Set<HealthInfo> healthInfos = new HashSet<HealthInfo>(0);
	private Set<SickRecord> sickRecords = new HashSet<SickRecord>(0);
	private Set<OldPeopleCheckRecord> oldPeopleCheckRecords = new HashSet<OldPeopleCheckRecord>(
			0);
	private Set<OldPeopleOutInRecord> oldPeopleOutInRecords = new HashSet<OldPeopleOutInRecord>(
			0);
	private Set<VolunteerActivity> volunteerActivity=new HashSet<VolunteerActivity>(0);

	// Constructors

	/** default constructor */
	public OldPeople() {
	}

	/** minimal constructor */
	public OldPeople(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public OldPeople(String uuid, Employee employeeByCarePeople1Uuid,
			Employee employeeByCarePeople2Uuid, String oldPeopleNo,
			String oldPeopleName, String idCard, String roomNo, String bedNo,
			String grade, String headImage, String birthday, String sex,
			String nation, String party, String marriage, String phone,
			String homeTel, String email, String bloodType,
			String allergicHistory, String sugarSick, String bloodPressure,
			String heartSick, String brainBloodSick, String eyeSick,
			String getUpEarly, String shitTime, String canNotEat,
			String oldWorkCompany, String oldWorkName, String oldWork,
			String oldWorkContent, String liveAddress, String idCardAddress,
			String familyInfoHtmlSource, String emergencyPeopleHtmlSource,
			Float walletMoney, String insertTime, String status,
			String moreHtmlSource, String hobby, String emergencyPeople1,
			String emergencyPeople2, 
			Set<TakeMedicineRecord> takeMedicineRecords,
			Set<HealthInfo> healthInfos, Set<SickRecord> sickRecords,
			Set<OldPeopleCheckRecord> oldPeopleCheckRecords,
			Set<OldPeopleOutInRecord> oldPeopleOutInRecords,Set<VolunteerActivity> volunteerActivity) {
		this.uuid = uuid;
		this.employeeByCarePeople1Uuid = employeeByCarePeople1Uuid;
		this.employeeByCarePeople2Uuid = employeeByCarePeople2Uuid;
		this.oldPeopleNo = oldPeopleNo;
		this.oldPeopleName = oldPeopleName;
		this.idCard = idCard;
		this.roomNo = roomNo;
		this.bedNo = bedNo;
		this.grade = grade;
		this.headImage = headImage;
		this.birthday = birthday;
		this.sex = sex;
		this.nation = nation;
		this.party = party;
		this.marriage = marriage;
		this.phone = phone;
		this.homeTel = homeTel;
		this.email = email;
		this.bloodType = bloodType;
		this.allergicHistory = allergicHistory;
		this.sugarSick = sugarSick;
		this.bloodPressure = bloodPressure;
		this.heartSick = heartSick;
		this.brainBloodSick = brainBloodSick;
		this.eyeSick = eyeSick;
		this.getUpEarly = getUpEarly;
		this.shitTime = shitTime;
		this.canNotEat = canNotEat;
		this.oldWorkCompany = oldWorkCompany;
		this.oldWorkName = oldWorkName;
		this.oldWork = oldWork;
		this.oldWorkContent = oldWorkContent;
		this.liveAddress = liveAddress;
		this.idCardAddress = idCardAddress;
		this.familyInfoHtmlSource = familyInfoHtmlSource;
		this.emergencyPeopleHtmlSource = emergencyPeopleHtmlSource;
		this.walletMoney = walletMoney;
		this.insertTime = insertTime;
		this.status = status;
		this.moreHtmlSource = moreHtmlSource;
		this.hobby = hobby;
		this.emergencyPeople1 = emergencyPeople1;
		this.emergencyPeople2 = emergencyPeople2;
		//this.oldPeoplePaies = oldPeoplePaies;
		this.takeMedicineRecords = takeMedicineRecords;
		this.healthInfos = healthInfos;
		this.sickRecords = sickRecords;
		this.oldPeopleCheckRecords = oldPeopleCheckRecords;
		this.oldPeopleOutInRecords = oldPeopleOutInRecords;
		this.volunteerActivity=volunteerActivity;
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
	@JoinColumn(name = "care_people1_uuid")
	public Employee getEmployeeByCarePeople1Uuid() {
		return this.employeeByCarePeople1Uuid;
	}

	public void setEmployeeByCarePeople1Uuid(Employee employeeByCarePeople1Uuid) {
		this.employeeByCarePeople1Uuid = employeeByCarePeople1Uuid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "care_people2_uuid")
	public Employee getEmployeeByCarePeople2Uuid() {
		return this.employeeByCarePeople2Uuid;
	}

	public void setEmployeeByCarePeople2Uuid(Employee employeeByCarePeople2Uuid) {
		this.employeeByCarePeople2Uuid = employeeByCarePeople2Uuid;
	}

	@Column(name = "old_people_no", length = 45)
	public String getOldPeopleNo() {
		return this.oldPeopleNo;
	}

	public void setOldPeopleNo(String oldPeopleNo) {
		this.oldPeopleNo = oldPeopleNo;
	}

	@Column(name = "old_people_name", length = 100)
	public String getOldPeopleName() {
		return this.oldPeopleName;
	}

	public void setOldPeopleName(String oldPeopleName) {
		this.oldPeopleName = oldPeopleName;
	}

	@Column(name = "id_card", length = 60)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	@Column(name = "room_no")
	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	@Column(name = "bed_no")
	public String getBedNo() {
		return this.bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	@Column(name = "grade", length = 200)
	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "head_image")
	public String getHeadImage() {
		return this.headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	@Column(name = "birthday", length = 45)
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

	@Column(name = "nation", length = 60)
	public String getNation() {
		return this.nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
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

	@Column(name = "email", length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "blood_type", length = 45)
	public String getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "allergic_history", length = 65535)
	public String getAllergicHistory() {
		return this.allergicHistory;
	}

	public void setAllergicHistory(String allergicHistory) {
		this.allergicHistory = allergicHistory;
	}

	@Column(name = "sugar_sick", length = 65535)
	public String getSugarSick() {
		return this.sugarSick;
	}

	public void setSugarSick(String sugarSick) {
		this.sugarSick = sugarSick;
	}

	@Column(name = "blood_pressure", length = 65535)
	public String getBloodPressure() {
		return this.bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	@Column(name = "heart_sick", length = 65535)
	public String getHeartSick() {
		return this.heartSick;
	}

	public void setHeartSick(String heartSick) {
		this.heartSick = heartSick;
	}

	@Column(name = "brain_blood_sick", length = 65535)
	public String getBrainBloodSick() {
		return this.brainBloodSick;
	}

	public void setBrainBloodSick(String brainBloodSick) {
		this.brainBloodSick = brainBloodSick;
	}

	@Column(name = "eye_sick", length = 65535)
	public String getEyeSick() {
		return this.eyeSick;
	}

	public void setEyeSick(String eyeSick) {
		this.eyeSick = eyeSick;
	}

	@Column(name = "get_up_early", length = 45)
	public String getGetUpEarly() {
		return this.getUpEarly;
	}

	public void setGetUpEarly(String getUpEarly) {
		this.getUpEarly = getUpEarly;
	}

	@Column(name = "shit_time", length = 65535)
	public String getShitTime() {
		return this.shitTime;
	}

	public void setShitTime(String shitTime) {
		this.shitTime = shitTime;
	}

	@Column(name = "can_not_eat", length = 65535)
	public String getCanNotEat() {
		return this.canNotEat;
	}

	public void setCanNotEat(String canNotEat) {
		this.canNotEat = canNotEat;
	}

	@Column(name = "old_work_company")
	public String getOldWorkCompany() {
		return this.oldWorkCompany;
	}

	public void setOldWorkCompany(String oldWorkCompany) {
		this.oldWorkCompany = oldWorkCompany;
	}

	@Column(name = "old_work_name", length = 100)
	public String getOldWorkName() {
		return this.oldWorkName;
	}

	public void setOldWorkName(String oldWorkName) {
		this.oldWorkName = oldWorkName;
	}

	@Column(name = "old_work", length = 100)
	public String getOldWork() {
		return this.oldWork;
	}

	public void setOldWork(String oldWork) {
		this.oldWork = oldWork;
	}

	@Column(name = "old_work_content", length = 65535)
	public String getOldWorkContent() {
		return this.oldWorkContent;
	}

	public void setOldWorkContent(String oldWorkContent) {
		this.oldWorkContent = oldWorkContent;
	}

	@Column(name = "live_address", length = 65535)
	public String getLiveAddress() {
		return this.liveAddress;
	}

	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}

	@Column(name = "id_card_address", length = 65535)
	public String getIdCardAddress() {
		return this.idCardAddress;
	}

	public void setIdCardAddress(String idCardAddress) {
		this.idCardAddress = idCardAddress;
	}

	@Column(name = "family_info_html_source", length = 65535)
	public String getFamilyInfoHtmlSource() {
		return this.familyInfoHtmlSource;
	}

	public void setFamilyInfoHtmlSource(String familyInfoHtmlSource) {
		this.familyInfoHtmlSource = familyInfoHtmlSource;
	}

	@Column(name = "emergency_people_html_source", length = 65535)
	public String getEmergencyPeopleHtmlSource() {
		return this.emergencyPeopleHtmlSource;
	}

	public void setEmergencyPeopleHtmlSource(String emergencyPeopleHtmlSource) {
		this.emergencyPeopleHtmlSource = emergencyPeopleHtmlSource;
	}

	@Column(name = "wallet_money", precision = 12, scale = 0)
	public Float getWalletMoney() {
		return this.walletMoney;
	}

	public void setWalletMoney(Float walletMoney) {
		this.walletMoney = walletMoney;
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

	@Column(name = "more_html_source", length = 65535)
	public String getMoreHtmlSource() {
		return this.moreHtmlSource;
	}

	public void setMoreHtmlSource(String moreHtmlSource) {
		this.moreHtmlSource = moreHtmlSource;
	}

	@Column(name = "hobby", length = 65535)
	public String getHobby() {
		return this.hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	@Column(name = "emergency_people1", length = 65535)
	public String getEmergencyPeople1() {
		return this.emergencyPeople1;
	}

	public void setEmergencyPeople1(String emergencyPeople1) {
		this.emergencyPeople1 = emergencyPeople1;
	}

	@Column(name = "emergency_people2", length = 65535)
	public String getEmergencyPeople2() {
		return this.emergencyPeople2;
	}

	public void setEmergencyPeople2(String emergencyPeople2) {
		this.emergencyPeople2 = emergencyPeople2;
	}

	/*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<OldPeoplePay> getOldPeoplePaies() {
		return this.oldPeoplePaies;
	}

	public void setOldPeoplePaies(Set<OldPeoplePay> oldPeoplePaies) {
		this.oldPeoplePaies = oldPeoplePaies;
	}*/

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<TakeMedicineRecord> getTakeMedicineRecords() {
		return this.takeMedicineRecords;
	}

	public void setTakeMedicineRecords(
			Set<TakeMedicineRecord> takeMedicineRecords) {
		this.takeMedicineRecords = takeMedicineRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<HealthInfo> getHealthInfos() {
		return this.healthInfos;
	}

	public void setHealthInfos(Set<HealthInfo> healthInfos) {
		this.healthInfos = healthInfos;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<SickRecord> getSickRecords() {
		return this.sickRecords;
	}

	public void setSickRecords(Set<SickRecord> sickRecords) {
		this.sickRecords = sickRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<OldPeopleCheckRecord> getOldPeopleCheckRecords() {
		return this.oldPeopleCheckRecords;
	}

	public void setOldPeopleCheckRecords(
			Set<OldPeopleCheckRecord> oldPeopleCheckRecords) {
		this.oldPeopleCheckRecords = oldPeopleCheckRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<OldPeopleOutInRecord> getOldPeopleOutInRecords() {
		return this.oldPeopleOutInRecords;
	}

	public void setOldPeopleOutInRecords(
			Set<OldPeopleOutInRecord> oldPeopleOutInRecords) {
		this.oldPeopleOutInRecords = oldPeopleOutInRecords;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oldPeople")
	public Set<VolunteerActivity> getVolunteerActivity() {
		return volunteerActivity;
	}

	public void setVolunteerActivity(Set<VolunteerActivity> volunteerActivity) {
		this.volunteerActivity = volunteerActivity;
	}
	
	

}