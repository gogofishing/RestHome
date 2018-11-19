package com.resthome.vo;

import com.panjun.annotation.Name;

public class OutStorageVo {
	private String uuid;
	private String empNo;
	private String empName;
	private String goodsNo;
	private String goodsName;
	private String outNum;
	private String outDate;
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
	@Name(name="goods",className="com.resthome.entity.Goods",relativeName="goodsNo")
	public String getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}
	@Name(name="goods",className="com.resthome.entity.Goods",relativeName="goodsName")
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	@Name(name="outNum")
	public String getOutNum() {
		return outNum;
	}
	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	@Name(name="outDate")
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
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
