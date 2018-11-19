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
import com.resthome.entity.Goods;
import com.resthome.entity.Volunteer;
import com.resthome.service.VolunteerServiceInter;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.GoodsVo;
import com.resthome.vo.PageVo;
import com.resthome.vo.VolunteerVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/Volunteer")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class VolunteerAction extends ActionSupport{
	@Resource 
	VolunteerServiceInter volunteerService;
	
	private Volunteer volunteer;
	private String result = "error";
	private PageVo pageVo;
	private List<Volunteer> volunteerList;
	private List<Object> volist = new ArrayList<Object>();
	
	
	public Volunteer getVolunteer() {
		return volunteer;
	}
	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
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
	public List<Volunteer> getVolunteerList() {
		return volunteerList;
	}
	public void setVolunteerList(List<Volunteer> volunteerList) {
		this.volunteerList = volunteerList;
	}
	
	
	@Action(value = "findVolunteerByPage", results = { @Result(name = "success", location = "/pages/VolunteerManager.jsp") })
	public String findVolunteerByPage() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		
		int totalNum =volunteerService.getTotalNum(volunteer);
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
				
		volunteerList=volunteerService.findVolunteerByPage(pageVo, volunteer);
		for(Volunteer v:volunteerList){
			VolunteerVo vo=new VolunteerVo();
			Handle.entity2Vo(v, vo);
			volist.add(vo);
		}
		
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(volist);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	
	@Action(value = "addVolunteer", results = { @Result(name = "success", location = "/pages/VolunteerManager.jsp") })
	public String addVolunteer() {		
		result = volunteerService.addVolunteer(volunteer);
		return result;
	}
	
	@Action(value = "delVolunteer", results = { @Result(name = "success", location = "findVolunteerByPage", type = "redirectAction") })
	public String delVolunteer() {
		result = volunteerService.delVolunteer(volunteer);
		return result;
	}
	
	@Action(value = "modifyVolunteer", results = { @Result(name = "success", location = "/pages/VolunteerManager.jsp"),@Result(name="input",location="/pages/GoodsManager.jsp") })
	public String modifyVolunteer() {
		VolunteerVo vo=new VolunteerVo();
		Handle.entity2Vo(volunteer, vo);
		result = volunteerService.updateVolunteer(vo);		
		return result;
	}
	
	@Action(value = "getVolunteerBy", results = { @Result(name = "success", location = "/pages/VolunteerManager.jsp") })
	public String getVolunteerBy() {
		Volunteer vlt = volunteerService.getVolunteer(volunteer);
		VolunteerVo vo=new VolunteerVo();
		Handle.entity2Vo(vlt, vo);
		JSONObject object = ToJson.ToJsonObject(vo);
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

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	

}
