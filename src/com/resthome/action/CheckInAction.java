package com.resthome.action;

import java.io.IOException;
import java.util.ArrayList;
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
import com.resthome.entity.Employee;
import com.resthome.entity.SystemMeta;
import com.resthome.service.CheckInServiceInter;
import com.resthome.service.EmployeeServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.GetPageLine;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.CheckInVo;
import com.resthome.vo.PageVo;

@ParentPackage(value = "abstract_struts")
@Namespace("/CheckIn")
@Results({ @Result(name = "error", location = "/pages/500.jsp") })
@InterceptorRefs({ @InterceptorRef("myInterceptor") })
@ExceptionMappings({ @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class CheckInAction extends ActionSupport {

	@Resource
	private CheckInServiceInter checkInService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	@Resource
	private EmployeeServiceInter employeeService;

	private CheckIn checkIn;
	private Employee employee;
	private String result = "error";
	private PageVo pageVo;
	private List<CheckIn> checkInList;
	private List<Object> ckVoList = new ArrayList<Object>();
	private Map map = new HashMap<String, Object>();

	public CheckIn getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(CheckIn checkIn) {
		this.checkIn = checkIn;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public List<CheckIn> getCheckInList() {
		return checkInList;
	}

	public void setCheckInList(List<CheckIn> checkInList) {
		this.checkInList = checkInList;
	}

	@Action(value = "addCheckIn", results = {
			@Result(name = "success", location = "/pages/CheckInManager.jsp"),
			@Result(name = "120", location = "/pages/AddCheckIn.jsp") })
	public String addCheckIn() {
		if (employee != null) {
			Employee emp = employeeService.getEmployee(employee);
			if (emp != null) {
				result = checkInService.addCheckIn(checkIn, emp);
			} else {
				result = "120"; // 员工不存在
				addActionError("员工不存在！");
			}

		}

		return result;
	}

	@Action(value = "delCheckIn", results = { @Result(name = "success", location = "findCheckInsByPage", type = "redirectAction") })
	public String delCheckIn() {
		result = checkInService.delCheckIn(checkIn);
		return result;
	}

	@Action(value = "modifyCheckIn", results = { @Result(name = "success", location = "/pages/CheckInManager.jsp") })
	public String modifyCheckIn() {
		result = checkInService.modifyCheckIn(checkIn);
		return result;
	}

	@Action(value = "findCheckInsByPage", results = { @Result(name = "success", location = "/pages/CheckInManager.jsp") })
	public String findCheckInsByPage() {
		if (pageVo == null) {
			pageVo = new PageVo();
			pageVo.setNowPage(1);
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);

		int totalNum = checkInService
				.getTotalNum(
						pageVo,
						(checkIn == null
								|| checkIn.getEmployee() == null
								|| checkIn.getEmployee().getEmployeeNo() == null || checkIn
								.getEmployee().getEmployeeNo().equals("")) ? null
								: checkIn.getEmployee().getEmployeeNo(),
						(checkIn == null || checkIn.getBeginDate() == null || checkIn
								.getBeginDate().equals("")) ? null : checkIn
								.getBeginDate(),
						(checkIn == null || checkIn.getEndDate() == null || checkIn
								.getEndDate().equals("")) ? null : checkIn
								.getEndDate());
		int totalPage = totalNum % pageVo.getRowsPerPage() == 0 ? totalNum
				/ pageVo.getRowsPerPage() : totalNum / pageVo.getRowsPerPage()
				+ 1;
		checkInList = checkInService
				.findCheckInByPage(
						pageVo,
						(checkIn == null
								|| checkIn.getEmployee() == null
								|| checkIn.getEmployee().getEmployeeNo() == null || checkIn
								.getEmployee().getEmployeeNo().equals("")) ? null
								: checkIn.getEmployee().getEmployeeNo(),
						(checkIn == null || checkIn.getBeginDate() == null || checkIn
								.getBeginDate().equals("")) ? null : checkIn
								.getBeginDate(),
						(checkIn == null || checkIn.getEndDate() == null || checkIn
								.getEndDate().equals("")) ? null : checkIn
								.getEndDate());
		for (CheckIn ck : checkInList) {
			CheckInVo cv = new CheckInVo();
			Handle.entity2Vo(ck, cv);
			ckVoList.add(cv);
		}

		pageVo.setTotalNum(totalNum);
		pageVo.setTotalPage(totalPage);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
		pageVo.setListData(ckVoList);
		JSONObject object = ToJson.ToJsonObject(pageVo);
		this.writeJson(object.toString());

		return "success";
	}

	@Action(value = "getCheckInByUuid", results = { @Result(name = "success", location = "/pages/ModifyCheckIn.jsp") })
	public String getCheckInByUuid() {
		CheckIn ck = (CheckIn) checkInService.getCheckInByUuid(checkIn
				.getUuid());
		CheckInVo cVo = new CheckInVo();
		Handle.entity2Vo(ck, cVo);
		SystemMeta systemMeta = new SystemMeta();
		systemMeta.setParentMetaName("6");
		List<SystemMeta> systemMetas = systemMetaService
				.findSystemMetaByParent(systemMeta);
		
		map.put("cVo", cVo);
		map.put("sysms", systemMetas);
		JSONObject object = ToJson.ToJsonObject(map);
		this.writeJson(object.toString());

		return "success";
	}

	@Action(value = "findReasonTypes", results = { @Result(name = "success", location = "/pages/AddCheckIn.jsp") })
	public String findReasonTypes() {
		SystemMeta systemMeta = new SystemMeta();
		systemMeta.setParentMetaName("6");
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
