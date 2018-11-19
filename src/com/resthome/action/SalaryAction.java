package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.panjun.handle.Handle;
import com.resthome.entity.CheckIn;
import com.resthome.entity.Employee;
import com.resthome.entity.EmployeeSalary;
import com.resthome.entity.SystemMeta;
import com.resthome.service.EmployeeServiceInter;
import com.resthome.service.SalaryServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.Entity2Vo;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.CheckInVo;
import com.resthome.vo.EmployeeSalaryVo;
import com.resthome.vo.EmployeeVo;
import com.resthome.vo.PageVo;
import com.sun.xml.internal.ws.message.saaj.SAAJHeader;

@ParentPackage(value = "abstract_struts")
@Namespace("/Salary")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class SalaryAction extends ActionSupport {

	@Resource
	private SalaryServiceInter salaryService;
	@Resource
	private EmployeeServiceInter employeeService;

	private EmployeeSalary salary;
	private Employee employee;
	private String result = "error";
	private PageVo pageVo;
	
	private List<EmployeeSalary> salaryList;
	private List<Object> salaryVoList=new ArrayList<Object>();

	public EmployeeSalary getSalary() {
		return salary;
	}

	public void setSalary(EmployeeSalary salary) {
		this.salary = salary;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}	

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}	
	

	@Action(value = "addSalary", results = { @Result(name = "success", location = "/pages/EntrySalary.jsp") })
	public String addSalary() {
		Map<String,Object> map= salaryService.addSalary(employee);
		JSONObject object = ToJson.ToJsonObject(map);
		this.writeJson(object.toString());		
		
		return result;
	}
	
	@Action(value = "findEmployees", results = { @Result(name = "success", location = "/pages/EmployeeList.jsp") })
	public String findEmployees() {		
		
		if(pageVo == null){			
			pageVo = new PageVo();
			pageVo.setNowPage(1);		
		}		
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		if(employee==null){
			employee=new Employee();
		}
			
		EmployeeVo employeeVo=new EmployeeVo();
		Handle.entity2Vo(employee, employeeVo);
		int totalPage = employeeService.getTotalPage(employeeVo);	
		int totalNum = employeeService.getTotalNum(employeeVo);
		List<Object> empVoList=employeeService.findEmployeeByPage(pageVo, employeeVo,null);
		
		pageVo.setTotalPage(totalPage);	
		pageVo.setTotalNum(totalNum);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(empVoList);
			
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());			
		return "success";
	}

	@Action(value = "findSalary", results = { @Result(name = "success", location = "/pages/SalaryManager.jsp") })
	public String findSalary() {
		System.out.println(employee.getUuid());
		if(employee!=null&&salary!=null){
			Employee emp = employeeService.getEmployee(employee);
			EmployeeVo empVo=new EmployeeVo();
			Handle.entity2Vo(emp, empVo);
			EmployeeSalary es=salaryService.getSalaryByUuid(salary);
			
			if(emp!=null&&es!=null){
				Map map=new HashMap<String, Object>(); 
				map.put("emp", empVo);
				map.put("salary", es);
				JSONObject object = ToJson.ToJsonObject(map);
				this.writeJson(object.toString());
				return "success";
			}else{
				return "error";
			}
			
		}else{
			return "error";
		}
		
	}
	
	@Action(value = "modifySalary", results = { @Result(name = "success", location = "/pages/SalaryManager.jsp") })
	public String modifySalary() {	
		System.out.println(salary.getUuid());
		result = salaryService.modifySalary(salary, employee);
		return result;
	}
	
	@Action(value = "delSalary", results = { @Result(name = "success", location = "findSalarysByPage",type="redirectAction") })
	public String delSalary() {
		result=salaryService.delSalary(salary);
		return result;
	}

	@Action(value = "findSalarysByPage", results = { @Result(name = "success", location = "/pages/SalaryManager.jsp") })
	public String findSalarysByPage() {
		
		if(pageVo == null){			
			pageVo = new PageVo();
			pageVo.setNowPage(1);		
		}		
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);

		int totalNum = salaryService.getTotalNum(pageVo,employee);
		int totalPage = totalNum%pageVo.getRowsPerPage()==0?totalNum/pageVo.getRowsPerPage():totalNum/pageVo.getRowsPerPage()+1;		
					
		salaryList=salaryService.findEmployeeSalaryByPage(pageVo, employee);
		for(EmployeeSalary es:salaryList){	
			Employee e=new Employee();
			e.setUuid(es.getEmployeeUuid());
			Employee emp=employeeService.getEmployee2(e);
			EmployeeSalaryVo esVo=Entity2Vo.EmployeeSalary2EmployeeSalaryVo(es, emp);				
			salaryVoList.add(esVo);
		}	
		
	
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);	
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(salaryVoList);
		
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());						
		
		return "success";
	}
	
	private void writeJson(String json) {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
}
