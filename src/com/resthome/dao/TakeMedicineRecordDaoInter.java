package com.resthome.dao;

import java.util.List;

import com.resthome.entity.OldPeople;
import com.resthome.entity.TakeMedicineRecord;
import com.resthome.vo.TakeMedicineRecordVo;

public interface TakeMedicineRecordDaoInter {
	
	public String saveOne(TakeMedicineRecord takeMedicineRecord);

	public String updateByUuid(TakeMedicineRecordVo takeMedicineRecordVo, OldPeople oldPeople);

	public List<TakeMedicineRecord> findAllByPages(int nowPage, int rowsPerPage, String hql, List<Object> params);

	public String updateByDelete(String uuid);

	public int findTotalNumByPage(List<Object> params, String hql);

	public TakeMedicineRecord findOneByUuid(String uuid);
	
}
