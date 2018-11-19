package com.resthome.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.resthome.dao.EmployeeDaoInter;
import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.dao.SystemMetaDaoInter;
import com.resthome.entity.Employee;
import com.resthome.entity.OldPeople;
import com.resthome.entity.SystemMeta;
import com.resthome.utils.CheckNullUtil;
import com.resthome.utils.GetSerialNum;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.OldPeopleVo;
import com.resthome.vo.PageVo;


@Service(value="oldPeopleService")
public class OldPeopleServiceImpl implements OldPeopleServiceInter {

	@Resource
	private OldPeopleDaoInter oldPeopleDao;
	
	@Resource
	private EmployeeDaoInter employeeDao;
	@Resource
	private SystemMetaDaoInter systemMetaDao;
	
	@Override
	public String getAllByPage(int nowPage,String oldPeopleName,String oldPeopleNo) {
		List<OldPeople> opList = new ArrayList<OldPeople>();
		String hql = "from OldPeople where status = '"+ParamUtil.STATUS_UNDELETE+"'";
		if(("".equals(oldPeopleNo))&&("".equals(oldPeopleName))){
			opList = oldPeopleDao.findAllByPage(nowPage, ParamUtil.rowsPerPage,(hql+" order by insertTime desc"));
		}else{ 
			if(!CheckNullUtil.ifOneNullOrEmpty(oldPeopleName)){
				hql += (" and oldPeopleName like '%"+oldPeopleName+"%'"); 
			}
			if(!CheckNullUtil.ifOneNullOrEmpty(oldPeopleNo)){
				hql += " and oldPeopleNo like '%"+oldPeopleNo+"%'"; 
			}
			opList = oldPeopleDao.findAllByPage(nowPage, ParamUtil.rowsPerPage,(hql+" order by insertTime desc"));
		}
		List<OldPeopleVo> opVoList = new ArrayList<OldPeopleVo>();
		if((opList!=null)&&(opList.size()>0)){
			for (OldPeople oldPeople : opList) {
				OldPeopleVo opVo = new OldPeopleVo(oldPeople.getUuid(),
						oldPeople.getOldPeopleNo(),
						oldPeople.getOldPeopleName(),
						oldPeople.getRoomNo(),
						oldPeople.getBedNo(),
						oldPeople.getSex(),
						oldPeople.getGrade()
						);
				opVoList.add(opVo);
			}
		}
		int totalNum = oldPeopleDao.findTotalNumByPage(hql);
		int totalPage = 0;
		if((totalNum%ParamUtil.rowsPerPage)==0){
			totalPage = totalNum / ParamUtil.rowsPerPage;
		}else{
			totalPage = totalNum / ParamUtil.rowsPerPage + 1;
		}
		PageVo pageVo = new PageVo();
		pageVo.setListData(opVoList);
		pageVo.setNowPage(nowPage);
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		return ToJson.ToJsonPageVo(pageVo).toString();
	}

	@Override
	public String addOldPeople(OldPeople oldPeople, String uuid,
			String dbPicPath) {
		String hql = "";
		Employee employee = null;
		//judge null or ""
		if(CheckNullUtil.ifOneNullOrEmpty(oldPeople.getOldPeopleName(),oldPeople.getIdCard(),oldPeople.getRoomNo(),oldPeople.getBedNo(),oldPeople.getGrade(),oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo(),oldPeople.getBirthday(),oldPeople.getSex(),oldPeople.getNation(),oldPeople.getParty(),oldPeople.getMarriage(),oldPeople.getPhone())
				||CheckNullUtil.ifNull(oldPeople.getOldPeopleName(),oldPeople.getIdCard(),oldPeople.getRoomNo(),oldPeople.getBedNo(),oldPeople.getGrade(),oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo(),oldPeople.getBirthday(),oldPeople.getSex(),oldPeople.getNation(),oldPeople.getParty(),oldPeople.getMarriage(),oldPeople.getPhone())){
			return "null";
		}
		hql = "from Employee e where e.employeeNo = '"+oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo()+"' and e.status = '"+ParamUtil.STATUS_UNDELETE+"'" ;
		employee = employeeDao.getEmployee(hql);
		if(employee==null){
			return "empnull1";
		}
		oldPeople.setEmployeeByCarePeople1Uuid(employee);
		if(!(CheckNullUtil.ifOneNullOrEmpty(oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo())||CheckNullUtil.ifNull(oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo()))){
			hql = "from Employee e where e.employeeNo = '"+oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo()+"' and e.status = '"+ParamUtil.STATUS_UNDELETE+"'" ;
			employee = employeeDao.getEmployee(hql);
			if(employee==null){
				return "empnull2";
			}
			oldPeople.setEmployeeByCarePeople2Uuid(employee);
		}else{
			oldPeople.setEmployeeByCarePeople2Uuid(null);
		}
		Calendar a = Calendar.getInstance();
		int nowYear = a.get(Calendar.YEAR);
		int oldPeopleNum = oldPeopleDao.findTotalNumNowYear(nowYear);
		//get oldPeopleNo by special rule
		String oldPeopleNo = GetSerialNum.createOldPeopleNo(nowYear,oldPeopleNum+1);
		SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd");
		try {
			oldPeople.setBirthday(out.format(in.parse(oldPeople.getBirthday())));
		} catch (ParseException e) {
			e.printStackTrace();
			return "error";
		}
		oldPeople.setOldPeopleNo(oldPeopleNo);
		oldPeople.setHeadImage(dbPicPath);
		oldPeople.setUuid(uuid);
		oldPeople.setInsertTime(GetSystemTime.currentTime());
		oldPeople.setStatus(ParamUtil.STATUS_UNDELETE);
		String result = oldPeopleDao.saveOldPeople(oldPeople);
		if(result=="success"){
			return "success";
		}
		return "error";
	}

