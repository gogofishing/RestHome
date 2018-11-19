package com.resthome.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.OldPeople;
import com.resthome.utils.ParamUtil;

@Repository(value="oldPeopleDao")
public class OldPeopleDaoImpl extends BaseDaoImpl implements OldPeopleDaoInter {

	@Override
	public OldPeople findByUuid(String uuid) {
		String hql = "from OldPeople where uuid = '"+uuid+"' and status = '"+ParamUtil.STATUS_UNDELETE+"'";
		OldPeople oldPeople =  (OldPeople) super.findObjectByHql(hql);
		return oldPeople;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OldPeople> findAllByPage(int nowPage, int rowsPerPage,String hql) {
		return (List<OldPeople>) super.findObjectsByPage(nowPage, rowsPerPage, hql);
	}

	@Override
	public int findTotalNumByPage(String hql) {
		return super.findTotleNum(hql);
	}

	@Override
	public String saveOldPeople(OldPeople oldPeople) {
		return super.addObject(oldPeople);
	}

	@Override
	public int findTotalNumNowYear(int nowYear) {
		String hql = "from OldPeople where insertTime like '"+nowYear+"%'";
		return super.findTotleNum(hql);
	}

	@Override
	public String updateByDelete(String opUuid) {
		String hql = "update OldPeople set status = '"+ParamUtil.STATUS_DELETEED+"' where uuid = '"+opUuid+"'";
		return super.updateByHql(hql);
	}

	@Override
	public String updateByOldPeople(OldPeople oldPeople) {
		StringBuffer hql = new StringBuffer();
		hql.append(
				"update OldPeople set oldPeopleName=?"
					+",employeeByCarePeople1Uuid=?"
					+",employeeByCarePeople2Uuid=?"
					+ ",idCard=?"
					+ ",roomNo=?"
					+ ",bedNo=?"
					+ ",grade=?"
					+ ",birthday=?"
					+ ",sex=?"
					+ ",nation=?"
					+ ",party=?"
					+ ",marriage=?"
					+ ",phone=?"
					+ ",homeTel=?"
					+ ",email=?"
					+ ",bloodType=?"
					+ ",allergicHistory=?"
					+ ",sugarSick=?"
					+ ",bloodPressure=?"
					+ ",heartSick=?"
					+ ",brainBloodSick=?"
					+ ",eyeSick=?"
					+ ",getUpEarly=?"
					+ ",shitTime=?"
					+ ",canNotEat=?"
					+ ",oldWorkCompany=?"
					+ ",oldWorkName=?"
					+ ",oldWork=?"
					+ ",oldWorkContent=?"
					+ ",liveAddress=?"
					+ ",idCardAddress=?"
					+ ",familyInfoHtmlSource=?"
					+ ",emergencyPeopleHtmlSource=?"
					+ ",walletMoney=?"
					+ ",moreHtmlSource=?"
					+ ",hobby=?"
				);
		List<Object> params = new ArrayList<Object>();
		params.add(oldPeople.getOldPeopleName());
		params.add(oldPeople.getEmployeeByCarePeople1Uuid());
		params.add(oldPeople.getEmployeeByCarePeople2Uuid());
		params.add(oldPeople.getIdCard());
		params.add(oldPeople.getRoomNo());
		params.add(oldPeople.getBedNo());
		params.add(oldPeople.getGrade());
		params.add(oldPeople.getBirthday());
		params.add(oldPeople.getSex());
		params.add(oldPeople.getNation());
		params.add(oldPeople.getParty());
		params.add(oldPeople.getMarriage());
		params.add(oldPeople.getPhone());
		params.add(oldPeople.getHomeTel());
		params.add(oldPeople.getEmail());
		params.add(oldPeople.getBloodType());
		params.add(oldPeople.getAllergicHistory());
		params.add(oldPeople.getSugarSick());
		params.add(oldPeople.getBloodPressure());
		params.add(oldPeople.getHeartSick());
		params.add(oldPeople.getBrainBloodSick());
		params.add(oldPeople.getEyeSick());
		params.add(oldPeople.getGetUpEarly());
		params.add(oldPeople.getShitTime());
		params.add(oldPeople.getCanNotEat());
		params.add(oldPeople.getOldWorkCompany());
		params.add(oldPeople.getOldWorkName());
		params.add(oldPeople.getOldWork());
		params.add(oldPeople.getOldWorkContent());
		params.add(oldPeople.getLiveAddress());
		params.add(oldPeople.getIdCardAddress());
		params.add(oldPeople.getFamilyInfoHtmlSource());
		params.add(oldPeople.getEmergencyPeopleHtmlSource());
		params.add(oldPeople.getWalletMoney());
		params.add(oldPeople.getMoreHtmlSource());
		params.add(oldPeople.getHobby());
		if(oldPeople.getHeadImage()!=null){
			hql.append(",headImage=?");
			params.add(oldPeople.getHeadImage());
		}
		hql.append(" where uuid=?");
		params.add(oldPeople.getUuid());
		return super.updateByHql(hql.toString(), params);
	}

	@Override
	public OldPeople findOldPeopleByNo(String oldPeopleNo) {
		String hql = "from OldPeople where oldPeopleNo = ? and status=?";
		List<Object> params = new ArrayList<Object>();
		params.add(oldPeopleNo);
		params.add(ParamUtil.STATUS_UNDELETE);
		return (OldPeople) super.findObjectByHql(hql, params);
	}

}
