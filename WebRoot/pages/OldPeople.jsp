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
        <title>老人管理</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="" />
        <meta name="author" content="stilearning" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <jsp:include page="css.jsp"/>
        <link href="<%=basePath%>pages/css/datepicker.css" rel="stylesheet">
        <link href="<%=basePath%>pages/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    </head>
    <body>
       <jsp:include page="Header.jsp"/>
       
        <section class="section">
            <div class="row-fluid">
        		<jsp:include page="LeftMenu.jsp"/>

                <div class="span9">
                    <div class="content">
                        <div class="content-header">
                            <h2><i class="icofont-table"></i>老人管理</h2>
                        </div>
                        <div class="content-breadcrumb">
                            <ul class="breadcrumb">
                                <li><h6><a href="<%=basePath %>pages/index.jsp"><i class="icofont-home"></i> 主页</a>
                                 <span class="divider">&rsaquo;</span></h6></li>                                
                                <li><h6><a href="<%=basePath %>pages/OldPeople.jsp">老人信息</a></h6></li>
                            </ul>
                        </div>
                        <div class="content-body">
                         	<div id="oldPeople_showMessage" class="label label-warning" style="display:none;font-size: 14px;font-weight: bold;"></div>
                        	<s:fielderror cssStyle="color:red"/>
                        	<s:actionerror cssStyle="color:red"/>
                        	<div id="oldPeople_form_toolbar" class="row-fluid" style="display:none">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white color-silver-dark corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <span>请填老人信息(只有基本信息中带*号的为必填项，其余选填)</span>
                                        </div>
                                        <div class="box-body">
                                            <!--wizard-->
                                            <div id="form-horizontal">
                                                <form id="oldPeople_form" method="post" action="" class="form-horizontal" enctype="multipart/form-data">
                                                	<fieldset>
                                                		<div class="row-fluid">
                                                			<div class="span12">
							                                  <!--box tab-->
							                                    <div class="box-tab corner-all">
							                                        <div class="box-header corner-top">
							                                            <ul class="nav nav-pills" id="oldPeople_tab_ul">
							                                                <!--tab menus-->
							                                                <li class="active"><a data-toggle="tab" href="#boxtabpill-1">基本信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-2">常见病</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-3">生活习惯</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-4">工作信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-5">爱好</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-6">地址信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-7">家庭信息</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-8">紧急联系地址</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-9">附加项</a></li>
							                                                <li class="afterBase"><a data-toggle="tab" href="#boxtabpill-10">紧急联系人</a></li>
							                                            </ul>
							                                        </div>
							                                        <div class="box-body">
							                                            <!-- widgets-tab-body -->
							                                            <div class="tab-content">
							                                                <div class="tab-pane fade active in" id="boxtabpill-1">
							                                                    <div class="control-group" style="display:none">
							                                                         <label class="control-label">老人uuid：</label>
							                                                         <div class="controls">
							                                                             <input type="text" id="oldPeople_uuid" name="oldPeople.uuid" class="grd-white" style="height:30px;"/>
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
							                                                         <label class="control-label">老人姓名：*</label>
							                                                         <div class="controls">
							                                                         	 <label id="oldPeople_opName_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                             <input type="text" id="oldPeople_opName" name="oldPeople.oldPeopleName" maxlength="15" class="grd-white" style="height:30px;"/>
							                                                         </div>
							                                                    </div>
							                                                    <div class="control-group">
						                                                         	<label class="control-label">身份证号：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_idCard_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_idCard" name="oldPeople.idCard" maxlength="18" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">房间号：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_roomNo_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_roomNo" name="oldPeople.roomNo" maxlength="50" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">床位号：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_bedNo_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_bedNo" name="oldPeople.bedNo" maxlength="50" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
							                                                    	<label class="control-label">等级：*</label>
						                                                            <div class="controls">
						                                                            	<label id="oldPeople_grade_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                                <select id="oldPeople_grade" data-form="select2" name="oldPeople.grade" style="width:200px" data-placeholder="Your Favorite Type of Bear">
						                                                                </select>
						                                                            </div>
							                                                    </div>
							                                                    <div class="control-group">
						                                                         	<label class="control-label">照片：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_headImage_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<input type="file" id="oldPeople_headImage" name="headImage" />
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">看护人1编号：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_empNo1_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_empNo1" name="oldPeople.employeeByCarePeople1Uuid.employeeNo" maxlength="25" class="grd-white" style="height:30px;" onblur="checkExistEmp(this.value,document.getElementById('oldPeople_empNo1_Label'))" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="return false" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">看护人2编号：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_empNo2_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_empNo2" name="oldPeople.employeeByCarePeople2Uuid.employeeNo" maxlength="25" class="grd-white" style="height:30px;" onblur="checkExistEmp(this.value,document.getElementById('oldPeople_empNo2_Label'))" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="return false" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">出生日期：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_birthday_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                         	<div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
							                                                                <input id="oldPeople_birthday" name="oldPeople.birthday" class="grd-white" style="height:30px;disabled:disabled;" size="16" type="text" />
							                                                                <span class="add-on"><i class="icon-th"></i></span>
							                                                            </div>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">性别：*</label>
							                                                        <div class="controls">
							                                                         	<label id="oldPeople_sex_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                         	<input type="radio" name="oldPeople.sex" id="oldPeople_sex_radios1" value="男" checked="" />男&nbsp;&nbsp;&nbsp;&nbsp;
							                                                            <input type="radio" name="oldPeople.sex" id="oldPeople_sex_radios2" value="女" />女
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                     		<label class="control-label">民族：*</label>
							                                                        <div class="controls">
							                                                        	<label id="oldPeople_nation_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                                <select id="oldPeople_nation" data-form="select2" name="oldPeople.nation" style="width:200px" data-placeholder="Your Favorite Type of Bear">
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
						                                                         	<label class="control-label">政治面貌：*</label>
							                                                        <div class="controls">
							                                                        	<label id="oldPeople_party_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                                <select id="oldPeople_party" data-form="select2" name="oldPeople.party" style="width:200px" onchange="comboboxEvent(this,$('#oldPeople_party_other'))">
						                                                                </select>
						                                                                <input type="text" id="oldPeople_party_other" maxlength="20"  style="height:30px;display:none;" onchange="lostFocusGlobal(this)"/>
						                                                            </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">结婚情况：*</label>
							                                                        <div class="controls">
							                                                        	<label id="oldPeople_marriage_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                                <select id="oldPeople_marriage" data-form="select2" name="oldPeople.marriage" style="width:200px">
						                                                                </select>
						                                                            </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">移动电话：*</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_phone_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_phone" name="oldPeople.phone" maxlength="20" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	
						                                                     	
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">家庭电话：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_homeTel_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_homeTel" name="oldPeople.homeTel" maxlength="20" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">E-Mail：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_email_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_email" name="oldPeople.email" maxlength="40" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">血型：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_bloodType_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_bloodType" name="oldPeople.bloodType" maxlength="11" class="grd-white" style="height:30px;"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<!-- <div class="control-group">
						                                                         	<label class="control-label">一卡通余额：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_walletMoney_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                            <input type="text" id="oldPeople_walletMoney" name="oldPeople.walletMoney" style="height:30px" onblur="onlyMoney()" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div> -->
							                                                </div>
							                                                <div class="tab-pane" id="boxtabpill-2">
							                                                    <div class="control-group">
						                                                         	<label class="control-label">过敏史：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_allergicHistory_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_allergicHistory" name="oldPeople.allergicHistory" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">糖尿病：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_sugarSick_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_sugarSick" name="oldPeople.sugarSick" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">高血压：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_bloodPressure_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_bloodPressure" name="oldPeople.bloodPressure" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">心脏病：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_heartSick_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_heartSick" name="oldPeople.heartSick" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">脑血管：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_brainBloodSick_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_brainBloodSick" name="oldPeople.brainBloodSick" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">眼睛：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_eyeSick_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_eyeSick" name="oldPeople.eyeSick" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
							                                                </div>
							                                                <div class="tab-pane" id="boxtabpill-3">
							                                                    <div class="control-group">
						                                                         	<label class="control-label">早起与否：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_getUpEarly_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<input type="text" id="oldPeople_getUpEarly" name="oldPeople.getUpEarly" maxlength="11" style="height:30px" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">大便时间：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_shitTime_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_shitTime" name="oldPeople.shitTime" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">忌口：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_canNotEat_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_canNotEat" name="oldPeople.canNotEat" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-4">
							                                                    <div class="control-group">
						                                                         	<label class="control-label">原工作单位：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_oldWorkCompany_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<input type="text" id="oldPeople_oldWorkCompany" name="oldPeople.oldWorkCompany" maxlength="60" style="height:30px" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">职务：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_oldWork_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<input type="text" id="oldPeople_oldWork" name="oldPeople.oldWorkName" maxlength="20" style="height:30px" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">职称：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_oldWorkName_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<input type="text" id="oldPeople_oldWorkName" name="oldPeople.oldWork" maxlength="20" style="height:30px" onchange="lostFocusGlobal(this)"/>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">工作内容：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_oldWorkContent_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_oldWorkContent" name="oldPeople.oldWorkContent" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-5">
							                                                    <div class="control-group">
						                                                         	<label class="control-label">爱好：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_hobby_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_hobby" name="oldPeople.hobby" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-6">
							                                                    <div class="control-group">
						                                                         	<label class="control-label">居住地址：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_liveAddress_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_liveAddress" name="oldPeople.liveAddress" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">户籍地址：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_idCardAddress_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_idCardAddress" name="oldPeople.idCardAddress" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-7">
							                                                    <p>
						                                                         	<label id="oldPeople_fihs_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                            <div style="z-index:-999;">
						                                                            	附件：<br>
						                                                    			<script id="oldPeople_fihs_editor" name="oldPeople.familyInfoHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
						                                                   			</div>
						                                                     	</p>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-8">
							                                                    <p>
						                                                         	<label id="oldPeople_ephs_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                            <div style="z-index:-999;">
						                                                            	附件：<br>
						                                                    			<script id="oldPeople_ephs_editor" name="oldPeople.emergencyPeopleHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
						                                                   			</div>
						                                                     	</p>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-9">
							                                                    <p>
						                                                         	<label id="oldPeople_mtm_Label" class="text-error helper-font-small" style="display:none"></label>
						                                                            <div style="z-index:-999;">
						                                                            	附件：<br>
						                                                    			<script id="oldPeople_mtm_editor" name="oldPeople.moreHtmlSource" type="text/plain" style="width:99%;height:300px;z-index:-9999;"></script>
						                                                   			</div>
						                                                     	</p>
							                                                </div>
							                                                <div class="tab-pane fade" id="boxtabpill-10">
							                                                	<div class="control-group">
						                                                         	<label class="control-label">紧急联系人1：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_emergencyPeople1_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_emergencyPeople1" name="oldPeople.emergencyPeople1" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
						                                                     	<div class="control-group">
						                                                         	<label class="control-label">紧急联系人2：</label>
							                                                         <div class="controls">
							                                                         	<label id="oldPeople_emergencyPeople2_Label" class="text-error helper-font-small" style="display:none"></label>
							                                                        	<textarea id="oldPeople_emergencyPeople2" name="oldPeople.emergencyPeople2" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
							                                                        </div>
						                                                     	</div>
							                                              
							                                            </div><!--/widgets-tab-body-->
							                                        </div>
							                                    </div><!--/box body-->
						                                        <div class="form-actions">
			                                                    	<button type="button" id="oldPeople_submit" class="btn btn-success">保存</button>
			                                                        <button type="button" id="oldPeople_modify" class="btn btn-primary">修改</button>
			                                                        <button type="button" id="oldPeople_cancel" class="btn">取消</button>
			                                                    </div>
			                                                   
							                                </div><!--/box tab-->
                                                		</div><!--/span12-->
                                                		</div>
                                                    </fieldset>
                                                </form>
                                            </div>
                                            
                                        </div><!--/box body-->
                                    </div>
                                </div> 
                            </div>
                          	
                       		<div class="row-fluid" id="oldPeople_table_toolbar">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <a class="btn btn-info" id="oldPeople_add">
												<span>新增老人</span>
											</a>
                                        </div>
                                        <div class="box-body">
                                        	<div class='row-fluid'>
                                                 <div class="span4">	                                                 
	                                                  <div class="controls">
	                                                      <label id="oldPeople_opNo_search_Label" class="text-error helper-font-small" style="display:none"></label>
	                                               		  <input type="text" id="oldPeople_opNo_search" maxlength="20" class="grd-white" style="height:30px" placeholder="请输入老人编号查询"/>
	                                                  </div>
                                                 </div>
                                                 <div class="span4">
														<div class="controls">
		                                                        <div class="input-append">
		                                                            <input type="text" class="grd-white" id="oldPeople_opName_search" style="height:30px" class="search-query" placeholder="请输入老人姓名查询"/>
		                                                           
		                                                        </div>
		                                                </div>
                                                </div>
                                                <div class="span3">
                                                	<div class="controls">
                                                		<div class="form-search">
                                                			 <button type="button" id="oldPeople_search" class="btn btn-info">搜索</button>
                                                		</div>
                                                	</div>
                                                </div>
												<div class="span6" style="display:none;">
													<input type="text" id="oldPeople_hidden" class="grd-white"/>
												</div>
											</div>
                                            <table id="oldPeople_table" class="table table-hover responsive">
                                            </table>
	                                        <div class='row-fluid'>
	                                         	<div class="span6">
	                                         		<div id="oldPeople_paging" class="dataTables_paginate paging_bootstrap pagination">
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
              
             <%--   <jsp:include page="Right.jsp"/> --%>
            </div>
        </section>
       
        
        </div>
        
        <jsp:include page="Footer.jsp"/>
		 
		<jsp:include page="Js.jsp"/>
		<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.js"></script>
		<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
		<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
		<script src="<%=basePath%>pages/js/oldPeople/oldPeople.js"></script>
		<script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/ueditor.all.min.js"> </script>
	    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	    <script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/lang/zh-cn/zh-cn.js"></script>
        <script type="text/javascript">
         /* 百度富文本编辑器 */
		var oldPeople_fihs_ue = UE.getEditor('oldPeople_fihs_editor',{
			toolbars : [
					[ 'source', 'undo', 'redo', 'cleardoc', 'fontfamily',
							'fontsize', 'paragraph', 'simpleupload',
							'insertimage', 'attachment' ],
					[ 'bold', 'italic', 'underline', 'fontborder',
							'strikethrough', 'superscript', 'subscript',
							'removeformat', 'formatmatch', 'autotypeset',
							'blockquote', 'pasteplain', '|', 'forecolor',
							'backcolor', 'insertorderedlist',
							'insertunorderedlist', 'selectall', 'cleardoc',
							'justifyleft', 'justifycenter', 'justifyright',
							'lineheight' ] ,
					[ 'inserttable','deletetable','splittocells','splittorows', 
					  'deleterow', 'splittocols',
					  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
					]
		});
		var oldPeople_ephs_ue = UE.getEditor('oldPeople_ephs_editor',{
			toolbars : [
					[ 'source', 'undo', 'redo', 'cleardoc', 'fontfamily',
							'fontsize', 'paragraph', 'simpleupload',
							'insertimage', 'attachment' ],
					[ 'bold', 'italic', 'underline', 'fontborder',
							'strikethrough', 'superscript', 'subscript',
							'removeformat', 'formatmatch', 'autotypeset',
							'blockquote', 'pasteplain', '|', 'forecolor',
							'backcolor', 'insertorderedlist',
							'insertunorderedlist', 'selectall', 'cleardoc',
							'justifyleft', 'justifycenter', 'justifyright',
							'lineheight' ] ,
					[ 'inserttable','deletetable','splittocells','splittorows', 
					  'deleterow', 'splittocols',
					  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
					]
		});
		var oldPeople_mtm_ue = UE.getEditor('oldPeople_mtm_editor',{
			toolbars : [
					[ 'source', 'undo', 'redo', 'cleardoc', 'fontfamily',
							'fontsize', 'paragraph', 'simpleupload',
							'insertimage', 'attachment' ],
					[ 'bold', 'italic', 'underline', 'fontborder',
							'strikethrough', 'superscript', 'subscript',
							'removeformat', 'formatmatch', 'autotypeset',
							'blockquote', 'pasteplain', '|', 'forecolor',
							'backcolor', 'insertorderedlist',
							'insertunorderedlist', 'selectall', 'cleardoc',
							'justifyleft', 'justifycenter', 'justifyright',
							'lineheight' ] ,
					[ 'inserttable','deletetable','splittocells','splittorows', 
					  'deleterow', 'splittocols',
					  'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable']
					]
		});
		
        </script>
    </body>
</html>
