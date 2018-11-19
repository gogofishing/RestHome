package com.resthome.service;

import java.util.List;
import java.util.Map;

import com.resthome.entity.Employee;
import com.resthome.vo.EmployeeVo;
import com.resthome.vo.PageVo;

public interface EmployeeServiceInter {

	String addEmployee(Employee employee);

	String delEmployee(Employee employee);

	String updateEmployee(EmployeeVo employeeVo);

	Employee getEmployee(Employee employee);

	Employee getEmployee2(Employee employee);

	List<Object> findEmployeeByPage(PageVo pageVo,EmployeeVo employeeVo,Map<String,String> order);

	Integer getTotalPage(EmployeeVo employeeVo);
 
	Integer getTotalNum(EmployeeVo employeeVo);

	String getFamilyParam();

	String getEmergencyPeopleParam();

	String getWorkExperienceParam();

	String getEducationExperienceParam();

}
