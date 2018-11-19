package com.resthome.service;

import java.util.List;

import com.resthome.entity.OldPeopleCheckRecord;

public interface OldPeopleCheckRecordServiceInter {

	String addCheckRecord(OldPeopleCheckRecord checkRecord);

	String modifyCheckRecord(OldPeopleCheckRecord checkRecord);

	String delCheckRecord(OldPeopleCheckRecord checkRecord);

	String getCheckRecord(OldPeopleCheckRecord checkRecord);

	String isExistEmployee(String empNo);
}
