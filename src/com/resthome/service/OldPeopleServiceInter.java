package com.resthome.service;

import com.resthome.entity.OldPeople;

public interface OldPeopleServiceInter {


	public String getAllByPage(int nowPage,String oldPeopleName,String oldPeopleNo);

	public String addOldPeople(OldPeople oldPeople, String uuid, String dbPicPath);

	public String deleteOldPeopleByUuid(String opUuid);

	public String getOldPeopleByUuid(String opUuid);

	public OldPeople getOldPeopleMoreInfo(String opUuid);

	public String modifyOldPeople(OldPeople oldPeople, String dbPicPath);

	public String isExistEmployee(String empNo);

	public String getGrade();

	public String getMarriage();

	public String getParty();

	public String getFamilyParam();

	public String getEmergencyPeopleParam();

	public String getMoreHtmlSourceParam();
	
}
