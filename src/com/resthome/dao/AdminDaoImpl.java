package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.Admin;
import com.resthome.vo.PageVo;
@Repository(value="adminDao")
public class AdminDaoImpl extends BaseDaoImpl implements AdminDaoInter{

	@Override
	public List<Admin> findAdminByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		List<Admin> admins=(List<Admin>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql.toString());
		return admins;
	}

	@Override
	public String addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return super.addObject(admin);
	}

	@Override
	public String modifyAdmin(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public Admin findAdminByHql(String hql) {
		// TODO Auto-generated method stub
		Admin admin=(Admin)super.findObjectByHql(hql);
		 return admin;
	}

	@Override
	public Integer getTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

}
