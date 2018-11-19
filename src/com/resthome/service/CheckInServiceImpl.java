package com.resthome.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.resthome.dao.CheckInDaoInter;
import com.resthome.dao.EmployeeDaoInter;
import com.resthome.entity.CheckIn;
import com.resthome.entity.Employee;
import com.resthome.utils.GetSerialNum;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;



@Repository(value="checkInService")
public class CheckInServiceImpl implements CheckInServiceInter {

	@Resource
	private CheckInDaoInter checkInDao;
	@Resource 
	private EmployeeDaoInter employeeDao;
	
	@Override
	public String addCheckIn(CheckIn checkIn,Employee employee) {
		// TODO Auto-generated method stub
		if(employee!=null){
			String hql="from Employee where employeeNo='"+employee.getEmployeeNo()+"'";
			Employee emp=employeeDao.getEmployee(hql);
			if(emp!=null){
				checkIn.setUuid(UUID.randomUUID().toString());
				checkIn.setEmployee(emp);
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				Integer num = checkInDao.getTotalNum("from CheckIn where insertTime like '%"+year+"%'");
				String checkNo=GetSerialNum.createCheckInNo(year, num+1);
				checkIn.setCheckNo(checkNo);
				checkIn.setInsertTime(GetSystemTime.currentTime());
				checkIn.setStatus(ParamUtil.STATUS_UNDELETE);
				checkIn.setTiemLength(checkIn.getTiemLength()==null||checkIn.getTiemLength().equals("")?null:checkIn.getTiemLength());
				checkIn.setReson(checkIn.getReson()==null||checkIn.getReson().equals("")?null:checkIn.getReson());
				return checkInDao.addCheckIn(checkIn);
				
			}else{
				return "120";			//该员工不存在
			}
		}else{
			return "error";				//输入为空
		}	
		
	}

	@Override
	public String delCheckIn(CheckIn checkIn) {
		// TODO Auto-generated method stub
		String hql="update CheckIn set status='0' where uuid='"+checkIn.getUuid()+"'";
		return checkInDao.modifyCheckIn(hql);
	}

	@Override
	public String modifyCheckIn(CheckIn checkIn) {
		// TODO Auto-generated method stub		
			if(checkIn!=null){
				StringBuffer hql=new StringBuffer("update CheckIn set ");
				if(checkIn.getCheckNo()!=null){
					hql.append("checkNo='"+checkIn.getCheckNo()+"',");
				}
				if(checkIn.getBeginTime()!=null){
					hql.append("beginTime='"+checkIn.getBeginTime()+"',");
				}
				if(checkIn.getEndTime()!=null){
					hql.append("endTime='"+checkIn.getEndTime()+"',");
				}
				if(checkIn.getBeginDate()!=null){
					hql.append("beginDate='"+checkIn.getBeginDate()+"',");
				}
				if(checkIn.getEndDate()!=null){
					hql.append("endDate='"+checkIn.getEndDate()+"',");
				}
			
				hql.append("tiemLength='"+(checkIn.getTiemLength()==null||checkIn.getTiemLength().equals("")?null:checkIn.getTiemLength())+"',");
				hql.append("reson='"+(checkIn.getReson()==null||checkIn.getReson().equals("")?null:checkIn.getReson())+"',");
				
				if(checkIn.getResonType()!=null){
					hql.append("resonType='"+checkIn.getResonType()+"',");
				}
				
				hql.append("insertTime='"+GetSystemTime.currentTime()+"' where uuid='"+checkIn.getUuid()+"' and status!='0'");
					
				//System.out.println("~~~~~~~~~"+hql.toString());
				return checkInDao.modifyCheckIn(hql.toString());
			}
			return "error";
	
		
	}

	@Override
	public CheckIn getCheckInByUuid(String uuid) {
		// TODO Auto-generated method stub
		String hql="from CheckIn where uuid='"+uuid+"' and status!='0'";
		CheckIn ck=checkInDao.findCheckInByHql(hql);
		if(ck!=null){
			return ck;
		}else{
			return null;
		}
		
	}

	@Override
	public List<CheckIn> findCheckInByPage(PageVo pageVo, String empNo,
			String beginDate, String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from CheckIn where ");		
		
		if(empNo!=null){
			hql.append("employee.employeeNo like '%"+empNo+"%' and ");			
		}
		if(beginDate!=null){
			hql.append("beginDate > '"+beginDate+"'and ");
		}
		if(endDate!=null){
			hql.append("endDate < '"+endDate+"'and ");
		}
		hql.append(" status!='0'");
		//System.out.println("hql="+hql.toString());
		return checkInDao.findCheckInByPage(pageVo, hql.toString());
		
		
		
	}


	@Override
	public Integer getTotalNum(PageVo pageVo, String empNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from CheckIn where ");		
		
		if(empNo!=null){
			hql.append("employee.employeeNo like '%"+empNo+"%'and ");			
		}
		if(beginDate!=null){
			hql.append("beginDate > '"+beginDate+"'and ");
		}
		if(endDate!=null){
			hql.append("endDate < '"+endDate+"'and ");
		}
		hql.append(" status!='0'");
		//System.out.println(hql);
		return checkInDao.getTotalNum(hql.toString());	
		
		
		
	
	}

	
	
	
	

}
