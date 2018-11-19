package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.Employee;
import com.resthome.entity.Volunteer;
import com.resthome.vo.PageVo;

@Repository(value="volunteerDao")
public class VolunteerDaoImpl extends BaseDaoImpl implements VolunteerDaoInter{

	@Override
	public String addVolunteer(Volunteer volunteer) {
		// TODO Auto-generated method stub
		return super.addObject(volunteer);
	}

	@Override
	public String updateVolunteer(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public Volunteer getVolunteer(String hql) {
		// TODO Auto-generated method stub
		return (Volunteer)super.findObjectByHql(hql);
	}

	@Override
	public List<Volunteer> findVolunteerByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		return (List<Volunteer>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
	}

	@Override
	public Integer findTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}



}
