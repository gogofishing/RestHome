package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

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
import com.resthome.entity.HealthInfo;
import com.resthome.entity.OldPeople;
import com.resthome.entity.SystemMeta;
import com.resthome.service.OldPeopleHealthInfoServiceInter;
import com.resthome.service.OldPeopleServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.ToJson;
import com.resthome.vo.PageVo;

@ParentPackage(value="abstract_struts")
@Namespace("/HealthInfo")
@Results({@Result(name="error",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException",result = "error") })
public class OldPeopleHealthInfoAction extends ActionSupport{
	@Resource
	private OldPeopleHealthInfoServiceInter oldPeopleHealthInfoService;
	@Resource
	private OldPeopleServiceInter oldPeopleService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	private HealthInfo healthinfo;
	private OldPeople oldPeople;
	private String oldPeopleName;
	private PageVo pagevo;
	private String result="";
	private int nowPage;
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getOldPeopleName() {
		return oldPeopleName;
	}
	public void setOldPeopleName(String oldPeopleName) {
		this.oldPeopleName = oldPeopleName;
	}
	
	public HealthInfo getHealthinfo() {
		return healthinfo;
	}
	public void setHealthinfo(HealthInfo healthinfo) {
		this.healthinfo = healthinfo;
	}
	
	public OldPeople getOldPeople() {
		return oldPeople;
	}
	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}
	@Action(value="addOldPeopleHealthInfo",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String addOldPeopleHealthInfo(){
		result=oldPeopleHealthInfoService.addHealthInfo(healthinfo);
		/*if(result.trim().equals("success")){
			this.writeJson("1");
		}else{
			this.writeJson("-1");
		}*/
		return result;
	}
	
	@Action(value="getHealthInfo",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getHealthInfo(){
		result=oldPeopleHealthInfoService.getHealthInfo(healthinfo.getOldPeople().getUuid());
		this.writeJson(result);
		return "success";
	}
	@Action(value="getAllHealthInfo",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String getAllHealthInfo(){
		result=oldPeopleHealthInfoService.getAllHealthInfo(healthinfo, pagevo);
		this.writeJson(result);
		return "success";
	}
	
	@Action(value="modifyOldPeopleHealthInfo",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String modifyOldPeopleHealthInfo(){
		result=oldPeopleHealthInfoService.modifyHealthInfo(healthinfo);
		return result;
	}
	
	@Action(value="delHealthInfo",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String delHealthInfo(){
		result=oldPeopleHealthInfoService.delHealthInfo(healthinfo.getUuid());
		return result;
	}
	@Action(value="queryAllPeopleByPage",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String queryAllPeopleByPage(){
		result = oldPeopleService.getAllByPage(nowPage,oldPeopleName,null);
		this.writeJson(result);
		System.out.println(result);
		return "success";
	}
	@Action(value="getCureInfoHtmlSourceByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCureInfoHtmlSourceByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("32");
		List<SystemMeta> systemMetas=systemMetaService.findSystemMetaByParent(systemMeta);
		List <Object> obj = new ArrayList<Object>();
		obj.addAll(systemMetas);
		JSONArray ja = ToJson.ToJsonArray(obj);
		
		System.out.println(ja.toString());
		this.writeJson(ja.toString());
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
