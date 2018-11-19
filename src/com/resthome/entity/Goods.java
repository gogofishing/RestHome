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
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods", catalog = "rest_home")
public class Goods implements java.io.Serializable {

	// Fields

	private String uuid;
	private Employee employee;
	private String goodsNo;
	private String goodsName;
	private String goodsManufacturer;
	private String goodsProductionDate;
	private String goodsPrice;
	private String goodsNum;
	private String goodsInDate;
	private String insertTime;
	private String status;
	private Set<OutStorage> outStorages = new HashSet<OutStorage>(0);
	private Set<InStorage> inStorages = new HashSet<InStorage>(0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** minimal constructor */
	public Goods(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public Goods(String uuid, Employee employee, String goodsNo,
			String goodsName, String goodsManufacturer,
			String goodsProductionDate, String goodsPrice, String goodsNum,
			String goodsInDate, String insertTime, String status,
			Set<OutStorage> outStorages, Set<InStorage> inStorages) {
		this.uuid = uuid;
		this.employee = employee;
		this.goodsNo = goodsNo;
		this.goodsName = goodsName;
		this.goodsManufacturer = goodsManufacturer;
		this.goodsProductionDate = goodsProductionDate;
		this.goodsPrice = goodsPrice;
		this.goodsNum = goodsNum;
		this.goodsInDate = goodsInDate;
		this.insertTime = insertTime;
		this.status = status;
		this.outStorages = outStorages;
		this.inStorages = inStorages;
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
	@JoinColumn(name = "purchase_people")
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "goods_no",length = 100)
	public String getGoodsNo() {
		return this.goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	@Column(name = "goods_name",length = 100)
	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Column(name = "goods_manufacturer",length = 45)
	public String getGoodsManufacturer() {
		return this.goodsManufacturer;
	}

	public void setGoodsManufacturer(String goodsManufacturer) {
		this.goodsManufacturer = goodsManufacturer;
	}

	@Column(name = "goods_production_date",length = 45)
	public String getGoodsProductionDate() {
		return this.goodsProductionDate;
	}

	public void setGoodsProductionDate(String goodsProductionDate) {
		this.goodsProductionDate = goodsProductionDate;
	}

	@Column(name = "goods_price",length = 20)
	public String getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Column(name = "goods_num",length = 20)
	public String getGoodsNum() {
		return this.goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	@Column(name = "goods_in_date",length = 45)
	public String getGoodsInDate() {
		return this.goodsInDate;
	}

	public void setGoodsInDate(String goodsInDate) {
		this.goodsInDate = goodsInDate;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<OutStorage> getOutStorages() {
		return this.outStorages;
	}

	public void setOutStorages(Set<OutStorage> outStorages) {
		this.outStorages = outStorages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<InStorage> getInStorages() {
		return this.inStorages;
	}

	public void setInStorages(Set<InStorage> inStorages) {
		this.inStorages = inStorages;
	}

}