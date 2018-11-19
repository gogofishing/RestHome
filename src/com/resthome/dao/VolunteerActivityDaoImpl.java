package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.Volunteer;
import com.resthome.entity.VolunteerActivity;
import com.resthome.vo.PageVo;

@Repository(value="volunteerActivityDao")
public class VolunteerActivityDaoImpl extends BaseDaoImpl implements VolunteerActivityDaoInter{

	@Override
	public String addVolunteerActivity(VolunteerActivity volunteerActivity) {
		// TODO Auto-generated method stub
		return super.addObject(volunteerActivity);
	}

	@Override
	public String updateVolunteerActivity(String hql) {
		// TODO Auto-generated method stub		
		return super.updateByHql(hql);
	}

	@Override
	public VolunteerActivity getVolunteerActivity(String hql) {
		// TODO Auto-generated method stub
		return (VolunteerActivity)super.findObjectByHql(hql);
	}

	@Override
	public List<VolunteerActivity> findVolunteerActivityByPage(PageVo pageVo,
			String hql) {
		// TODO Auto-generated method stub
		return (List<VolunteerActivity>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
	}

	@Override
	public Integer findTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	
}
