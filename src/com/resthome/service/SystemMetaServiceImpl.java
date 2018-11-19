package com.resthome.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.panjun.handle.Handle;
import com.resthome.dao.SystemMetaDaoInter;
import com.resthome.entity.SystemMeta;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.HqlUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;
import com.resthome.vo.SystemMetaVo;


@Repository(value = "systemMetaService")
public class SystemMetaServiceImpl implements SystemMetaServiceInter {
	@Resource
	private SystemMetaDaoInter systemMetaDao;

	@Override
	public String addSystemMeta(SystemMetaVo systemMetaVo) {
		// TODO Auto-generated method stub
		systemMetaVo.setUuid(UUID.randomUUID().toString());
		systemMetaVo.setInsertTime(GetSystemTime.currentTime());
		systemMetaVo.setStatus("1");
		SystemMeta systemMeta=new SystemMeta();
		Handle.vo2Entity(systemMetaVo, systemMeta);
		return systemMetaDao.addSystemMeta(systemMeta);
	}

	@Override
	public String delSystemMeta(SystemMetaVo systemMetaVo) {
		// TODO Auto-generated method stub
		SystemMeta sysm = this.findSystemMeta(systemMetaVo);
		String hql1 = "update SystemMeta set status='0' where uuid='"
				+ systemMetaVo.getUuid() + "'";
		String hql2 = "from SystemMeta where childMetaName='"
				+ sysm.getParentMetaName() + "' and status!='0'";
		List<SystemMeta> syss = systemMetaDao.findStstemMetasByCN(hql2);

		if (syss != null) {
			for (SystemMeta s : syss) {
				SystemMetaVo vo=new SystemMetaVo();
				Handle.entity2Vo(s, vo);
				this.delSystemMeta(vo);
			}
		}
		return systemMetaDao.updateSystemMeta(hql1);
	}

	@Override
	public String updateSystemMeta(SystemMetaVo systemMetaVo) {
		// TODO Auto-generated method stub
		if (systemMetaVo == null) {
			return "error";
		}
		systemMetaVo.setInsertTime(GetSystemTime.currentTime());
		String set = HqlUtils.getUpdateSet(systemMetaVo);
		if (set == null) {
			return "error";
		}		
		SystemMeta sysm = this.findSystemMeta(systemMetaVo);
		String hql1 = "update SystemMeta set " + set + " where uuid='"
				+ systemMetaVo.getUuid() + "'";
		String hql2 = "from SystemMeta where childMetaName='"
				+ sysm.getParentMetaName() + "' and status!='0'";
		List<SystemMeta> syss = systemMetaDao.findStstemMetasByCN(hql2);
		
		if (syss != null) {
			for (SystemMeta s : syss) {
				SystemMetaVo vo=new SystemMetaVo();
				Handle.entity2Vo(s, vo);
				String hql3="update SystemMeta set childMetaName='"
	/*待测！*/			+systemMetaVo.getParentMetaName()+"' where uuid='"
					+ s.getUuid()+ "'";
				systemMetaDao.updateSystemMeta(hql3);
			}
		}
		return systemMetaDao.updateSystemMeta(hql1);
	}

	@Override
	public SystemMeta findSystemMeta(SystemMetaVo systemMetaVo) {
		// TODO Auto-generated method stub
		String hql=null;
		if(systemMetaVo.getUuid()!=null){
			hql = "from SystemMeta where uuid='" + systemMetaVo.getUuid()
					+ "' and status!='0'";
		}else{
			hql = "from SystemMeta where parentMetaName='"+systemMetaVo.getParentMetaName()
					+"' and status!='0'";
		}
		
		return systemMetaDao.findSystemMeta(hql);
	}

	@Override
	public List<Object> findSystemMetaByPage(PageVo pageVo,
			SystemMetaVo systemMetaVo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from SystemMeta ");
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
			pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		}

		if (systemMetaVo != null) {
			hql = hql
					.append(" where "
							+ HqlUtils.getQueryConditions(systemMetaVo, null)
							+ " and ");
		}
		hql.append(" status!='0'");
		// System.out.println("ooooooooo"+hql.toString());
		List<SystemMeta> systemMetaList = systemMetaDao.findSystemMetaByPage(
				pageVo, hql.toString());
		List<Object> systemVoList = new ArrayList<Object>();
		if (systemMetaList != null) {
			for (SystemMeta smte : systemMetaList) {
				SystemMetaVo svo = new SystemMetaVo();
				Handle.entity2Vo(smte, svo);
				systemVoList.add(svo);
			}
		}

		return systemVoList;
	}

	
	@Override
	public List<SystemMeta> findSystemMetaByParent(SystemMeta systemMeta) {
		// TODO Auto-generated method stub
		String hql="from SystemMeta where childMetaName='"+systemMeta.getParentMetaName()+"' and status!='0' order by listOrder";
		List<SystemMeta> systemMetas=systemMetaDao.findStstemMetasByCN(hql);
		return systemMetas;
	}


	@Override
	public Integer getTotalNum(PageVo pageVo, SystemMetaVo systemMetaVo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from SystemMeta ");
		/*
		 * if(pageVo==null){ pageVo = new PageVo(); pageVo.setNowPage(1);
		 * pageVo.setRowsPerPage(ParamUtil.rowsPerPage); }
		 */
		if (systemMetaVo != null) {
			hql = hql.append(" where "
					+ HqlUtils.getQueryConditions(systemMetaVo, null) + " and ");
		}
		hql.append(" status!='0'");
		return systemMetaDao.getTotalNum(hql.toString());
	}

	@Override
	public Integer queryMaxOrder(SystemMeta systemMeta) {
		// TODO Auto-generated method stub
		int maxOrder = 0;
		String hql = "from SystemMeta where metaValueType='SYSTEMMETA_CHILD'";
		List<SystemMeta> systemMetas = systemMetaDao.findStstemMetasByCN(hql);
		if (systemMetas != null && systemMetas.size() >= 1) {
			maxOrder = Integer.parseInt(systemMetas.get(0).getListOrder());
			for (int i = 1; i < systemMetas.size(); i++) {
				if (Integer.parseInt(systemMetas.get(i).getListOrder()) > maxOrder) {
					maxOrder = Integer.parseInt(systemMetas.get(i)
							.getListOrder());
				}
			}
		}

		return maxOrder;
	}

	@Override
	public Integer queryMaxPn(SystemMeta systemMeta) {
		// TODO Auto-generated method stub
		int maxPn = 0;
		String hql = "from SystemMeta where metaValueType='SYSTEMMETA_PARENT'";
		List<SystemMeta> systemMetas = systemMetaDao.findStstemMetasByCN(hql);
		if (systemMetas != null && systemMetas.size() >= 1) {
			maxPn = Integer
					.parseInt(systemMetas.get(0).getParentMetaName() == null ? "0"
							: systemMetas.get(0).getParentMetaName());
			for (int i = 1; i < systemMetas.size(); i++) {
				if (Integer
						.parseInt(systemMetas.get(i).getParentMetaName() == null ? "0"
								: systemMetas.get(i).getParentMetaName()) > maxPn) {
					maxPn = Integer.parseInt(systemMetas.get(i)
							.getParentMetaName());
				}
			}
		}
		return maxPn;
	}

	
}