	@Override
	public String deleteOldPeopleByUuid(String opUuid) {
		if(CheckNullUtil.ifOneNullOrEmpty(opUuid)||CheckNullUtil.ifNull(opUuid)){
			return "error";
		}
		String result = oldPeopleDao.updateByDelete(opUuid);
		return result;
	}

	@Override
	public String getOldPeopleByUuid(String opUuid) {
		if(CheckNullUtil.ifOneNullOrEmpty(opUuid)||CheckNullUtil.ifNull(opUuid)){
			return "error";
		}
		OldPeople oldPeople = oldPeopleDao.findByUuid(opUuid);
		if(oldPeople==null){
			return "error";
		}
		if(oldPeople.getEmployeeByCarePeople2Uuid()==null){
			OldPeopleVo opVo = new OldPeopleVo(oldPeople.getUuid(),
					oldPeople.getOldPeopleName(),
					oldPeople.getRoomNo(),
					oldPeople.getBedNo(),
					oldPeople.getSex(),
					oldPeople.getGrade(),
					oldPeople.getIdCard(),
					oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo(),
					"",
					oldPeople.getBirthday(),
					oldPeople.getNation(),
					oldPeople.getParty(),
					oldPeople.getMarriage(),
					oldPeople.getPhone(),
					oldPeople.getHomeTel(),
					oldPeople.getEmail(),
					oldPeople.getBloodType(),
					oldPeople.getAllergicHistory(),
					oldPeople.getSugarSick(),
					oldPeople.getBloodPressure(),
					oldPeople.getHeartSick(),
					oldPeople.getBrainBloodSick(),
					oldPeople.getEyeSick(),
					oldPeople.getGetUpEarly(),
					oldPeople.getShitTime(),
					oldPeople.getCanNotEat(),
					oldPeople.getOldWorkCompany(),
					oldPeople.getOldWorkName(),
					oldPeople.getOldWork(),
					oldPeople.getOldWorkContent(),
					oldPeople.getLiveAddress(),
					oldPeople.getIdCardAddress(),
					oldPeople.getFamilyInfoHtmlSource(),
					oldPeople.getEmergencyPeopleHtmlSource(),
					oldPeople.getWalletMoney()+"",
					oldPeople.getMoreHtmlSource(),
					oldPeople.getHobby()
					);
			return ToJson.ToJsonObject(opVo).toString();
		}else{
			OldPeopleVo opVo = new OldPeopleVo(oldPeople.getUuid(),
					oldPeople.getOldPeopleName(),
					oldPeople.getRoomNo(),
					oldPeople.getBedNo(),
					oldPeople.getSex(),
					oldPeople.getGrade(),
					oldPeople.getIdCard(),
					oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo(),
					oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo(),
					oldPeople.getBirthday(),
					oldPeople.getNation(),
					oldPeople.getParty(),
					oldPeople.getMarriage(),
					oldPeople.getPhone(),
					oldPeople.getHomeTel(),
					oldPeople.getEmail(),
					oldPeople.getBloodType(),
					oldPeople.getAllergicHistory(),
					oldPeople.getSugarSick(),
					oldPeople.getBloodPressure(),
					oldPeople.getHeartSick(),
					oldPeople.getBrainBloodSick(),
					oldPeople.getEyeSick(),
					oldPeople.getGetUpEarly(),
					oldPeople.getShitTime(),
					oldPeople.getCanNotEat(),
					oldPeople.getOldWorkCompany(),
					oldPeople.getOldWorkName(),
					oldPeople.getOldWork(),
					oldPeople.getOldWorkContent(),
					oldPeople.getLiveAddress(),
					oldPeople.getIdCardAddress(),
					oldPeople.getFamilyInfoHtmlSource(),
					oldPeople.getEmergencyPeopleHtmlSource(),
					oldPeople.getWalletMoney()+"",
					oldPeople.getMoreHtmlSource(),
					oldPeople.getHobby()
					);
			return ToJson.ToJsonObject(opVo).toString();
		}
	}

