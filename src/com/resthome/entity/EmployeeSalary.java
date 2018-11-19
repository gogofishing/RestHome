package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * EmployeeSalary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "employee_salary", catalog = "rest_home")
public class EmployeeSalary implements java.io.Serializable {

	// Fields

	private String uuid;
	private String employeeUuid;
	private Float PBasicSalary;
	private Float PPositionSalary;
	private Float PBaoxianbuchangjin;
	private Float PNormalPlusSalary;
	private Float PHolidaySalary;
	private Float PYanglaobaoxian;
	private Float PYiliaobaoxian;
	private Float PShiyebaoxian;
	private Float PZhufangbaoxian;
	private Float PDaebaoxian;
	private Float PLengnuanfei;
	private Float personalBaoxianSummary;
	private Float CYanglaobaoxian;
	private Float CYiliaobaoxian;
	private Float CGongshangbaoxian;
	private Float CShiyebaoxian;
	private Float CShengyubaoxian;
	private Float CZhufangbaoxian;
	private Float companyBaoxianSummary;
	private Float personalTax;
	private Float gongjijinSummary;
	private Float realSalary;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public EmployeeSalary() {
	}

	/** minimal constructor */
	public EmployeeSalary(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public EmployeeSalary(String uuid, String employeeUuid, Float PBasicSalary,
			Float PPositionSalary, Float PBaoxianbuchangjin,
			Float PNormalPlusSalary, Float PHolidaySalary,
			Float PYanglaobaoxian, Float PYiliaobaoxian, Float PShiyebaoxian,
			Float PZhufangbaoxian, Float PDaebaoxian, Float PLengnuanfei,
			Float personalBaoxianSummary, Float CYanglaobaoxian,
			Float CYiliaobaoxian, Float CGongshangbaoxian, Float CShiyebaoxian,
			Float CShengyubaoxian, Float CZhufangbaoxian,
			Float companyBaoxianSummary, Float personalTax,
			Float gongjijinSummary, Float realSalary, String insertTime,
			String status) {
		this.uuid = uuid;
		this.employeeUuid = employeeUuid;
		this.PBasicSalary = PBasicSalary;
		this.PPositionSalary = PPositionSalary;
		this.PBaoxianbuchangjin = PBaoxianbuchangjin;
		this.PNormalPlusSalary = PNormalPlusSalary;
		this.PHolidaySalary = PHolidaySalary;
		this.PYanglaobaoxian = PYanglaobaoxian;
		this.PYiliaobaoxian = PYiliaobaoxian;
		this.PShiyebaoxian = PShiyebaoxian;
		this.PZhufangbaoxian = PZhufangbaoxian;
		this.PDaebaoxian = PDaebaoxian;
		this.PLengnuanfei = PLengnuanfei;
		this.personalBaoxianSummary = personalBaoxianSummary;
		this.CYanglaobaoxian = CYanglaobaoxian;
		this.CYiliaobaoxian = CYiliaobaoxian;
		this.CGongshangbaoxian = CGongshangbaoxian;
		this.CShiyebaoxian = CShiyebaoxian;
		this.CShengyubaoxian = CShengyubaoxian;
		this.CZhufangbaoxian = CZhufangbaoxian;
		this.companyBaoxianSummary = companyBaoxianSummary;
		this.personalTax = personalTax;
		this.gongjijinSummary = gongjijinSummary;
		this.realSalary = realSalary;
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

	@Column(name = "employee_uuid", length = 36)
	public String getEmployeeUuid() {
		return this.employeeUuid;
	}

	public void setEmployeeUuid(String employeeUuid) {
		this.employeeUuid = employeeUuid;
	}

	@Column(name = "p_basic_salary", precision = 12, scale = 0)
	public Float getPBasicSalary() {
		return this.PBasicSalary;
	}

	public void setPBasicSalary(Float PBasicSalary) {
		this.PBasicSalary = PBasicSalary;
	}

	@Column(name = "p_position_salary", precision = 12, scale = 0)
	public Float getPPositionSalary() {
		return this.PPositionSalary;
	}

	public void setPPositionSalary(Float PPositionSalary) {
		this.PPositionSalary = PPositionSalary;
	}

	@Column(name = "p_baoxianbuchangjin", precision = 12, scale = 0)
	public Float getPBaoxianbuchangjin() {
		return this.PBaoxianbuchangjin;
	}

	public void setPBaoxianbuchangjin(Float PBaoxianbuchangjin) {
		this.PBaoxianbuchangjin = PBaoxianbuchangjin;
	}

	@Column(name = "p_normal_plus_salary", precision = 12, scale = 0)
	public Float getPNormalPlusSalary() {
		return this.PNormalPlusSalary;
	}

	public void setPNormalPlusSalary(Float PNormalPlusSalary) {
		this.PNormalPlusSalary = PNormalPlusSalary;
	}

	@Column(name = "p_holiday_salary", precision = 12, scale = 0)
	public Float getPHolidaySalary() {
		return this.PHolidaySalary;
	}

	public void setPHolidaySalary(Float PHolidaySalary) {
		this.PHolidaySalary = PHolidaySalary;
	}

	@Column(name = "p_yanglaobaoxian", precision = 12, scale = 0)
	public Float getPYanglaobaoxian() {
		return this.PYanglaobaoxian;
	}

	public void setPYanglaobaoxian(Float PYanglaobaoxian) {
		this.PYanglaobaoxian = PYanglaobaoxian;
	}

	@Column(name = "p_yiliaobaoxian", precision = 12, scale = 0)
	public Float getPYiliaobaoxian() {
		return this.PYiliaobaoxian;
	}

	public void setPYiliaobaoxian(Float PYiliaobaoxian) {
		this.PYiliaobaoxian = PYiliaobaoxian;
	}

	@Column(name = "p_shiyebaoxian", precision = 12, scale = 0)
	public Float getPShiyebaoxian() {
		return this.PShiyebaoxian;
	}

	public void setPShiyebaoxian(Float PShiyebaoxian) {
		this.PShiyebaoxian = PShiyebaoxian;
	}

	@Column(name = "p_zhufangbaoxian", precision = 12, scale = 0)
	public Float getPZhufangbaoxian() {
		return this.PZhufangbaoxian;
	}

	public void setPZhufangbaoxian(Float PZhufangbaoxian) {
		this.PZhufangbaoxian = PZhufangbaoxian;
	}

	@Column(name = "p_daebaoxian", precision = 12, scale = 0)
	public Float getPDaebaoxian() {
		return this.PDaebaoxian;
	}

	public void setPDaebaoxian(Float PDaebaoxian) {
		this.PDaebaoxian = PDaebaoxian;
	}

	@Column(name = "p_lengnuanfei", precision = 12, scale = 0)
	public Float getPLengnuanfei() {
		return this.PLengnuanfei;
	}

	public void setPLengnuanfei(Float PLengnuanfei) {
		this.PLengnuanfei = PLengnuanfei;
	}

	@Column(name = "personal_baoxian_summary", precision = 12, scale = 0)
	public Float getPersonalBaoxianSummary() {
		return this.personalBaoxianSummary;
	}

	public void setPersonalBaoxianSummary(Float personalBaoxianSummary) {
		this.personalBaoxianSummary = personalBaoxianSummary;
	}

	@Column(name = "c_yanglaobaoxian", precision = 12, scale = 0)
	public Float getCYanglaobaoxian() {
		return this.CYanglaobaoxian;
	}

	public void setCYanglaobaoxian(Float CYanglaobaoxian) {
		this.CYanglaobaoxian = CYanglaobaoxian;
	}

	@Column(name = "c_yiliaobaoxian", precision = 12, scale = 0)
	public Float getCYiliaobaoxian() {
		return this.CYiliaobaoxian;
	}

	public void setCYiliaobaoxian(Float CYiliaobaoxian) {
		this.CYiliaobaoxian = CYiliaobaoxian;
	}

	@Column(name = "c_gongshangbaoxian", precision = 12, scale = 0)
	public Float getCGongshangbaoxian() {
		return this.CGongshangbaoxian;
	}

	public void setCGongshangbaoxian(Float CGongshangbaoxian) {
		this.CGongshangbaoxian = CGongshangbaoxian;
	}

	@Column(name = "c_shiyebaoxian", precision = 12, scale = 0)
	public Float getCShiyebaoxian() {
		return this.CShiyebaoxian;
	}

	public void setCShiyebaoxian(Float CShiyebaoxian) {
		this.CShiyebaoxian = CShiyebaoxian;
	}

	@Column(name = "c_shengyubaoxian", precision = 12, scale = 0)
	public Float getCShengyubaoxian() {
		return this.CShengyubaoxian;
	}

	public void setCShengyubaoxian(Float CShengyubaoxian) {
		this.CShengyubaoxian = CShengyubaoxian;
	}

	@Column(name = "c_zhufangbaoxian", precision = 12, scale = 0)
	public Float getCZhufangbaoxian() {
		return this.CZhufangbaoxian;
	}

	public void setCZhufangbaoxian(Float CZhufangbaoxian) {
		this.CZhufangbaoxian = CZhufangbaoxian;
	}

	@Column(name = "company_baoxian_summary", precision = 12, scale = 0)
	public Float getCompanyBaoxianSummary() {
		return this.companyBaoxianSummary;
	}

	public void setCompanyBaoxianSummary(Float companyBaoxianSummary) {
		this.companyBaoxianSummary = companyBaoxianSummary;
	}

	@Column(name = "personal_tax", precision = 12, scale = 0)
	public Float getPersonalTax() {
		return this.personalTax;
	}

	public void setPersonalTax(Float personalTax) {
		this.personalTax = personalTax;
	}

	@Column(name = "gongjijin_summary", precision = 12, scale = 0)
	public Float getGongjijinSummary() {
		return this.gongjijinSummary;
	}

	public void setGongjijinSummary(Float gongjijinSummary) {
		this.gongjijinSummary = gongjijinSummary;
	}

	@Column(name = "real_salary", precision = 12, scale = 0)
	public Float getRealSalary() {
		return this.realSalary;
	}

	public void setRealSalary(Float realSalary) {
		this.realSalary = realSalary;
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