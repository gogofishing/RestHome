package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.CommunityActivity;
import com.resthome.vo.PageVo;
@Repository(value="communityActivityDao")
public class CommunityActivityDaoImpl extends BaseDaoImpl implements CommunityActivityDaoInter{

	@Override
	public String addCommunityActivity(CommunityActivity communityActivity) {
		// TODO Auto-generated method stub
		return super.addObject(communityActivity);
	}

	@Override
	public String updateCommunityActivity(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public CommunityActivity getCommunityActivity(String hql) {
		// TODO Auto-generated method stub
		return (CommunityActivity)super.findObjectByHql(hql);
	}

	@Override
	public List<CommunityActivity> findCommunityActivityByPage(PageVo pageVo,
			String hql) {
		// TODO Auto-generated method stub
		return (List<CommunityActivity>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
	}

	@Override
	public Integer findTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

}
