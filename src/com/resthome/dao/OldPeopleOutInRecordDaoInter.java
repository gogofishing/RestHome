package com.resthome.dao;

import java.util.List;

import com.resthome.entity.OldPeopleOutInRecord;


public interface OldPeopleOutInRecordDaoInter {
	

	public List<OldPeopleOutInRecord> findAllByPage(int nowPage, int rowsPerPage, List<Object> params, String hql);

	public int findTotalNumByPage(List<Object> params, String hql);

	public String updateByDelete(String uuid);

	public String saveOldPeopleOutInRecord(OldPeopleOutInRecord oldPeopleOutInRecord);

	public OldPeopleOutInRecord findOldPeopleOutInRecordByUuid(String uuid);
	
}
