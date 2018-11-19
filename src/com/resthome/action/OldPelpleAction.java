package com.resthome.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.annotation.Resource;

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
import com.resthome.entity.OldPeople;
import com.resthome.service.OldPeopleServiceInter;

@ParentPackage(value="abstract_struts")
@Namespace("/oldPeople")
@Results({@Result(name="error",location="/pages/500.jsp")})
@InterceptorRefs({@InterceptorRef("myInterceptor")})
@ExceptionMappings( { @ExceptionMapping(exception = "java.lang.RuntimeException", result = "error") })
public class OldPelpleAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7051534608999141446L;
	
	
	@Resource
	private OldPeopleServiceInter oldPeopleService;
	
	private File headImage;
	private String headImageContentType;
	private String headImageFileName;
	private String savePath;
	private String allowTypes;
	private OldPeople oldPeople;
	private String json;
	private int nowPage;
	private String opUuid;	
	private String oldPeopleName;	
	private String oldPeopleNo;	
	private String empNo;
	
	
	
	
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
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
	public String getOpUuid() {
		return opUuid;
	}
	public void setOpUuid(String opUuid) {
		this.opUuid = opUuid;
	}
	public File getHeadImage() {
		return headImage;
	}
	public void setHeadImage(File headImage) {
		this.headImage = headImage;
	}
	public String getHeadImageContentType() {
		return headImageContentType;
	}
	public void setHeadImageContentType(String headImageContentType) {
		this.headImageContentType = headImageContentType;
	}
	public String getHeadImageFileName() {
		return headImageFileName;
	}
	public void setHeadImageFileName(String headImageFileName) {
		this.headImageFileName = headImageFileName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}
	public OldPeople getOldPeople() {
		return oldPeople;
	}
	public void setOldPeople(OldPeople oldPeople) {
		this.oldPeople = oldPeople;
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


	@Action(value="addOldPeople",
			params={"savePath","/uploadImg","allowTypes","image/bmp,image/png,image/gif,image/jpeg,image/pjpeg",},
			interceptorRefs={@InterceptorRef(value="fileUpload",params={"maximumSize","5000000"}),@InterceptorRef(value="defaultStack")},
			results={@Result(name="success",type="redirect",location="/pages/OldPeople.jsp"),@Result(name="input",location="/pages/OldPeople.jsp")})
	public String OldPeopleAdd(){
		String filterResult = filterType(getAllowTypes().split(","));
		if(filterResult != null){
			addFieldError("headImage","您 要上传的图片类型不正确!(bmp/png/gif/jpeg/pjpeg)");
			//return filterResult;
		}
		String uuid = UUID.randomUUID().toString();
		String newName = uuid+headImageFileName.substring(headImageFileName.lastIndexOf("."));
		String parentPath = ServletActionContext.getServletContext().getRealPath(getSavePath());
		String picPath = parentPath+"/"+newName;
		File file=new File(parentPath);
		if(!file.exists()){
			file.mkdir();
		}
		FileOutputStream fos;
		FileInputStream fis;
		try {
			fos = new FileOutputStream(picPath);
			fis = new FileInputStream(getHeadImage());
			byte[] buffer = new byte[1024];
			int len = 0;
			try {
				while((len = fis.read(buffer))>0){
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "error";
		}
		setHeadImageFileName(newName);
		String dbPicPath = getSavePath()+"/"+newName;
		String result = oldPeopleService.addOldPeople(oldPeople,uuid,dbPicPath);
		if(result == "null"){
			addActionError("老人基本信息部分带*号的不能为空，请填写。");
			return INPUT;
		}else if(result == "empnull1"){
			addActionError("看护人1*编号不存在，请重新填写。。。");
			return INPUT;
		}else if(result == "empnull2"){
			addActionError("看护人2*编号不存在，请重新填写。。。");
			return INPUT;
		}
		return result;
	}

	public String filterType(String[] types){
		String fileType = getHeadImageContentType();
		for(String type : types){
			if(type.equals(fileType)){
				return null;
			}
		}
		return INPUT;
	}
	
	@Action(value="queryAllByPage",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String AllOldPeopleFind(){
		json = oldPeopleService.getAllByPage(nowPage,oldPeopleName,oldPeopleNo);
		return "success";
	}
	
	@Action(value="deleteOldPeople",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String OldPeopleDelete(){
		json = oldPeopleService.deleteOldPeopleByUuid(opUuid);
		return "success";
	}
	
	@Action(value="getOldPeople",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String OldPeopleGet(){
		json = oldPeopleService.getOldPeopleByUuid(opUuid);
		return "success";
	}
	
	@Action(value="getMoreInfo",results={@Result(name="success",location="/pages/bonus-page/resume/personalCenter.jsp"),@Result(name="input",location="/pages/OldPeople.jsp")})
	public String OldPeopleGetMoreInfo(){
		this.oldPeople = oldPeopleService.getOldPeopleMoreInfo(opUuid);
		if(null==oldPeople){
			addActionError("服务器繁忙，请稍后重试。。。");
			return INPUT;
		}
		return "success";
	}
	
	@Action(value="modifyOldPeople",
			params={"savePath","/uploadImg","allowTypes","image/bmp,image/png,image/gif,image/jpeg,image/pjpeg",},
			interceptorRefs={@InterceptorRef(value="fileUpload",params={"maximumSize","5000000"}),@InterceptorRef(value="defaultStack")},
			results={@Result(name="success",location="/pages/OldPeople.jsp"),@Result(name="input",location="/pages/OldPeople.jsp")})
	public String OldPeopleModify(){
		String result;
		if(headImage!=null){
			String filterResult = filterType(getAllowTypes().split(","));
			if(filterResult != null){
				addFieldError("headImage","您 要上传的图片类型不正确!(bmp/png/gif/jpeg/pjpeg)");
				return filterResult;
			}
			String uuid = UUID.randomUUID().toString();
			String newName = uuid+headImageFileName.substring(headImageFileName.lastIndexOf("."));
			String parentPath = ServletActionContext.getServletContext().getRealPath(getSavePath());
			String picPath = parentPath+"/"+newName;
			File file=new File(parentPath);
			if(!file.exists()){
				file.mkdir();
			}
			FileOutputStream fos;
			FileInputStream fis;
			try {
				fos = new FileOutputStream(picPath);
				fis = new FileInputStream(getHeadImage());
				byte[] buffer = new byte[1024];
				int len = 0;
				try {
					while((len = fis.read(buffer))>0){
						fos.write(buffer, 0, len);
					}
					fos.close();
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
					return "error";
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "error";
			}
			setHeadImageFileName(newName);
			String dbPicPath = getSavePath()+"/"+newName;
			result = oldPeopleService.modifyOldPeople(oldPeople,dbPicPath);
		}else{
			result = oldPeopleService.modifyOldPeople(oldPeople,null);
		}
		if(result == "null"){
			addActionError("老人基本信息部分带*号的不能为空，请填写。");
			return INPUT;
		}else if(result == "empnull1"){
			addActionError("看护人1*编号不存在，请重新填写。。。");
			return INPUT;
		}else if(result == "empnull2"){
			addActionError("看护人2*编号不存在，请重新填写。。。");
			return INPUT;
		}
		return "success";
	}
	
	
	@Action(value="isExistEmp",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String isExistEmployee(){
		json = oldPeopleService.isExistEmployee(empNo);
		return "success";
	}
	
	@Action(value="findGrade",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String gradeFind(){
		json = oldPeopleService.getGrade();
		return "success";
	}
	
	@Action(value="findMarriage",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String marriageFind(){
		json = oldPeopleService.getMarriage();
		return "success";
	}
	
	@Action(value="findParty",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String partyFind(){
		json = oldPeopleService.getParty();
		return "success";
	}
	
	@Action(value="findFamilyParam",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String familyParamFind(){
		json = oldPeopleService.getFamilyParam();
		return "success";
	}
	
	@Action(value="findEmergencyPeopleParam",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String emergencyPeopleFind(){
		json = oldPeopleService.getEmergencyPeopleParam();
		return "success";
	}
	
	@Action(value="findMoreHtmlSourceParam",results={@Result(name="success",location="/pages/showJsonUtil.jsp")})
	public String moreHtmlSourceParamFind(){
		json = oldPeopleService.getMoreHtmlSourceParam();
		return "success";
	}
	
	
	
	
	
	
	
	

}
