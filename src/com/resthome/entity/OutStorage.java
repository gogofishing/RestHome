package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OutStorage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "out_storage", catalog = "rest_home")
public class OutStorage implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private Goods goods;
	private String outNum;
	private String outDate;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public OutStorage() {
	}

	/** minimal constructor */
	public OutStorage(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public OutStorage(String uuid, Employee employee, Goods goods,
			String outNum, String outDate, String insertTime, String status) {
		this.uuid = uuid;
		this.employee = employee;
		this.goods = goods;
		this.outNum = outNum;
		this.outDate = outDate;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_uuid")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goods_uuid")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "out_Num",length = 20)
	public String getOutNum() {
		return this.outNum;
	}

	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}

	@Column(name = "out_Date",length = 45)
	public String getOutDate() {
		return this.outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	@Column(name = "insert_Time",length = 45)
	public String getInsertTime() {
		return this.insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	@Column(name = "status",length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}