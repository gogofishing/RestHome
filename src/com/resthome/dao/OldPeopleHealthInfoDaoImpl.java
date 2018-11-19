package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.HealthInfo;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;

@Repository(value="oldPeopleHealthInfoDao")
public class OldPeopleHealthInfoDaoImpl extends BaseDaoImpl implements OldPeopleHealthInfoDaoInter {

	@Override
	public String addHealthInfo(HealthInfo healthinfo) {
		// TODO Auto-generated method stub
		return super.addObject(healthinfo);
	}

	@Override
	public String modifyHealthInfo(HealthInfo healthinfo) {
		// TODO Auto-generated method stub
		String hql;
		if(healthinfo!=null && (healthinfo.getSickName()!=null
				|| healthinfo.getSickDate()!=null
				|| healthinfo.getCureDate()!=null
				|| healthinfo.getCureInfoHtmlSource()!=null
				|| healthinfo.getSequela()!=null))
		{
			hql="update HealthInfo set ";
			if(healthinfo.getSickName()!=null){
				hql=hql+"sickName = '"+healthinfo.getSickName()+"',";
			}
			if(healthinfo.getSickDate()!=null){
				hql=hql+"sickDate = '"+healthinfo.getSickDate()+"',";
			}
			if(healthinfo.getCureDate()!=null){
				hql=hql+"cureDate = '"+healthinfo.getCureDate()+"',";
			}
			if(healthinfo.getCureInfoHtmlSource()!=null){
				hql=hql+"cureInfoHtmlSource = '"+healthinfo.getCureInfoHtmlSource()+"',";
			}
			if(healthinfo.getSequela()!=null){
				hql=hql+"sequela = '"+healthinfo.getSequela()+"'";
			}
			if(hql.endsWith(",")){
				hql=hql.substring(0, hql.length()-1);
			}
			
			hql=hql+" where uuid = '"+healthinfo.getUuid()+"'";
		}else{
			return "success";
		}
		
		return super.updateByHql(hql);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HealthInfo> getHealthInfo(String id) {
		// TODO Auto-generated method stub
		String hql="from HealthInfo where oldPeople.uuid = '"+id+"' and status = '"+ParamUtil.STATUS_UNDELETE+"'";
		
		return (List<HealthInfo>) super.findObjectsByHql(hql);
	}

	@Override
	public List<HealthInfo> getAllHealthInfo(HealthInfo healthinfo,PageVo pagevo) {
		// TODO Auto-generated method stub
		String sql="from HealthInfo where status = '"+ParamUtil.STATUS_UNDELETE+"'";
		
		return (List<HealthInfo>) super.findObjectsByPage(pagevo.getNowPage(), pagevo.getRowsPerPage(), sql);
	}

	@Override
	public String delHealthInfo(String id) {
		// TODO Auto-generated method stub
		String hql="update HealthInfo set status='"+ParamUtil.STATUS_DELETEED+"' where uuid='"+id+"'";
		return super.updateByHql(hql);
	}

}
