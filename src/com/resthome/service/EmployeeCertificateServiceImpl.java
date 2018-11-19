package com.resthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Repository;

import com.panjun.handle.Handle;
import com.resthome.dao.EmployeeCertificateDaoInter;
import com.resthome.entity.EmployeeCertificate;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.HqlUtils;
import com.resthome.utils.ToJson;
import com.resthome.vo.EmployeeCertificateVo;
@Repository(value="employeeCertificateService")
public class EmployeeCertificateServiceImpl implements
		EmployeeCertificateServiceInter {
	@Resource
	private EmployeeCertificateDaoInter employeeCertificateDao;

	@Override
	public String addEmployeeCertificate(EmployeeCertificateVo ecvo) {
		// TODO Auto-generated method stub
		EmployeeCertificate ec= new EmployeeCertificate();
		if(ecvo!=null){
			
			Handle.vo2Entity(ecvo, ec);
			ec.setUuid(UUID.randomUUID().toString());
			ecvo.setUuid(ec.getUuid());
			ec.setStatus("1");
			ec.setInsertTime(GetSystemTime.currentTime());
			employeeCertificateDao.addCertificate(ec);
			JSONObject jso = ToJson.ToJsonObject(ecvo);
			return jso.toString();
		}else{
			return "error";
		}
		
	}

	@Override
	public String updateEmployeeCertificate(EmployeeCertificateVo ecvo) {
		// TODO Auto-generated method stub
		if(ecvo!=null){
			
			String setStr = HqlUtils.getUpdateSetStr(ecvo);
			if(setStr!=null){
				String hql = "update EmployeeCertificate set "+setStr+" where uuid=?";
				List<Object> params = HqlUtils.getParams(ecvo);
				params.add(ecvo.getUuid());
				return employeeCertificateDao.updateCertificate(hql, params);
				
			}
		}
		return "error";
	}

	@Override
	public String delEmployeeCertificate(EmployeeCertificateVo ecvo) {
		// TODO Auto-generated method stub
		if(ecvo!=null){
			if(ecvo.getUuid()!=null){
				String hql = "update EmployeeCertificate set status='0' where uuid=?";
				List<Object> params = new ArrayList<Object>();
				params.add(ecvo.getUuid());
				return employeeCertificateDao.updateCertificate(hql, params);
			}
			
		}
		return "error";
	}

	@Override
	public EmployeeCertificateVo getEmployeeCertificate(
			EmployeeCertificateVo ecvo) {
		// TODO Auto-generated method stub
        String hql = "from EmployeeCertificate where employee.uuid=?";
       
		return null;
	}

	@Override
	public List<EmployeeCertificateVo> getEmployeeCertificates(
			EmployeeCertificateVo ecvo) {
		// TODO Auto-generated method stub
		if(ecvo!=null){
			String hql = "from EmployeeCertificate where employee.uuid=? and status!='0'";
			List<Object> params = new ArrayList<Object>();
			params.add(ecvo.getEmpUuid());
			List<EmployeeCertificate> list = employeeCertificateDao.getCertificates(hql, params);
			
		    List<EmployeeCertificateVo> ecvs = new ArrayList<EmployeeCertificateVo>();
		    if(list==null){
		    	return null;
		    }
		    for(EmployeeCertificate ec:list){
		    	EmployeeCertificateVo ecv = new EmployeeCertificateVo();
		    	Handle.entity2Vo(ec, ecv);
		    	ecv.setCertificateStatus(this.isInEndTime(ec.getEndTime()));
		    	ecvs.add(ecv);
		    	
		    	
		    }
		    return ecvs;
		}
		return null;
	}
	private String isInEndTime(String endTime){
		String nowTime = GetSystemTime.currentTime();
		if(nowTime.compareToIgnoreCase(endTime)>0){
			return "过期";
		}else{
			return "未过期";
		}
	}

}
