package com.resthome.dao;

import java.util.List;

import com.resthome.entity.VolunteerActivity;
import com.resthome.vo.PageVo;

public interface VolunteerActivityDaoInter {
	
	String addVolunteerActivity(VolunteerActivity volunteerActivity);
	
	String updateVolunteerActivity(String hql);
	
	VolunteerActivity getVolunteerActivity(String hql);
	
	List<VolunteerActivity> findVolunteerActivityByPage(PageVo pageVo,String hql);
	
	Integer findTotalNum(String hql);

}
