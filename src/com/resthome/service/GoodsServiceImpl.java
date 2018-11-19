package com.resthome.service;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.management.loading.PrivateClassLoader;

import org.springframework.stereotype.Repository;

import com.resthome.dao.EmployeeDaoInter;
import com.resthome.dao.GoodsDaoInter;
import com.resthome.entity.CheckIn;
import com.resthome.entity.Employee;
import com.resthome.entity.Goods;
import com.resthome.entity.InStorage;
import com.resthome.entity.OutStorage;
import com.resthome.utils.GetSerialNum;
import com.resthome.utils.GetSystemTime;
import com.resthome.utils.ParamUtil;
import com.resthome.vo.PageVo;

@Repository(value = "goodsService")
public class GoodsServiceImpl implements GoodsServiceInter{
	@Resource
	private GoodsDaoInter goodsDao;
	@Resource 
	private EmployeeDaoInter employeeDao;
	

	@Override
	public String addGoods(Goods good,Employee employee) {
		// TODO Auto-generated method stub
		String hql="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
		Employee emp=employeeDao.getEmployee(hql);
		if(emp!=null){
			good.setUuid(UUID.randomUUID().toString());
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			Integer num = goodsDao.getTotalNum("from Goods where insertTime like '%"+year+"%'");
			String goodsNo=GetSerialNum.createGoodsNo(year, num+1);
			good.setGoodsNo(goodsNo);			
			good.setEmployee(emp);
			good.setInsertTime(GetSystemTime.currentTime());
			good.setStatus(ParamUtil.STATUS_UNDELETE);
			return goodsDao.addGoods(good);			
		}else{
			return "empnull";			//采购人编号不存在
		}		
	}

	@Override
	public String updateGoods(Goods good) {
		// TODO Auto-generated method stub
		if(good!=null){
			StringBuffer hql=new StringBuffer("update Goods set ");
			if(good.getGoodsName()!=null){
				hql.append("goodsName='"+good.getGoodsName()+"',");				
			}
			if(good.getGoodsManufacturer()!=null){
				hql.append("goodsManufacturer='"+good.getGoodsManufacturer()+"',");
			}
			if(good.getGoodsProductionDate()!=null){
				hql.append("goodsProductionDate='"+good.getGoodsProductionDate()+"',");
			}
			if(good.getGoodsPrice()!=null){
				hql.append("goodsPrice='"+good.getGoodsPrice()+"',");
			}
			if(good.getGoodsNum()!=null){
				hql.append("goodsNum='"+good.getGoodsNum()+"',");
			}
			if(good.getGoodsInDate()!=null){
				hql.append("goodsInDate='"+good.getGoodsInDate()+"',");
			}
			/*if(good.getEmployee()!=null&&good.getEmployee().getEmployeeNo()!=null){
				String hql0 = "from Employee e where e.employeeNo = '"+good.getEmployee().getEmployeeNo()+"' and e.status = '"+ParamUtil.STATUS_UNDELETE+"'" ;
				Employee employee = employeeDao.getEmployee(hql0);
				if(employee==null){
					return "empnull";
				}
				hql.append("employee.employeeNo='"+employee.getEmployeeNo()+"',");
			}*/			
			
			hql.append("insertTime='"+GetSystemTime.currentTime()+"' where uuid='"+good.getUuid()+"' and status!='0'");
			
			return goodsDao.updateGoods(hql.toString());
		}else{
			return "error";
		}
	}

	@Override
	public String delGoods(Goods good) {
		// TODO Auto-generated method stub
		if(good==null){
			return "error";
		}else{
			String hql="update Goods set status='"+ParamUtil.STATUS_DELETEED+"' where uuid='"+good.getUuid()+"'";
			return goodsDao.updateGoods(hql);
		}
	}

