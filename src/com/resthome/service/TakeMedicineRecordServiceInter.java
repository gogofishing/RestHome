package com.resthome.service;

import com.resthome.vo.TakeMedicineRecordVo;

public interface TakeMedicineRecordServiceInter {

	public String addTakeMedicineRecord(TakeMedicineRecordVo takeMedicineRecordVo);

	public String modifyTakeMedicineRecord(TakeMedicineRecordVo takeMedicineRecordVo);

	public String deleteTakeMedicineRecord(String uuid);
	
	public String getAllByPage(int nowPage, String oldPeopleNo, String oldPeopleName);

	public String getMoreInfo(String uuid);

	public String getOldPeopleByNo(String oldPeopleNo);

	public String getTakeMedicineRecord(String uuid);

	public String getTakeTimes();
}