	@Override
	public String modifyOldPeople(OldPeople oldPeople, String dbPicPath) {
		String hql = "";
		Employee employee = null;
		//judge null or ""
		if(CheckNullUtil.ifOneNullOrEmpty(oldPeople.getOldPeopleName(),oldPeople.getIdCard(),oldPeople.getRoomNo(),oldPeople.getBedNo(),oldPeople.getGrade(),oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo(),oldPeople.getBirthday(),oldPeople.getSex(),oldPeople.getNation(),oldPeople.getParty(),oldPeople.getMarriage(),oldPeople.getPhone())
				||CheckNullUtil.ifNull(oldPeople.getOldPeopleName(),oldPeople.getIdCard(),oldPeople.getRoomNo(),oldPeople.getBedNo(),oldPeople.getGrade(),oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo(),oldPeople.getBirthday(),oldPeople.getSex(),oldPeople.getNation(),oldPeople.getParty(),oldPeople.getMarriage(),oldPeople.getPhone())){
			return "null";
		}
		hql = "from Employee e where e.employeeNo = '"+oldPeople.getEmployeeByCarePeople1Uuid().getEmployeeNo()+"' and e.status = '"+ParamUtil.STATUS_UNDELETE+"'" ;
		employee = employeeDao.getEmployee(hql);
		if(employee==null){
			return "empnull1";
		}
		oldPeople.setEmployeeByCarePeople1Uuid(employee);
		if(!(CheckNullUtil.ifOneNullOrEmpty(oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo())||CheckNullUtil.ifNull(oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo()))){
			hql = "from Employee e where e.employeeNo = '"+oldPeople.getEmployeeByCarePeople2Uuid().getEmployeeNo()+"' and e.status = '"+ParamUtil.STATUS_UNDELETE+"'" ;
			employee = employeeDao.getEmployee(hql);
			if(employee==null){
				return "empnull2";
			}
			oldPeople.setEmployeeByCarePeople2Uuid(employee);
		}else{
			oldPeople.setEmployeeByCarePeople2Uuid(null);
		}
		oldPeople.setHeadImage(dbPicPath);
		String result = oldPeopleDao.updateByOldPeople(oldPeople);
		if(result=="success"){
			return "success";
		}
		return "error";
	}

	@Override
	public OldPeople getOldPeopleMoreInfo(String opUuid) {
		if(CheckNullUtil.ifOneNullOrEmpty(opUuid)||CheckNullUtil.ifNull(opUuid)){
			return null;
		}
		OldPeople oldPeople = oldPeopleDao.findByUuid(opUuid);
		return oldPeople;
	}

	@Override
	public String isExistEmployee(String empNo) {
		String hql = "from Employee where employeeNo = '"+empNo+"' and status = '"+ParamUtil.STATUS_UNDELETE+"'";
		Employee employee = employeeDao.getEmployee(hql);
		if(employee==null){
			return "error";
		}
		return "success";
	}

	@Override
	public String getGrade() {
		
		String hql="from SystemMeta where childMetaName='8' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("metaValue", systemMeta.getMetaValue());
			ja.add(jo);
		}
		return ja.toString();
	}

	@Override
	public String getMarriage() {
		String hql="from SystemMeta where childMetaName='10' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("metaValue", systemMeta.getMetaValue());
			ja.add(jo);
		}
		return ja.toString();
	}

	@Override
	public String getParty() {
		String hql="from SystemMeta where childMetaName='9' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("metaValue", systemMeta.getMetaValue());
			ja.add(jo);
		}
		return ja.toString();
	}

	@Override
	public String getFamilyParam() {
		String hql="from SystemMeta where childMetaName='29' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}

	@Override
	public String getEmergencyPeopleParam() {
		String hql="from SystemMeta where childMetaName='30' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}

	@Override
	public String getMoreHtmlSourceParam() {
		String hql="from SystemMeta where childMetaName='31' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("htmlsource", systemMeta.getMetaValueHtmlSource());
			ja.add(jo);
		}
		return ja.toString();
	}

	
	
	
}
