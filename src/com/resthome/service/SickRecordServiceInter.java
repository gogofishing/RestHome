package com.resthome.service;

import java.util.List;

import com.resthome.entity.SickRecord;

public interface SickRecordServiceInter {

	String addSickRecord(SickRecord sickRecord);

	String modifySickRecord(SickRecord sickRecord);

	String delSickRecord(SickRecord sickRecord);

	String getSickReord(SickRecord sickRecord); 
}
