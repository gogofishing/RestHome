package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.CheckIn;
import com.resthome.vo.PageVo;


@Repository(value="checkInDao")
public class CheckInDaoImpl extends BaseDaoImpl implements CheckInDaoInter {

	@Override
	public List<CheckIn> findCheckInByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		List<CheckIn> checkIns=(List<CheckIn>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
		return checkIns;
	}

	@Override
	public String addCheckIn(CheckIn checkIn) {
		// TODO Auto-generated method stub
		return super.addObject(checkIn);
	}

	
	@Override
	public String modifyCheckIn(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public CheckIn findCheckInByHql(String hql) {
		// TODO Auto-generated method stub
		CheckIn c=(CheckIn)super.findObjectByHql(hql);		
		return c;
	}
	
	@Override
	public Integer getTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	
}
