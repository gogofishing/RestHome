package com.resthome.dao;

import java.util.List;

import com.resthome.entity.Employee;
import com.resthome.vo.PageVo;

public interface EmployeeDaoInter {
	
	String addEmployee(Employee employee);

	String updateEmployee(String hql);
	
	Employee getEmployee(String hql);
	
	List<Employee> findEmployeeByPage(PageVo pageVo,String hql);

	Integer findTotalNum(String hql);

	Integer findTotalPage(String hql);
	
	
	

}
