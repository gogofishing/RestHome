package com.resthome.dao;

import java.util.List;

import com.resthome.entity.EmployeeSalary;
import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;

public interface SalaryDaoInter {

	String addSalary(EmployeeSalary salary);
	 
	List<EmployeeSalary> findEmployeeSalaryByPage(PageVo pageVo,String empNo);

	String modifySalary(String hql);
 
	Integer getTotalNum(String hql);

	EmployeeSalary findSalaryByHql(String hql);
	

	
	
	

}
