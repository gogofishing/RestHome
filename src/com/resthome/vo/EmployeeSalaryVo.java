package com.resthome.vo;

import com.panjun.annotation.Name;

public class EmployeeSalaryVo {
	
	private String uuid;
	private String employeeUuid;
	private String employeeName;
	private String employeeNo;
	/*private Float PBasicSalary;
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
	private Float gongjijinSummary;*/
	private Float realSalary;
	private String insertTime;
	
	@Name(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Name(name="employeeUuid")
	public String getEmployeeUuid() {
		return employeeUuid;
	}
	public void setEmployeeUuid(String employeeUuid) {
		this.employeeUuid = employeeUuid;
	}
	@Name(name="employeeName",className="com.resthome.entity.Employee",relativeName="employeeName")
	public String getEmployeeName() {
		return employeeName;
	}	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	@Name(name="employeeNo",className="com.resthome.entity.Employee",relativeName="employeeNo")
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	/*@Name(name="PBasicSalary")
	public Float getPBasicSalary() {
		return PBasicSalary;
	}
	public void setPBasicSalary(Float pBasicSalary) {
		PBasicSalary = pBasicSalary;
	}
	@Name(name="PPositionSalary")
	public Float getPPositionSalary() {
		return PPositionSalary;
	}
	public void setPPositionSalary(Float pPositionSalary) {
		PPositionSalary = pPositionSalary;
	}
	@Name(name="PBaoxianbuchangjin")
	public Float getPBaoxianbuchangjin() {
		return PBaoxianbuchangjin;
	}
	public void setPBaoxianbuchangjin(Float pBaoxianbuchangjin) {
		PBaoxianbuchangjin = pBaoxianbuchangjin;
	}
	@Name(name="PNormalPlusSalary")
	public Float getPNormalPlusSalary() {
		return PNormalPlusSalary;
	}
	public void setPNormalPlusSalary(Float pNormalPlusSalary) {
		PNormalPlusSalary = pNormalPlusSalary;
	}
	@Name(name="PHolidaySalary")
	public Float getPHolidaySalary() {
		return PHolidaySalary;
	}
	public void setPHolidaySalary(Float pHolidaySalary) {
		PHolidaySalary = pHolidaySalary;
	}
	@Name(name="PYanglaobaoxian")
	public Float getPYanglaobaoxian() {
		return PYanglaobaoxian;
	}
	public void setPYanglaobaoxian(Float pYanglaobaoxian) {
		PYanglaobaoxian = pYanglaobaoxian;
	}
	@Name(name="PYiliaobaoxian")
	public Float getPYiliaobaoxian() {
		return PYiliaobaoxian;
	}
	public void setPYiliaobaoxian(Float pYiliaobaoxian) {
		PYiliaobaoxian = pYiliaobaoxian;
	}
	@Name(name="PShiyebaoxian")
	public Float getPShiyebaoxian() {
		return PShiyebaoxian;
	}
	public void setPShiyebaoxian(Float pShiyebaoxian) {
		PShiyebaoxian = pShiyebaoxian;
	}
	@Name(name="PZhufangbaoxian")
	public Float getPZhufangbaoxian() {
		return PZhufangbaoxian;
	}
	public void setPZhufangbaoxian(Float pZhufangbaoxian) {
		PZhufangbaoxian = pZhufangbaoxian;
	}
	@Name(name="PDaebaoxian")
	public Float getPDaebaoxian() {
		return PDaebaoxian;
	}
	public void setPDaebaoxian(Float pDaebaoxian) {
		PDaebaoxian = pDaebaoxian;
	}
	@Name(name="PLengnuanfei")
	public Float getPLengnuanfei() {
		return PLengnuanfei;
	}
	public void setPLengnuanfei(Float pLengnuanfei) {
		PLengnuanfei = pLengnuanfei;
	}
	@Name(name="personalBaoxianSummary")
	public Float getPersonalBaoxianSummary() {
		return personalBaoxianSummary;
	}
	public void setPersonalBaoxianSummary(Float personalBaoxianSummary) {
		this.personalBaoxianSummary = personalBaoxianSummary;
	}
	@Name(name="CYanglaobaoxian")
	public Float getCYanglaobaoxian() {
		return CYanglaobaoxian;
	}
	public void setCYanglaobaoxian(Float cYanglaobaoxian) {
		CYanglaobaoxian = cYanglaobaoxian;
	}
	@Name(name="CYiliaobaoxian")
	public Float getCYiliaobaoxian() {
		return CYiliaobaoxian;
	}
	public void setCYiliaobaoxian(Float cYiliaobaoxian) {
		CYiliaobaoxian = cYiliaobaoxian;
	}
	@Name(name="CGongshangbaoxian")
	public Float getCGongshangbaoxian() {
		return CGongshangbaoxian;
	}
	public void setCGongshangbaoxian(Float cGongshangbaoxian) {
		CGongshangbaoxian = cGongshangbaoxian;
	}
	@Name(name="CShiyebaoxian")
	public Float getCShiyebaoxian() {
		return CShiyebaoxian;
	}
	public void setCShiyebaoxian(Float cShiyebaoxian) {
		CShiyebaoxian = cShiyebaoxian;
	}
	@Name(name="CShengyubaoxian")
	public Float getCShengyubaoxian() {
		return CShengyubaoxian;
	}
	public void setCShengyubaoxian(Float cShengyubaoxian) {
		CShengyubaoxian = cShengyubaoxian;
	}
	@Name(name="CZhufangbaoxian")
	public Float getCZhufangbaoxian() {
		return CZhufangbaoxian;
	}
	public void setCZhufangbaoxian(Float cZhufangbaoxian) {
		CZhufangbaoxian = cZhufangbaoxian;
	}
	@Name(name="companyBaoxianSummary")
	public Float getCompanyBaoxianSummary() {
		return companyBaoxianSummary;
	}
	public void setCompanyBaoxianSummary(Float companyBaoxianSummary) {
		this.companyBaoxianSummary = companyBaoxianSummary;
	}
	@Name(name="personalTax")
	public Float getPersonalTax() {
		return personalTax;
	}
	public void setPersonalTax(Float personalTax) {
		this.personalTax = personalTax;
	}
	@Name(name="gongjijinSummary")
	public Float getGongjijinSummary() {
		return gongjijinSummary;
	}
	public void setGongjijinSummary(Float gongjijinSummary) {
		this.gongjijinSummary = gongjijinSummary;
	}*/
	@Name(name="realSalary")
	public Float getRealSalary() {
		return realSalary;
	}
	public void setRealSalary(Float realSalary) {
		this.realSalary = realSalary;
	}
	@Name(name="insertTime")
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	
	
	

}
