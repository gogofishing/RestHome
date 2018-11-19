package com.resthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Repository;

import com.resthome.dao.SickRecordDaoInter;
import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.entity.OldPeople;
import com.resthome.entity.SickRecord;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.SickRecordVo;

@Repository(value="sickRecordService")
public class SickRecordServiceImpl implements SickRecordServiceInter{

	@Resource
	private SickRecordDaoInter sickRecordDao;
	@Resource
	private OldPeopleDaoInter oldPeopleDao;
	
	@Override
	public String addSickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		OldPeople oldPeople = oldPeopleDao.findByUuid(sickRecord.getOldPeople().getUuid());
		System.out.println(oldPeople);
		sickRecord.setUuid(UUID.randomUUID().toString());
		sickRecord.setInsertTime(GetSystemTime.currentTime());
		sickRecord.setStatus(ParamUtil.STATUS_UNDELETE);
		sickRecord.setOldPeople(oldPeople);
		return sickRecordDao.addSickRecord(sickRecord);
	}

	@Override
	public String modifySickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		return sickRecordDao.modifySickRecord(sickRecord);
	}

	@Override
	public String delSickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		return sickRecordDao.delSickRecord(sickRecord);
	}

	@Override
	public String getSickReord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		List <SickRecord> srList = sickRecordDao.getSickRecord(sickRecord);
		JSONArray ja = new JSONArray();
		List <Object> obj = new ArrayList<Object>();
		for(int i=0;i<srList.size();i++){
			SickRecordVo sickRecordVo = new SickRecordVo();
			sickRecordVo.setUuid(srList.get(i).getUuid());
			sickRecordVo.setOldPeopleUuid(srList.get(i).getOldPeople().getUuid());
			sickRecordVo.setCheckDate(srList.get(i).getCheckDate());
			sickRecordVo.setInHospitalDate(srList.get(i).getInHospitalDate());
			sickRecordVo.setOutHospitalDate(srList.get(i).getOutHospitalDate());
			sickRecordVo.setSickResonHtmlSource(srList.get(i).getSickResonHtmlSource());
			sickRecordVo.setSickType(srList.get(i).getSickType());
			sickRecordVo.setDoctor(srList.get(i).getDoctor());
			sickRecordVo.setCheckInfoHtmlSource(srList.get(i).getCheckInfoHtmlSource());
			sickRecordVo.setCarePeopleInfoHtmlSource(srList.get(i).getCarePeopleInfoHtmlSource());
			sickRecordVo.setCureInfoHtmlSource(srList.get(i).getCureInfoHtmlSource());
			sickRecordVo.setCheckProjectInfoHtmlSource(srList.get(i).getCheckProjectInfoHtmlSource());
			sickRecordVo.setCheckResultHtmlSource(srList.get(i).getCheckResultHtmlSource());
			
			obj.add(sickRecordVo);
			ja = ToJson.ToJsonArray(obj);
		}
		
		System.out.println("******"+ja.toString());
		return ja.toString();
	}
	
}
