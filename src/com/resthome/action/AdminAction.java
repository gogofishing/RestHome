package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
import com.resthome.entity.Admin;
import com.resthome.entity.CheckIn;
import com.resthome.entity.Employee;
import com.resthome.entity.SystemMeta;
import com.resthome.service.AdminServiceInter;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.AdminVo;
import com.resthome.vo.CheckInVo;
import com.resthome.vo.PageVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/Admin")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class AdminAction extends ActionSupport{
	@Resource
	private AdminServiceInter adminService;
	
	private Admin admin;
	private String result = "error";
	private PageVo pageVo;
	private List<Admin> adminList;
	private List<Object> voList = new ArrayList<Object>();
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
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
	public List<Admin> getAdminList() {
		return adminList;
	}
	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}
	public List<Object> getVoList() {
		return voList;
	}
	public void setVoList(List<Object> voList) {
		this.voList = voList;
	}
	
	@Action(value = "login", results = {
			@Result(name = "success", location = "/pages/index.jsp"), @Result(name = "input", location = "/pages/login.jsp")})
	public String login() {
		HttpSession session=ServletActionContext.getRequest().getSession();
		
		if(admin==null||admin.getAdminName()==null||admin.getAdminName().equals("")){
			addActionError("账户名不能为空！");
			return INPUT;
		}
		if(admin==null||admin.getAdminPwd()==null||admin.getAdminPwd().equals("")){
			addActionError("密码不能为空！");
			return INPUT;
		}
		Admin ad=adminService.getAdminBy(admin);
		if(ad==null){
			addActionError("账户名不存在！");
			return INPUT;
		}else{
			if(!ad.getAdminPwd().equals(admin.getAdminPwd())){
				addActionError("密码错误!");
				return INPUT;
			}else{
				/*session.setAttribute("adminName", ad.getAdminName());
				session.setAttribute("adminUuid", ad.getUuid());
				session.setAttribute("identify", ad.getIdentify());*/
				session.setAttribute("admin", ad);
				result="success";
			}
		}
		return result;
	}

	
	@Action(value = "addAdmin", results = {
			@Result(name = "success", location = "/pages/AdminManager.jsp") })
	public String addAdmin() {
		result=adminService.addAdmin(admin);
		return result;
	}
	
	@Action(value = "delAdmin", results = { @Result(name = "success", location = "findAdminsByPage", type = "redirectAction") })
	public String delAdmin() {
		result = adminService.delAdmin(admin);
		return result;
	}

	@Action(value = "modifyAdmin", results = { @Result(name = "success", location = "/pages/AdminManager.jsp") })
	public String modifyAdmin() {
		result = adminService.modifyAdmin(admin);
		return result;
	}

	@Action(value = "findAdminsByPage", results = { @Result(name = "success", location = "/pages/CheckInManager.jsp") })
	public String findAdminsByPage() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);

		int totalNum =adminService.getTotalNum(pageVo, (admin==null||admin.getAdminName()==null||admin.getAdminName().equals(""))?null:admin.getAdminName());
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
		adminList =adminService.findAdminByPage(pageVo, (admin==null||admin.getAdminName()==null||admin.getAdminName().equals(""))?null:admin.getAdminName());
		for(Admin ad:adminList){
			AdminVo adVo=new AdminVo();
			Handle.entity2Vo(ad, adVo);
			voList.add(adVo);
		}
		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(voList);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}
	
	@Action(value = "getAdminByUuid", results = { @Result(name = "success", location = "/pages/ModifyCheckIn.jsp") })
	public String getAdminByUuid() {
		Admin ad=adminService.getAdminBy(admin);
		AdminVo adVo = new AdminVo();
		Handle.entity2Vo(ad, adVo);		
		JSONObject object = ToJson.ToJsonObject(adVo);
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
