package com.resthome.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SystemMeta entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "system_meta", catalog = "rest_home")
public class SystemMeta implements java.io.Serializable {

	// Fields

	private String uuid;
	private String childMetaName;
	private String metaNickName;
	private String metaValue;
	private String metaValueType;
	private String metaValueHtmlSource;
	private String insertTime;
	private String status;
	private String listOrder;
	private String parentMetaName;

	// Constructors

	/** default constructor */
	public SystemMeta() {
	}

	/** minimal constructor */
	public SystemMeta(String uuid) {
		this.uuid = uuid;
	}

	/** full constructor */
	public SystemMeta(String uuid, String childMetaName, String metaNickName,
			String metaValue, String metaValueType, String metaValueHtmlSource,
			String insertTime, String status, String listOrder,
			String parentMetaName) {
		this.uuid = uuid;
		this.childMetaName = childMetaName;
		this.metaNickName = metaNickName;
		this.metaValue = metaValue;
		this.metaValueType = metaValueType;
		this.metaValueHtmlSource = metaValueHtmlSource;
		this.insertTime = insertTime;
		this.status = status;
		this.listOrder = listOrder;
		this.parentMetaName = parentMetaName;
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

	@Column(name = "child_meta_name")
	public String getChildMetaName() {
		return this.childMetaName;
	}

	public void setChildMetaName(String childMetaName) {
		this.childMetaName = childMetaName;
	}

	@Column(name = "meta_nick_name")
	public String getMetaNickName() {
		return this.metaNickName;
	}

	public void setMetaNickName(String metaNickName) {
		this.metaNickName = metaNickName;
	}

	@Column(name = "meta_value")
	public String getMetaValue() {
		return this.metaValue;
	}

	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}

	@Column(name = "meta_value_type")
	public String getMetaValueType() {
		return this.metaValueType;
	}

	public void setMetaValueType(String metaValueType) {
		this.metaValueType = metaValueType;
	}

	@Column(name = "meta_value_html_source", length = 65535)
	public String getMetaValueHtmlSource() {
		return this.metaValueHtmlSource;
	}

	public void setMetaValueHtmlSource(String metaValueHtmlSource) {
		this.metaValueHtmlSource = metaValueHtmlSource;
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

	@Column(name = "list_order", length = 45)
	public String getListOrder() {
		return this.listOrder;
	}

	public void setListOrder(String listOrder) {
		this.listOrder = listOrder;
	}

	@Column(name = "parent_meta_name")
	public String getParentMetaName() {
		return this.parentMetaName;
	}

	public void setParentMetaName(String parentMetaName) {
		this.parentMetaName = parentMetaName;
	}

}