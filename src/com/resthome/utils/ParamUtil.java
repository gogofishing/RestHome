package com.resthome.utils;


public class ParamUtil {
	
	/**
	 * 分页显示的每页记录条数
	 */
	public static final int rowsPerPage=15;
	
	/**
	 * app端的分页显示的每页记录条数(出错时默认9个)
	 */
	public static final int appRowsPerPage = 9;
	/**
	 * app端的分页显示的当前页数(出错时默认为第一页)
	 */
	public static final int appNowPage = 1;
	/**
	 * 未删除标识1
	 */
	public static final String STATUS_UNDELETE="1";
	
	/**
	 * 已删除标识0
	 */
	public static final String STATUS_DELETEED="0";
	
	/**
	 * 普通用户user001 
	 */
	public static final String OrdinaryUser = "user001";
	
	/**
	 * 医生user002
	 */
	public static final String DoctorUser = "user002";
	
	public static final Integer POWER = 5;
	

	
}
