package com.resthome.action;

import javax.annotation.Resource;

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
import com.resthome.service.OldPeopleOutInRecordServiceInter;
import com.resthome.vo.OldPeopleOutInRecordVo;

@ParentPackage(value="abstract_struts")
@Namespace(value="/oldPeopleOutInRecord")
@Results({@Result(name="error",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings({@ExceptionMapping(exception="java.lang.RuntimeException",result="error")})
public class OldPeopleOutInRecordAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1356687943289270040L;
	
	@Resource
	private OldPeopleOutInRecordServiceInter oldPeopleOutInRecordService;
	private int nowPage;
	private String oldPeopleName;
	private String oldPeopleNo;
	private String json;
	private String empNo;
	private OldPeopleOutInRecordVo oldPeopleOutInRecordVo;
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public OldPeopleOutInRecordVo getOldPeopleOutInRecordVo() {
		return oldPeopleOutInRecordVo;
	}
	public void setOldPeopleOutInRecordVo(
			OldPeopleOutInRecordVo oldPeopleOutInRecordVo) {
		this.oldPeopleOutInRecordVo = oldPeopleOutInRecordVo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
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
	public String getOldPeopleNo() {
		return oldPeopleNo;
	}
	public void setOldPeopleNo(String oldPeopleNo) {
		this.oldPeopleNo = oldPeopleNo;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}



	@Action(value="queryAllByPage",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String allOldPeopleOutInRecordFind(){
		json = oldPeopleOutInRecordService.getAllByPage(nowPage, oldPeopleNo, oldPeopleName);
		return "success";
	}
	
	@Action(value="isExistOldPeople",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String isExistOldPeople(){
		json = oldPeopleOutInRecordService.getOldPeopleByNo(oldPeopleNo);
		return "success";
	}
	@Action(value="isExistEmployee",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String isExistEmployee(){
		json = oldPeopleOutInRecordService.getEmployeeByNo(empNo);
		return "success";
	}
	
	@Action(value="addOldPeopleOutInRecord",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String oldPeopleOutInRecordAdd(){
		json = oldPeopleOutInRecordService.addOldPeopleOutInRecord(oldPeopleOutInRecordVo);
		return "success";
	}
	
	@Action(value="deleteOldPeopleOutInRecord",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String oldPeopleOutInRecordDelete(){
		json = oldPeopleOutInRecordService.deleteOldPeopleOutInRecord(uuid);
		return "success";
	}
	
	
	@Action(value="findMoreInfo",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String oldPeopleOutInRecordMoreInfo(){
		json = oldPeopleOutInRecordService.getMoreInfo(uuid);
		return "success";
	}
	
	
	
	
	
	
	
	
	
	

}
