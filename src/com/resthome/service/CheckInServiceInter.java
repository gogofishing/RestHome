package com.resthome.service;

import java.util.List;

import com.resthome.entity.CheckIn;
import com.resthome.entity.Employee;
import com.resthome.vo.PageVo;


public interface CheckInServiceInter {
	

	String addCheckIn(CheckIn checkIn,Employee employee);
 
	String delCheckIn(CheckIn checkIn);
 
	String modifyCheckIn(CheckIn checkIn);
 
	CheckIn getCheckInByUuid(String uuid);
 
	List<CheckIn> findCheckInByPage(PageVo pageVo,String empNo,String beginDate,String endDate);

	Integer getTotalNum(PageVo pageVo,String empNo,String beginDate,String endDate);
	
}
