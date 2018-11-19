package com.resthome.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.panjun.handle.Handle;
import com.resthome.dao.EmployeeDaoInter;
import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.dao.SystemMetaDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.SystemMeta;
import com.resthome.utils.GetSerialNum;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.HqlUtils;
import com.resthome.utils.ToJson;
import com.resthome.vo.EmployeeVo;
import com.resthome.vo.PageVo;
@Repository(value="employeeService")
public class EmployeeServiceImpl implements EmployeeServiceInter {
    @Resource
	private EmployeeDaoInter employeeDao;
    @Resource
	private SystemMetaDaoInter systemMetaDao;
   
    
	@Override
	public String addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		this.addStatusEtc(employee);
		String uuid = UUID.randomUUID().toString();
		employee.setUuid(uuid);
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		Integer num = employeeDao.findTotalNum("from Employee where insertTime like '%"+year+"%'");
		String employeeNo = GetSerialNum.createEmployeeNo(year, num+1);
	    employee.setEmployeeNo(employeeNo);
	    employee.setGrade("1");
		EmployeeVo empVo = new EmployeeVo();
//		empVo.setUuid(uuid);
//		empVo.setEmployeeNo(employeeNo);
		Handle.entity2Vo(employee, empVo);
//		if(checkBaoXian(empVo)){
//			return "error";
//		}
		employeeDao.addEmployee(employee);
		String returnStr = ToJson.ToJsonObject(empVo).toString();
	    return returnStr;
	}

	@Override
	public String delEmployee(Employee employee) {
		String hql=null;
		if(employee==null){
			return "error";
		}else{
			if(employee.getEmployeeNo()!=null){
				hql="update Employee set status=0 where employeeNo='"+employee.getEmployeeNo()+"'";
				
			}else if(employee.getUuid()!=null){
				hql="update Employee set status=0 where uuid='"+employee.getUuid()+"'";
			}else{
				return "error";
			}
		}
		return employeeDao.updateEmployee(hql);
	}

	@Override
	public String updateEmployee(EmployeeVo employeeVo) {
		// TODO Auto-generated method stub
	    if(employeeVo==null){
			return "error";
		}
//	    if(checkBaoXian(employeeVo)){
//	    	return "error";
//	    }
	    String str = HqlUtils.getUpdateSet(employeeVo);
        if(str==null){
	    	return "error";
	    }
	    String hql = "update Employee set "+str+" where uuid='"+employeeVo.getUuid()+"'";//记得set后面一定加一个空格，因为用HqlUtils返回的字符串前面没有空格
      
	    return employeeDao.updateEmployee(hql);
	}

	@Override
	public Employee getEmployee(Employee employee) {
		// TODO Auto-generated method stub
		String hql=null;
		if(employee!=null){
			if(employee.getUuid()!=null){
				hql = "from Employee where uuid='"+employee.getUuid()+"'";
			}
			if(employee.getEmployeeNo()!=null){
				hql = "from Employee where employeeNo='"+employee.getEmployeeNo()+"'";
			}
			return employeeDao.getEmployee(hql);
			
		}
		return null;
	}
	
	@Override
	public Employee getEmployee2(Employee employee) {
		// TODO Auto-generated method stub
		String hql=null;
		if(employee!=null){
			if(employee.getUuid()!=null){
				//hql = "select new Employee(uuid,employeeName,employeeNo) from Employee where uuid='"+employee.getUuid()+"'";
				hql = "select new Employee(uuid,employeeName,employeeNo,department,grade,workStatus,workName,status) from Employee where status='1' and uuid='"+employee.getUuid()+"'";
			}
			return employeeDao.getEmployee(hql);
			
		}
		return null;
	}

	@Override
	public List<Object> findEmployeeByPage(PageVo pageVo,EmployeeVo employeeVo,Map<String,String> order) {
		// TODO Auto-generated method stub
		String hql = "select new Employee(uuid,employeeName,employeeNo,department,grade,workStatus,workName,status) from Employee where status='1' ";
		if(employeeVo!=null){
			List<String> except = new ArrayList<String>();
			except.add("employeeName");
			String str = HqlUtils.getQueryConditions(employeeVo, null);
			if(str!=null&&!str.trim().isEmpty()){
			  hql = hql+" and "+str;
			}
			if(employeeVo.getEmployeeName()!=null){
				hql = hql+" and employeeName like '%"+employeeVo.getEmployeeName()+"%'";
			}
		}
        if(order!=null){
        	hql = hql+" order by ";
        	Set<String> keySet = order.keySet();
         	for(String key:keySet){
        		hql = hql+" "+key+" "+order.get(key)+",";
        	}
        }
        if(hql.endsWith(",")){
        	hql = hql.substring(0, hql.length()-1);
        }
       
		List<Employee> empList = employeeDao.findEmployeeByPage(pageVo, hql);
		List<Object> empVoList = new ArrayList<Object>();
		if(empList!=null){
		for(Employee e:empList){		
			EmployeeVo evo = new EmployeeVo();
			Handle.entity2Vo(e, evo);
			empVoList.add(evo);
		  }
		}
		return empVoList;
	}
	private void addStatusEtc(Employee employee){
		employee.setStatus("1");
		employee.setInsertTime(GetSystemTime.currentTime());
	}
	private boolean checkBaoXian(EmployeeVo employeeVo){
		Double baoxianjishu = Double.parseDouble(employeeVo.getBaoxianjishu());
		Double baoxianjishuUp = Double.parseDouble(employeeVo.getBaoxianjishuUp());
		Double baoxianjishuDown = Double.parseDouble(employeeVo.getBaoxianjishuDown());
		Double zhufangjishu = Double.parseDouble(employeeVo.getZhufangjishu());
		Double zhufangjishuUp = Double.parseDouble(employeeVo.getZhufangjishuUp());
		Double zhufangjishudown = Double.parseDouble(employeeVo.getZhufangjishuDown());
		Double baoxianbuchangjin = Double.parseDouble(employeeVo.getBaoxianbuchangjin());
		if(baoxianjishu!=null||baoxianjishuUp!=null||baoxianjishuDown!=null
				||zhufangjishu!=null||zhufangjishuUp!=null||zhufangjishudown!=null){
			if(baoxianbuchangjin!=null){
				return false;
			}
		}
		if(baoxianjishu>baoxianjishuUp||baoxianjishu<baoxianjishuDown){
			return false;
		}
		if(zhufangjishu>zhufangjishuUp||zhufangjishu<zhufangjishudown){
			return false;
		}
		
	    return true;
	    
	}
