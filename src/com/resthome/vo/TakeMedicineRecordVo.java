package com.resthome.vo;

public class TakeMedicineRecordVo {
	private String uuid;
	private String oldPeopleNo;
	private String beforeEat;
	private String takeTimes;
	private String oldPeopleName;
	private String beginDate;
	private String endDate;
	private String ifPrescriptionMedicine;
	private String takeTime;
	private String medicineNum;
	private String medicineName;
	private String insertTime;



	public TakeMedicineRecordVo(){}
	
	
	public TakeMedicineRecordVo(String uuid, String oldPeopleNo,
			String takeTimes, String oldPeopleName, String beginDate,
			String endDate, String medicineName) {
		super();
		this.uuid = uuid;
		this.oldPeopleNo = oldPeopleNo;
		this.takeTimes = takeTimes;
		this.oldPeopleName = oldPeopleName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.medicineName = medicineName;
	}
	
	

	public TakeMedicineRecordVo(String uuid, String oldPeopleNo,
			String beforeEat, String takeTimes, String beginDate,
			String endDate, String ifPrescriptionMedicine, String takeTime,
			String medicineNum, String medicineName) {
		super();
		this.uuid = uuid;
		this.oldPeopleNo = oldPeopleNo;
		this.beforeEat = beforeEat;
		this.takeTimes = takeTimes;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.ifPrescriptionMedicine = ifPrescriptionMedicine;
		this.takeTime = takeTime;
		this.medicineNum = medicineNum;
		this.medicineName = medicineName;
	}


	public TakeMedicineRecordVo(String uuid, String oldPeopleNo,
			String beforeEat, String takeTimes, String oldPeopleName,
			String beginDate, String endDate, String ifPrescriptionMedicine,
			String takeTime, String medicineNum, String medicineName,
			String insertTime) {
		super();
		this.uuid = uuid;
		this.oldPeopleNo = oldPeopleNo;
		this.beforeEat = beforeEat;
		this.takeTimes = takeTimes;
		this.oldPeopleName = oldPeopleName;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.ifPrescriptionMedicine = ifPrescriptionMedicine;
		this.takeTime = takeTime;
		this.medicineNum = medicineNum;
		this.medicineName = medicineName;
		this.insertTime = insertTime;
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
	public String getBeforeEat() {
		return beforeEat;
	}
	public void setBeforeEat(String beforeEat) {
		this.beforeEat = beforeEat;
	}
	public String getTakeTimes() {
		return takeTimes;
	}
	public void setTakeTimes(String takeTimes) {
		this.takeTimes = takeTimes;
	}
	public String getOldPeopleName() {
		return oldPeopleName;
	}
	public void setOldPeopleName(String oldPeopleName) {
		this.oldPeopleName = oldPeopleName;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getIfPrescriptionMedicine() {
		return ifPrescriptionMedicine;
	}
	public void setIfPrescriptionMedicine(String ifPrescriptionMedicine) {
		this.ifPrescriptionMedicine = ifPrescriptionMedicine;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public String getMedicineNum() {
		return medicineNum;
	}
	public void setMedicineNum(String medicineNum) {
		this.medicineNum = medicineNum;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	
	
	
	
	
	
}
