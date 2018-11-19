package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.resthome.entity.CommunityActivity;
import com.resthome.entity.Employee;
import com.resthome.entity.SystemMeta;
import com.resthome.service.CommunityActivityServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.CommunityActivityVo;
import com.resthome.vo.PageVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/communityActivity")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class CommunityActivityAction extends ActionSupport{
	
	@Resource 
	private CommunityActivityServiceInter communityActivityService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	
	private CommunityActivity comActivity;
	private Employee employee;
	private String result = "error";
	private PageVo pageVo;
	private List<CommunityActivity> comActivityList;
	private List<Object> volist = new ArrayList<Object>();
	public CommunityActivity getComActivity() {
		return comActivity;
	}
	public void setComActivity(CommunityActivity comActivity) {
		this.comActivity = comActivity;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
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
	public List<CommunityActivity> getComActivityList() {
		return comActivityList;
	}
	public void setComActivityList(List<CommunityActivity> comActivityList) {
		this.comActivityList = comActivityList;
	}
	
	
	@Action(value = "findCommunityActivityByPage", results = { @Result(name = "success", location = "/pages/CommunityActivity.jsp") })
	public String findCommunityActivityByPage() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		int totalNum=communityActivityService.getTotalNum(comActivity);
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
				
		comActivityList=communityActivityService.findCommunityActivityByPage(pageVo, comActivity);
		for(CommunityActivity com:comActivityList){
			CommunityActivityVo comVo=new CommunityActivityVo();
			Handle.entity2Vo(com, comVo);
			volist.add(comVo);
		}
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(volist);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	
	@Action(value = "addCommunityActivity", results = { @Result(name = "success", location = "/pages/CommunityActivity.jsp"),@Result(name="input",location="/pages/CommunityActivity.jsp") })
	public String addCommunityActivity() {	
		result=communityActivityService.addCommunityActivity(comActivity, employee);
		if(result=="empNull"){
			addActionError("负责人编号不存在，请重新输入。");
			return INPUT;
		}
		return result;
	}
	
	
	@Action(value = "delCommunityActivity", results = { @Result(name = "success", location = "findCommunityActivityByPage", type = "redirectAction") })
	public String delCommunityActivity() {
		result=communityActivityService.delCommunityActivity(comActivity);
		return result;
	}
	
	
	@Action(value = "modifyCommunityActivity", results = { @Result(name = "success", location = "/pages/CommunityActivity.jsp"),@Result(name="input",location="/pages/CommunityActivity.jsp") })
	public String modifyCommunityActivity() {
		result=communityActivityService.updateCommunityActivity(comActivity, employee);
		if(result=="empNull"){
			addActionError("负责人编号不存在，请重新输入。");
			return INPUT;
		}
		return result;
	}
	
	@Action(value = "getCommunityActivity", results = { @Result(name = "success", location = "/pages/CommunityActivity.jsp") })
	public String getCommunityActivity() {
		CommunityActivity com=communityActivityService.getCommunityActivity(comActivity);
		CommunityActivityVo comVo=new CommunityActivityVo();
		Handle.entity2Vo(com, comVo);
		JSONObject object = ToJson.ToJsonObject(comVo);
		this.writeJson(object.toString());
		return "success";
	}
	
	
	@Action(value = "findActivityTypes", results = { @Result(name = "success", location = "/pages/CommunityActivity.jsp") })
	public String findActivityTypes() {
		SystemMeta systemMeta = new SystemMeta();
		systemMeta.setParentMetaName("54");
		List<SystemMeta> systemMetas = systemMetaService
				.findSystemMetaByParent(systemMeta);
		List<Object> sysms = new ArrayList<Object>();
		for (SystemMeta s : systemMetas) {
			sysms.add(s);
		}
		JSONArray array = ToJson.ToJsonArray(sysms);
		this.writeJson(array.toString());
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

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
