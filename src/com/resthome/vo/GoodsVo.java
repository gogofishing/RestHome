package com.resthome.vo;

import com.panjun.annotation.Name;

public class GoodsVo {
	
	private String uuid;
	private String empNo;
	private String empName;
	private String goodsNo;
	private String goodsName;
	private String goodsManufacturer;
	private String goodsProductionDate;
	private String goodsPrice;
	private String goodsNum;
	private String goodsInDate;
	private String insertTime;
	private String status;
	
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
	@Name(name="goodsNo")
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	@Name(name="goodsName")
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Name(name="goodsManufacturer")
	public String getGoodsManufacturer() {
		return goodsManufacturer;
	}
	public void setGoodsManufacturer(String goodsManufacturer) {
		this.goodsManufacturer = goodsManufacturer;
	}
	@Name(name="goodsProductionDate")
	public String getGoodsProductionDate() {
		return goodsProductionDate;
	}
	public void setGoodsProductionDate(String goodsProductionDate) {
		this.goodsProductionDate = goodsProductionDate;
	}
	@Name(name="goodsPrice")
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	@Name(name="goodsNum")
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	@Name(name="goodsInDate")
	public String getGoodsInDate() {
		return goodsInDate;
	}
	public void setGoodsInDate(String goodsInDate) {
		this.goodsInDate = goodsInDate;
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
	
	

}
