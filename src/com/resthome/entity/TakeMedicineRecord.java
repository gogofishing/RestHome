package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TakeMedicineRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "take_medicine_record", catalog = "rest_home")
public class TakeMedicineRecord implements java.io.Serializable {

	// Fields

	private String uuid;
	private OldPeople oldPeople;
	private String beginDate;
	private String endDate;
	private String takeTime;
	private String beforeEat;
	private String medicineName;
	private String medicineNum;
	private String ifPrescriptionMedicine;
	private String insertTime;
	private String status;
	private String takeTimes;

	// Constructors

	/** default constructor */
	public TakeMedicineRecord() {
	}

	/** minimal constructor */
	public TakeMedicineRecord(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public TakeMedicineRecord(String uuid, OldPeople oldPeople,
			String beginDate, String endDate, String takeTime,
			String beforeEat, String medicineName, String medicineNum,
			String ifPrescriptionMedicine, String insertTime, String status,
			String takeTimes) {
		this.uuid = uuid;
		this.oldPeople = oldPeople;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.takeTime = takeTime;
		this.beforeEat = beforeEat;
		this.medicineName = medicineName;
		this.medicineNum = medicineNum;
		this.ifPrescriptionMedicine = ifPrescriptionMedicine;
		this.insertTime = insertTime;
		this.status = status;
		this.takeTimes = takeTimes;
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
	@JoinColumn(name = "old_people_uuid")
	public OldPeople getOldPeople() {
		return this.oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
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

	@Column(name = "take_time", length = 65535)
	public String getTakeTime() {
		return this.takeTime;
	}

	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}

	@Column(name = "before_eat", length = 45)
	public String getBeforeEat() {
		return this.beforeEat;
	}

	public void setBeforeEat(String beforeEat) {
		this.beforeEat = beforeEat;
	}

	@Column(name = "medicine_name", length = 65535)
	public String getMedicineName() {
		return this.medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	@Column(name = "medicine_num")
	public String getMedicineNum() {
		return this.medicineNum;
	}

	public void setMedicineNum(String medicineNum) {
		this.medicineNum = medicineNum;
	}

	@Column(name = "if_prescription_medicine", length = 45)
	public String getIfPrescriptionMedicine() {
		return this.ifPrescriptionMedicine;
	}

	public void setIfPrescriptionMedicine(String ifPrescriptionMedicine) {
		this.ifPrescriptionMedicine = ifPrescriptionMedicine;
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

	@Column(name = "take_times", length = 45)
	public String getTakeTimes() {
		return this.takeTimes;
	}

	public void setTakeTimes(String takeTimes) {
		this.takeTimes = takeTimes;
	}

}