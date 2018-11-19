package com.resthome.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.resthome.dao.OldPeopleDaoInter;
import com.resthome.dao.SystemMetaDaoInter;
import com.resthome.dao.TakeMedicineRecordDaoInter;
import com.resthome.entity.OldPeople;
import com.resthome.entity.SystemMeta;
import com.resthome.entity.TakeMedicineRecord;
import com.resthome.utils.CheckNullUtil;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.PageVo;
import com.resthome.vo.TakeMedicineRecordVo;

@Service(value="takeMedicineRecordService")
public class TakeMedicineRecordServiceImpl implements
		TakeMedicineRecordServiceInter {
	
	@Resource
	private TakeMedicineRecordDaoInter takeMedicineRecordDao;
	@Resource
	private OldPeopleDaoInter oldPeopleDao;
	@Resource
	private SystemMetaDaoInter systemMetaDao;

	@Override
	public String addTakeMedicineRecord(
			TakeMedicineRecordVo takeMedicineRecordVo) {
		if(takeMedicineRecordVo.getBeginDate().compareTo(takeMedicineRecordVo.getEndDate())>0){
			return "error";
		}
		OldPeople oldPeople = oldPeopleDao.findOldPeopleByNo(takeMedicineRecordVo.getOldPeopleNo());
		if(oldPeople==null){
			return "notexist1";
		}
		TakeMedicineRecord takeMedicineRecord = new TakeMedicineRecord();
		takeMedicineRecord.setUuid(UUID.randomUUID().toString());
		takeMedicineRecord.setOldPeople(oldPeople);
		takeMedicineRecord.setTakeTimes(takeMedicineRecordVo.getTakeTimes());
		takeMedicineRecord.setBeforeEat(takeMedicineRecordVo.getBeforeEat());
		takeMedicineRecord.setBeginDate(takeMedicineRecordVo.getBeginDate());
		takeMedicineRecord.setEndDate(takeMedicineRecordVo.getEndDate());
		takeMedicineRecord.setIfPrescriptionMedicine(takeMedicineRecordVo.getIfPrescriptionMedicine());
		takeMedicineRecord.setTakeTime(takeMedicineRecordVo.getTakeTime());
		takeMedicineRecord.setMedicineName(takeMedicineRecordVo.getMedicineName());
		takeMedicineRecord.setMedicineNum(takeMedicineRecordVo.getMedicineNum());
		takeMedicineRecord.setInsertTime(GetSystemTime.currentTime());
		takeMedicineRecord.setStatus(ParamUtil.STATUS_UNDELETE);
		String result = takeMedicineRecordDao.saveOne(takeMedicineRecord);
		return result;
	}

	@Override
	public String modifyTakeMedicineRecord(
			TakeMedicineRecordVo takeMedicineRecordVo) {
		if(takeMedicineRecordVo.getBeginDate().compareTo(takeMedicineRecordVo.getEndDate())>0){
			return "error";
		}
		OldPeople oldPeople = oldPeopleDao.findOldPeopleByNo(takeMedicineRecordVo.getOldPeopleNo());
		if(oldPeople==null){
			return "notexist1";
		}
		String result = takeMedicineRecordDao.updateByUuid(takeMedicineRecordVo,oldPeople);
		return result;
	}

	@Override
	public String deleteTakeMedicineRecord(String uuid) {
		String result = "error";
		if(!(CheckNullUtil.ifOneNullOrEmpty(uuid)||CheckNullUtil.ifNull(uuid)||uuid.length()<36)){
			result = takeMedicineRecordDao.updateByDelete(uuid);
		}
		return result;
	}

	@Override
	public String getAllByPage(int nowPage, String oldPeopleNo,
			String oldPeopleName) {
		List<Object> params = new ArrayList<Object>();
		params.add(ParamUtil.STATUS_UNDELETE);
		String hql = "from TakeMedicineRecord where status=?";
		List<TakeMedicineRecord> tmrList = new ArrayList<TakeMedicineRecord>();
		if(("".equals(oldPeopleNo))&&("".equals(oldPeopleName))){
			tmrList = takeMedicineRecordDao.findAllByPages(nowPage, ParamUtil.rowsPerPage,(hql+" order by insertTime desc"),params);
		}else{
			if(!CheckNullUtil.ifOneNullOrEmpty(oldPeopleName)){
				hql += (" and oldPeople.oldPeopleName like ?");
				params.add("%"+oldPeopleName+"%");
			}
			if(!CheckNullUtil.ifOneNullOrEmpty(oldPeopleNo)){
				hql += " and oldPeople.oldPeopleNo like ?"; 
				params.add("%"+oldPeopleNo+"%");
			}
			tmrList = takeMedicineRecordDao.findAllByPages(nowPage, ParamUtil.rowsPerPage,(hql+" order by insertTime desc"),params);
		}
		List<TakeMedicineRecordVo> tmrVoList = new ArrayList<TakeMedicineRecordVo>();
		if((tmrList!=null)&&(tmrList.size()>0)){
			for (TakeMedicineRecord takeMedicineRecord : tmrList) {
				TakeMedicineRecordVo tmrVo = new TakeMedicineRecordVo(takeMedicineRecord.getUuid()
						,takeMedicineRecord.getOldPeople().getOldPeopleNo()
						,takeMedicineRecord.getTakeTimes()
						,takeMedicineRecord.getOldPeople().getOldPeopleName()
						,takeMedicineRecord.getBeginDate()
						,takeMedicineRecord.getEndDate()
						,takeMedicineRecord.getMedicineName());
				tmrVoList.add(tmrVo);
			}
		}
		int totalNum = takeMedicineRecordDao.findTotalNumByPage(params, hql);
		int totalPage = 0;
		if((totalNum%ParamUtil.rowsPerPage)==0){
			totalPage = totalNum / ParamUtil.rowsPerPage;
		}else{
			totalPage = totalNum / ParamUtil.rowsPerPage + 1;
		}
		PageVo pageVo = new PageVo();
		pageVo.setListData(tmrVoList);
		pageVo.setNowPage(nowPage);
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		return ToJson.ToJsonPageVo(pageVo).toString();
	}

	@Override
	public String getMoreInfo(String uuid) {
		if(CheckNullUtil.ifOneNullOrEmpty(uuid)||CheckNullUtil.ifNull(uuid)){
			return "error";
		}
		TakeMedicineRecord takeMedicineRecord = takeMedicineRecordDao.findOneByUuid(uuid);
		if(takeMedicineRecord==null){
			return "error";
		}
		TakeMedicineRecordVo takeMedicineRecordVo = new TakeMedicineRecordVo(takeMedicineRecord.getUuid()
				,takeMedicineRecord.getOldPeople().getOldPeopleNo()
				,takeMedicineRecord.getBeforeEat()
				,takeMedicineRecord.getTakeTimes()
				,takeMedicineRecord.getOldPeople().getOldPeopleName()
				,takeMedicineRecord.getBeginDate()
				,takeMedicineRecord.getEndDate()
				,takeMedicineRecord.getIfPrescriptionMedicine()
				,takeMedicineRecord.getTakeTime()
				,takeMedicineRecord.getMedicineNum()
				,takeMedicineRecord.getMedicineName()
				,takeMedicineRecord.getInsertTime());
		return ToJson.ToJsonObject(takeMedicineRecordVo).toString();
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
	public String getTakeMedicineRecord(String uuid) {
		if(CheckNullUtil.ifOneNullOrEmpty(uuid)||CheckNullUtil.ifNull(uuid)){
			return "error";
		}
		TakeMedicineRecord takeMedicineRecord = takeMedicineRecordDao.findOneByUuid(uuid);
		if(takeMedicineRecord==null){
			return "error";
		}
		TakeMedicineRecordVo takeMedicineRecordVo = new TakeMedicineRecordVo(takeMedicineRecord.getUuid()
				,takeMedicineRecord.getOldPeople().getOldPeopleNo()
				,takeMedicineRecord.getBeforeEat()
				,takeMedicineRecord.getTakeTimes()
				,takeMedicineRecord.getBeginDate()
				,takeMedicineRecord.getEndDate()
				,takeMedicineRecord.getIfPrescriptionMedicine()
				,takeMedicineRecord.getTakeTime()
				,takeMedicineRecord.getMedicineNum()
				,takeMedicineRecord.getMedicineName());
		return ToJson.ToJsonObject(takeMedicineRecordVo).toString();
	}

	@Override
	public String getTakeTimes() {
		String hql="from SystemMeta where childMetaName='7' and status!='0' order by listOrder";
		List<SystemMeta> smList = systemMetaDao.findStstemMetasByCN(hql);
		if(!((smList!=null)&&(smList.size()>0))){
			return "error";
		}
		JSONArray ja = new JSONArray();
		for (SystemMeta systemMeta : smList) {
			JSONObject jo = new JSONObject();
			jo.put("metaValue", systemMeta.getMetaValue());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	
	

}
