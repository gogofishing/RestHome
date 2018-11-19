package com.resthome.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.resthome.dao.EmployeeDaoInter;
import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.dao.VolunteerActivityDaoInter;
import com.resthome.dao.VolunteerDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.OldPeople;
import com.resthome.entity.Volunteer;
import com.resthome.entity.VolunteerActivity;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;
@Repository(value = "volunteerActivityService")
public class VolunteerActivityServiceImpl implements VolunteerActivityServiceInter{
	
	@Resource
	private VolunteerActivityDaoInter  volunteerActivityDao;
	@Resource 
	private EmployeeDaoInter employeeDao;
	@Resource
	private VolunteerDaoInter volunteerDao;
	@Resource
	private OldPeopleDaoInter oldPeopleDao;

	@Override
	public String addVolunteerActivity(VolunteerActivity volunteerActivity,Volunteer volunteer,
			OldPeople oldPeople, Employee employee) {
		String hql1="from Volunteer where volunteerNo='"+volunteer.getVolunteerNo()+"' and status!='0'";		
		String hql2="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
		Volunteer vlt=volunteerDao.getVolunteer(hql1);
		OldPeople old=oldPeopleDao.findOldPeopleByNo(oldPeople.getOldPeopleNo());
		Employee emp=employeeDao.getEmployee(hql2);
		if(vlt==null){
			return "vltNull";
		}
		if(emp==null){
			return "empNull";
		}
		if(old==null){
			return "oldNull";
		}
		
		volunteerActivity.setUuid(UUID.randomUUID().toString());
		volunteerActivity.setVolunteer(vlt);
		volunteerActivity.setOldPeople(old);
		volunteerActivity.setEmployee(emp);			
		volunteerActivity.setInsertTime(GetSystemTime.currentTime());
		volunteerActivity.setStatus(ParamUtil.STATUS_UNDELETE);		
		
		String hql="update Volunteer set workHours='"+(vlt.getWorkHours()+volunteerActivity.getHours())+"' where uuid='"+vlt.getUuid()+"'";
		volunteerDao.updateVolunteer(hql);
		
		return volunteerActivityDao.addVolunteerActivity(volunteerActivity);
		
		
	}

	@Override
	public String updateVolunteerActivity(VolunteerActivity volunteerActivity,Volunteer volunteer,
			OldPeople oldPeople, Employee employee) {
		if(volunteerActivity!=null&&oldPeople!=null&&employee!=null){
			StringBuffer hql=new StringBuffer("update VolunteerActivity set ");
			String hql1="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
			String hql2="from Volunteer where volunteerNo='"+volunteer.getVolunteerNo()+"' and status!='0'";	
			OldPeople old=oldPeopleDao.findOldPeopleByNo(oldPeople.getOldPeopleNo());
			Employee emp=employeeDao.getEmployee(hql1);		
			Volunteer vlt=volunteerDao.getVolunteer(hql2);
			VolunteerActivity va=this.getVolunteerActivity(volunteerActivity);
			if(emp==null){
				return "empNull";
			}
			if(old==null){
				return "oldNull";
			}
			
			hql.append("oldPeople.uuid='"+old.getUuid()+"',");
			hql.append("employee.uuid='"+emp.getUuid()+"',");	
			hql.append("activityContent='"+volunteerActivity.getActivityContent()+"',");
			hql.append("activityType='"+volunteerActivity.getActivityType()+"',");
			hql.append("activityPlace='"+volunteerActivity.getActivityPlace()+"',");
			hql.append("startTime='"+volunteerActivity.getStartTime()+"',");
			hql.append("endTime='"+volunteerActivity.getEndTime()+"',");
			hql.append("beginDate='"+volunteerActivity.getBeginDate()+"',");
			hql.append("endDate='"+volunteerActivity.getEndDate()+"',");
			hql.append("hours='"+volunteerActivity.getHours()+"',");
			hql.append("insertTime='"+GetSystemTime.currentTime()+"' where uuid='"+volunteerActivity.getUuid()+"' and status!='0'");
			
			String hql3="update Volunteer set workHours='"+(vlt.getWorkHours()-va.getHours()+volunteerActivity.getHours())
					+"' where uuid='"+vlt.getUuid()+"'";
			volunteerDao.updateVolunteer(hql3);
			
			return volunteerActivityDao.updateVolunteerActivity(hql.toString());
		}
		return "error";
	}

	@Override
	public String delVolunteerActivity(VolunteerActivity volunteerActivity) {
		// TODO Auto-generated method stub
		if(volunteerActivity==null){
			return "error";
		}else{
			String hql="update VolunteerActivity set status='0' where uuid='"+volunteerActivity.getUuid()+"'";	
			return volunteerActivityDao.updateVolunteerActivity(hql);
		}
	}

	@Override
	public VolunteerActivity getVolunteerActivity(
			VolunteerActivity volunteerActivity) {
		// TODO Auto-generated method stub
		String hql="from VolunteerActivity where uuid='"+volunteerActivity.getUuid()+"'";
		VolunteerActivity vltActivity=volunteerActivityDao.getVolunteerActivity(hql);
		if(vltActivity!=null){			
				return vltActivity;
		}
		return null;
	}

	@Override
	public List<VolunteerActivity> findVolunteerActivityByPage(PageVo pageVo,Volunteer volunteer,
			VolunteerActivity volunteerActivity) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from VolunteerActivity where ");
		if(volunteer!=null&&volunteer.getVolunteerNo()!=null&&!volunteer.getVolunteerNo().equals("")){
			String hql1="from Volunteer where volunteerNo='"+volunteer.getVolunteerNo()+"' and status!='0'";
			Volunteer vlt=volunteerDao.getVolunteer(hql1);
			hql.append("volunteer.uuid='"+vlt.getUuid()+"' and ");
		}
		if(volunteerActivity!=null&&volunteerActivity.getBeginDate()!=null&&!volunteerActivity.getBeginDate().equals("")){
			hql.append("beginDate >= '"+volunteerActivity.getBeginDate()+"' and ");
		}
		if(volunteerActivity!=null&&volunteerActivity.getEndDate()!=null&&!volunteerActivity.getEndDate().equals("")){
			hql.append("endDate <= '"+volunteerActivity.getEndDate()+"' and ");
		}
		hql.append(" status!='0'");
		return volunteerActivityDao.findVolunteerActivityByPage(pageVo, hql.toString());
	}

	@Override
	public Integer getTotalNum(VolunteerActivity volunteerActivity,Volunteer volunteer) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from VolunteerActivity where ");
		if(volunteer!=null&&volunteer.getVolunteerNo()!=null&&!volunteer.getVolunteerNo().equals("")){
			String hql1="from Volunteer where volunteerNo='"+volunteer.getVolunteerNo()+"' and status!='0'";
			Volunteer vlt=volunteerDao.getVolunteer(hql1);
			hql.append("volunteer.uuid='"+vlt.getUuid()+"' and ");
		}
		if(volunteerActivity!=null&&volunteerActivity.getBeginDate()!=null&&!(volunteerActivity.getBeginDate().equals(""))){
			hql.append("beginDate >= '"+volunteerActivity.getBeginDate()+"' and ");
		}
		if(volunteerActivity!=null&&volunteerActivity.getEndDate()!=null&&!(volunteerActivity.getEndDate().equals(""))){
			hql.append("endDate <= '"+volunteerActivity.getEndDate()+"' and ");
		}
		hql.append(" status!='0'");
		return volunteerActivityDao.findTotalNum(hql.toString());
	}

}
