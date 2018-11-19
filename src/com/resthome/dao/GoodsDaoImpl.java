package com.resthome.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.resthome.entity.Goods;
import com.resthome.entity.InStorage;
import com.resthome.entity.OutStorage;
import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;

@Repository(value = "goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl implements GoodsDaoInter{

	@Override
	public String addGoods(Goods good) {
		// TODO Auto-generated method stub
		return super.addObject(good);
	}

	@Override
	public String updateGoods(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public List<Goods> findGoodsByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		return (List<Goods>) super.findObjectsByPage(pageVo.getNowPage(),
				pageVo.getRowsPerPage(), hql);
	}

	@Override
	public Integer getTotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	@Override
	public Goods findByHql(String hql) {
		// TODO Auto-generated method stub
		Goods good=(Goods)super.findObjectByHql(hql);
		return good;
	}

	@Override
	public String addInStorage(InStorage ins) {
		// TODO Auto-generated method stub
		return super.addObject(ins);
	}

	@Override
	public String addOutStorage(OutStorage out) {
		// TODO Auto-generated method stub
		return super.addObject(out);
	}

	@Override
	public List<InStorage> findInStorageByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		return (List<InStorage>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
	}

	@Override
	public List<OutStorage> findOutStorageByPage(PageVo pageVo, String hql) {
		// TODO Auto-generated method stub
		return (List<OutStorage>)super.findObjectsByPage(pageVo.getNowPage(), pageVo.getRowsPerPage(), hql);
	}

	@Override
	public Integer getInStorage_TotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	@Override
	public Integer getOutStorage_TotalNum(String hql) {
		// TODO Auto-generated method stub
		return super.findTotleNum(hql);
	}

	@Override
	public String updateInStorage(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

	@Override
	public String updateOutStorage(String hql) {
		// TODO Auto-generated method stub
		return super.updateByHql(hql);
	}

}
