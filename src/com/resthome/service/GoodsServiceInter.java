package com.resthome.service;

import java.util.List;

import com.resthome.entity.Employee;
import com.resthome.entity.Goods;
import com.resthome.entity.InStorage;
import com.resthome.entity.OutStorage;
import com.resthome.vo.PageVo;
import com.resthome.vo.SystemMetaVo;

public interface GoodsServiceInter {
	
	String addGoods(Goods good,Employee employee);
	
	String updateGoods(Goods good);
	
	String delGoods(Goods good);
	
	String delInStorage(InStorage ins);
	
	String delOutStorage(OutStorage out);
	
	Goods getGoodByUuid(String uuid);
	
	List<Goods> findGoodsByPage(PageVo pageVo,Goods good);
	
	Integer getTotalNum(Goods good);
	
	Integer getInStorage_TotalNum(InStorage ins,Goods good);
	
	Integer getOutStorage_TotalNum(OutStorage out,Goods good);
	
	String addIn_Storage(InStorage ins,Employee employee,Goods good);
	
	String addOut_Storage(OutStorage out,Employee employee,Goods good);
	
	List<InStorage> findInStorageByPage(PageVo pageVo,Goods good,InStorage ins);
	
	List<OutStorage> findOutStorageByPage(PageVo pageVo,Goods good,OutStorage out);

}
