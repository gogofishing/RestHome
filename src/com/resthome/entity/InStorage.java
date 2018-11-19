package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * InStorage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "in_storage", catalog = "rest_home")
public class InStorage implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private Goods goods;
	private String inNum;
	private String inDate;
	private String insertTime;
	private String status;

	// Constructors

	/** default constructor */
	public InStorage() {
	}

	/** minimal constructor */
	public InStorage(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public InStorage(String uuid, Employee employee, Goods goods, String inNum,
			String inDate, String insertTime, String status) {
		this.uuid = uuid;
		this.employee = employee;
		this.goods = goods;
		this.inNum = inNum;
		this.inDate = inDate;
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

	@Column(name = "in_num",length = 20)
	public String getInNum() {
		return this.inNum;
	}

	public void setInNum(String inNum) {
		this.inNum = inNum;
	}

	@Column(name = "in_date",length = 45)
	public String getInDate() {
		return this.inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	@Column(name = "insert_time",length = 45)
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