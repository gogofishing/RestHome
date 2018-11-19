package com.resthome.service;

import java.util.List;

import com.resthome.entity.CommunityActivity;
import com.resthome.entity.Employee;
import com.resthome.vo.PageVo;

public interface CommunityActivityServiceInter {
	
	String addCommunityActivity(CommunityActivity communityActivity,Employee employee);
	
	String updateCommunityActivity(CommunityActivity communityActivity,Employee employee);
	
	String delCommunityActivity(CommunityActivity communityActivity);
	
	CommunityActivity getCommunityActivity(CommunityActivity communityActivity);
	
	List<CommunityActivity> findCommunityActivityByPage(PageVo pageVo,CommunityActivity communityActivity);
	
	Integer getTotalNum(CommunityActivity communityActivity);

}
