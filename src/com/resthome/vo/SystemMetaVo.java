package com.resthome.vo;

import com.panjun.annotation.Name;

public class SystemMetaVo {
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
	@Name(name="uuid")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Name(name="childMetaName")
	public String getChildMetaName() {
		return childMetaName;
	}
	public void setChildMetaName(String childMetaName) {
		this.childMetaName = childMetaName;
	}
	@Name(name="metaNickName")
	public String getMetaNickName() {
		return metaNickName;
	}
	public void setMetaNickName(String metaNickName) {
		this.metaNickName = metaNickName;
	}
	@Name(name="metaValue")
	public String getMetaValue() {
		return metaValue;
	}
	public void setMetaValue(String metaValue) {
		this.metaValue = metaValue;
	}
	@Name(name="metaValueType")
	public String getMetaValueType() {
		return metaValueType;
	}
	public void setMetaValueType(String metaValueType) {
		this.metaValueType = metaValueType;
	}
	@Name(name="metaValueHtmlSource")
	public String getMetaValueHtmlSource() {
		return metaValueHtmlSource;
	}
	public void setMetaValueHtmlSource(String metaValueHtmlSource) {
		this.metaValueHtmlSource = metaValueHtmlSource;
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
	@Name(name="listOrder")
	public String getListOrder() {
		return listOrder;
	}
	public void setListOrder(String listOrder) {
		this.listOrder = listOrder;
	}
	@Name(name="parentMetaName")
	public String getParentMetaName() {
		return parentMetaName;
	}
	public void setParentMetaName(String parentMetaName) {
		this.parentMetaName = parentMetaName;
	}
	
}
	