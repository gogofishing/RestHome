package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.Employee;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;
@Repository(value="employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDaoInter {

	@Override
	public String addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return super.addObject(employee);
	}

	@Override
	public String updateEmployee(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public Employee getEmployee(String hql) {
		// TODO Auto-generated method stub
		return (Employee) super.findObjectByHql(hql);
	}

	@Override
	public List<Employee> findEmployeeByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		return (List<Employee>) super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
		
	}

	@Override
	public Integer findTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	@Override
	public Integer findTotalPage(String hql) {
		// TODO Auto-generated method stub
		return super.findTotlePage(ParamUtil.rowsPerPage, hql);
	}

}
