package com.resthome.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.resthome.dao.AdminDaoInter;
import com.resthome.entity.Admin;
import com.resthome.entity.CheckIn;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;

@Repository(value="adminService")
public class AdminServiceImpl implements AdminServiceInter{
	
	@Resource 
	private AdminDaoInter adminDao;
	

	@Override
	public String addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(admin!=null){
			admin.setUuid(UUID.randomUUID().toString());
			admin.setIdentify("普通管理员");
			admin.setAdminPwd("666666");
			admin.setInsertTime(GetSystemTime.currentTime());
			admin.setStatus(ParamUtil.STATUS_UNDELETE);
			return adminDao.addAdmin(admin);
		}
		return "error";
	}

	@Override
	public String delAdmin(Admin admin) {
		// TODO Auto-generated method stub
		String hql="update Admin set status='0' where uuid='"+admin.getUuid()+"'";
		return adminDao.modifyAdmin(hql);
	}

	@Override
	public String modifyAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(admin!=null){
			StringBuffer hql=new StringBuffer("update Admin set ");
			if(admin.getAdminName()!=null){
				hql.append("adminName='"+admin.getAdminName()+"',");
			}
			if(admin.getAdminPwd()!=null){
				hql.append("adminPwd='"+admin.getAdminPwd()+"',");
			}
			if(admin.getCommunityAuthority()!=null){
				hql.append("communityAuthority='"+admin.getCommunityAuthority()+"',");
			}
			if(admin.getEmployeeAuthority()!=null){
				hql.append("employeeAuthority='"+admin.getEmployeeAuthority()+"',");
			}
			if(admin.getOldpeopleAuthority()!=null){
				hql.append("oldpeopleAuthority='"+admin.getOldpeopleAuthority()+"',");
			}
			if(admin.getStorageAuthority()!=null){
				hql.append("storageAuthority='"+admin.getStorageAuthority()+"',");
			}
			if(admin.getSystemMetaAuthority()!=null){
				hql.append("systemMetaAuthority='"+admin.getSystemMetaAuthority()+"',");
			}			
			hql.append("insertTime='"+GetSystemTime.currentTime()+"' where uuid='"+admin.getUuid()+"' and status!='0'");
			return adminDao.modifyAdmin(hql.toString());
		}
		return "error";
	}

	@Override
	public Admin getAdminBy(Admin admin) {
		// TODO Auto-generated method stub
		String hql="";
		if(admin.getUuid()!=null&&!admin.getUuid().equals("")){
			hql="from Admin where uuid='"+admin.getUuid()+"' and status!='0'";
		}else if(admin.getAdminName()!=null&&!admin.getAdminName().equals("")){
			hql="from Admin where adminName='"+admin.getAdminName()+"' and status!='0'";
		}		
		Admin ad=adminDao.findAdminByHql(hql);
		if(ad!=null){
			return ad;
		}else{
			return null;
		}
	}

	@Override
	public List<Admin> findAdminByPage(PageVo pageVo, String adminName) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Admin where ");	
		if(adminName!=null){
			hql.append("adminName like '%"+adminName+"%' and ");	
		}
		hql.append(" status!='0'");
		return adminDao.findAdminByPage(pageVo, hql.toString());
	}

	@Override
	public Integer getTotalNum(PageVo pageVo, String adminName) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Admin where ");	
		if(adminName!=null){
			hql.append("adminName like '%"+adminName+"%' and ");	
		}
		hql.append(" status!='0'");
		return adminDao.getTotalNum(hql.toString());
	}

	

	
}
