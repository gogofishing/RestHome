package com.resthome.dao;

import java.util.List;




import com.resthome.entity.CheckIn;
import com.resthome.vo.PageVo;


public interface CheckInDaoInter {
	
	/** 
	 * @Title: findCheckInByPage 
	 * @Description: TODO(根据页号查找考勤记录表) 
	 * @param @param pageVo
	 * @param @param hql
	 * @param @return 设定文件 
	 * @return List<CheckIn> 返回类型 
	 * @throws 
	 */ 
	List<CheckIn> findCheckInByPage(PageVo pageVo,String hql);
	
	
	
	
	
	/** 
	 * @Title: addCheckIn 
	 * @Description: TODO(添加考勤记录) 
	 * @param @param checkIn
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws 
	 */ 
	String addCheckIn(CheckIn checkIn);
	
	
	
	/** 
	 * @Title: modifyCheckIn 
	 * @Description: TODO(修改考勤记录) 
	 * @param @param hql
	 * @param @return 设定文件 
	 * @return String 返回类型 
	 * @throws 
	 */ 
	String modifyCheckIn(String hql);
	
	
	
	/** 
	 * @Title: findCheckInByHql 
	 * @Description: TODO(根据hql查找考勤记录) 
	 * @param @param hql
	 * @param @return 设定文件 
	 * @return CheckIn 返回类型 
	 * @throws 
	 */ 
	CheckIn findCheckInByHql(String hql);

	
	
	 /** 
	 * @Title: getTotalNum 
	 * @Description: TODO(获得总条数) 
	 * @param @param hql
	 * @param @return 设定文件 
	 * @return Integer 返回类型 
	 * @throws 
	 */ 
	Integer getTotalNum(String hql);
	
	
}
