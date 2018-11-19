package com.resthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Repository;

import com.resthome.dao.EmployeeDaoInter;
import com.resthome.dao.OldPeopleCheckRecordDaoInter;
import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.OldPeople;
import com.resthome.entity.OldPeopleCheckRecord;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.OldPeopleCheckRecordVo;

@Repository(value="oldPeopleCheckRecordService")
public class OldPeopleCheckRecordServiceImpl implements
		OldPeopleCheckRecordServiceInter {
	
	@Resource
	private OldPeopleCheckRecordDaoInter oldPeopleCheckRecordDao;
	@Resource
	private OldPeopleDaoInter oldPeopleDao;
	@Resource
	private EmployeeDaoInter employeeDao;
	
	@Override
	public String addCheckRecord(OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		OldPeople oldPeople = oldPeopleDao.findByUuid(checkRecord.getOldPeople().getUuid());
		
		checkRecord.setUuid(UUID.randomUUID().toString());
		checkRecord.setInsertTime(GetSystemTime.currentTime());
		checkRecord.setStatus(ParamUtil.STATUS_UNDELETE);
		checkRecord.setOldPeople(oldPeople);
		return oldPeopleCheckRecordDao.addCheckRecord(checkRecord);
	}

	@Override
	public String modifyCheckRecord(OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		return oldPeopleCheckRecordDao.modifyCheckRecord(checkRecord);
	}

	@Override
	public String delCheckRecord(OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
 		return oldPeopleCheckRecordDao.delCheckRecord(checkRecord);
	}

	@Override
	public String getCheckRecord(
			OldPeopleCheckRecord checkRecord) {
		// TODO Auto-generated method stub
		List<OldPeopleCheckRecord> crList = oldPeopleCheckRecordDao.getCheckRecord(checkRecord);
		List<Object> obj = new ArrayList<Object>();
		JSONArray ja = new JSONArray();
		for(int i=0;i<crList.size();i++){
			OldPeopleCheckRecordVo oldPeoplecheckRecordVo = new OldPeopleCheckRecordVo();
			oldPeoplecheckRecordVo.setUuid(crList.get(i).getUuid());
			oldPeoplecheckRecordVo.setOldPeopleUuid(crList.get(i).getOldPeople().getUuid());
			oldPeoplecheckRecordVo.setCheckInfoHtmlSource(crList.get(i).getCheckInfoHtmlSource());
			oldPeoplecheckRecordVo.setCheckProject(crList.get(i).getCheckProject());
			oldPeoplecheckRecordVo.setCheckResultHtmlSource(crList.get(i).getCheckResultHtmlSource());
			oldPeoplecheckRecordVo.setNextCheckTime(crList.get(i).getNextCheckTime());
			oldPeoplecheckRecordVo.setWorkPeopleNo(crList.get(i).getWorkPeopleNo());
			oldPeoplecheckRecordVo.setWorkPeopleName(crList.get(i).getWorkPeopleName());
			
			obj.add(oldPeoplecheckRecordVo);
			ja = ToJson.ToJsonArray(obj);
		}
		
		System.out.println(ja.toString());
		return ja.toString();
	}
	
	public String isExistEmployee(String empNo) {
		String hql = "from Employee where employeeNo = '"+empNo+"' and status = '"+ParamUtil.STATUS_UNDELETE+"'";
		Employee employee = employeeDao.getEmployee(hql);
		if(employee==null){
			return "error";
		}
		return "success";
	}

}
