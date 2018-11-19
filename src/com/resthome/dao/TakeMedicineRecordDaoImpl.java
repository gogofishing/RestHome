package com.resthome.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.OldPeople;
import com.resthome.entity.TakeMedicineRecord;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.TakeMedicineRecordVo;


@Repository(value="takeMedicineRecordDao")
public class TakeMedicineRecordDaoImpl extends BaseDaoImpl implements
		TakeMedicineRecordDaoInter {

	@Override
	public String saveOne(TakeMedicineRecord takeMedicineRecord) {
		return super.addObject(takeMedicineRecord);
	}

	@Override
	public String updateByUuid(TakeMedicineRecordVo takeMedicineRecordVo, OldPeople oldPeople) {
		String hql = "update TakeMedicineRecord set oldPeople=?,beginDate=?,endDate=?,takeTimes=?,beforeEat=?,ifPrescriptionMedicine=?,takeTime=?,medicineNum=?,medicineName=? where uuid = ? and status = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(oldPeople);
		params.add(takeMedicineRecordVo.getBeginDate());
		params.add(takeMedicineRecordVo.getEndDate());
		params.add(takeMedicineRecordVo.getTakeTimes());
		params.add(takeMedicineRecordVo.getBeforeEat());
		params.add(takeMedicineRecordVo.getIfPrescriptionMedicine());
		params.add(takeMedicineRecordVo.getTakeTime());
		params.add(takeMedicineRecordVo.getMedicineNum());
		params.add(takeMedicineRecordVo.getMedicineName());
		params.add(takeMedicineRecordVo.getUuid());
		params.add(ParamUtil.STATUS_UNDELETE);
		return super.updateByHql(hql, params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TakeMedicineRecord> findAllByPages(int nowPage,
			int rowsPerPage, String hql, List<Object> params) {
		return (List<TakeMedicineRecord>) super.findObjectsByHqlByPage(nowPage, rowsPerPage, hql, params);
	}

	@Override
	public String updateByDelete(String uuid) {
		String hql = "update TakeMedicineRecord set status = ? where uuid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(ParamUtil.STATUS_DELETEED);
		params.add(uuid);
		return super.updateByHql(hql, params);
	}

	@Override
	public int findTotalNumByPage(List<Object> params, String hql) {
		return super.findTotleNum(hql, params);
	}

	@Override
	public TakeMedicineRecord findOneByUuid(String uuid) {
		String hql = "from TakeMedicineRecord where uuid = ? and status = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uuid);
		params.add(ParamUtil.STATUS_UNDELETE);
		return (TakeMedicineRecord) super.findObjectByHql(hql, params);
	}

}
