<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("/RestHome/pages/login.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <base href="<%=basePath%>">
        <title>员工管理</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="" />
        <meta name="author" content="stilearning" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <jsp:include page="css.jsp"/>
        <link href="<%=basePath%>pages/css/datepicker.css" rel="stylesheet">
        <style type="text/css">
        .myCenter{
        text-align: center !important;
        }
        #certificate_add_div{
        float:center;
        }
        
        </style>
       
    </head>
    <body>
       <jsp:include page="Header.jsp"/>
      <span id="rootpath" style='display:none;'><%=basePath%></span>
        <section class="section">
            <div class="row-fluid">
        		<jsp:include page="LeftMenu.jsp"/>

                <div class="span9">
                	 <div class="content">
                  <%--   <li><a href="<%=basePath %>pages/OldPeople.jsp">员工管理</a><span class="divider">&rsaquo;</span></li><div class="content"> --%>
                        <div class="content-header">
                            <h2><i class="icofont-table"></i>员工管理</h2>
                        </div>
                        <div class="content-breadcrumb">
                            <ul class="breadcrumb">
                                <li><h6><a href="<%=basePath %>pages/index.jsp"><i class="icofont-home"></i> 主页</a>
                                 <span class="divider">&rsaquo;</span></h6></li>                                
                                <li><h6><a href="#">员工信息</a></h6></li>
                            </ul>
                        </div>
                        <div class="content-body">
                        <!-- ------------------------------------------------------------- -->
                        
                        <div id="employee_form_toolbar" class="row-fluid" style="display:none">
                        
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white color-silver-dark corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <span>请填员工信息(只有基本信息中带*号的为必填项，其余选填)</span>
                                        </div>
                                        <div class="box-body">
                                            <!--wizard-->
                                            <div id="form-horizontal">
                                                <form id="employee_form"  class="form-horizontal">
                                                	<fieldset>
                                                		<div class="row-fluid">
                                                			<div class="span12">
							                                  <!--box tab-->
							                                    <div class="box-tab corner-all">
							                                        <div class="box-header corner-top">
							                                            <ul class="nav nav-pills" id="employee_tab_ul">
							                                                <!--tab menus-->
							                                                <li class="active"><a data-toggle="tab" href="#boxtabpill-1">基本信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-2" >照片</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-3" >工作信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-4" >工资与保险</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-5" >家庭信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-6" >紧急联系人信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-7" >主要工作</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-8" >工作经历</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-9" >教育经历</a></li>
							                                                <li id="certificate" class="afterBase"><a data-toggle="tab" href="#boxtabpill-10" >资格信息</a></li>
							                                                <li id="final" style="display:none;">final</li>
							                                            </ul>
							                                        </div>
							                                        <div class="box-body">
							                                            <!-- widgets-tab-body -->
							                                            <div class="tab-content">
							                                                <div class="tab-pane fade active in" id="boxtabpill-1">
							                                                    <div class="control-group" style="display:none">
							                                                         <label class="control-label">员工uuid :</label>
							                                                         <div class="controls">							                                                            
							                                                              <input id="emp_uuid" type="text"  name="employeeVo.uuid" maxlength="15" class="grd-white" style="height:30px"/>
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">员工姓名：*</label>
							                                                         <div class="controls">
							                                                         	 <label id="employee_empName_Label" class="text-error helper-font-small" style="display:none">员工姓名不能为空！</label>
							                                                             <input type="text" id="emp_name" name="employeeVo.employeeName" maxlength="15" class="grd-white require" style="height:30px" />
							                                                         </div>
							                                                    </div>
																		<div class="control-group">
																			<label class="control-label">身份证号：*</label>
																			<div class="controls">
																				<label id="employee_idCard_Label"
																					class="text-error helper-font-small"
																					style="display:none">身份证号格式不正确，请重新输入。</label> <input
																					type="text" id="employee_idCard"
																					name="employeeVo.idCard" maxlength="18"
																					class="grd-white require" style="height:30px" />
																			</div>
																		</div>

																	<div class="control-group">
																			<label class="control-label">出生日期：</label>
																			<div class="controls">
																				<label id="employee_birthday_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label>
																				<div class="input-append date"
																					data-form="datepicker" data-date=""
																					data-date-format="yyyy-mm-dd">
																					<input readonly id="employee_birthday"
																						name="employeeVo.birthday" class="grd-white"
																						style="height:30px;disabled:disabled;" size="30"
																						type="text" value="" /> <span class="add-on"><i
																						class="icon-th"></i></span>
																				</div>
																			</div>
																		</div>


																		<div class="control-group">
						                                                         	<label class="control-label">性别：</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_sex_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                         	<input type="radio" name="employeeVo.sex" id="employee_sex_radios1" value="男"  checked=""/>男&nbsp;&nbsp;&nbsp;&nbsp;
							                                                            <input type="radio" name="employeeVo.sex" id="employee_sex_radios2" value="女" />女
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">民族：</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_nation_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <!--  <input type="text" id="employee_nation" name="employeeVo.nation" maxlength="15" class="grd-white" style="height:30px" />-->
							                                                             <select id="employee_nation" data-form="select2" name="employeeVo.nation" style="width:200px" data-placeholder="Your Favorite Type of Bear">
							                                                             <option value="汉族" selected="">汉族</option>
																						     <option value="蒙古族">蒙古族</option>
																						     <option value="回族">回族</option>
																						     <option value="藏族">藏族</option>
																						     <option value="维吾尔族">维吾尔族</option>
																						     <option value="维吾尔族">维吾尔族</option>
																						     <option value="苗族">苗族</option>
																						     <option value="彝族">彝族</option>
																						     <option value="壮族">壮族</option>
																						     <option value="布依族">布依族</option>
																						     <option value="朝鲜族">朝鲜族</option>
																						     <option value="满族">满族</option>
																						     <option value="侗族">侗族</option>
																						     <option value="瑶族">瑶族</option>
																						     <option value="白族">白族</option>
																						     <option value="土家族">土家族</option>
																						     <option value="哈尼族">哈尼族</option>
																						     <option value="哈萨克族">哈萨克族</option>
																						     <option value="傣族">傣族</option>
																						     <option value="黎族">黎族</option>
																						     <option value="傈僳族">傈僳族</option>
																						     <option value="佤族">佤族</option>
																						     <option value="畲族">畲族</option>
																						     <option value="高山族">高山族</option>
																						     <option value="拉祜族">拉祜族</option>
																						     <option value="水族">水族</option>
																						     <option value="东乡族">东乡族</option>
																						     <option value="纳西族">纳西族</option>
																						     <option value="景颇族">景颇族</option>
																						     <option value="柯尔克孜族">柯尔克孜族</option>
																						     <option value="土族">土族</option>
																						     <option value="达斡尔族">达斡尔族</option>
																						     <option value="仫佬族">仫佬族</option>
																						     <option value="羌族">羌族</option>
																						     <option value="布朗族">布朗族</option>
																						     <option value="撒拉族">撒拉族</option>
																						     <option value="毛南族">毛南族</option>
																						     <option value="仡佬族">仡佬族</option>
																						     <option value="锡伯族">锡伯族</option>
																						     <option value="阿昌族">阿昌族</option>
																						     <option value="普米族">普米族</option>
																						     <option value="塔吉克族">塔吉克族</option>
																						     <option value="怒族">怒族</option>
																						     <option value="乌孜别克族">乌孜别克族</option>
																						     <option value="俄罗斯族">俄罗斯族</option>
																						     <option value="鄂温克族">鄂温克族</option>
																						     <option value="德昂族">德昂族</option>
																						     <option value="保安族">保安族</option>
																						     <option value="裕固族">裕固族</option>
																						     <option value="京族">京族</option>
																						     <option value="塔塔尔族">塔塔尔族</option>
																						     <option value="独龙族">独龙族</option>
																						     <option value="鄂伦春族">鄂伦春族</option>
																						     <option value="赫哲族">赫哲族</option>
																						     <option value="门巴族">门巴族</option>
																						     <option value="珞巴族">珞巴族</option>
																						     <option value="基诺族">基诺族</option>
							                                                             </select>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">政治面貌：</label>
							                                                         <div class="controls">
							                                                        
							                                                         	<label id="employee_party_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <!--  <input type="text" id="employee_party" name="employeeVo.party" maxlength="25" class="grd-white" style="height:30px" />-->
							                                                            <select   id="employee_party" style="width:200px" name="employeeVo.party">
							                                                            </select>
							                                                        </div>
						                                                     	</div>
						                                                     	
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">婚姻情况：</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_marriage_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <!--  <input type="text" id="employee_marriage" name="employeeVo.marriage" maxlength="36" class="grd-white" style="height:30px" />-->
							                                                            <select id="employee_marriage" data-form="select2" name="employeeVo.marriage" style="width:200px" data-placeholder="Your Favorite Type of Bear">
							                                                            <option value="未婚">未婚</option>
							                                                             <option value="已婚">已婚</option>
							                                                              <option value="离异">离异</option>
							                                                               <option value="保密">保密</option>
							                                                            </select>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">移动电话：*</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_phone_Label" class="text-error helper-font-small" style="display:none">手机号格式不正确，请重新输入。</label>
							                                                            <input type="text" id="employee_phone" name="employeeVo.phone" maxlength="20" class="grd-white require" style="height:30px" />
							                                                        </div>
						                                                     	</div>
						                                                     	
						                                                     	
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">家庭电话：</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_homeTel_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="employee_homeTel" name="employeeVo.homeTel" maxlength="20" class="grd-white" style="height:30px" />
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">E-Mail：</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_email_Label" class="text-error helper-font-small" style="display:none">邮箱格式不正确,请重新输入。</label>
							                                                            <input type="text" id="employee_email" name="employeeVo.email" maxlength="40" class="grd-white" style="height:30px" />
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">家庭地址：</label>
							                                                         <div class="controls">
							                                                         	<label id="employee_address_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="employee_address" name="employeeVo.address" maxlength="11" class="grd-white require" style="height:30px" />
							                                                        </div>
						                                                     	</div>
						                                                     	
							                                                </div>
							                                                
							                                             <!-- --------------------------------- -->
																	<div class="tab-pane" id="boxtabpill-2">
																		<div class="control-group" id="image_divA">
																			<label class="control-label" for="inputAuto">身份证照正面</label>
																			<div class="controls" id="image_div1">
																			<img id="imageA" alt="身份证照正面" src="<%=basePath%>img/home_png_icon-24.png" width="120px" height="160px">
																				<input type="file" id="file1" class="grd-white"
																					name="image" style="height:30px;"> <a
																					class="btn upload_but" id="f1"> <span>上传</span>
																				</a>
																				<!--  <input type="button" value="提交" id="idcardImageA_but" class="upload_but">-->
																			</div>
																		</div>
                                                                    <hr color='#626262'>
																		<div class="control-group" id="image_divB">
																			<label class="control-label" for="minlength">身份证照反面</label>
																			<div class="controls" id="image_div2">
																			<img id="imageB" alt="身份证照反面" src="<%=basePath%>img/home_png_icon-24.png" width="120px" height="160px">
																				<input type="file" class="grd-white" name="image"
																					id="file2">
																				<a
																					class="btn upload_but" id="f2"> <span>上传</span>
																				</a>
																			</div>

																		</div>
                                                                       <hr color='#626262'>
																		<div class="control-group" id="image_divC">
																			<label class="control-label" for="maxlength">头像</label>
																			<div class="controls" id="image_div3">
																			<img id="imageC" alt="头像" src="<%=basePath%>img/home_png_icon-27.png" width="120px" height="160px">
																				<input type="file" class="grd-white" name="image"
																					id="file3">
																				<a
																					class="btn upload_but" id="f3"> <span>上传</span>
																				</a>
																			</div>

																		</div>





																	</div>
																	<!-- ----------------------------------------- -->
							                                            
							                                                   <div class="tab-pane" id="boxtabpill-3">
							                                                    <div class="control-group">
							                                                         <label class="control-label">员工编号：</label>
							                                                         <div class="controls">
							                                                            <input readonly type="text" id="employee_no" name="employeeVo.employeeNo" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                     <div class="control-group">
							                                                         <label class="control-label">所属部门：</label>
							                                                         <div class="controls">
							                                                            <!--  <input  type="text" id="employee_department" name="employeeVo.department" maxlength="11" class="grd-white" style="height:30px" />-->
							                                                              <select id="employee_department" data-form="select2" name="employeeVo.department" style="width:200px" data-placeholder="Your Favorite Type of Bear">
							                                                              </select>
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">工作：</label>
							                                                         <div class="controls">
							                                                           <!--   <input type="text" id="employee_workName" name="employeeVo.workName" maxlength="11" class="grd-white" style="height:30px" />-->
							                                                         <select id="employee_workName" data-form="select2" name="employeeVo.workName" style="width:200px" data-placeholder="Your Favorite Type of Bear">
							                                                              </select>
							                                                         </div>
							                                                    </div>
							                                                   
							                                                    <div class="control-group">
							                                                         <label class="control-label">等级：</label>
							                                                         <div class="controls">
							                                                            <input  type="text" id="employee_grade" name="employeeVo.grade" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <!--  
							                                                    <div class="control-group">
							                                                         <label class="control-label">开始工作时间：</label>
							                                                         <div class="controls">
							                                                            <input  type="text" id="employee_beginWorkTime" name="employeeVo.beginWorkTime" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    -->
							                                                    <div class="control-group">
																			<label class="control-label">开始工作时间：</label>
																			<div class="controls">
																				
																				<div class="input-append date"
																					data-form="datepicker" data-date=""
																					data-date-format="yyyy-mm-dd">
																					<input readonly id="employee_beginWorkTime"
																						name="employeeVo.beginWorkTime" class="grd-white"
																						style="height:30px;disabled:disabled;" size="30"
																						type="text" value="" /> <span class="add-on"><i
																						class="icon-th"></i></span>
																				</div>
																			</div>
																		</div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">工作状态：</label>
							                                                         <div class="controls">
							                                                            <input type="text" id="employee_workStatus" name="employeeVo.workStatus" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    </div>
							                                                    <!-- ---------------------------------------- -->
							                                                    
							                                                   <div class="tab-pane" id="boxtabpill-4">
							                                                    <div class="control-group">
							                                                         <label class="control-label">基本工资：</label>
							                                                         <div class="controls">
							                                                           <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input  type="text" id="employee_basicSalary" name="employeeVo.basicSalary" value="0.0" maxlength="11" class="grd-white shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                     <div class="control-group">
							                                                         <label class="control-label">岗位工资：</label>
							                                                         <div class="controls">
							                                                         <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input  type="text" id="employee_positionSalary" name="employeeVo.positionSalary" value="0.0" maxlength="11" class="grd-white shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">保险基数上限：</label>
							                                                         <div class="controls">
							                                                         <label id="baoxianupdown" class="text-error helper-font-small updown" style="display:none"></label>
							                                                          <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input type="text" id="employee_baoxianjishuUp" name="employeeVo.baoxianjishuUp" value="0.0" maxlength="11" class="grd-white jishu shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">保险基数下限：</label>
							                                                         <div class="controls">
							                                                         <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input  type="text" id="employee_baoxianjishuDown" name="employeeVo.baoxianjishuDown"  value="0.0" maxlength="11" class="grd-white jishu shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">保险基数：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                         <label id="employee_baoxianjishu_Label" class="text-error helper-font-small" style="display:none">保险基数不正确</label>
							                                                            <input  type="text" id="employee_baoxianjishu" name="employeeVo.baoxianjishu" maxlength="11" value="0.0" class="grd-white jishu shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">住房基数上限：</label>
							                                                         <div class="controls">
							                                                         <label id="zhufangupdown" class="text-error helper-font-small updown" style="display:none"></label>
							                                                          <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input  type="text" id="employee_zhufangjishuUp" name="employeeVo.zhufangjishuUp" value="0.0" maxlength="11" class="grd-white jishu shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">住房基数下限：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input type="text" id="employee_zhufangjishuDown" name="employeeVo.zhufangjishuDown" value="0.0" maxlength="11" class="grd-white jishu shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">住房基数：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                          <label id="employee_zhufangjishu_Label" class="text-error helper-font-small" style="display:none">住房基数不正确</label>
							                                                            <input type="text" id="employee_zhufangjishu" name="employeeVo.zhufangjishu" value="0.0" maxlength="11" class="grd-white jishu shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">保险补偿金：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small shuzhilabel" style="display:none"></label>
							                                                            <input type="text" id="employee_baoxianbuchangjin" name="employeeVo.baoxianbuchangjin" value="0.0" maxlength="11" class="grd-white buchangjin shuzhi" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                </div>
							                                                <!-- -------------------------------------------- -->
							                                                 <div class="tab-pane" id="boxtabpill-5">
							                                                     <div class="control-group">
							                                                       <script id="familyHtml" name="employeeVo.familyHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
							                                                        <!-- <input type="hidden" id="familyHtmlSource" name="employeeVo.familyHtmlSource">  -->
							                                                    </div>
							                                                 </div>
							                                                <!-- -------------------------------------------- -->
							                                               <div class="tab-pane" id="boxtabpill-6">
							                                                     <div class="control-group">
							                                                       <script id="emergencyPeopleHtml" name="employeeVo.emergencyPeopleHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
							                                                        <!-- <input type="hidden" id="emergencyPeopleHtmlSource" name="employeeVo.emergencyPeopleHtmlSource">  -->
							                                                    </div>
							                                                 </div>
							                                                   <!-- -------------------------------------------- -->
							                                               <div class="tab-pane" id="boxtabpill-7">
							                                                     <div class="control-group">
							                                                       <script id="mianWorkHtml" name="content" type="text/plain">主要工作内容</script>
							                                                        <input type="hidden" id="mianWorkHtmlSource" name="employeeVo.mianWorkHtmlSource"> 
							                                                    </div>
							                                                 </div>
							                                                   <!-- -------------------------------------------- -->
							                                               <div class="tab-pane" id="boxtabpill-8">
							                                                     <div class="control-group">
							                                                       <script id="workExperienceHtml" name="employeeVo.workExperienceHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
							                                                        <!-- <input type="hidden" id="workExperienceHtmlSource" name="employeeVo.workExperienceHtmlSource">  -->
							                                                    </div>
							                                                 </div>
							                                                 <!-- -------------------------------------------- -->
							                                               <div class="tab-pane" id="boxtabpill-9">
							                                                     <div class="control-group">
							                                                       <script id="educationExperienceHtml" name="employeeVo.educationExperienceHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
							                                                        <!-- <input type="hidden" id="educationExperienceHtmlSource" name="employeeVo.educationExperienceHtmlSource">  -->
							                                                    </div>
							                                                 </div>
							                                                   <!-- -------------------------------------------- -->
							                                                    <div class="tab-pane" id="boxtabpill-10">

																		<div id="certificate_div" class="invoice-table" style="display:none;">
																			<table class="table table-bordered invoice responsive">
																				<thead>
																					<tr>
																						<th style="display:none;">uuid</th>
																						<th>证书名</th>
																						<th>证书编号</th>
																						<th>证书级别</th>
																						<th>颁发机构</th>
																						<th>获得时间</th>
																						<th>过期时间</th>
																						<th>状态</th>
																						<th colspan="2">操作</th>
																					</tr>
																				</thead>
																				<tbody id="certificate_tbody">
																					


																				</tbody>
																				
																			</table>
																			
																		</div>
																		
																		
																		<div class="control-group" style="display:none;">
																			<label class="control-label">uuid：</label>
																			<div class="controls">
																				<label class="text-error helper-font-small"
																					style="display:none"></label> <input type="text"
																					id="ecv_empUuid" name="ecv.empUuid"
																					maxlength="11" class="grd-white"
																					style="height:30px" />
																			</div>
																		</div>




																		<div class="control-group">
																			<label class="control-label">证书名：</label>
																			<div class="controls">
																				<label class="text-error helper-font-small"
																					style="display:none"></label> <input type="text"
																					id="certificateName" name="ecv.certificateName"
																					maxlength="11" class="grd-white"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
							                                                         <label class="control-label">证书编号：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="certificateNo" name="ecv.certificateNo" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">证书级别：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="certificateGrade" name="ecv.certificateGrade" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                     <div class="control-group">
							                                                         <label class="control-label">颁发机构：</label>
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="publishPart" name="ecv.publishPart" maxlength="11" class="grd-white" style="height:30px" />
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
						                                                         	<label class="control-label">获得时间：</label>
							                                                         <div class="controls">
							                                                         	<label id="ecv_getTime_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                         	<div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
							                                                                <input readonly id="getTime" name="ecv.getTime" class="grd-white" style="height:30px;disabled:disabled;" size="30" type="text" value=""/>
							                                                                <span class="add-on"><i class="icon-th"></i></span>
							                                                            </div>
							                                                        </div>
						                                                     	</div>
							                                                     <div class="control-group">
						                                                         	<label class="control-label">有效期至：</label>
							                                                         <div class="controls">
							                                                         	<label id="ecv_getTime_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                         	<div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
							                                                                <input readonly id="endTime" name="ecv.endTime" class="grd-white" style="height:30px;disabled:disabled;" size="30" type="text" value=""/>
							                                                                <span class="add-on"><i class="icon-th"></i></span>
							                                                            </div>
							                                                        </div>
						                                                     	</div>
							                                                    <div class="control-group">
							                                                         
							                                                         <div class="controls">
							                                                          <label  class="text-error helper-font-small" style="display:none"></label>
							                                                         <button type="button" id="certificate_add" class="btn btn-primary">添加</button>
							                                                         </div>
							                                                    </div>
                                                                            



																	</div>
							                                                <!-- ---------------------------------------- -->
							                                                   
							                                            </div><!--/widgets-tab-body-->
							                                        </div>
							                                    </div><!--/box tab-->
						                                        <div class="form-actions">
			                                                    	<button type="button" id="employee_submit" class="btn btn-success">保存</button>
			                                                    	<!-- <button type="button" id="finsh" class="btn btn-primary">完成</button> -->
			                                                        <button type="button" id="employee_modify" class="btn btn-primary">修改</button>
			                                                        <button type="button" id="employee_cancel" class="btn">取消</button>
			                                                    </div>
							                                </div><!--/span-->
                                                		</div>
                                                    </fieldset>
                                                </form>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div> 
                            </div>
                          	
                        
                        <!-- --------------------------------------------------------- -->
                         <div class="row-fluid" id="employee_table_toolbar">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <a class="btn btn-info" id="employee_add">
												<span>新增员工</span>
											</a>
                                        </div>
                                        <div class="box-body">
                                         
                                        	<div class='row-fluid'>
                                        		
                                                         
	                                                    	员工编号：<input type="text" id="empNo" maxlength="20" class="grd-white" style="height:30px" />&nbsp;
	                                                    	员工姓名：<input type="text" id="empName" maxlength="20"  class="grd-white"style="height:30px" />&nbsp;
	                                                    	员工部门：<input type="text" id="empDept" maxlength="20" class="grd-white" style="height:30px" /><br>
	                                                    	员工工作：<input type="text" id="empWork" maxlength="20" class="grd-white" style="height:30px" />&nbsp;
	                                                    	员工等级：<input type="text" id="empGrade" maxlength="20" class="grd-white" style="height:30px" />&nbsp;&nbsp;
	                                                    	<!-- <button id="employee_search">查找</button> -->
	                                                    	 <a class="btn btn-info" id="employee_search">
												             <span>搜索</span>
											                 </a>
                                                 
                                        		
												
											</div>
											
                                           <table id="datatablestools"
													class="table table-hover responsive dataTable"
													aria-describedby="datatablestools_info">
													<thead>
												 
														<tr role="row">
															<th style="width: 186px;display:none;">uuid</th>
															<th style="width: 186px;">员工编号</th>
															<th style="width: 205px;">姓名</th>
															<th id="dept_sort" class="" role="columnheader" tabindex="0"
																aria-controls="datatablestools" rowspan="1" colspan="1"
																aria-label="Platform(s): activate to sort column ascending"
																style="width: 230px;">部门</th>
															<th id="post_sort" class="" role="columnheader" tabindex="0"
																aria-controls="datatablestools" rowspan="1" colspan="1"
																aria-label="Platform(s): activate to sort column ascending"
																style="width: 230px;">工作</th>
															<th id="grade_sort" class="" role="columnheader" tabindex="0"
																aria-controls="datatablestools" rowspan="1" colspan="1"
																aria-label="Engine version: activate to sort column ascending"
																style="width: 155px;">等级</th>

															<th style="width: 155px;">操作</th>
														</tr>
											
										</thead>

													<tbody id="empList" role="alert" aria-live="polite"
														aria-relevant="all">


													</tbody>
												</table>
	                                        <div class='row-fluid'>
	                                         	<div class="span6">
	                                         		<div id="emp_paging" class="dataTables_paginate paging_bootstrap pagination">
	                                       				<ul>
	                                       				
	                                       				</ul>
                                        			</div>
	                                			</div>
	                                			<div class="span5">
										<p ><span>总共<a id="totalNum"></a>条记录</span></p>
										</div>
	                                        </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
              
               <%-- <jsp:include page="Right.jsp"/> --%>
            </div>
        </section>
       
        
        </div>
        
        <jsp:include page="Footer.jsp"/>
		 
		<jsp:include page="Js.jsp"/>
		<script type="text/javascript" src="<%=basePath %>pages/js/datepicker/bootstrap-datepicker.js"></script>
	    <script src="<%=basePath%>pages/js/ajaxfileupload.js" type="text/javascript"></script>
	    <script type="text/javascript" src="<%=basePath %>pages/js/employee/employeeManager.js"></script>
	    <script type="text/javascript">
	   // $(function(){
	        //上传图片
	        /*
	        $("#idcardImageA_but").click(function() {
	        	alert(123);
	        	var empUuid = $('#emp_uuid').val();
	        	alert(empUuid);
	                ajaxFileUpload(empUuid);
	        });
	        */
	     //});
	  
 	
 	    </script>
	 
	    <script type="text/javascript" src="<%=basePath%>ueditor/ueditor.config.js"></script>    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=basePath%>ueditor/ueditor.all.js"></script>
    
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
    var familyHtmlUe = UE.getEditor('familyHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                             ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight'],
                                                             [ 'inserttable','deletetable','splittocells','splittorows', 
                                                         	  'deleterow', 'splittocols',
                                                         	  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']	
                                                         ], initialFrameHeight:250});
    var emergencyPeopleHtmlUe = UE.getEditor('emergencyPeopleHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                             ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight'],
                                                             [ 'inserttable','deletetable','splittocells','splittorows', 
                                         					  'deleterow', 'splittocols',
                                         					  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
                                                         ], initialFrameHeight:250});
    var mianWorkHtmlUe = UE.getEditor('mianWorkHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                                               ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight'],
                                                                               [ 'inserttable','deletetable','splittocells','splittorows', 
                                                                           	  'deleterow', 'splittocols',
                                                                           	  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
                                                                           ], initialFrameHeight:250});
    var workExperienceHtmlUe = UE.getEditor('workExperienceHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                                 ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight'],
                                                                 [ 'inserttable','deletetable','splittocells','splittorows', 
                                                             	  'deleterow', 'splittocols',
                                                             	  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
                                                             ], initialFrameHeight:250});
   
    var  educationExperienceHtmlUe = UE.getEditor('educationExperienceHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                                             ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight'],
                                                                             [ 'inserttable','deletetable','splittocells','splittorows', 
                                                                         	  'deleterow', 'splittocols',
                                                                         	  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
                                                                         ], initialFrameHeight:250});
    </script>
	  
    </body>
</html>
