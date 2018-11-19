package com.resthome.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.apache.taglibs.standard.tag.common.core.ParamSupport;

















import com.opensymphony.xwork2.ActionSupport;
import com.panjun.handle.Handle;
import com.resthome.entity.Employee;
import com.resthome.entity.SystemMeta;
import com.resthome.service.EmployeeCertificateServiceInter;
import com.resthome.service.EmployeeServiceInter;
import com.resthome.service.SystemMetaServiceInter;
import com.resthome.utils.CheckNullUtil;
import com.resthome.utils.PageUtils;
import com.resthome.utils.ParamUtil;
import com.resthome.utils.ToJson;
import com.resthome.vo.EmployeeCertificateVo;
import com.resthome.vo.EmployeeVo;
/**
 * @author 潘军
 * EmployeeAction   
 * 2014年9月11日 上午9:29:21   
 * employee管理action
 */
import com.resthome.vo.PageVo;
import com.resthome.vo.SystemMetaVo;
@ParentPackage(value="abstract_struts")
@Namespace("/employee")
@Results({@Result(name="error",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings( { @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class EmployeeAction extends ActionSupport {
	private EmployeeVo employeeVo;
	private Employee employee;
	private String result = "error";//返回的结果
	private PageVo pageVo;
	private String imageSelect;//判断上传图片是身份证正面、反面，头像
	private File image;
	private String imageFileName;
	private String imageContentType;
	private String allowTypes;
	private String savePath;
	private EmployeeCertificateVo ecv;
	private String deptOrder = "department";
	private String postOrder = "workName";
	private String gradeOrder = "grade";
	private String deptOrderType = "asc";
	private String postOrderType = "asc";
	private String gradeOrderType = "asc";
	private SystemMetaVo systemMetaVo;
	private String json;
	
	
	@Resource
	private EmployeeServiceInter employeeService;
	@Resource
	private EmployeeCertificateServiceInter employeeCertificateService;
	@Resource
	private SystemMetaServiceInter systemMetaService;
	public EmployeeVo getEmployeeVo() {
		return employeeVo;
	}
	public void setEmployeeVo(EmployeeVo employeeVo) {
		this.employeeVo = employeeVo;
	}
	public PageVo getPageVo() {
		return pageVo;
	}
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	public String getImageSelect() {
		return imageSelect;
	}
	public void setImageSelect(String imageSelect) {
		this.imageSelect = imageSelect;
	}

	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public EmployeeCertificateVo getEcv() {
		return ecv;
	}
	public void setEcv(EmployeeCertificateVo ecv) {
		this.ecv = ecv;
	}
	public String getDeptOrder() {
		return deptOrder;
	}
	public void setDeptOrder(String deptOrder) {
		this.deptOrder = deptOrder;
	}
	public String getPostOrder() {
		return postOrder;
	}
	public void setPostOrder(String postOrder) {
		this.postOrder = postOrder;
	}
	public String getGradeOrder() {
		return gradeOrder;
	}
	public void setGradeOrder(String gradeOrder) {
		this.gradeOrder = gradeOrder;
	}
	public String getDeptOrderType() {
		return deptOrderType;
	}
	public void setDeptOrderType(String deptOrderType) {
		this.deptOrderType = deptOrderType;
	}
	public String getPostOrderType() {
		return postOrderType;
	}
	public void setPostOrderType(String postOrderType) {
		this.postOrderType = postOrderType;
	}
	public String getGradeOrderType() {
		return gradeOrderType;
	}
	public void setGradeOrderType(String gradeOrderType) {
		this.gradeOrderType = gradeOrderType;
	}
	public SystemMetaVo getSystemMetaVo() {
		return systemMetaVo;
	}
	public void setSystemMetaVo(SystemMetaVo systemMetaVo) {
		this.systemMetaVo = systemMetaVo;
	}
	
	
	@Action(value="delEmployee")
	public void delEmployee(){
		
		 result = employeeService.delEmployee(employee);
	}
	
	@Action(value="addEmployee")
	public void addEmployee(){
		employee = new Employee();
	    Handle.vo2Entity(employeeVo, employee);
	    result = employeeService.addEmployee(employee);

	    if(!result.equals("error")){
	      writeJson(result);
	    }
	    

		
	}
	@Action(value="findEmployeeByPage")
	public void findEmployeeByPage(){
		if(pageVo==null){
			pageVo = new PageVo();
			pageVo.setNowPage(1);
			
		}
		pageVo.setRowsPerPage(ParamUtil.rowsPerPage);
		Map<String,String> order = new HashMap<String,String>();
		order.put(deptOrder, deptOrderType);
		order.put(postOrder, postOrderType);
		order.put(gradeOrder,gradeOrderType);
		List<Object> empVoList = employeeService.findEmployeeByPage(pageVo, employeeVo,order);
	
		Integer totalPage = employeeService.getTotalPage(employeeVo);
		Integer totalNum = employeeService.getTotalNum(employeeVo);
	  
		pageVo.setListData(empVoList);
		pageVo.setTotalPage(totalPage);
		pageVo.setTotalNum(totalNum);
		pageVo.setPageLine(PageUtils.getPageLine(pageVo.getNowPage(), totalPage));
	    JSONObject jsonObject = ToJson.ToJsonObject(pageVo);
		this.writeJson(jsonObject.toString());
		
	}
	@Action(value="updateEmployee")
	public void updateEmployee(){
	    result = employeeService.updateEmployee(employeeVo);
	    this.writeJson(result);
		
	}
	@Action(value="addCertificate")
	public void addCertificate(){
		  result = employeeCertificateService.addEmployeeCertificate(ecv);
		  if(!result.equals("error")){
			 this.writeJson(result); 
		  }
	}
	@Action(value="delCertificate")
	public void delCertificate(){
		 result = employeeCertificateService.delEmployeeCertificate(ecv);
		 this.writeJson(result); 
	}
	@Action(value = "uploadImg", params = { "savePath", "/empImg",
			"allowTypes",
			"image/bmp,image/png,image/gif,image/jpeg,image/pjpeg" }, interceptorRefs = {
			@InterceptorRef(value = "fileUpload", params = { "maximumSize",
					"5000000" }), @InterceptorRef(value = "defaultStack") })
	public void uploadImg() {
		String imgPath = null;
		String returnPath = null;
		if (image != null) {
			String filterResult = filterType(getAllowTypes().split(","));
			if (filterResult != null) {
				return;
			}
			String newImgName = UUID.randomUUID().toString()
					+ imageFileName.substring(
							imageFileName.lastIndexOf("."),
							imageFileName.length());
			String path = ServletActionContext.getServletContext().getRealPath(
					getSavePath());
			imgPath = path + "/" + newImgName;
			//System.out.println(getSavePath());
			returnPath = getSavePath() + "/" + newImgName;
			switch (imageSelect) {
			case "idCardImageA":employeeVo.setIdCardImageA(getSavePath() + "/" + newImgName);break;
			case "idCardImageB":employeeVo.setIdCardImageB(getSavePath() + "/" + newImgName);break;
			case "headImage":employeeVo.setHeadImage(getSavePath() + "/" + newImgName);break;
			default:
				break;
			}
			
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(imgPath);
				FileInputStream fis = new FileInputStream(getImage());
				byte[] buffer = new byte[1024];
				int len = 0;
				try {
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					fos.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
					
					
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
				
			}
		}


		result = employeeService.updateEmployee(employeeVo);
	
		this.writeJson("{'msg':'success','imagePath':'"+returnPath+"'}");
		//return result;
	}
	@Action(value="findEmployee")
	public void findEmployee(){
		Employee emp = employeeService.getEmployee(employee);
		EmployeeCertificateVo ecv = new EmployeeCertificateVo();
		ecv.setEmpUuid(emp.getUuid());
		List<EmployeeCertificateVo> list = employeeCertificateService.getEmployeeCertificates(ecv);
		EmployeeVo empvo = new EmployeeVo();
		
		Handle.entity2Vo(emp, empvo);
		empvo.setEcvList(list);
		
		JSONObject jsonObject = ToJson.ToJsonObject(empvo);
		this.writeJson(jsonObject.toString());
		
	}
	@Action(value="findEmployeeOnly",results={@Result(name="success",location="/pages/bonus-page/resume/employeeCenter.jsp")})
	public String findEmployeeOnly(){
		employee = employeeService.getEmployee(employee);
		employeeVo = new EmployeeVo();
		Handle.entity2Vo(employee, employeeVo);
		return "success";
		
	}
	
	@Action(value="findSelectOption")
	public void findSelectOption(){
		SystemMeta systemMeta = new SystemMeta();
		Handle.vo2Entity(systemMetaVo, systemMeta);
		List<SystemMeta> systemMetas = systemMetaService.findSystemMetaByParent(systemMeta);
		List<Object> systemMetaVos = new ArrayList<Object>();
		for(SystemMeta s:systemMetas){
			SystemMetaVo sys = new SystemMetaVo();
			Handle.entity2Vo(s, sys);
			systemMetaVos.add(sys);
		}
		JSONArray jsonArray = ToJson.ToJsonArray(systemMetaVos);
		this.writeJson(jsonArray.toString());
		
	}
	
	
	@Action(value="findFamilyParam")
	public String familyParamFind(){
		json = employeeService.getFamilyParam();
		this.writeJson(json);
		return "success";
	}
	
	@Action(value="findEmergencyPeopleParam")
	public String emergencyPeopleFind(){
		json = employeeService.getEmergencyPeopleParam();
		this.writeJson(json);
		return "success";
	}
	
	@Action(value="findWorkExperienceParam")
	public String workExperienceFind(){
		json = employeeService.getWorkExperienceParam();
		this.writeJson(json);
		return "success";
	}
	
	@Action(value="findEducationExperienceParam")
	public String educationExperienceFind(){
		json = employeeService.getEducationExperienceParam();
		this.writeJson(json);
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
	private String filterType(String[] types) {
		String fileType = this.getImageContentType();
		for (String type : types) {
			if (type.equals(fileType)) {
				return null;
			}
		}
		return INPUT;
	}
	
	
	

}
