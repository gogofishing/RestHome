package com.resthome.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.OldPeopleOutInRecord;
import com.resthome.utils.ParamUtil;

@Repository(value="oldPeopleOutInRecordDao")
public class OldPeopleOutInRecordDaoImpl extends BaseDaoImpl implements
		OldPeopleOutInRecordDaoInter {

	@SuppressWarnings("unchecked")
	@Override
	public List<OldPeopleOutInRecord> findAllByPage(int nowPage, int rowsPerPage, List<Object> params, String hql) {
		return (List<OldPeopleOutInRecord>) super.findObjectsByHqlByPage(nowPage, rowsPerPage, hql, params);
	}

	@Override
	public int findTotalNumByPage(List<Object> params, String hql) {
		return super.findTotleNum(hql, params);
	}

	@Override
	public String updateByDelete(String uuid) {
		String hql = "update OldPeopleOutInRecord set status=? where uuid = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(ParamUtil.STATUS_DELETEED);
		params.add(uuid);
		return super.updateByHql(hql, params);
	}

	@Override
	public String saveOldPeopleOutInRecord(
			OldPeopleOutInRecord oldPeopleOutInRecord) {
		return super.addObject(oldPeopleOutInRecord);
	}

	@Override
	public OldPeopleOutInRecord findOldPeopleOutInRecordByUuid(String uuid) {
		String hql = "from OldPeopleOutInRecord where uuid = ? and status = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(uuid);
		params.add(ParamUtil.STATUS_UNDELETE);
		return (OldPeopleOutInRecord) super.findObjectByHql(hql, params);
	}


}
