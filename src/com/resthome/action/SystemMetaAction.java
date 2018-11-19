package com.resthome.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
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
import com.resthome.entity.CheckIn;
import com.resthome.entity.SystemMeta;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.PageVo;
/**
 * @author 潘军
 * SystemMetaAction   
 * 2014年9月13日 下午4:18:08   
 */
import com.resthome.vo.SystemMetaVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/SystemMeta")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class SystemMetaAction extends ActionSupport {
	private SystemMetaVo systemMetaVo;
	private PageVo pageVo;
	

	@Resource
	private SystemMetaServiceInter systemMetaService;

	public SystemMetaVo getSystemMetaVo() {
		return systemMetaVo;
	}

	public void setSystemMetaVo(SystemMetaVo systemMetaVo) {
		this.systemMetaVo = systemMetaVo;
	}

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	
	
	@Action(value = "addSystemMetaType", results = { @Result(name = "success", location = "/pages/SystemMetaTypeManager.jsp") , @Result(name = "120" , location = "/pages/SystemMetaTypeManager.jsp")})
	public String addSystemMetaType() {
		String result=null;
		systemMetaVo.setMetaValueType("SYSTEMMETA_TYPE");			
		
		SystemMeta s=systemMetaService.findSystemMeta(systemMetaVo);
		if(s==null){
			systemMetaService.addSystemMeta(systemMetaVo);
			result="success";									
		}else{
			addActionError("该系统参数名称已存在！请重新输入！");		//系统参数名称已存在
			result="120";	
		}
		
		return result;
	}
	

	@Action(value = "addSystemMeta", results = { @Result(name = "success", location = "/pages/SystemMetaManager.jsp") })
	public String addSystemMeta() {		
		SystemMeta sys=systemMetaService.findSystemMeta(systemMetaVo);
		if(sys!=null){
			systemMetaVo.setUuid(null);
			systemMetaVo.setChildMetaName(sys.getParentMetaName());
			systemMetaVo.setMetaValueType("SYSTEMMETA_PARENT");
			systemMetaVo.setParentMetaName(Integer.toString(systemMetaService.queryMaxPn(sys)+1));
		}else{			
			systemMetaVo=null;
		}
		if(systemMetaVo!=null){
			systemMetaService.addSystemMeta(systemMetaVo);
			return "success";
		}else{
			return "error";
		}		
		
		
	}
	
	
	@Action(value = "addChildSystemMeta", results = { @Result(name = "success", location = "/pages/SystemMetaManager.jsp") })
	public String addChildSystemMeta() {
		SystemMeta sys=systemMetaService.findSystemMeta(systemMetaVo);
		if(sys!=null){
			systemMetaVo.setUuid(null);
			systemMetaVo.setChildMetaName(sys.getParentMetaName());
			systemMetaVo.setMetaValueType("SYSTEMMETA_CHILD");
			systemMetaVo.setListOrder(Integer.toString((systemMetaService.queryMaxOrder(sys)+1)));
		}else{
			systemMetaVo=null;
		}
		if(systemMetaVo!=null){
			systemMetaService.addSystemMeta(systemMetaVo);	
			return "success";
		}else{
			return "error";
		}
		
	}	
	
	

	@Action(value = "delSystemMeta", results = { @Result(name = "success", location = "/pages/SystemMetaManager.jsp") })
	public String delSystemMeta() {
		systemMetaService.delSystemMeta(systemMetaVo);
		this.writeJson("1");
		return "success";
	}
	
	@Action(value = "modifySystemMeta", results = { @Result(name = "success", location = "/pages/index.jsp") })
	public String modifySystemMeta() {
		//System.out.println(systemMetaVo.getUuid()+"=="+systemMetaVo.getParentMetaName());
		systemMetaService.updateSystemMeta(systemMetaVo);
		this.writeJson("1");
		return "success";
	}
	
	
	
	@Action(value = "findSystemMetaTypeList",results={@Result(name="success",location="/pages/SystemMetaManager.jsp")})
	public String findSystemMetaTypeList() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		systemMetaVo=new SystemMetaVo();
		systemMetaVo.setMetaValueType("SYSTEMMETA_TYPE");		
			
		int totalNum=systemMetaService.getTotalNum(pageVo, systemMetaVo);
		int totalPage = totalNum%pageVo.getRowsPerPage()==0?totalNum/pageVo.getRowsPerPage():totalNum/pageVo.getRowsPerPage()+1;
		List<Object> systemMetaVoList = systemMetaService.findSystemMetaByPage(
				pageVo, systemMetaVo);
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(systemMetaVoList);
		
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());
		return "success";
	}
	
	

	@Action(value = "findSystemMetaList",results={@Result(name="success",location="/pages/SystemMetaManager.jsp")})
	public String findSystemMetaList() {	
		
		SystemMeta sys=systemMetaService.findSystemMeta(systemMetaVo);
		if(sys!=null){
			systemMetaVo.setUuid(null);
			systemMetaVo.setChildMetaName(sys.getParentMetaName());
			systemMetaVo.setMetaValueType("SYSTEMMETA_PARENT");
		}else{
			systemMetaVo=null;
		}
		
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);		
			
		int totalNum=systemMetaService.getTotalNum(pageVo, systemMetaVo);
		int totalPage = totalNum%pageVo.getRowsPerPage()==0?totalNum/pageVo.getRowsPerPage():totalNum/pageVo.getRowsPerPage()+1;
		List<Object> systemMetaVoList = systemMetaService.findSystemMetaByPage(
				pageVo, systemMetaVo);
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(systemMetaVoList);
		
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());	
	
		return "success";
	}
	
	
	
	@Action(value = "findChildSystemMetaList",results={@Result(name="success",location="/pages/SystemMetaManager.jsp")})
	public String findChildSystemMetaList() {	
		
		SystemMeta sys=systemMetaService.findSystemMeta(systemMetaVo);
		if(sys!=null){
			systemMetaVo.setUuid(null);
			systemMetaVo.setChildMetaName(sys.getParentMetaName());
			systemMetaVo.setMetaValueType("SYSTEMMETA_CHILD");
		}else{
			systemMetaVo=null;
		}
		
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);		

		int totalNum=systemMetaService.getTotalNum(pageVo, systemMetaVo);
		int totalPage = totalNum%pageVo.getRowsPerPage()==0?totalNum/pageVo.getRowsPerPage():totalNum/pageVo.getRowsPerPage()+1;
		List<Object> systemMetaVoList = systemMetaService.findSystemMetaByPage(
				pageVo, systemMetaVo);
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));		
		pageVo.setListData(systemMetaVoList);	
		
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());	
	
		return "success";
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

}
