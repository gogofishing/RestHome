package com.resthome.service;


import java.util.List;

import com.resthome.entity.Employee;
import com.resthome.entity.Volunteer;
import com.resthome.vo.PageVo;
import com.resthome.vo.VolunteerVo;

public interface VolunteerServiceInter {
	
	String addVolunteer(Volunteer volunteer);
	
	String delVolunteer(Volunteer volunteer);
	
	String updateVolunteer(VolunteerVo volunteerVo);
	
	Volunteer getVolunteer(Volunteer volunteer);
	
	List<Volunteer> findVolunteerByPage(PageVo pageVo,Volunteer volunteer);
	
	Integer getTotalNum(Volunteer volunteer);

}
