package com.resthome.vo;

import java.util.List;
import java.util.Map;

public class PageVo {

	/**
	 * 数据的集合
	 */
	private List<?> listData;
	/**
	 * 总记录条数
	 */
	private int totalNum;
	/**
	 * 分页后的总的页数
	 */
	private int totalPage;
	/**
	 * 当前是第几页
	 */
	private int nowPage;
	/**
	 * 每一页显示的记录数
	 */
	private int rowsPerPage;
	
	private Map<String,Object> conditions;
	/**
	 * 用于方便分页条显示
	 */
	private List<Object> pageLine;
	
	
	/**
	 * PageVo的空构造方法
	 */
	public PageVo() {
		super();
	}


	/**
	 * @param listData
	 * @param totalNum
	 * @param totalPage
	 * @param nowPage
	 * @param rowsPerPage
	 * PageVo类的全构造方法
	 */
	public PageVo(List<?> listData, int totalNum, int totalPage, int nowPage,
			int rowsPerPage) {
		super();
		this.listData = listData;
		this.totalNum = totalNum;
		this.totalPage = totalPage;
		this.nowPage = nowPage;
		this.rowsPerPage = rowsPerPage;
	}


	public List<?> getListData() {
		return listData;
	}


	public void setListData(List<?> listData) {
		this.listData = listData;
	}


	public int getTotalNum() {
		return totalNum;
	}


	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}


	public int getTotalPage() {
		return totalPage;
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getNowPage() {
		return nowPage;
	}


	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}


	public int getRowsPerPage() {
		return rowsPerPage;
	}


	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}


	public Map<String, Object> getConditions() {
		return conditions;
	}


	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}


	public List<Object> getPageLine() {
		return pageLine;
	}


	public void setPageLine(List<Object> pageLine) {
		this.pageLine = pageLine;
	}


	


	
	
	
	
	
}
