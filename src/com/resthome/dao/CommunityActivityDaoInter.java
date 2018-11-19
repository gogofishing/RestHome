package com.resthome.dao;

import java.util.List;

import com.resthome.entity.CommunityActivity;
import com.resthome.vo.PageVo;

public interface CommunityActivityDaoInter {
	
	String addCommunityActivity(CommunityActivity communityActivity);
	
	String updateCommunityActivity(String hql);
	
	CommunityActivity getCommunityActivity(String hql);
	
	List<CommunityActivity> findCommunityActivityByPage(PageVo pageVo,String hql);
	
	Integer findTotalNum(String hql);

}
