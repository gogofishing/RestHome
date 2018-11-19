package com.resthome.dao;

import java.util.List;

import com.resthome.entity.Goods;
import com.resthome.entity.InStorage;
import com.resthome.entity.OutStorage;
import com.resthome.entity.SystemMeta;
import com.resthome.vo.PageVo;

public interface GoodsDaoInter {
	
	String addGoods(Goods good);
	
	String updateGoods(String hql);
	
	String updateInStorage(String hql);
	
	String updateOutStorage(String hql);
	
	List<Goods> findGoodsByPage(PageVo pageVo,String hql);
	
	Goods findByHql(String hql);
	
	Integer getTotalNum(String hql);
	
	Integer getInStorage_TotalNum(String hql);
	
	Integer getOutStorage_TotalNum(String hql);
	
	String addInStorage(InStorage ins);
	
	String addOutStorage(OutStorage out);
	
	List<InStorage> findInStorageByPage(PageVo pageVo,String hql);
	
	List<OutStorage> findOutStorageByPage(PageVo pageVo,String hql);

}
