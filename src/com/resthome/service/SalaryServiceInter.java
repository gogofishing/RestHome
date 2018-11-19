package com.resthome.service;

import java.util.List;
import java.util.Map;

import com.resthome.entity.Employee;
import com.resthome.entity.EmployeeSalary;
import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;

public interface SalaryServiceInter {

	Map<String,Object> addSalary(Employee employee);

	String modifySalary(EmployeeSalary es,Employee employee);

	String delSalary(EmployeeSalary salary);
 
	List<EmployeeSalary> findEmployeeSalaryByPage(PageVo pageVo,Employee hql);

	Integer getTotalNum(PageVo pageVo,Employee empNo);

	EmployeeSalary getSalaryByUuid(EmployeeSalary salary);
	
	
}
