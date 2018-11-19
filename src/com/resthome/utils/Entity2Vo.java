package com.resthome.utils;

import com.resthome.entity.CheckIn;
import com.resthome.entity.Employee;
import com.resthome.entity.EmployeeSalary;
import com.resthome.vo.CheckInVo;
import com.resthome.vo.EmployeeSalaryVo;

public class Entity2Vo {
	public static CheckInVo CheckIn2CheckInVo(CheckIn ck){
		CheckInVo cv=new CheckInVo();
		cv.setUuid(ck.getUuid());
		cv.setEmpNo(ck.getEmployee().getEmployeeNo());
		cv.setEmpName(ck.getEmployee().getEmployeeName());
		cv.setCheckNo(ck.getCheckNo());
		cv.setBeginTime(ck.getBeginTime());
		cv.setEndTime(ck.getEndTime());
		cv.setBeginDate(ck.getBeginDate());
		cv.setEndDate(ck.getEndDate());
		cv.setTiemLength(ck.getTiemLength());
		cv.setReson(ck.getReson());
		cv.setResonType(ck.getResonType());
		cv.setInsertTime(ck.getInsertTime());
		return cv;
		
	}
	
	public static EmployeeSalaryVo EmployeeSalary2EmployeeSalaryVo(EmployeeSalary es,Employee emp){
		EmployeeSalaryVo esVo=new EmployeeSalaryVo();
		esVo.setUuid(es.getUuid());
		esVo.setEmployeeUuid(es.getEmployeeUuid());
		esVo.setEmployeeNo(emp==null||emp.getEmployeeNo()==null?null:emp.getEmployeeNo());
		esVo.setEmployeeName(emp==null||emp.getEmployeeName()==null?null:emp.getEmployeeName());
		/*esVo.setPBasicSalary(es.getPBasicSalary());
		esVo.setPPositionSalary(es.getPPositionSalary());
		esVo.setPBaoxianbuchangjin(es.getPBaoxianbuchangjin());
		esVo.setPNormalPlusSalary(es.getPNormalPlusSalary());
		esVo.setPHolidaySalary(es.getPHolidaySalary());
		esVo.setPYanglaobaoxian(es.getPYanglaobaoxian());
		esVo.setPYiliaobaoxian(es.getPYiliaobaoxian());
		esVo.setPShiyebaoxian(es.getPShiyebaoxian());
		esVo.setPZhufangbaoxian(es.getPZhufangbaoxian());
		esVo.setPDaebaoxian(es.getPDaebaoxian());
		esVo.setPLengnuanfei(es.getPLengnuanfei());
		esVo.setPersonalBaoxianSummary(es.getPersonalBaoxianSummary());
		esVo.setCYanglaobaoxian(es.getCYanglaobaoxian());
		esVo.setCYiliaobaoxian(es.getCYiliaobaoxian());
		esVo.setCGongshangbaoxian(es.getCGongshangbaoxian());
		esVo.setCShiyebaoxian(es.getCShiyebaoxian());
		esVo.setCShengyubaoxian(es.getCShengyubaoxian());
		esVo.setCZhufangbaoxian(es.getCZhufangbaoxian());
		esVo.setCompanyBaoxianSummary(es.getCompanyBaoxianSummary());
		esVo.setPersonalTax(es.getPersonalTax());
		esVo.setGongjijinSummary(es.getGongjijinSummary());*/
		esVo.setRealSalary(es.getRealSalary());
		//esVo.setRealSalary(es.getRealSalary()==null?Float.parseFloat("0"):es.getRealSalary());
		esVo.setInsertTime(es.getInsertTime());
		return esVo;
	}

}
