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

import com.resthome.entity.OldPeople;
import com.resthome.entity.SickRecord;
import com.resthome.entity.SystemMeta;
import com.resthome.service.SickRecordServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.ToJson;

@ParentPackage(value="abstract_struts")
@Namespace("/sickRecord")
@Results({@Result(name="error",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")})
public class SickRecordAction {
	
	@Resource
	private SickRecordServiceInter sickRecordService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	private SickRecord sickRecord;
	private OldPeople oldPeople;
	private String result="";
	
	public SickRecord getSickRecord() {
		return sickRecord;
	}

	public void setSickRecord(SickRecord sickRecord) {
		this.sickRecord = sickRecord;
	}

	public OldPeople getOldPeople() {
		return oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}


	@Action(value="addSickRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String addSickRecord(){
		
		return sickRecordService.addSickRecord(sickRecord);
	}
	
	@Action(value="getOldPeopleSickRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getOldPeopleSickRecord(){
		result = sickRecordService.getSickReord(sickRecord);
		this.writeJson(result);
//		System.out.println(result);
		return "success";
	}
	
	@Action(value="modifySickRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String modifySickRecord(){
		return sickRecordService.modifySickRecord(sickRecord);
	}
	
	@Action(value="delSickRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String delSickRecord(){
		return sickRecordService.delSickRecord(sickRecord);
	}
	
	@Action(value="getSickTypeByCombobox",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getSickTypeByCombobox(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("11");
		List<SystemMeta> systemMetas=systemMetaService.findSystemMetaByParent(systemMeta);
		List <Object> obj = new ArrayList<Object>();
		obj.addAll(systemMetas);
		JSONArray ja = ToJson.ToJsonArray(obj);
		System.out.println(ja.toString());
		
		this.writeJson(ja.toString());
		return "success";
		
	}
	@Action(value="getSickReasonByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getSickReasonByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("33");
		List<SystemMeta> systemMetas=systemMetaService.findSystemMetaByParent(systemMeta);
		List <Object> obj = new ArrayList<Object>();
		obj.addAll(systemMetas);
		JSONArray ja = ToJson.ToJsonArray(obj);
		System.out.println(ja.toString());
		
		this.writeJson(ja.toString());
		return "success";
	}
	@Action(value="getCureInfoByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCureInfoByParam(){
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
	
	@Action(value="getCarePeopleInfoByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCarePeopleInfoByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("35");
		List<SystemMeta> systemMetas=systemMetaService.findSystemMetaByParent(systemMeta);
		List <Object> obj = new ArrayList<Object>();
		obj.addAll(systemMetas);
		JSONArray ja = ToJson.ToJsonArray(obj);
		System.out.println(ja.toString());
		
		this.writeJson(ja.toString());
		return "success";
	}
	
	@Action(value="getCheckProjectInfoByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCheckProjectInfoByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("36");
		List<SystemMeta> systemMetas=systemMetaService.findSystemMetaByParent(systemMeta);
		List <Object> obj = new ArrayList<Object>();
		obj.addAll(systemMetas);
		JSONArray ja = ToJson.ToJsonArray(obj);
		System.out.println(ja.toString());
		
		this.writeJson(ja.toString());
		return "success";
	}
	
	@Action(value="getCheckInfoByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCheckInfoByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("38");
		List<SystemMeta> systemMetas=systemMetaService.findSystemMetaByParent(systemMeta);
		List <Object> obj = new ArrayList<Object>();
		obj.addAll(systemMetas);
		JSONArray ja = ToJson.ToJsonArray(obj);
		System.out.println(ja.toString());
		
		this.writeJson(ja.toString());
		return "success";
	}
	
	@Action(value="getCheckResulByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCheckResulByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("37");
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
