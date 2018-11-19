package com.resthome.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.OldPeopleCheckRecord;
import com.resthome.utils.ParamUtil;

@Repository(value="oldPeopleCheckRecordDao")
public class OldPeopleCheckRecordDaoImpl extends BaseDaoImpl implements
		OldPeopleCheckRecordDaoInter {

	@Override
	public String addCheckRecord(OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		return super.addObject(checkRecord);
	}

	@Override
	public String modifyCheckRecord(OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		List<Object> crList = new ArrayList<Object>();
		String hql=null;
		if(checkRecord!=null){
			hql="update OldPeopleCheckRecord set ";
			
			if(checkRecord.getCheckInfoHtmlSource()!=null){
				hql=hql+"checkInfoHtmlSource = ?,";
				crList.add(checkRecord.getCheckInfoHtmlSource());
			}
			if(checkRecord.getCheckProject()!=null){
				hql=hql+"checkProject = ?,";
				crList.add(checkRecord.getCheckProject());
			}
			if(checkRecord.getCheckResultHtmlSource()!=null){
				hql=hql+"checkResultHtmlSource = ?,";
				crList.add(checkRecord.getCheckResultHtmlSource());
			}
			if(checkRecord.getNextCheckTime()!=null){
				hql=hql+"nextCheckTime = ?,";
				crList.add(checkRecord.getNextCheckTime());
			}
			if(checkRecord.getWorkPeopleName()!=null){
				hql=hql+"workPeopleName = ?,";
				crList.add(checkRecord.getWorkPeopleName());
			}
			if(checkRecord.getWorkPeopleNo()!=null){
				hql=hql+"workPeopleNo = ?,";
				crList.add(checkRecord.getWorkPeopleNo());
			}
			if(hql.endsWith(",")){
				hql=hql.substring(0, hql.length()-1);
			}
			hql=hql+"where uuid = ?";
			crList.add(checkRecord.getUuid());
		}else{
			return "success";
		}
		
		
		return super.updateByHql(hql, crList);
	}

	@Override
	public String delCheckRecord(OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		String hql="update OldPeopleCheckRecord set status= ? where uuid = ? ";
		List<Object> crList = new ArrayList<Object>();
		crList.add(ParamUtil.STATUS_DELETEED);
		crList.add(checkRecord.getUuid());
		System.out.println(hql);
		return super.updateByHql(hql, crList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OldPeopleCheckRecord> getCheckRecord(
			OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		String hql="from OldPeopleCheckRecord where status = ? and oldPeople.uuid = ?";
		List<Object> crList = new ArrayList<Object>();
		crList.add(ParamUtil.STATUS_UNDELETE);
		crList.add(checkRecord.getOldPeople().getUuid());
		return (List<OldPeopleCheckRecord>) super.findObjectsByHqlByPage(null, null, hql, crList);
	}

}
