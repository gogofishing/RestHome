package com.resthome.dao;

import java.util.List;

import com.resthome.entity.HealthInfo;
import com.resthome.vo.PageVo;

public interface OldPeopleHealthInfoDaoInter {
	
	String addHealthInfo(HealthInfo healthinfo);
	
	String modifyHealthInfo(HealthInfo healthinfo);

	List<HealthInfo> getHealthInfo(String id);

	String delHealthInfo(String id);

	List<HealthInfo> getAllHealthInfo(HealthInfo healthinfo,PageVo pagevo);
}
