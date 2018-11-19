package com.resthome.dao;

import java.util.List;

import com.resthome.entity.OldPeopleCheckRecord;


public interface OldPeopleCheckRecordDaoInter {
	
	String addCheckRecord(OldPeopleCheckRecord checkRecord);
	
	String modifyCheckRecord(OldPeopleCheckRecord checkRecord);
	
	String delCheckRecord(OldPeopleCheckRecord checkRecord);

	List <OldPeopleCheckRecord> getCheckRecord(OldPeopleCheckRecord checkRecord);
}
