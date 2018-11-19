package com.resthome.service;

import com.resthome.vo.OldPeopleOutInRecordVo;


public interface OldPeopleOutInRecordServiceInter {
	

	public String getAllByPage(int nowPage,String oldPeopleNo,String oldPeopleName);
	
	public String getOldPeopleByNo(String oldPeopleNo);
	
	public String getEmployeeByNo(String empNo);

	public String addOldPeopleOutInRecord(OldPeopleOutInRecordVo oldPeopleOutInRecordVo);

	public String deleteOldPeopleOutInRecord(String uuid);

	public String getMoreInfo(String uuid);
}