//	private String getEmployeeNo(int year,int num){
//		String employeeNo = (year+"").substring(2);
//		String sNum = "";
//		if (num < 1000) {
//			//System.out.println(new Integer(num).toString().length()+"===============================");
//			int size = new Integer(num).toString().length();
//		if(size==1){
//			sNum = "000"+num;
//		}
//		if(size==2){
//			sNum = "00"+num;
//		}
//		if(size==3){
//			sNum = "0"+num;
//		}
//		if(size==4){
//			sNum = num+"";
//		}
//		}
//		
//		employeeNo = employeeNo+sNum;
//		
//		return employeeNo;
//	}

	@Override
	public Integer getTotalPage(EmployeeVo employeeVo) {
		// TODO Auto-generated method stub
		String hql = "from Employee where status='1' ";
		if(employeeVo!=null){
			String str = HqlUtils.getQueryConditions(employeeVo, null);
			if(str!=null&&!str.trim().isEmpty()){
			  hql = hql+" and "+str;
			}
		}
		
		Integer totalPage = employeeDao.findTotalPage(hql);
		return totalPage;
		
	}

	@Override
	public Integer getTotalNum(EmployeeVo employeeVo) {
		// TODO Auto-generated method stub
		String hql = "from Employee where status='1' ";
		if(employeeVo!=null){
			String str = HqlUtils.getQueryConditions(employeeVo, null);
			if(str!=null&&!str.trim().isEmpty()){
			  hql = hql+" and "+str;
			}
		}
		
		Integer totalNum = employeeDao.findTotalNum(hql);
		return totalNum;
	}
	
	@Override
	public String getFamilyParam() {
		String hql="from SystemMeta where childMetaName='29' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}
	
	@Override
	public String getEmergencyPeopleParam() {
		String hql="from SystemMeta where childMetaName='30' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}
	
	@Override
	public String getWorkExperienceParam() {
		String hql="from SystemMeta where childMetaName='51' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}
	
	@Override
	public String getEducationExperienceParam() {
		String hql="from SystemMeta where childMetaName='52' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}



}
