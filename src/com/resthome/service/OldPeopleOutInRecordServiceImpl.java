package com.resthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.resthome.dao.EmployeeDaoInter;
import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.dao.OldPeopleOutInRecordDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.OldPeople;
import com.resthome.entity.OldPeopleOutInRecord;
import com.resthome.utils.CheckNullUtil;
import com.resthome.utils.CheckUserInfo;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.OldPeopleOutInRecordVo;
import com.resthome.vo.PageVo;

@Service(value="oldPeopleOutInRecordService")
public class OldPeopleOutInRecordServiceImpl implements
		OldPeopleOutInRecordServiceInter {

	@Resource
	private OldPeopleOutInRecordDaoInter oldPeopleOutInRecordDao;
	@Resource
	private OldPeopleDaoInter oldPeopleDao;
	@Resource
	private EmployeeDaoInter employeeDao;

	@Override
	public String getAllByPage(int nowPage, String oldPeopleNo,
			String oldPeopleName) {
		List<Object> params = new ArrayList<Object>();
		params.add(ParamUtil.STATUS_UNDELETE);
		String hql = "from OldPeopleOutInRecord where status=?";
		List<OldPeopleOutInRecord> opoirList = new ArrayList<OldPeopleOutInRecord>();
		if(("".equals(oldPeopleNo))&&("".equals(oldPeopleName))){
			opoirList = oldPeopleOutInRecordDao.findAllByPage(nowPage, ParamUtil.rowsPerPage,params,(hql+" order by insertTime desc"));
		}else{ 
			if(!CheckNullUtil.ifOneNullOrEmpty(oldPeopleName)){
				hql += (" and oldPeople.oldPeopleName like ?");
				params.add("%"+oldPeopleName+"%");
			}
			if(!CheckNullUtil.ifOneNullOrEmpty(oldPeopleNo)){
				hql += " and oldPeople.oldPeopleNo like ?"; 
				params.add("%"+oldPeopleNo+"%");
			}
			opoirList = oldPeopleOutInRecordDao.findAllByPage(nowPage, ParamUtil.rowsPerPage,params,(hql+" order by insertTime desc"));
		}
		List<OldPeopleOutInRecordVo> opoirVoList = new ArrayList<OldPeopleOutInRecordVo>();
		if((opoirList!=null)&&(opoirList.size()>0)){
			for (OldPeopleOutInRecord oldPeopleOutInRecord : opoirList) {
				OldPeopleOutInRecordVo opoirVo = new OldPeopleOutInRecordVo(oldPeopleOutInRecord.getUuid()
						,oldPeopleOutInRecord.getOldPeople().getOldPeopleNo()
						,oldPeopleOutInRecord.getOldPeople().getOldPeopleName()
						,oldPeopleOutInRecord.getWorkPeopleNo()
						,oldPeopleOutInRecord.getWorkPeopleName()
						,oldPeopleOutInRecord.getBeginTime()
						,oldPeopleOutInRecord.getEndTime());
				opoirVoList.add(opoirVo);
			}
		}
		int totalNum = oldPeopleOutInRecordDao.findTotalNumByPage(params, hql);
		int totalPage = 0;
		if((totalNum%ParamUtil.rowsPerPage)==0){
			totalPage = totalNum / ParamUtil.rowsPerPage;
		}else{
			totalPage = totalNum / ParamUtil.rowsPerPage + 1;
		}
		PageVo pageVo = new PageVo();
		pageVo.setListData(opoirVoList);
		pageVo.setNowPage(nowPage);
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		return ToJson.ToJsonPageVo(pageVo).toString();
	}

	@Override
	public String getOldPeopleByNo(String oldPeopleNo) {
		OldPeople oldPeople = oldPeopleDao.findOldPeopleByNo(oldPeopleNo);
		if(oldPeople==null){
			return "error";
		}
		return "success";
	}

	@Override
	public String getEmployeeByNo(String empNo) {
		String hql = "from Employee where status = '"+ParamUtil.STATUS_UNDELETE+"' and employeeNo = '"+empNo+"'";
		Employee employee = employeeDao.getEmployee(hql);
		if(employee==null){
			return "error";
		}
		return "success";
	}

	@Override
	public String addOldPeopleOutInRecord(
			OldPeopleOutInRecordVo oldPeopleOutInRecordVo) {
		OldPeople oldPeople = oldPeopleDao.findOldPeopleByNo(oldPeopleOutInRecordVo.getOldPeopleNo());
		if(oldPeople==null){
			return "notexist1";
		}
		String hql = "from Employee where employeeNo = '"+oldPeopleOutInRecordVo.getEmpNo()+"' and status = '"+ParamUtil.STATUS_UNDELETE+"'";
		Employee employee = employeeDao.getEmployee(hql);
		if(employee==null){
			return "notexist2";
		}
		OldPeopleOutInRecord oldPeopleOutInRecord = new OldPeopleOutInRecord();
		String uuid = UUID.randomUUID().toString();
		oldPeopleOutInRecord.setUuid(uuid);
		oldPeopleOutInRecord.setOldPeople(oldPeople);
		oldPeopleOutInRecord.setRecordDate(oldPeopleOutInRecordVo.getRecordDate());
		oldPeopleOutInRecord.setBeginTime(oldPeopleOutInRecordVo.getBeginTime());
		oldPeopleOutInRecord.setEndTime(oldPeopleOutInRecordVo.getEndTime());
		oldPeopleOutInRecord.setReason(oldPeopleOutInRecordVo.getReason());
		oldPeopleOutInRecord.setFamilyInfo(oldPeopleOutInRecordVo.getFamilyInfo());
		oldPeopleOutInRecord.setStatus(ParamUtil.STATUS_UNDELETE);
		oldPeopleOutInRecord.setInsertTime(GetSystemTime.currentTime());
		oldPeopleOutInRecord.setWorkPeopleNo(employee.getEmployeeNo());
		oldPeopleOutInRecord.setWorkPeopleName(employee.getEmployeeName());
		return oldPeopleOutInRecordDao.saveOldPeopleOutInRecord(oldPeopleOutInRecord);
	}

	@Override
	public String deleteOldPeopleOutInRecord(String uuid) {
		String result = "error";
		if(!(CheckNullUtil.ifOneNullOrEmpty(uuid)||CheckNullUtil.ifNull(uuid)||uuid.length()<36)){
			result = oldPeopleOutInRecordDao.updateByDelete(uuid);
		}
		return result;
	}

	@Override
	public String getMoreInfo(String uuid) {
		OldPeopleOutInRecord oldPeopleOutInRecord = oldPeopleOutInRecordDao.findOldPeopleOutInRecordByUuid(uuid);
		if(oldPeopleOutInRecord==null){
			return "error";
		}
		OldPeopleOutInRecordVo oldPeopleOutInRecordVo = new OldPeopleOutInRecordVo(oldPeopleOutInRecord.getUuid()
				,oldPeopleOutInRecord.getOldPeople().getOldPeopleNo()
				,oldPeopleOutInRecord.getOldPeople().getOldPeopleName()
				,oldPeopleOutInRecord.getWorkPeopleNo()
				,oldPeopleOutInRecord.getWorkPeopleName()
				,oldPeopleOutInRecord.getBeginTime()
				,oldPeopleOutInRecord.getEndTime()
				,oldPeopleOutInRecord.getRecordDate()
				,oldPeopleOutInRecord.getReason()
				,oldPeopleOutInRecord.getFamilyInfo());
		return ToJson.ToJsonObject(oldPeopleOutInRecordVo).toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
