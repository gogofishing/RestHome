package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;

@Repository(value = "systemMetaDao")
public class SystemMetaDaoImpl extends BaseDaoImpl implements
		SystemMetaDaoInter {

	@Override
	public String addSystemMeta(SystemMeta systemMeta) {
		// TODO Auto-generated method stub
		return super.addObject(systemMeta);
	}

	@Override
	public String updateSystemMeta(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public List<SystemMeta> findSystemMetaByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		return (List<SystemMeta>) super.findObjectsByPage(pageVo.getNowPage(),
				pageVo.getRowsPerPage(), hql);
	}

	@Override
	public SystemMeta findSystemMeta(String hql) {
		// TODO Auto-generated method stub
		return (SystemMeta) super.findObjectByHql(hql);
	}

	@Override
	public List<SystemMeta> findSystemMetas(String hql, List<Object> params) {
		// TODO Auto-generated method stub

		return (List<SystemMeta>) super.findObjectsByHqlByPage(null, null, hql,
				params);
	}

	@Override
	public Integer getTotalNum(String hql) {
		// TODO Auto-generated method stub
		// System.out.println("sss+"+hql);
		return super.findTotleNum(hql);
	}

	@Override
	public List<SystemMeta> findStstemMetasByCN(String hql) {
		// TODO Auto-generated method stub
		return (List<SystemMeta>) super.findObjectsByHql(hql);
	}

}
