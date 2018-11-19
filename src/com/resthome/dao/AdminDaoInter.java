package com.resthome.dao;

import java.util.List;

import com.resthome.entity.Admin;
import com.resthome.vo.PageVo;

public interface AdminDaoInter {

	List<Admin> findAdminByPage(PageVo pageVo,String hql);
	
	String addAdmin(Admin admin);
	
	String modifyAdmin(String hql);
	
	Admin findAdminByHql(String hql);
	
	Integer getTotalNum(String hql);
	
	
	
	
}
