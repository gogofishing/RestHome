package com.resthome.service;

import java.util.List;

import com.resthome.vo.EmployeeCertificateVo;

public interface EmployeeCertificateServiceInter {

	String addEmployeeCertificate(EmployeeCertificateVo ecvo);

	String updateEmployeeCertificate(EmployeeCertificateVo ecvo);

	String delEmployeeCertificate(EmployeeCertificateVo ecvo);

	EmployeeCertificateVo getEmployeeCertificate(EmployeeCertificateVo ecvo);

	List<EmployeeCertificateVo> getEmployeeCertificates(EmployeeCertificateVo ecvo);

}
