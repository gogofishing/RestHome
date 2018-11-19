package com.resthome.service;

import java.util.List;

import com.resthome.entity.Admin;
import com.resthome.vo.PageVo;

public interface AdminServiceInter {

	String addAdmin(Admin admin);
	
	String delAdmin(Admin admin);
	
	String modifyAdmin(Admin admin);
	
	Admin getAdminBy(Admin admin);
	
	List<Admin> findAdminByPage(PageVo pageVo,String adminName);
	
	Integer getTotalNum(PageVo pageVo, String adminName);
	
	
}
