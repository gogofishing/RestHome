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
import com.resthome.entity.SystemMeta;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.service.TakeMedicineRecordServiceInter;
import com.resthome.vo.TakeMedicineRecordVo;

@ParentPackage(value="abstract_struts")
@Namespace("/takeMedicineRecord")
@Results({@Result(name="success",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings({@ExceptionMapping(exception = "java.lang.RuntimeException", result = "error")})
public class TakeMedicineRecordAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1391284904893808263L;
	
	@Resource
	private TakeMedicineRecordServiceInter takeMedicineRecordService;
	
	@Resource
	private SystemMetaServiceInter systemMetaService;
	private String uuid;
	private TakeMedicineRecordVo tmrVo;
	private String oldPeopleNo;
	private String oldPeopleName;
	private int nowPage;
	private String json;
	
	public TakeMedicineRecordVo getTmrVo() {
		return tmrVo;
	}
	public void setTmrVo(TakeMedicineRecordVo tmrVo) {
		this.tmrVo = tmrVo;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOldPeopleNo() {
		return oldPeopleNo;
	}
	public void setOldPeopleNo(String oldPeopleNo) {
		this.oldPeopleNo = oldPeopleNo;
	}
	public String getOldPeopleName() {
		return oldPeopleName;
	}
	public void setOldPeopleName(String oldPeopleName) {
		this.oldPeopleName = oldPeopleName;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}

	@Action(value="addTakeMedicineRecord",results={@Result(name="success",location="/pages/500.jsp")})
	public String taskMedicineRecordAdd(){
		return "success";
	}
	
	@Action(value="deleteTakeMedicineRecord",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String taskMedicineRecordDelete(){
		json = takeMedicineRecordService.deleteTakeMedicineRecord(uuid);
		return "success";
	}
	
	@Action(value="modifyTakeMedicineRecord",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String taskMedicineRecordModify(){
		json = takeMedicineRecordService.modifyTakeMedicineRecord(tmrVo);
		return "success";
	}

	@Action(value="findAllByPage",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String allByPageFind(){
		json = takeMedicineRecordService.getAllByPage(nowPage, oldPeopleNo, oldPeopleName);
		return "success";
	}
	
	@Action(value="findMoreInfo",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String moreInfoFind(){
		json = takeMedicineRecordService.getMoreInfo(uuid);
		return "success";
	}
	
	@Action(value="isExistOldPeople",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String isExistOldPeople(){
		json = takeMedicineRecordService.getOldPeopleByNo(oldPeopleNo);
		return "success";
	}
	
	@Action(value="addOne",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String takeMedicineRecordAdd(){
		json = takeMedicineRecordService.addTakeMedicineRecord(tmrVo);
		return "success";
	}
	
	@Action(value="queryAllByPage",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String allTakeMedicineRecordQuery(){
		json = takeMedicineRecordService.getAllByPage(nowPage,oldPeopleNo,oldPeopleName);
		return "success";
	}
	
	@Action(value="findTakeMedicineRecord",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String takeMedicineRecordFind(){
		json = takeMedicineRecordService.getTakeMedicineRecord(uuid);
		return "success";
	}
	
	@Action(value="findTakeTimes",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String takeTimesFind(){
		json = takeMedicineRecordService.getTakeTimes();
		return "success";
	}

}
