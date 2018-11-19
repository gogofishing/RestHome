package com.resthome.dao;

import java.util.List;

import com.resthome.entity.OldPeople;

public interface OldPeopleDaoInter {
	
	public OldPeople findByUuid(String uuid);
	
	public List<OldPeople> findAllByPage(int nowPage, int rowsPerPage, String hql);
	
	public int findTotalNumByPage(String hql);
	
	public String saveOldPeople(OldPeople oldPeople);
	
	public int findTotalNumNowYear(int nowYear);
	
	public String updateByDelete(String opUuid);
	
	public String updateByOldPeople(OldPeople oldPeople);

	public OldPeople findOldPeopleByNo(String oldPeopleNo);

}
