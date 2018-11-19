package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ExceptionMapping;
import org.apache.struts2.convention.annotation.ExceptionMappings;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Results;

import com.resthome.entity.OldPeopleCheckRecord;
import com.resthome.entity.SystemMeta;
import com.resthome.service.OldPeopleCheckRecordServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.ToJson;

@ParentPackage(value="abstract_struts")
@Namespace("/oldPeopleCheckRecord")
@Results({@Result(name="error",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")})
public class OldPeopleCheckRecordAction {
	
	@Resource
	private OldPeopleCheckRecordServiceInter oldPeopleCheckRecordService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	private OldPeopleCheckRecord checkRecord;
	private String empNo;
	private String result="";
	
	public OldPeopleCheckRecord getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(OldPeopleCheckRecord checkRecord) {
		this.checkRecord = checkRecord;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	@Action(value="addOldPeopleCheckRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String addOldPeopleCheckRecord(){
		return oldPeopleCheckRecordService.addCheckRecord(checkRecord);
	}
	
	@Action(value="modifyOldPeopleCheckRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String modifyOldPeopleCheckRecord(){
		return oldPeopleCheckRecordService.modifyCheckRecord(checkRecord);
	}
	
	@Action(value="getOldPeopleCheckRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getOldPeopleCheckRecord(){
		result = oldPeopleCheckRecordService.getCheckRecord(checkRecord);
		this.writeJson(result);
		return "success";
	}
	
	@Action(value="delOldPeopleCheckRecord",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String delOldPeopleCheckRecord(){
		return oldPeopleCheckRecordService.delCheckRecord(checkRecord);
	}
	
	@Action(value="checkIsExistEmployee",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String checkIsExistEmployee(){
		result = oldPeopleCheckRecordService.isExistEmployee(empNo);
		if(result != null){
			this.writeJson(result);
		}
		return result;
	}
	
	@Action(value="getCheckResultByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCheckResultByParam(){
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
	
	@Action(value="getCheckInfoByParam",results={@Result(name="success",location="/pages/HealthInfo.jsp")})
	public String getCheckInfoByParam(){
		SystemMeta systemMeta=new SystemMeta();
		systemMeta.setParentMetaName("39");
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
