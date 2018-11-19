package com.resthome.service;

import java.util.List;

import com.resthome.entity.Employee;
import com.resthome.entity.Goods;
import com.resthome.entity.OldPeople;
import com.resthome.entity.Volunteer;
import com.resthome.entity.VolunteerActivity;
import com.resthome.vo.PageVo;

public interface VolunteerActivityServiceInter {
	
	String addVolunteerActivity(VolunteerActivity volunteerActivity,Volunteer volunteer,OldPeople oldPeople,Employee employee);
	
	String updateVolunteerActivity(VolunteerActivity volunteerActivity,Volunteer volunteer,OldPeople oldPeople,Employee employee);
	
	String delVolunteerActivity(VolunteerActivity volunteerActivity);
	
	VolunteerActivity getVolunteerActivity(VolunteerActivity volunteerActivity);
	
	List<VolunteerActivity> findVolunteerActivityByPage(PageVo pageVo,Volunteer volunteer,VolunteerActivity volunteerActivity);
	
	Integer getTotalNum(VolunteerActivity volunteerActivity,Volunteer volunteer);

}
