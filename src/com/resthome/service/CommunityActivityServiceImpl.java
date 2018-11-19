package com.resthome.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.resthome.dao.CommunityActivityDaoInter;
import com.resthome.dao.EmployeeDaoInter;
import com.resthome.entity.CommunityActivity;
import com.resthome.entity.Employee;
import com.resthome.entity.VolunteerActivity;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;

@Repository(value = "communityActivityService")
public class CommunityActivityServiceImpl implements CommunityActivityServiceInter{
	
	@Resource
	private CommunityActivityDaoInter communityActivityDao;
	@Resource 
	private EmployeeDaoInter employeeDao;

	@Override
	public String addCommunityActivity(CommunityActivity communityActivity,
			Employee employee) {
		// TODO Auto-generated method stub
		String hql1="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
		Employee emp=employeeDao.getEmployee(hql1);
		if(emp==null){
			return "empNull";
		}
		communityActivity.setUuid(UUID.randomUUID().toString());
		communityActivity.setEmployee(emp);
		communityActivity.setInsertTime(GetSystemTime.currentTime());
		communityActivity.setStatus(ParamUtil.STATUS_UNDELETE);
		
		return communityActivityDao.addCommunityActivity(communityActivity);
	}

	@Override
	public String updateCommunityActivity(CommunityActivity communityActivity,
			Employee employee) {
		// TODO Auto-generated method stub
		if(communityActivity!=null&&employee!=null){
			StringBuffer hql=new StringBuffer("update CommunityActivity set ");
			String hql1="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
			Employee emp=employeeDao.getEmployee(hql1);	
			if(emp==null){
				return "empNull";
			}
			
			hql.append("employee.uuid='"+emp.getUuid()+"',");
			hql.append("activityName='"+communityActivity.getActivityName()+"',");
			hql.append("activityContent='"+communityActivity.getActivityContent()+"',");
			hql.append("activityType='"+communityActivity.getActivityType()+"',");
			hql.append("activityPlace='"+communityActivity.getActivityPlace()+"',");
			hql.append("startTime='"+communityActivity.getStartTime()+"',");
			hql.append("endTime='"+communityActivity.getEndTime()+"',");
			hql.append("beginDate='"+communityActivity.getBeginDate()+"',");
			hql.append("endDate='"+communityActivity.getEndDate()+"',");
			hql.append("activityNumber='"+communityActivity.getActivityNumber()+"',");
			hql.append("insertTime='"+GetSystemTime.currentTime()+"' where uuid='"+communityActivity.getUuid()+"' and status!='0'");
			
			return communityActivityDao.updateCommunityActivity(hql.toString());
		}
		return "error";
	}

	@Override
	public String delCommunityActivity(CommunityActivity communityActivity) {
		// TODO Auto-generated method stub
		if(communityActivity==null){
			return "error";
		}else{
			String hql="update CommunityActivity set status='0' where uuid='"+communityActivity.getUuid()+"'";	
			return communityActivityDao.updateCommunityActivity(hql);
		}
	}

	@Override
	public CommunityActivity getCommunityActivity(
			CommunityActivity communityActivity) {
		// TODO Auto-generated method stub
		String hql="from CommunityActivity where uuid='"+communityActivity.getUuid()+"'";
		CommunityActivity comActivity=communityActivityDao.getCommunityActivity(hql);
		if(comActivity!=null){			
				return comActivity;
		}
		return null;
	}

	@Override
	public List<CommunityActivity> findCommunityActivityByPage(PageVo pageVo,
			CommunityActivity communityActivity) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from CommunityActivity where ");
		if(communityActivity!=null&&communityActivity.getActivityName()!=null&&!(communityActivity.getActivityName().equals(""))){
			hql.append("activityName like '%"+communityActivity.getActivityName()+"%' and ");
		}
		if(communityActivity!=null&&communityActivity.getBeginDate()!=null&&!(communityActivity.getBeginDate().equals(""))){
			hql.append("beginDate >= '"+communityActivity.getBeginDate()+"' and ");
		}
		if(communityActivity!=null&&communityActivity.getEndDate()!=null&&!(communityActivity.getEndDate().equals(""))){
			hql.append("endDate <= '"+communityActivity.getEndDate()+"' and ");
		}
		hql.append(" status!='0'");
		return communityActivityDao.findCommunityActivityByPage(pageVo, hql.toString());
	}

	@Override
	public Integer getTotalNum(CommunityActivity communityActivity) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from CommunityActivity where ");
		if(communityActivity!=null&&communityActivity.getActivityName()!=null&&!(communityActivity.getActivityName().equals(""))){
			hql.append("activityName like '%"+communityActivity.getActivityName()+"%' and ");
		}
		if(communityActivity!=null&&communityActivity.getBeginDate()!=null&&!(communityActivity.getBeginDate().equals(""))){
			hql.append("beginDate >= '"+communityActivity.getBeginDate()+"' and ");
		}
		if(communityActivity!=null&&communityActivity.getEndDate()!=null&&!(communityActivity.getEndDate().equals(""))){
			hql.append("endDate <= '"+communityActivity.getEndDate()+"' and ");
		}
		hql.append(" status!='0'");
		return communityActivityDao.findTotalNum(hql.toString());
	}

}
