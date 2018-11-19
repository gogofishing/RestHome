package com.resthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Repository;

import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.dao.OldPeopleHealthInfoDaoInter;
import com.resthome.entity.HealthInfo;
import com.resthome.entity.OldPeople;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.HealthInfoVo;
import com.resthome.vo.PageVo;

@Repository(value="oldPeopleHealthInfoService")
public class OldPeopleHealthInfoServiceImpl implements
		OldPeopleHealthInfoServiceInter {
	
	@Resource
	private OldPeopleHealthInfoDaoInter OldPeopleHealthInfoDao;
	@Resource
	private OldPeopleDaoInter oldPeopleDao;
	
	@Override
	public String addHealthInfo(HealthInfo healthinfo) {
		// TODO Auto-generated method stub
		//String uuid = healthinfo.getOldPeople().getUuid();
		OldPeople oldPeople = oldPeopleDao.findByUuid(healthinfo.getOldPeople().getUuid());
		
		healthinfo.setUuid(UUID.randomUUID().toString());
		healthinfo.setInsertTime(GetSystemTime.currentTime());
		healthinfo.setStatus(ParamUtil.STATUS_UNDELETE);
		
		healthinfo.setOldPeople(oldPeople);
		
		return OldPeopleHealthInfoDao.addHealthInfo(healthinfo);
	}

	@Override
	public String modifyHealthInfo(HealthInfo healthinfo) {
		// TODO Auto-generated method stub
		return OldPeopleHealthInfoDao.modifyHealthInfo(healthinfo);
	}

	@Override
	public String getHealthInfo(String id) {
		// TODO Auto-generated method stub
		 List<HealthInfo> list =OldPeopleHealthInfoDao.getHealthInfo(id);
		 JSONArray ja=new JSONArray();
		 
		 List<Object> obj= new ArrayList<Object>();
		 for(int i=0;i<list.size();i++){
			 HealthInfoVo healthinfovo=new HealthInfoVo();
			 
			 healthinfovo.setUuid(list.get(i).getUuid());
			 healthinfovo.setSickName(list.get(i).getSickName());
			 healthinfovo.setSickDate(list.get(i).getSickDate());
			 healthinfovo.setCureDate(list.get(i).getCureDate());
			 healthinfovo.setCureInfoHtmlSource(list.get(i).getCureInfoHtmlSource());
			 healthinfovo.setSequela(list.get(i).getSequela());
			 healthinfovo.setOldPeopleUuid(list.get(i).getOldPeople().getUuid());
			 obj.add(healthinfovo);
			 ja = ToJson.ToJsonArray(obj);
		 }
		 
		 System.out.println(ja.toString());
		 return ja.toString();
	}

	@Override
	public String getAllHealthInfo(HealthInfo healthinfo,PageVo pagevo) {
		// TODO Auto-generated method stub
		List<HealthInfo> list=OldPeopleHealthInfoDao.getAllHealthInfo(healthinfo, pagevo);
		
		JSONArray ja=new JSONArray();
		List<Object> obj= new ArrayList<Object>();
		 for(int i=0;i<list.size();i++){
			 HealthInfoVo healthinfovo=new HealthInfoVo();
			 
			 healthinfovo.setUuid(list.get(i).getUuid());
			 healthinfovo.setSickName(list.get(i).getSickName());
			 healthinfovo.setSickDate(list.get(i).getSickDate());
			 healthinfovo.setCureDate(list.get(i).getCureDate());
			 healthinfovo.setCureInfoHtmlSource(list.get(i).getCureInfoHtmlSource());
			 healthinfovo.setSequela(list.get(i).getSequela());
			 healthinfovo.setOldPeopleNo(list.get(i).getOldPeople().getOldPeopleNo());
			 healthinfovo.setOldPeopleName(list.get(i).getOldPeople().getOldPeopleName());
			 obj.add(healthinfovo);
			 ja = ToJson.ToJsonArray(obj);
		 }
		 
		 System.out.println(ja.toString());
		 return ja.toString();
	}

	@Override
	public String delHealthInfo(String id) {
		// TODO Auto-generated method stub
		return OldPeopleHealthInfoDao.delHealthInfo(id);
	}

}
