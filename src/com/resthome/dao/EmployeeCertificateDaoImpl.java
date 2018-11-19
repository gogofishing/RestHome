package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.EmployeeCertificate;
@Repository(value="employeeCertificateDao")
public class EmployeeCertificateDaoImpl extends BaseDaoImpl implements
		EmployeeCertificateDaoInter {

	@Override
	public String addCertificate(EmployeeCertificate ec) {
		// TODO Auto-generated method stub
		return super.addObject(ec);
	}

	@Override
	public String updateCertificate(String hql, List<Object> params) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql, params);
	}

	@Override
	public EmployeeCertificate getCertificate(String hql, List<Object> params) {
		// TODO Auto-generated method stub
		return (EmployeeCertificate) super.findObjectByHql(hql, params);
	}

	@Override
	public List<EmployeeCertificate> getCertificates(String hql, List<Object> params) {
		// TODO Auto-generated method stub
		return (List<EmployeeCertificate>) super.findObjectsByHqlByPage(null, null, hql, params);
	}

}
