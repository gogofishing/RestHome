package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;
import com.panjun.handle.Handle;
import com.resthome.entity.Employee;
import com.resthome.entity.Goods;
import com.resthome.entity.InStorage;
import com.resthome.entity.OutStorage;
import com.resthome.service.GoodsServiceInter;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.GoodsVo;
import com.resthome.vo.InStorageVo;
import com.resthome.vo.OutStorageVo;
import com.resthome.vo.PageVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/Goods")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class GoodsAction extends ActionSupport{
	@Resource
	private GoodsServiceInter goodsService;
	
	private Goods good;
	private Employee employee;
	private InStorage inStorage;
	private OutStorage outStorage;
	private String result = "error";
	private PageVo pageVo;
	private List<Goods> goodsList;
	private List<InStorage> insList;
	private List<OutStorage> outList;
	private List<Object> voList = new ArrayList<Object>();
	

	public Goods getGood() {
		return good;
	}

	public void setGood(Goods good) {
		this.good = good;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public InStorage getInStorage() {
		return inStorage;
	}

	public void setInStorage(InStorage inStorage) {
		this.inStorage = inStorage;
	}

	public OutStorage getOutStorage() {
		return outStorage;
	}

	public void setOutStorage(OutStorage outStorage) {
		this.outStorage = outStorage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	
	public List<InStorage> getInsList() {
		return insList;
	}

	public void setInsList(List<InStorage> insList) {
		this.insList = insList;
	}

	public List<OutStorage> getOutList() {
		return outList;
	}

	public void setOutList(List<OutStorage> outList) {
		this.outList = outList;
	}

	@Action(value = "findGoodsByPage", results = { @Result(name = "success", location = "/pages/GoodsManager.jsp") })
	public String findGoodsByPage() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		int totalNum =goodsService.getTotalNum(good);
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
				
		goodsList=goodsService.findGoodsByPage(pageVo, good);
		for(Goods good:goodsList){
			GoodsVo goodVo=new GoodsVo();
			Handle.entity2Vo(good, goodVo);
			voList.add(goodVo);
		}
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(voList);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	@Action(value = "addGoods", results = { @Result(name = "success", location = "/pages/GoodsManager.jsp"),@Result(name="input",location="/pages/GoodsManager.jsp")  })
	public String addGoods() {		
		result = goodsService.addGoods(good, employee);
		if(result=="empnull"){
			addActionError("采购人编号不存在，请重新填写。");
			return INPUT;
		}
		return result;
	}
	
	@Action(value = "delGoods", results = { @Result(name = "success", location = "findGoodsByPage", type = "redirectAction") })
	public String delGoods() {
		result = goodsService.delGoods(good);
		return result;
	}
	
	@Action(value = "modifyGoods", results = { @Result(name = "success", location = "/pages/GoodsManager.jsp")})
	public String modifyCheckIn() {
		result = goodsService.updateGoods(good);
		/*if(result=="empnull"){
			addActionError("采购人编号不存在，请重新填写。");
			return INPUT;
		}*/
		return result;
	}
	
	@Action(value = "getGoodsByUuid", results = { @Result(name = "success", location = "/pages/GoodsManager.jsp") })
	public String getGoodsByUuid() {
		Goods gd = (Goods) goodsService.getGoodByUuid(good.getUuid());
		GoodsVo gdVo = new GoodsVo();
		Handle.entity2Vo(gd, gdVo);
		JSONObject object = ToJson.ToJsonObject(gdVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	@Action(value = "in_Storage", results = { @Result(name = "success", location = "/pages/GoodsManager.jsp") })
	public String in_Storage() {		
		result = goodsService.addIn_Storage(inStorage, employee, good);
		return result;
	}
	
	@Action(value = "out_Storage", results = { @Result(name = "success", location = "/pages/GoodsManager.jsp") })
	public String out_Storage() {		
		result = goodsService.addOut_Storage(outStorage, employee, good);
		return result;
	}
	
	@Action(value = "findInStorageByPage", results = { @Result(name = "success", location = "/pages/InStorage.jsp") })
	public String findInStorageByPage() {		
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		int totalNum =goodsService.getInStorage_TotalNum(inStorage, good);
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
				
		insList=goodsService.findInStorageByPage(pageVo, good, inStorage);
		for(InStorage ins:insList){
			InStorageVo insVo=new InStorageVo();
			Handle.entity2Vo(ins, insVo);
			voList.add(insVo);
		}
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(voList);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	@Action(value = "findOutStorageByPage", results = { @Result(name = "success", location = "/pages/OutStorage.jsp") })
	public String findOutStorageByPage() {		
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		int totalNum =goodsService.getOutStorage_TotalNum(outStorage, good);
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
				
		outList=goodsService.findOutStorageByPage(pageVo, good, outStorage);
		for(OutStorage out:outList){
			OutStorageVo outVo=new OutStorageVo();
			Handle.entity2Vo(out, outVo);
			voList.add(outVo);
		}
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(voList);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	@Action(value = "delInStorage", results = { @Result(name = "success", location = "findGoodsByPage", type = "redirectAction") })
	public String delInStorage() {
		result = goodsService.delInStorage(inStorage);
		return result;
	}
	
	@Action(value = "delOutStorage", results = { @Result(name = "success", location = "findGoodsByPage", type = "redirectAction") })
	public String delOutStorage() {
		result = goodsService.delOutStorage(outStorage);
		return result;
	}
	
	
	private void writeJson(String json) {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
}
