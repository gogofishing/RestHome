package com.resthome.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.SickRecord;
import com.resthome.utils.ParamUtil;

@Repository(value="sickRecordDao")
public class SickRecordDaoImpl extends BaseDaoImpl implements SickRecordDaoInter{

	@Override
	public String addSickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		return super.addObject(sickRecord);
	}

	@Override
	public String modifySickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		String hql=null;
		List<Object> sList = new ArrayList<Object>();
		if(sickRecord!=null){
			hql = "update SickRecord set ";
			if(sickRecord.getCheckDate()!=null){
				hql=hql+"checkDate = ?,";
				sList.add(sickRecord.getCheckDate());
			}
			if(sickRecord.getInHospitalDate()!=null){
				hql=hql+"inHospitalDate = ?,";
				sList.add(sickRecord.getInHospitalDate());
			}
			if(sickRecord.getOutHospitalDate()!=null){
				hql=hql+"outHospitalDate = ?,";
				sList.add(sickRecord.getOutHospitalDate());
			}
			if(sickRecord.getSickResonHtmlSource()!=null){
				hql=hql+"sickResonHtmlSource = ?,";
				sList.add(sickRecord.getSickResonHtmlSource());
			}
			if(sickRecord.getSickType()!=null){
				hql=hql+"sickType = ?,";
				sList.add(sickRecord.getSickType());
			}
			if(sickRecord.getDoctor()!=null){
				hql=hql+"doctor = ?,";
				sList.add(sickRecord.getDoctor());
			}
			if(sickRecord.getCureInfoHtmlSource()!=null){
				hql=hql+"cureInfoHtmlSource = ?,";
				sList.add(sickRecord.getCureInfoHtmlSource());
			}
			if(sickRecord.getCarePeopleInfoHtmlSource()!=null){
				hql=hql+"carePeopleInfoHtmlSource = ?,";
				sList.add(sickRecord.getCarePeopleInfoHtmlSource());
			}
			if(sickRecord.getCheckProjectInfoHtmlSource()!=null){
				hql=hql+"checkProjectInfoHtmlSource = ?,";
				sList.add(sickRecord.getCheckProjectInfoHtmlSource());
			}
			if(sickRecord.getCheckResultHtmlSource()!=null){
				hql=hql+"checkResultHtmlSource = ?,";
				sList.add(sickRecord.getCheckResultHtmlSource());
			}
			if(sickRecord.getCheckInfoHtmlSource()!=null){
				hql=hql+"checkInfoHtmlSource = ?";
				sList.add(sickRecord.getCheckInfoHtmlSource());
			}
			if(hql.endsWith(",")){
				hql=hql.substring(0, hql.length()-1);
			}
			hql=hql+"where uuid = ?";
			sList.add(sickRecord.getUuid());
		}else{
			return "success";
		}
		
		return super.updateByHql(hql, sList);
	}

	@Override
	public String delSickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		String hql="update SickRecord set status= ? where uuid = ? ";
		List<Object> srList=new ArrayList<Object>();
		srList.add(ParamUtil.STATUS_DELETEED);
		srList.add(sickRecord.getUuid());
		return super.updateByHql(hql, srList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SickRecord> getSickRecord(SickRecord sickRecord) {
		// TODO Auto-generated method stub
		String hql="from SickRecord where status = ? and oldPeople.uuid = ?";
		List<Object> srList=new ArrayList<Object>();
		srList.add(ParamUtil.STATUS_UNDELETE);
		srList.add(sickRecord.getOldPeople().getUuid());
		
		return (List<SickRecord>) super.findObjectsByHqlByPage(null, null, hql, srList);
	}

}
