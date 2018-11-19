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
import com.resthome.entity.Employee;
import com.resthome.entity.OldPeople;
import com.resthome.entity.SystemMeta;
import com.resthome.entity.Volunteer;
import com.resthome.entity.VolunteerActivity;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.service.VolunteerActivityServiceInter;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.PageVo;
import com.resthome.vo.VolunteerActivityVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/volunteerActivity")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class VolunteerActivityAction extends ActionSupport{
	
	@Resource 
	private VolunteerActivityServiceInter volunteerActivityService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	
	private VolunteerActivity vltActivity;
	private Volunteer volunteer;
	private OldPeople oldPeople;
	private Employee employee;
	private String result = "error";
	private PageVo pageVo;
	private List<VolunteerActivity> vltActivityList;
	private List<Object> volist = new ArrayList<Object>();

	
	public Volunteer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}

	public OldPeople getOldPeople() {
		return oldPeople;
	}

	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public VolunteerActivity getVltActivity() {
		return vltActivity;
	}

	public void setVltActivity(VolunteerActivity vltActivity) {
		this.vltActivity = vltActivity;
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

	public List<VolunteerActivity> getVltActivityList() {
		return vltActivityList;
	}

	public void setVltActivityList(List<VolunteerActivity> vltActivityList) {
		this.vltActivityList = vltActivityList;
	}
	
	
	@Action(value = "findVolunteerActivityByPage", results = { @Result(name = "success", location = "/pages/VolunteerActivity.jsp") })
	public String findVolunteerActivityByPage() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		int totalNum=volunteerActivityService.getTotalNum(vltActivity,volunteer);
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
				
		vltActivityList=volunteerActivityService.findVolunteerActivityByPage(pageVo,volunteer, vltActivity);
		for(VolunteerActivity va:vltActivityList){
			VolunteerActivityVo vavo=new VolunteerActivityVo();
			Handle.entity2Vo(va, vavo);
			volist.add(vavo);
		}
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(volist);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	
	@Action(value = "addVolunteerActivity", results = { @Result(name = "success", location = "/pages/VolunteerActivity.jsp"),@Result(name="input",location="/pages/VolunteerActivity.jsp") })
	public String addVolunteerActivity() {	
		result=volunteerActivityService.addVolunteerActivity(vltActivity, volunteer, oldPeople, employee);
		if(result=="vltNull"){
			addActionError("志愿者编号不存在，请重新输入。");
			return INPUT;
		}else if(result=="oldNull"){
			addActionError("老人编号不存在，请重新输入。");
			return INPUT;
		}else if(result=="empNull"){
			addActionError("负责人编号不存在，请重新输入。");
			return INPUT;
		}
		return result;
		
		
	}
	
	@Action(value = "delVolunteerActivity", results = { @Result(name = "success", location = "findVolunteerActivityByPage", type = "redirectAction") })
	public String delVolunteerActivity() {
		result=volunteerActivityService.delVolunteerActivity(vltActivity);
		return result;
	}
	
	@Action(value = "modifyVolunteerActivity", results = { @Result(name = "success", location = "/pages/VolunteerActivity.jsp"),@Result(name="input",location="/pages/VolunteerActivity.jsp") })
	public String modifyVolunteerActivity() {
		result=volunteerActivityService.updateVolunteerActivity(vltActivity,volunteer, oldPeople, employee);
		if(result=="oldNull"){
			addActionError("老人编号不存在，请重新输入。");
			return INPUT;
		}else if(result=="empNull"){
			addActionError("负责人编号不存在，请重新输入。");
			return INPUT;
		}
		return result;
	}
	
	@Action(value = "getVolunteerActivityByUuid", results = { @Result(name = "success", location = "/pages/VolunteerActivity.jsp") })
	public String getVolunteerActivityByUuid() {
		VolunteerActivity va=volunteerActivityService.getVolunteerActivity(vltActivity);
		VolunteerActivityVo vavo=new VolunteerActivityVo();
		Handle.entity2Vo(va, vavo);
		JSONObject object = ToJson.ToJsonObject(vavo);
		this.writeJson(object.toString());
		return "success";
	}
	
	@Action(value = "findActivityTypes", results = { @Result(name = "success", location = "/pages/VolunteerActivity.jsp") })
	public String findActivityTypes() {
		SystemMeta systemMeta = new SystemMeta();
		systemMeta.setParentMetaName("53");
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
