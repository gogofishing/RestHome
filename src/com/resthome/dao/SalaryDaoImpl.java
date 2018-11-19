package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.EmployeeSalary;
import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;

@Repository(value="salaryDao")
public class SalaryDaoImpl extends BaseDaoImpl implements SalaryDaoInter {

	@Override
	public String addSalary(EmployeeSalary salary) {
		// TODO Auto-generated method stub
		return super.addObject(salary);
	}

	@Override
	public List<EmployeeSalary> findEmployeeSalaryByPage(PageVo pageVo,
			String hql) {
		// TODO Auto-generated method stub
		
		List<EmployeeSalary> salarys=(List<EmployeeSalary>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
		return salarys;
	}

	@Override
	public String modifySalary(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public Integer getTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	@Override
	public EmployeeSalary findSalaryByHql(String hql) {
		// TODO Auto-generated method stub
		EmployeeSalary es=(EmployeeSalary) super.findObjectByHql(hql);
		return es;
	}

	



}