	@Override
	public List<Goods> findGoodsByPage(PageVo pageVo, Goods good) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Goods where ");	
		if(good!=null&&good.getGoodsName()!=null&&!(good.getGoodsName().equals(""))){
			hql.append("goodsName like '%"+good.getGoodsName()+"%' and");	
		}
		hql.append(" status!='0'");
		return goodsDao.findGoodsByPage(pageVo, hql.toString());
	}

	@Override
	public Integer getTotalNum(Goods good) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from Goods where ");	
		if(good!=null&&good.getGoodsName()!=null&&!(good.getGoodsName().equals(""))){
			hql.append("goodsName like '%"+good.getGoodsName()+"%' and");	
		}
		hql.append(" status!='0'");
		return goodsDao.getTotalNum(hql.toString());
	}

	@Override
	public Goods getGoodByUuid(String uuid) {
		// TODO Auto-generated method stub
		String hql="from Goods where uuid='"+uuid+"' and status!='0'";
		Goods good=goodsDao.findByHql(hql);
		if(good!=null){
			return good;
		}else{
			return null;
		}
	}

	@Override
	public String addIn_Storage(InStorage ins,Employee employee,Goods good) {
		// TODO Auto-generated method stub
		String hql1="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
		String hql2="from Goods where uuid='"+good.getUuid()+"' and status!='0'";
		
		Employee emp=employeeDao.getEmployee(hql1);
		Goods gd=goodsDao.findByHql(hql2);
		if(emp!=null&&gd!=null){
			ins.setUuid(UUID.randomUUID().toString());
			ins.setGoods(gd);		
			ins.setEmployee(emp);
			ins.setInsertTime(GetSystemTime.currentTime());
			ins.setStatus(ParamUtil.STATUS_UNDELETE);
			String hql3="update Goods set goodsNum='"+(Integer.parseInt(gd.getGoodsNum())
					+Integer.parseInt(ins.getInNum()))+"' where uuid='"+gd.getUuid()+"'";
			goodsDao.updateGoods(hql3);
			return goodsDao.addInStorage(ins);			
		}else{
			return "120";			//该员工不存在，或该物品不存在
		}
	}

	@Override
	public String addOut_Storage(OutStorage out,Employee employee,Goods good) {
		// TODO Auto-generated method stub
		String hql1="from Employee where employeeNo='"+employee.getEmployeeNo()+"' and status!='0'";
		String hql2="from Goods where uuid='"+good.getUuid()+"' and status!='0'";
		Employee emp=employeeDao.getEmployee(hql1);
		Goods gd=goodsDao.findByHql(hql2);
		if(emp!=null&&gd!=null){
			out.setUuid(UUID.randomUUID().toString());
			out.setGoods(gd);			
			out.setEmployee(emp);
			out.setInsertTime(GetSystemTime.currentTime());
			out.setStatus(ParamUtil.STATUS_UNDELETE);
			String hql3="update Goods set goodsNum='"+(Integer.parseInt(gd.getGoodsNum())
					-Integer.parseInt(out.getOutNum()))+"' where uuid='"+gd.getUuid()+"'";
			goodsDao.updateGoods(hql3);
			return goodsDao.addOutStorage(out);			
		}else{
			return "120";			//该员工不存在
		}	
	}

	@Override
	public List<InStorage> findInStorageByPage(PageVo pageVo, Goods good,
			InStorage ins) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from InStorage where ");	
		if(good!=null&&good.getGoodsName()!=null&&!(good.getGoodsName().equals(""))){
			hql.append("goods.goodsName like '%"+good.getGoodsName()+"%' and");	
		}
		if(ins!=null&&ins.getInDate()!=null&&!(ins.getInDate().equals(""))){
			hql.append("inDate = '"+ins.getInDate()+"' and");	
		}
		hql.append(" status!='0'");
		return goodsDao.findInStorageByPage(pageVo, hql.toString());
	}

	@Override
	public List<OutStorage> findOutStorageByPage(PageVo pageVo, Goods good,
			OutStorage out) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from OutStorage where ");	
		if(good!=null&&good.getGoodsName()!=null&&!(good.getGoodsName().equals(""))){
			hql.append("goods.goodsName like '%"+good.getGoodsName()+"%' and");	
		}
		if(out!=null&&out.getOutDate()!=null&&!(out.getOutDate().equals(""))){
			hql.append("outDate = '"+out.getOutDate()+"' and");	
		}
		hql.append(" status!='0'");
		return goodsDao.findOutStorageByPage(pageVo, hql.toString());
	}

	@Override
	public Integer getInStorage_TotalNum(InStorage ins,Goods good) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from InStorage where ");	
		if(good!=null&&good.getGoodsName()!=null&&!(good.getGoodsName().equals(""))){
			hql.append("goods.goodsName like '%"+good.getGoodsName()+"%' and");	
		}
		if(ins!=null&&ins.getInDate()!=null&&!(ins.getInDate().equals(""))){
			hql.append("inDate = '"+ins.getInDate()+"' and");	
		}
		hql.append(" status!='0'");
		return goodsDao.getTotalNum(hql.toString());
	}

	@Override
	public Integer getOutStorage_TotalNum(OutStorage out,Goods good) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from OutStorage where ");	
		if(good!=null&&good.getGoodsName()!=null&&!(good.getGoodsName().equals(""))){
			hql.append("goods.goodsName like '%"+good.getGoodsName()+"%' and");	
		}
		if(out!=null&&out.getOutDate()!=null&&!(out.getOutDate().equals(""))){
			hql.append("outDate = '"+out.getOutDate()+"' and");	
		}
		hql.append(" status!='0'");
		return goodsDao.getOutStorage_TotalNum(hql.toString());
	}

	@Override
	public String delInStorage(InStorage ins) {
		// TODO Auto-generated method stub
		if(ins==null){
			return "error";
		}else{
			String hql="update InStorage set status='"+ParamUtil.STATUS_DELETEED+"' where uuid='"+ins.getUuid()+"'";
			return goodsDao.updateInStorage(hql);
		}
	}

	@Override
	public String delOutStorage(OutStorage out) {
		// TODO Auto-generated method stub
		if(out==null){
			return "error";
		}else{
			String hql="update OutStorage set status='"+ParamUtil.STATUS_DELETEED+"' where uuid='"+out.getUuid()+"'";
			return goodsDao.updateOutStorage(hql);
		}
	}
	
	

}
