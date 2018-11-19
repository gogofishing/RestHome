package com.resthome.service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.resthome.dao.VolunteerDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.Volunteer;
import com.resthome.utils.GetSerialNum;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.HqlUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;
import com.resthome.vo.VolunteerVo;

@Repository(value = "volunteerService")
public class VolunteerServiceImpl implements VolunteerServiceInter{
	 @Resource 
	 VolunteerDaoInter volunteerDao;

	@Override
	public String addVolunteer(Volunteer volunteer) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString();
		volunteer.setUuid(uuid);
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		Integer num = volunteerDao.findTotalNum("from Volunteer where insertTime like '%"+year+"%'");
		String volunteerNo=GetSerialNum.createVolunteerNo(year, num);
		volunteer.setVolunteerNo(volunteerNo);
		volunteer.setWorkHours(0);
		volunteer.setInsertTime(GetSystemTime.currentTime());
		volunteer.setStatus(ParamUtil.STATUS_UNDELETE);
		return volunteerDao.addVolunteer(volunteer);
	}

	@Override
	public String delVolunteer(Volunteer volunteer) {
		// TODO Auto-generated method stub
		if(volunteer==null){
			return "error";
		}else{
			String hql="update Volunteer set status='"+ParamUtil.STATUS_DELETEED+"' where uuid='"+volunteer.getUuid()+"'";
			return volunteerDao.updateVolunteer(hql);
		}
	}

	@Override
	public String updateVolunteer(VolunteerVo volunteerVo) {
		// TODO Auto-generated method stub
		if(volunteerVo==null){
			return "error";			
		}else{			
			String str = HqlUtils.getUpdateSet(volunteerVo);
	        if(str==null){
		    	return "error";
		    }
		    String hql = "update Volunteer set "+str+" where uuid='"+volunteerVo.getUuid()+"'";
		    return volunteerDao.updateVolunteer(hql);	      
		}

	}

	@Override
	public Volunteer getVolunteer(Volunteer volunteer) {
		// TODO Auto-generated method stub
		String hql=null;
		if(volunteer!=null){
			if(volunteer.getUuid()!=null){
				hql="from Volunteer where uuid='"+volunteer.getUuid()+"'";
			}
			if(volunteer.getVolunteerNo()!=null){
				hql = "from Volunteer where volunteerNo='"+volunteer.getVolunteerNo()+"'";
			}
		}
		return volunteerDao.getVolunteer(hql);	
		
	}

	@Override
	public List<Volunteer> findVolunteerByPage(PageVo pageVo, Volunteer volunteer) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Volunteer where ");	
		if(volunteer!=null&&volunteer.getVolunteerNo()!=null&&!(volunteer.getVolunteerNo().equals(""))){
			hql.append("volunteerNo like '%"+volunteer.getVolunteerNo()+"%' and");	
		}
		if(volunteer!=null&&volunteer.getVolunteerName()!=null&&!(volunteer.getVolunteerName().equals(""))){
			hql.append("volunteerName like '%"+volunteer.getVolunteerName()+"%' and");	
		}
		hql.append(" status!='0'");
		return volunteerDao.findVolunteerByPage(pageVo, hql.toString());
	}

	@Override
	public Integer getTotalNum(Volunteer volunteer) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Volunteer where ");	
		if(volunteer!=null&&volunteer.getVolunteerNo()!=null&&!(volunteer.getVolunteerNo().equals(""))){
			hql.append("volunteerNo like '%"+volunteer.getVolunteerNo()+"%' and");	
		}
		if(volunteer!=null&&volunteer.getVolunteerName()!=null&&!(volunteer.getVolunteerName().equals(""))){
			hql.append("volunteerName like '%"+volunteer.getVolunteerName()+"%' and");	
		}
		hql.append(" status!='0'");
		return volunteerDao.findTotalNum(hql.toString());
	}

}
