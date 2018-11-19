package com.resthome.dao;

import java.util.List;

import com.resthome.entity.Volunteer;
import com.resthome.vo.PageVo;

public interface VolunteerDaoInter {
	
	String addVolunteer(Volunteer volunteer);
	
	String updateVolunteer(String hql);
	
	Volunteer getVolunteer(String hql);
	
	List<Volunteer> findVolunteerByPage(PageVo pageVo,String hql);
	
	Integer findTotalNum(String hql);

}
