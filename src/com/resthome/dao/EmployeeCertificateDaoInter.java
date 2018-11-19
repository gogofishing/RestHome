package com.resthome.dao;

import java.util.List;

import com.resthome.entity.EmployeeCertificate;

public interface EmployeeCertificateDaoInter {
	/**
	 * @param ec
	 * @return
	 */
	String addCertificate(EmployeeCertificate ec);
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	String updateCertificate(String hql,List<Object> params);
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	EmployeeCertificate getCertificate(String hql,List<Object> params);
	
	/**
	 * @param hql
	 * @param params
	 * @return
	 */
	List<EmployeeCertificate> getCertificates(String hql,List<Object> params);
	
	

}
