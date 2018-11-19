package com.resthome.dao;

import java.util.List;

import com.resthome.entity.SickRecord;

public interface SickRecordDaoInter {

	String addSickRecord(SickRecord sickRecord);

	String modifySickRecord(SickRecord sickRecord);

	String delSickRecord(SickRecord sickRecord);

	List<SickRecord> getSickRecord(SickRecord sickRecord);
}
