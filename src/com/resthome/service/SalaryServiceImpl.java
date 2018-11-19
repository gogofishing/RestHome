package com.resthome.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Repository;

import com.panjun.handle.Handle;
import com.resthome.dao.SalaryDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.EmployeeSalary;
import com.resthome.entity.SystemMeta;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.EmployeeSalaryVo;
import com.resthome.vo.EmployeeVo;
import com.resthome.vo.PageVo;
import com.sun.mail.imap.protocol.FLAGS;

@Repository(value = "salaryService")
public class SalaryServiceImpl implements SalaryServiceInter {

	@Resource
	private SalaryDaoInter salaryDao;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	@Resource
	private EmployeeServiceInter employeeService;

	@Override
	public Map<String, Object> addSalary(Employee employee) {
		// TODO Auto-generated method stub

		Employee emp = employeeService.getEmployee(employee);

		SystemMeta systemMeta = new SystemMeta();
		systemMeta.setParentMetaName("3");
		List<SystemMeta> systemMetas = systemMetaService
				.findSystemMetaByParent(systemMeta);
		SystemMeta PYanglao = systemMetas.get(0);
		SystemMeta PYiliao = systemMetas.get(1);
		SystemMeta PShiye = systemMetas.get(2);
		SystemMeta PZhufang = systemMetas.get(3);
		SystemMeta CYanglao = systemMetas.get(4);
		SystemMeta CYiliao = systemMetas.get(5);
		SystemMeta CGongshang = systemMetas.get(6);
		SystemMeta CShiye = systemMetas.get(7);
		SystemMeta CShengyu = systemMetas.get(8);
		SystemMeta CZhufang = systemMetas.get(9);

		// 需要判断是否为空在进行计算
		if (emp != null && PYanglao != null && PYiliao != null
				&& PShiye != null && PZhufang != null && CYanglao != null
				&& CYiliao != null && CGongshang != null && CShiye != null
				&& CShengyu != null && CZhufang != null) {
			EmployeeSalary salary = new EmployeeSalary();
			salary.setUuid(UUID.randomUUID().toString());
			salary.setInsertTime(GetSystemTime.currentTime());
			salary.setStatus(ParamUtil.STATUS_DELETEED);
			salary.setEmployeeUuid(emp.getUuid());
			salary.setPBasicSalary(Float.parseFloat((emp.getBasicSalary() == null || emp
					.getBasicSalary().equals("")) ? "0" : emp.getBasicSalary()));
			salary.setPPositionSalary(Float.parseFloat((emp.getPositionSalary() == null || emp
					.getPositionSalary().equals("")) ? "0" : emp
					.getPositionSalary()));
			salary.setPBaoxianbuchangjin(Float.parseFloat((emp
					.getBaoxianbuchangjin() == null || emp
					.getBaoxianbuchangjin().equals("")) ? "0" : emp
					.getBaoxianbuchangjin()));
			salary.setPYanglaobaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(PYanglao.getMetaValue())));
			salary.setPYiliaobaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(PYiliao.getMetaValue())));
			salary.setPShiyebaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(PShiye.getMetaValue())));
			salary.setPZhufangbaoxian((Float.parseFloat((emp.getZhufangjishu() == null || emp
					.getZhufangjishu().equals("")) ? "0" : emp
					.getZhufangjishu()))
					* (Float.parseFloat(PZhufang.getMetaValue())));
			salary.setCYanglaobaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(CYanglao.getMetaValue())));
			salary.setCYiliaobaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(CYiliao.getMetaValue())));
			salary.setCGongshangbaoxian((Float.parseFloat((emp
					.getBaoxianjishu() == null || emp.getBaoxianjishu().equals(
					"")) ? "0" : emp.getBaoxianjishu()))
					* (Float.parseFloat(CGongshang.getMetaValue())));
			salary.setCShiyebaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(CShiye.getMetaValue())));
			salary.setCShengyubaoxian((Float.parseFloat((emp.getBaoxianjishu() == null || emp
					.getBaoxianjishu().equals("")) ? "0" : emp
					.getBaoxianjishu()))
					* (Float.parseFloat(CShengyu.getMetaValue())));
			salary.setCZhufangbaoxian((Float.parseFloat((emp.getZhufangjishu() == null || emp
					.getZhufangjishu().equals("")) ? "0" : emp
					.getZhufangjishu()))
					* (Float.parseFloat(CZhufang.getMetaValue())));
			salary.setGongjijinSummary((Float.parseFloat((emp.getZhufangjishu() == null || emp
					.getZhufangjishu().equals("")) ? "0" : emp
					.getZhufangjishu()))
					* (Float.parseFloat(PZhufang.getMetaValue()))
					+ (Float.parseFloat((emp.getZhufangjishu() == null || emp
							.getZhufangjishu().equals("")) ? "0" : emp
							.getZhufangjishu()))
					* (Float.parseFloat(CZhufang.getMetaValue())));
			salary.setCompanyBaoxianSummary((Float.parseFloat(CYanglao
					.getMetaValue()))
					* (Float.parseFloat((emp.getBaoxianjishu() == null || emp
							.getBaoxianjishu().equals("")) ? "0" : emp
							.getBaoxianjishu()))
					+ (Float.parseFloat(CYiliao.getMetaValue()))
					* (Float.parseFloat((emp.getBaoxianjishu() == null || emp
							.getBaoxianjishu().equals("")) ? "0" : emp
							.getBaoxianjishu()))
					+ (Float.parseFloat(CGongshang.getMetaValue()))
					* (Float.parseFloat((emp.getBaoxianjishu() == null || emp
							.getBaoxianjishu().equals("")) ? "0" : emp
							.getBaoxianjishu()))
					+ (Float.parseFloat(CShiye.getMetaValue()))
					* (Float.parseFloat((emp.getBaoxianjishu() == null || emp
							.getBaoxianjishu().equals("")) ? "0" : emp
							.getBaoxianjishu()))
					+ (Float.parseFloat(CShengyu.getMetaValue()))
					* (Float.parseFloat((emp.getBaoxianjishu() == null || emp
							.getBaoxianjishu().equals("")) ? "0" : emp
							.getBaoxianjishu()))
					+ (Float.parseFloat(CZhufang.getMetaValue()))
					* (Float.parseFloat((emp.getZhufangjishu() == null || emp
							.getZhufangjishu().equals("")) ? "0" : emp
							.getZhufangjishu())));

			EmployeeVo empVo = new EmployeeVo();
			Handle.entity2Vo(emp, empVo);
			Map map = new HashMap<String, Object>();
			map.put("emp", empVo);
			map.put("salary", salary);
			String result = salaryDao.addSalary(salary);
			if (result.equals("success")) {
				return map;
			} else {
				return null;
			}
		} else {
			return null; // 该员工不存在或参数不存在
		}

	}

	@Override
	public String modifySalary(EmployeeSalary es, Employee employee) {
		// TODO Auto-generated method stub

		if (es != null && employee != null) {
			EmployeeSalary salary = this.getSalaryByUuid(es);
			Employee emp = employeeService.getEmployee(employee);

			SystemMeta systemMeta = new SystemMeta();
			systemMeta.setParentMetaName("3");
			List<SystemMeta> systemMetas = systemMetaService
					.findSystemMetaByParent(systemMeta);
			SystemMeta PYanglao = systemMetas.get(0);
			SystemMeta PYiliao = systemMetas.get(1);
			SystemMeta PShiye = systemMetas.get(2);
			SystemMeta PZhufang = systemMetas.get(3);
			SystemMeta CYanglao = systemMetas.get(4);
			SystemMeta CYiliao = systemMetas.get(5);
			SystemMeta CGongshang = systemMetas.get(6);
			SystemMeta CShiye = systemMetas.get(7);
			SystemMeta CShengyu = systemMetas.get(8);
			SystemMeta CZhufang = systemMetas.get(9);

			if (salary != null && emp != null && PYanglao != null
					&& PYiliao != null && PShiye != null && PZhufang != null
					&& CYanglao != null && CYiliao != null
					&& CGongshang != null && CShiye != null && CShengyu != null
					&& CZhufang != null) {

				salary.setPDaebaoxian((es.getPDaebaoxian() == null || es
						.getPDaebaoxian().equals("")) ? Float.parseFloat("0")
						: es.getPDaebaoxian());
				salary.setPLengnuanfei((es.getPLengnuanfei() == null || es
						.getPLengnuanfei().equals("")) ? Float.parseFloat("0")
						: es.getPLengnuanfei());
				salary.setPNormalPlusSalary((es.getPNormalPlusSalary() == null || es
						.getPNormalPlusSalary().equals("")) ? Float
						.parseFloat("0") : es.getPNormalPlusSalary());
				salary.setPHolidaySalary((es.getPHolidaySalary() == null || es
						.getPHolidaySalary().equals("")) ? Float
						.parseFloat("0") : es.getPHolidaySalary());
				salary.setPersonalBaoxianSummary(((Float.parseFloat((emp
						.getBaoxianjishu() == null || emp.getBaoxianjishu()
						.equals("")) ? "0" : emp.getBaoxianjishu())) * (Float
						.parseFloat(PYanglao.getMetaValue())))
						+ ((Float.parseFloat((emp.getBaoxianjishu() == null || emp
								.getBaoxianjishu().equals("")) ? "0" : emp
								.getBaoxianjishu())) * (Float
								.parseFloat(PYiliao.getMetaValue())))
						+ ((Float.parseFloat((emp.getBaoxianjishu() == null || emp
								.getBaoxianjishu().equals("")) ? "0" : emp
								.getBaoxianjishu())) * (Float.parseFloat(PShiye
								.getMetaValue())))
						+ ((Float.parseFloat((emp.getZhufangjishu() == null || emp
								.getZhufangjishu().equals("")) ? "0" : emp
								.getZhufangjishu())) * (Float
								.parseFloat(PZhufang.getMetaValue())))
						+ ((es.getPDaebaoxian() == null || es.getPDaebaoxian()
								.equals("")) ? Float.parseFloat("0") : es
								.getPDaebaoxian())
						+ ((es.getPLengnuanfei() == null || es
								.getPLengnuanfei().equals("")) ? Float
								.parseFloat("0") : es.getPLengnuanfei()));
				salary.setPersonalTax(this.caculateTax(salary));
				salary.setRealSalary(salary.getPBasicSalary()
						+ salary.getPPositionSalary()
						+ salary.getPBaoxianbuchangjin()
						+ ((salary.getPHolidaySalary() == null || salary
								.getPHolidaySalary().equals("")) ? Float
								.parseFloat("0") : salary.getPHolidaySalary())
						+ ((salary.getPNormalPlusSalary() == null || salary
								.getPNormalPlusSalary().equals("")) ? Float
								.parseFloat("0") : salary
								.getPNormalPlusSalary())
						- salary.getPersonalBaoxianSummary()
						- salary.getPersonalTax());

			} else {
				return "120"; // 该工资记录不存在或参数不存在
			}

			StringBuffer hql = new StringBuffer("update EmployeeSalary set ");
			if (salary.getPNormalPlusSalary() != null) {
				hql.append("PNormalPlusSalary='"
						+ salary.getPNormalPlusSalary() + "',");
			}
			if (salary.getPHolidaySalary() != null) {
				hql.append("PHolidaySalary='" + salary.getPHolidaySalary()
						+ "',");
			}
			if (salary.getPDaebaoxian() != null) {
				hql.append("PDaebaoxian='" + salary.getPDaebaoxian() + "',");
			}
			if (salary.getPLengnuanfei() != null) {
				hql.append("PLengnuanfei='" + salary.getPLengnuanfei() + "',");
			}
			if (salary.getPersonalBaoxianSummary() != null) {
				hql.append("personalBaoxianSummary='"
						+ salary.getPersonalBaoxianSummary() + "',");
			}
			if (salary.getPersonalTax() != null) {
				hql.append("personalTax='" + salary.getPersonalTax() + "',");
			}
			if (salary.getRealSalary() != null) {
				hql.append("realSalary='" + salary.getRealSalary() + "',");
			}
			hql.append("insertTime='" + GetSystemTime.currentTime()
					+ "' , status='1' " + " where uuid='" + salary.getUuid()
					+ "'");
			// System.out.println("==="+hql.toString());
			String result = salaryDao.modifySalary(hql.toString());
			System.out.println(result);
			return result;
		}
		return "120"; // 该员工不存在

	}

	@Override
	public String delSalary(EmployeeSalary salary) {
		// TODO Auto-generated method stub
		String hql = "update EmployeeSalary set status='0' where uuid='"
				+ salary.getUuid() + "'";
		return salaryDao.modifySalary(hql);
	}

	@Override
	public List<EmployeeSalary> findEmployeeSalaryByPage(PageVo pageVo,
			Employee employee) {
		// TODO Auto-generated method stub

		StringBuffer hql = new StringBuffer("from EmployeeSalary where ");
		if (employee != null && employee.getEmployeeNo() != null
				&& (!employee.getEmployeeNo().equals(""))) {
			// System.out.println("UUUU");
			Employee emp = employeeService.getEmployee(employee);
			if (emp != null && emp.getUuid() != null) {
				hql.append("employeeUuid='" + emp.getUuid() + "' and ");
			}
		}
		hql.append(" status!='0'");
		List<EmployeeSalary> salaryList = salaryDao.findEmployeeSalaryByPage(
				pageVo, hql.toString());

		return salaryList;

	}

	@Override
	public Integer getTotalNum(PageVo pageVo, Employee employee) {
		// TODO Auto-generated method stub

		StringBuffer hql = new StringBuffer("from EmployeeSalary where ");
		if (employee != null && employee.getEmployeeNo() != null
				&& (!employee.getEmployeeNo().equals(""))) {
			// System.out.println("UUUU");
			Employee emp = employeeService.getEmployee(employee);
			if (emp != null && emp.getUuid() != null) {
				hql.append("employeeUuid='" + emp.getUuid() + "' and ");
			}
		}
		hql.append(" status!='0'");
		return salaryDao.getTotalNum(hql.toString());

	}

	public float caculateTax(EmployeeSalary salary) {

		SystemMeta systemMeta_tax = new SystemMeta();
		systemMeta_tax.setParentMetaName("1");
		List<SystemMeta> taxs = systemMetaService
				.findSystemMetaByParent(systemMeta_tax);

		SystemMeta tax1 = taxs.get(0);
		SystemMeta tax2 = taxs.get(1);
		SystemMeta tax3 = taxs.get(2);
		SystemMeta tax4 = taxs.get(3);
		SystemMeta tax5 = taxs.get(4);
		SystemMeta tax6 = taxs.get(5);
		SystemMeta tax7 = taxs.get(6);

		SystemMeta systemMeta_del = new SystemMeta();
		systemMeta_del.setParentMetaName("2");
		List<SystemMeta> dels = systemMetaService
				.findSystemMetaByParent(systemMeta_del);
		SystemMeta del1 = dels.get(0);
		SystemMeta del2 = dels.get(1);
		SystemMeta del3 = dels.get(2);
		SystemMeta del4 = dels.get(3);
		SystemMeta del5 = dels.get(4);
		SystemMeta del6 = dels.get(5);
		SystemMeta del7 = dels.get(6);

		SystemMeta systemMeta_exemption = new SystemMeta();
		systemMeta_exemption.setParentMetaName("4");
		List<SystemMeta> exemptions = systemMetaService
				.findSystemMetaByParent(systemMeta_exemption);
		SystemMeta exemption = exemptions.get(0);

		SystemMeta systemMeta_taxable = new SystemMeta();
		systemMeta_taxable.setParentMetaName("5");
		List<SystemMeta> taxables = systemMetaService
				.findSystemMetaByParent(systemMeta_taxable);
		SystemMeta taxable1 = taxables.get(0);
		SystemMeta taxable2 = taxables.get(1);
		SystemMeta taxable3 = taxables.get(2);
		SystemMeta taxable4 = taxables.get(3);
		SystemMeta taxable5 = taxables.get(4);
		SystemMeta taxable6 = taxables.get(5);

		if (salary != null && tax1 != null && tax2 != null && tax3 != null
				&& tax4 != null && tax5 != null && tax6 != null && tax7 != null
				&& del1 != null && del2 != null && del3 != null && del4 != null
				&& del5 != null && del6 != null && del7 != null
				&& exemption != null) {

			// 个税免征额
			Float taxable = salary.getPBasicSalary()
					+ salary.getPPositionSalary()
					+ salary.getPBaoxianbuchangjin()
					+ salary.getPNormalPlusSalary()
					+ salary.getPHolidaySalary()
					- salary.getPersonalBaoxianSummary()
					- Float.parseFloat(exemption.getMetaValue());

			Float revenue = null;
			if (taxable <= Float.parseFloat(taxable1.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax1.getMetaValue())
						- Float.parseFloat(del1.getMetaValue());
			} else if (taxable <= Float.parseFloat(taxable2.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax2.getMetaValue())
						- Float.parseFloat(del2.getMetaValue());
			} else if (taxable <= Float.parseFloat(taxable3.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax3.getMetaValue())
						- Float.parseFloat(del3.getMetaValue());
			} else if (taxable <= Float.parseFloat(taxable4.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax4.getMetaValue())
						- Float.parseFloat(del4.getMetaValue());
			} else if (taxable <= Float.parseFloat(taxable5.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax5.getMetaValue())
						- Float.parseFloat(del5.getMetaValue());
			} else if (taxable <= Float.parseFloat(taxable6.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax6.getMetaValue())
						- Float.parseFloat(del6.getMetaValue());
			} else if (taxable > Float.parseFloat(taxable6.getMetaValue())) {
				revenue = taxable * Float.parseFloat(tax7.getMetaValue())
						- Float.parseFloat(del7.getMetaValue());
			}
			return revenue;
		} else {
			return (Float) null;
		}

	}

	@Override
	public EmployeeSalary getSalaryByUuid(EmployeeSalary salary) {
		// TODO Auto-generated method stub
		// System.out.println("===");
		String hql = "from EmployeeSalary where uuid='" + salary.getUuid()
				+ "'";
		// System.out.println(hql);
		EmployeeSalary es = salaryDao.findSalaryByHql(hql);
		return es;
	}

}
