package com.resthome.service;

import java.util.List;

import com.resthome.entity.HealthInfo;
import com.resthome.entity.OldPeople;
import com.resthome.vo.PageVo;

public interface OldPeopleHealthInfoServiceInter {

	String addHealthInfo(HealthInfo healthinfo);

	String modifyHealthInfo(HealthInfo healthinfo);

	String getHealthInfo(String id);

	String delHealthInfo(String id);

	String getAllHealthInfo(HealthInfo healthinfo,PageVo pagevo);
}
