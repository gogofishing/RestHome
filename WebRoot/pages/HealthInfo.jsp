<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("/RestHome/pages/login.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>养老院管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<jsp:include page="css.jsp" />
<link href="<%=basePath%>pages/css/datepicker.css" rel="stylesheet">
<link href="<%=basePath%>pages/css/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="<%=basePath%>pages/css/bootstrap.css" rel="stylesheet">
</head>

<body>
	<jsp:include page="Header.jsp" />
	<section class="section">
	<div class="row-fluid">
		<jsp:include page="LeftMenu.jsp" />
		<div class="span9">
			<div class="content">
				<div class="content-header">
					<h2>
						<i class="icofont-table"></i> 老人管理
					</h2>
				</div>
				<div class="content-breadcrumb">
					<ul class="breadcrumb">
						<li><h6><a href="<%=basePath%>pages/index.jsp"> <i class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a href="<%=basePath %>pages/OldPeople.jsp"><i class="icofont-user"></i> 老人管理</a> <span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a style="font-weight: bold" href="<%=basePath%>pages/HealthInfo.jsp">老人健康信息</a></h6></li>
						<li>&nbsp;&nbsp;</li>
						<li><h6>
							<a id="main_healthinfo" href="javascript:void(0);" style="display:none;font-weight: bold;">老人病史记录</a>
							<a id="main_sickrecord" href="javascript:void(0);" style="display:none;font-weight: bold;">老人病例记录</a>
							<a id="main_checkrecord" href="javascript:void(0);" style="display:none;font-weight: bold;">老人检查记录</a>
							</h6>
						</li>
						<li>&nbsp;&nbsp;</li>
						<li><h6><span div id="oName" style="font-size: 12px;font-weight: bold;"> </span></h6></li>
						<li><h6><span div id="oUuid" style="display:none;font-size: 12px;font-weight: bold;"> </span></h6></li>
					</ul>
				</div>
				<!-- /content-breadcrumb -->

				<!-- content-body -->
				<div class="content-body">
				<div class="row-fluid" id="healthInfo_table_toolbar" >
					<div class="span12">
						<div class="box corner-all">
							<div class="box-header grd-white corner-top">
								<div class="header-control">
									<a data-box="collapse"><i class="icofont-caret-up"></i></a>
								</div>
								<span>老人健康信息</span>
							</div>
							<div class="box-body">
								<div id="healthInfo_oldPeople_page">
								<div class="span4" id="HO_search">
									<div class="controls">
										<div class="form-search">
											<div class="input-append">
												<input type="text" onchange="lostFocusGlobal(this)"
													id="healthinfo_oldPeople_opName_search" style="height:30px"
													class="grd-white" placeholder="请输入老人姓名查询" />
											</div>
												
										</div>
									</div>
										
								</div>
								<div class="span4">
									<div class="controls">
										<div class="form-search">
											<button type="button" id="healthinfo_oldPeople_search"
													class="btn btn-info">搜索</button>
										</div>
									</div>
								</div>
								<br><br> 
									<table id="healthInfo_oldPeople_table" class="table table-hover responsive"></table>
									
									<div class='row-fluid'>
										<div class="span6">
											<div id="healthInfo_oldPeople_paging"
												class="dataTables_paginate paging_bootstrap pagination">
												<ul></ul>
											</div>
										</div>
										<div class="span6">
											<p ><span>总共<a id="totalNum"></a>条记录</span></p>
										</div>
									</div>
								</div>
								<!-- <div>
									<table id="healthinfo_table"></table>
									<table id="sickRecord_table"></table>
									<table id="checkRecord_table"></table>
								</div> -->
							</div>
							<!-- /box-body -->
						</div>
						<!-- /box -->
					</div>
					<!-- /span -->
				</div>
				<!--/datatables tools-->
				<div class="row-fluid" id="healthInfo_table_toolbar2" style="display:none">
					<div class="span12">
						<div class="box corner-all">
							<div class="box-header grd-white corner-top">
								<div class="header-control">
									<a data-box="collapse"><i class="icofont-caret-up"></i></a>
								</div>
								<a class="btn btn-info" id="healthInfo_oldPeople_add">
									<span>新增病史</span>
								</a><br><br>
								<span>老人病史信息</span>
							</div>
							<div class="box-body">
								<table id="healthinfo_table"></table>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid" id="sickRecord_table_toolbar" style="display:none">
					<div class="span12">
						<div class="box corner-all">
							<div class="box-header grd-white corner-top">
								<div class="header-control">
									<a data-box="collapse"><i class="icofont-caret-up"></i></a>
								</div>
								<a class="btn btn-info" id="sickRecord_oldPeople_add">
									<span>新增病例</span>
								</a><br><br>
								<span>老人病例信息</span>
								
							</div>
							<div class="box-body">
								<table id="sickRecord_table"></table>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid" id="checkRecord_table_toolbar" style="display:none">
					<div class="span12">
						<div class="box corner-all">
							<div class="box-header grd-white corner-top">
								<div class="header-control">
									<a data-box="collapse"><i class="icofont-caret-up"></i></a>
								</div>
								<a class="btn btn-info" id="checkRecord_oldPeople_add">
									<span>新增检查</span>
								</a><br><br>
								<span>老人检查信息</span>
							</div>
							<div class="box-body">
								<table id="checkRecord_table"></table>
							</div>
						</div>
					</div>
				</div>		
				<!-- </div> -->				

				<div align="right" id="healthInfo_showMessage"
					class="label label-warning"
					style="display:none;font-size: 14px;font-weight: bold;"></div>
				<!-- <div class="content-body"> -->

					<!--添加老人健康信息开始  -->
					<div id="healthInfo_form_toolbar" class="row-fluid"
						style="display:none;">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>添加老人病史信息</span>
								</div>
								<div class="box-body">
									<div id="form-horizontal">
										<!-- <form id="healthInfo_form" method="post" action="" class="form-horizontal" enctype="multipart/form-data"> -->
										<fieldset>
											<div class="row-fluid">
												<div class="box-body">
													<div class="tab-content">
														<div class="tab-pane fade in active" id="boxtabpill-1">
															<form action="<%=basePath%>HealthInfo/addOldPeopleHealthInfo" method="post">
																<!-- uuid:  -->
																<!-- <input type="text"
																	name="healthinfo.uuid" id="healthInfoUuid" onchange="lostFocusGlobal(this)"> -->
																<!-- 老人uuid:  -->
																<!-- <input type="text" name="oldPeople.uuid" id="addHealthinfo_Uuid"> -->
																<table>
																<tr>
																	<td>&nbsp;</td>
																	<td>
																	<input type="text" style="display:none"
																	name="healthinfo.oldPeople.uuid" id="oldPeopleUuid"
																	style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)">
																	</td>
															    </tr>
																<tr><td style="width: 100px">疾病名称:</td>
																	<td><input type="text"
																	name="healthinfo.sickName"
																	style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)">
																	</td>
																</tr>
																<tr><td>发病日期:</td>
																	<td><div class="controls">
																	<label id="healthinfo_add_sickDate_Label"
																		class="text-error helper-font-small" style="display:none"></label>
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																		<input readonly id="healthinfo_add_sickDate" name="healthinfo.sickDate"
																			style="height:30px;disabled:disabled;" size="30" type="text"
																			value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																			class="add-on"><i class="icon-th"></i></span>
																	</div>
																	</div>
																	</td>
																</tr>
																<tr> <td>治愈日期</td>
																<td>
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																		<input readonly id="healthinfo_add_cureDate" name="healthinfo.cureDate"
																			style="height:30px;disabled:disabled;" size="30" type="text"
																			value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																			class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																</td>
																</tr>

																<tr>
																<td>治疗方案:</td>
																<td><div style="z-index:-999;">
																	<script id="healthinfo_mtm_editor"
																		name="healthinfo.cureInfoHtmlSource" type="text/plain"
																		style="width:100%;height:200px;"></script>
																</div>
																</td></tr>
																<tr>
																<td> 后遗症:</td>
																<td>
																<textarea name="healthinfo.sequela"
																	style="width:100%;height:200px;"></textarea>
																</td>	
																</tr>
																<tr><td>&nbsp;</td>
																<td>	
																<input id="healthinfoADD" type="submit" class="btn btn-success" value="添加">
																</td>
																</tr>
																</table>
															</form>

														</div>
													</div>
												</div>
											</div>
										</fieldset>
										<!-- </form> -->
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--添加老人健康信息结束  -->
					<!-- 修改信息开始 -->
					<div id="healthInfo_form_modify_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>修改老人病史信息</span>
								</div>
								<div class="box-body">
									<div id="form-horizontal">

										<fieldset>
											<div class="row-fluid">
												<!-- <div class="box-header corner-top">
															<ul class="nav nav-pills">
																tab menus
																<li class="active"><a data-toggle="tab"
																	href="#boxtabpill-11">修改健康信息</a></li>
															</ul>
														</div> -->
												<div class="box-body">
													<div class="tab-content">
														<div class="tab-pane fade in active" id="boxtabpill-11">

															<form
																action="<%=basePath%>HealthInfo/modifyOldPeopleHealthInfo"
																method="post">
																<!-- uuid:  -->
																<input type="text" style="display:none"
																	name="healthinfo.uuid" id="healthInfoUuid" onchange="lostFocusGlobal(this)">
																<!-- 老人uuid:  -->
																<input type="text" style="display:none"
																	name="healthinfo.oldPeople.uuid" id="oldPeopleUuid1">
																<span style="font-size: 14px;font-weight: bold;">疾病名称:</span><br>
																<input type="text" name="healthinfo.sickName"
																	id="healthInfoSickName"
																	style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)"><br>
																<span style="font-size: 14px;font-weight: bold;">发病日期:</span><br>
																
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																		<input readonly id="healthInfoSickDate" name="healthinfo.sickDate"
																			style="height:30px;disabled:disabled;" size="30" type="text"
																			value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																			class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																<br> <span
																	style="font-size: 14px;font-weight: bold;">治愈日期:</span>
																
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																		<input readonly id="healthInfoCureDate" name="healthinfo.cureDate"
																			style="height:30px;disabled:disabled;" size="30" type="text"
																			value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																			class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																<br>

																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">治疗方案:</span><br>
																	<script id="healthinfo_mtm_editor1"
																		name="healthinfo.cureInfoHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div>
																<br> <span
																	style="font-size: 14px;font-weight: bold;"> 后遗症:</span><br>
																<textarea id="healthInfoSequela"
																	name="healthinfo.sequela"
																	style="width:80%;height:100px;z-index:-9999;"></textarea>
																<br>
																<div align="left">
																	<input type="submit" class="btn btn-primary" value="修改">
																</div>

															</form>


														</div>
													</div>
												</div>
											</div>
										</fieldset>
										<!-- </form> -->
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--...........修改信息结束...........  -->
					<!-- 健康信息详细开始 -->
					<div id="healthInfo_form_detail_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>治疗方案与后遗症</span>
								</div>
								<div class="box-body">
									<div id="form-horizontal2">
										<fieldset>
											<div class="box-body">
												<div class="tab-content">
													<div class="tab-pane fade in active" id="boxtabpill-111">

														<span style="font-size: 16px;font-weight: bold;"
															readonly="readonly" name="healthinfo.sickName"
															id="hdSickName"></span><br>
														<hr>
														<span style="font-size: 14px;font-weight: bold;">治疗方案:</span><br>
														<span id="hdCureInfoHtmlSource" readonly="readonly"
															name="healthinfo.cureInfoHtmlSource"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">后遗症:</span><br>
														<span id="hdSequela" readonly="readonly"
															name="healthinfo.sequela"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br>
													</div>
												</div>
											</div>

										</fieldset>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 健康信息详细结束 -->
					<!-- 病例添加开始 -->
					<div id="sickRecord_form_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>病例添加</span>
								</div>
								<div class="box-body">
									<fieldset>
										<div class="row-fluid">
											<!-- <div class="span12">
												<div class="box-tab corner-all"> -->
											<div class="box-body">
												<div class="tab-content">
													<div class="tab-pane fade in active"
														id="boxtabpill-sickRecord">
														<hr>
														<form id="sickRecord_from_add" class="form-horizontal"
															action="sickRecord/addSickRecord" method="post">
															<!-- 老人uuid:  -->
																<input type="text" style="display:none"
																	name="sickRecord.oldPeople.uuid" id="add_sickRecord_Uuid">
															<table>
																<tr>
																	<td>主治医师:</td>
																	<td><input type="text" name="sickRecord.doctor"
																		style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)"></td>
																</tr>
															
																<tr>
																	<td>病症类型:</td>
																	<td>
																		<!-- <input type="text" name="sickRecord.sickType"
																		style="height:25px;disabled:disabled;"> -->
																	<select id="add_sickRecord_sickRecord" name="sickRecord.sickType" style="height:29px;">
																	</select>
																	</td>
																</tr>
																
																<tr>
																	<td width="100px">检查日期:<br></td>
																	<td style="text-align: left;">
																		<!-- <div class="controls"> -->
																			<div class="input-append date" data-form="datepicker"
																				data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																				<input readonly id="sickRecord_checkDate" name="sickRecord.checkDate"
																					style="height:30px;disabled:disabled;" size="30" type="text"
																					value="2014-10-01" onchange="lostFocusGlobal(this)" />
																				<span class="add-on"><i class="icon-th"></i></span>
																			</div>
																		<!-- </div> -->
																	</td>
																</tr>
																<tr>
																	<td>住院日期:</td>
																	<td>
																			<div class="input-append date" data-form="datepicker"
																				data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																				<input readonly id="sickRecord_add_inHospitalDate" name="sickRecord.inHospitalDate"
																					style="height:30px;disabled:disabled;" size="30" type="text"
																					value="2014-10-01" onchange="lostFocusGlobal(this)" />
																				<span class="add-on"><i class="icon-th"></i></span>
																			</div>
																	</td>
																</tr>
																<tr>
																	<td>出院日期:</td>
																	<td> 
																		<!-- <div class="controls"> -->
																			<div class="input-append date" data-form="datepicker"
																				data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																				<input readonly id="sickRecord_add_outHospitalDate" name="sickRecord.outHospitalDate"
																					style="height:30px;disabled:disabled;" size="30" type="text"
																					value="2014-10-01" onchange="lostFocusGlobal(this)" />
																				<span class="add-on"><i class="icon-th"></i></span>
																			</div>
																		<!-- </div> -->
																	</td>
																</tr>
																
																<tr>
																<tr>
																	<td>发病原因:</td>
																	<td><script id="sickRecord_sickReson_editor"
																			name="sickRecord.sickResonHtmlSource"
																			type="text/plain"
																			style="width:100%;height:200px;"></script></td>
																</tr>

																<td>治疗方案:</td>
																<td><script id="sickRecord_cureInfo_editor"
																		name="sickRecord.cureInfoHtmlSource" type="text/plain"
																		style="width:100%;height:200px;"></script></td>
																</tr>
																<tr>
																	<td>陪同人信息:</td>
																	<td><script id="sickRecord_carePeopleInfo_editor"
																			name="sickRecord.carePeopleInfoHtmlSource"
																			type="text/plain"
																			style="width:100%;height:200px;"></script></td>
																</tr>
																<tr>
																	<td>检查项目:</td>
																	<td><script
																			id="sickRecord_checkProjectInfo_editor"
																			name="sickRecord.checkProjectInfoHtmlSource"
																			type="text/plain"
																			style="width:100%;height:200px;"></script></td>
																</tr>
																<tr>
																	<td>检查结果:</td>
																	<td><script id="sickRecord_checkResult_editor"
																			name="sickRecord.checkResultHtmlSource"
																			type="text/plain"
																			style="width:100%;height:200px;z-index:-9999;"></script></td>
																</tr>
																<tr>
																	<td>检查资料:</td>
																	<td><script id="sickRecord_checkInfo_editor"
																			name="sickRecord.checkInfoHtmlSource"
																			type="text/plain"
																			style="width:100%;height:200px;"></script></td>
																</tr>

																<tr>
																	<td>&nbsp;</td>
																	<td>
																		<!-- <span style="font-size: 12px;font-weight: bold;"><button id="sickRecord_add_but">添加</button></span> -->
																		<input type="submit" class="btn btn-success" value="添加">
																	</td>
																</tr>
																<!-- <tr>
																	<td>老人uuid&nbsp;</td>
																	<td><input type="text" style="display: none;"
																		name="sickRecord.oldPeople.uuid"
																		id="sickRecord_oldPeople_uuid" onchange="lostFocusGlobal(this)"></td>
																</tr> -->

															</table>
														</form>
													</div>
												</div>
											</div>
											<!-- </div>
											</div> -->
										</div>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
					<!-- 病例添加结束 -->
					<!-- 检查记录添加开始 -->
					<div id="oldPeopleCheckRecord_form_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>检查记录添加</span>
								</div>
								<div class="box-body">
									<fieldset>
										<div class="row-fluid">
											<!-- <div class="box-body"> -->
											<div class="tab-content">
												<div class="tab-pane fade in active"
													id="boxtabpill-checkRecord">
													<hr>
													<form id="checkRecord_from_add" action="oldPeopleCheckRecord/addOldPeopleCheckRecord" method="post">
														<table align="left">
															<tr>
																<td>
																	<!-- 老人uuid -->
																</td>
																<td><input type="text" style="display: none;"
																	name="checkRecord.oldPeople.uuid"
																	id="checkRecord_oldPeople_uuid"
																	style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)"></td>
															</tr>
															<!-- <tr>
								                    				<td style="width: 100px">检查日期</td>
								                    				<td>
									                    				<div id="datetimepicker4" class="input-append date">
															      		<input type="text" name="sickRecord.checkDate" readonly="readonly">
															      		<span class="add-on">
															        		<i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
															      		</span>
															    		</div>
														    		</td>
								                    			</tr> -->
															<tr>
																<td style="width: 100px">检查项目</td>
																<td><input type="text"
																	name="checkRecord.checkProject"
																	style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)"></td>
															</tr>
															<tr>
																<td>检查人编号</td>
																<td><input onchange="lostFocusGlobal(this)" type="text" name="checkRecord.workPeopleNo" style="height:25px;disabled:disabled;"
																		 onblur="checkExistEmp(this.value,document.getElementById('checkRecord_empNo_Label'))">
																	<label id="checkRecord_empNo_Label" class="text-error helper-font-big" style="display:none"></label>
																</td>
															</tr>
															<tr>
																<td>检查人姓名</td>
																<td><input type="text"
																	name="checkRecord.workPeopleName"
																	style="height:25px;disabled:disabled;" onchange="lostFocusGlobal(this)"></td>
															</tr>
															<tr>
																<td>复查日期</td>
																<td>
																	<div class="controls">
																			<div class="input-append date" data-form="datepicker"
																				data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																				<input readonly id="checkRecord_add_nextCheckTime" name="checkRecord.nextCheckTime"
																					style="height:30px;disabled:disabled;" size="30" type="text"
																					value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																					class="add-on"><i class="icon-th"></i></span>
																			</div>
																	</div>
																</td>
															</tr>
															<tr>
																<td>检查结果</td>
																<td><script id="chaeckRecord_checkResult_editor"
																		name="checkRecord.checkResultHtmlSource"
																		type="text/plain"
																		style="width:100%;height:200px;"></script></td>
															</tr>
															<tr>
																<td>体检信息</td>
																<td><script id="checkRecord_checkInfo_editor"
																		name="checkRecord.checkInfoHtmlSource"
																		type="text/plain"
																		style="width:100%;height:200px;"></script></td>
															</tr>
															<tr>
																<td>&nbsp;&nbsp;</td>
																<td>
																	<input type="submit" class="btn btn-success" value="添加">
																</td>
															</tr>

														</table>
													</form>
												</div>
											</div>
											<!-- </div> -->
										</div>
									</fieldset>
								</div>
							</div>
						</div>
					</div>
					<!--检查记录添加结束   -->
					<!-- 病例详细信息显示开始 -->
					<div id="sickRecord_detail" class="row-fluid" style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>病例记录详细显示</span>
								</div>
								<div class="box-body">
									<div id="form-horizontal3">
										<fieldset>
											<div class="box-body">
												<div class="tab-content">
													<div class="tab-pane fade in active">

														<!-- <span style="font-size: 16px;font-weight: bold;"
																		readonly="readonly" name="healthinfo.sickName"
																		id="hdSickName">
															 </span><br>
															 <hr> -->
														<span style="font-size: 14px;font-weight: bold;">发病原因:</span><br>
														<span id="srDetail_sickResonHtmlSource"
															readonly="readonly" name="sickRecord.sickResonHtmlSource"
															style="width:100%;height:300px;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">治疗方案:</span><br>
														<span id="srDetail_cureInfoHtmlSource"
															readonly="readonly" name="sickRecord.cureInfoHtmlSource"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">陪同人信息:</span><br>
														<span id="srDetail_carePeopleInfoHtmlSource"
															readonly="readonly"
															name="sickRecord.carePeopleInfoHtmlSource"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">检查项目:</span><br>
														<span id="srDetail_checkProjectInfoHtmlSource"
															readonly="readonly"
															name="sickRecord.checkProjectInfoHtmlSource"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">检查结果:</span><br>
														<span id="srDetail_checkResultHtmlSource"
															readonly="readonly"
															name="sickRecord.checkResultHtmlSource"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">检查资料:</span><br>
														<span id="srDetail_checkInfoHtmlSource"
															readonly="readonly" name="sickRecord.checkInfoHtmlSource"
															style="width:100%;height:300px;z-index:-9999;"></span>
														<br>
													</div>
												</div>
											</div>

										</fieldset>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- 病例详细信息显示结束 -->
					
					<!-- 病例修改开始 -->
					<div id="sickRecord_modify" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>修改病例信息</span>
								</div>
								<div class="box-body">
									<fieldset>
											<div class="row-fluid">
												<div class="box-body">
													<div class="tab-content">
														<div class="tab-pane fade in active">

															<form action="<%=basePath%>sickRecord/modifySickRecord" method="post">
																<!-- uuid:  -->
																<input type="text" style="display:none"
																	name="sickRecord.uuid" id="modify_sickRecordUuid">
																<!-- 老人uuid:  -->
																<input type="text" style="display:none" name="sickRecord.oldPeople.uuid" id="modify_sickRecord_oldPeopleUuid">
																
																
																<span style="font-size: 14px;font-weight: bold;">检查日期:</span><br>
																
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																	<input readonly id="modify_sickRecord_checkDate" name="sickRecord.checkDate"
																		style="height:30px;disabled:disabled;" size="30" type="text"
																		value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																		class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																
																<span style="font-size: 14px;font-weight: bold;">住院日期:</span><br>
																
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																	<input readonly id="modify_sickRecord_inHospitalDate" name="sickRecord.inHospitalDate"
																		style="height:30px;disabled:disabled;" size="30" type="text"
																		value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																		class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																
																<br>
																<span style="font-size: 14px;font-weight: bold;">出院日期:</span><br>
																
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																	<input readonly id="modify_sickRecord_outHospitalDate" name="sickRecord.outHospitalDate"
																		style="height:30px;disabled:disabled;" size="30" type="text"
																		value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																		class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																<br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">病症类型:</span><br>
																	<!-- <input style="height:25px;" type="text" name="sickRecord.sickType" id="modify_sickRecord_sickType"> -->
																	<select id="modify_sickRecord_sickRecord" name="sickRecord.sickType" style="height:29px;">
																	</select>
																</div><br>
																<span style="font-size: 14px;font-weight: bold;"> 主治医师:</span><br>
																<input onchange="lostFocusGlobal(this)" style="height:25px;" type="text" name="sickRecord.doctor" id="modify_sickRecord_doctor">
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">发病原因:</span><br>
																	<script id="modify_sickRecord_sickResonHtmlSource"
																		name="sickRecord.sickResonHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">治疗方案:</span><br>
																	<script id="modify_sickRecord_cureInfoHtmlSource"
																		name="sickRecord.cureInfoHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">陪同人信息:</span><br>
																	<script id="modify_sickRecord_carePeopleInfoHtmlSource"
																		name="sickRecord.carePeopleInfoHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查项目:</span><br>
																	<script id="modify_sickRecord_checkProjectInfoHtmlSource"
																		name="sickRecord.checkProjectInfoHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查结果:</span><br>
																	<script id="modify_sickRecord_checkResultHtmlSource"
																		name="sickRecord.checkResultHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查资料:</span><br>
																	<script id="modify_sickRecord_checkInfoHtmlSource"
																		name="sickRecord.checkInfoHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div align="left">
																	<input type="submit" class="btn btn-primary" value="修改">
																</div>

															</form>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
								</div>
							</div>
						</div>
					</div>
					<!-- 病例修改结束 -->
					<!-- 检查详细信息显示 开始-->
					<div id="oldPeopleCheckRecord_detail" class="row-fluid" style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>检查记录详细显示</span>
								</div>
								<div class="box-body">
										<fieldset>
											<div class="box-body">
												<div class="tab-content">
													<div class="tab-pane fade in active">
														<span style="font-size: 14px;font-weight: bold;">检查结果:</span><br>
														<span id="crDetail_checkResultHtmlSource"
															readonly="readonly" name="oldPeopleCheckRecord.checkResultHtmlSource"
															style="width:100%;height:300px;"></span>
														<br><span style="font-size: 14px;font-weight: bold;">体检信息:</span><br>
														<span id="crDetail_checkInfoHtmlSource"
															readonly="readonly" name="oldPeopleCheckRecord.checkInfoHtmlSource"
															style="width:100%;height:300px;"></span>
														
													</div>
												</div>
											</div>
										</fieldset>
								</div>
							</div>
						</div>
					</div>
					<!-- 检查详细信息显示结束 -->
					<!-- 检查信息修改开始 -->
					<div id="oldPeopleCheckRecord_modify" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>修改检查信息</span>
								</div>
								<div class="box-body">
									<fieldset>
											<div class="row-fluid">
												<div class="box-body">
													<div class="tab-content">
														<div class="tab-pane fade in active">

															<form action="<%=basePath%>oldPeopleCheckRecord/modifyOldPeopleCheckRecord" method="post">
																<!-- uuid:  -->
																<input type="text" style="display:none"
																	name="checkRecord.uuid" id="modify_checkRecordUuid">
																<!-- 老人uuid:  -->
																<input type="text" style="display:none" name="checkRecord.oldPeople.uuid" id="modify_oldPeopleCheckRecord_oldPeopleUuid">
																
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查项目:</span><br>
																	<input onchange="lostFocusGlobal(this)" style="height:25px;" type="text" name="checkRecord.checkProject" id="modify_oldPeopleCheckRecord_checkProject">
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查人编号:</span><br>
																	<input onchange="lostFocusGlobal(this)" style="height:25px;" type="text" name="checkRecord.workPeopleNo" id="modify_oldPeopleCheckRecord_workPeopleNo"
																		onblur="checkExistEmp(this.value,document.getElementById('mod_checkRecord_empNo_Label'))">
																	<label id="mod_checkRecord_empNo_Label" class="text-error helper-font-big" style="display:none"></label>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查人姓名:</span><br>
																	<input onchange="lostFocusGlobal(this)" style="height:25px;" type="text" name="checkRecord.workPeopleName" id="modify_oldPeopleCheckRecord_workPeopleName">
																	
																</div><br>
																<span style="font-size: 14px;font-weight: bold;">复查日期:</span><br>
																<div class="controls">
																	<div class="input-append date" data-form="datepicker"
																		data-date="2014-10-01" data-date-format="yyyy-mm-dd">
																	<input readonly id="modify_oldPeopleCheckRecord_nextCheckTime" name="checkRecord.nextCheckTime"
																		style="height:30px;disabled:disabled;" size="30" type="text"
																		value="2014-10-01" onchange="lostFocusGlobal(this)" /> <span
																		class="add-on"><i class="icon-th"></i></span>
																	</div>
																</div>
																
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">检查结果:</span><br>
																	<script id="modify_oldPeopleCheckRecord_checkResultHtmlSource"
																		name="checkRecord.checkResultHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																<div style="z-index:-999;">
																	<span style="font-size: 14px;font-weight: bold;">体检信息:</span><br>
																	<script id="modify_oldPeopleCheckRecord_checkInfoHtmlSource"
																		name="checkRecord.checkInfoHtmlSource" type="text/plain"
																		style="width:90%;height:200px;z-index:-9999;"></script>
																</div><br>
																
																<div align="left">
																	<input type="submit" class="btn btn-primary" value="修改">
																</div>

															</form>
														</div>
													</div>
												</div>
											</div>
										</fieldset>
								</div>
							</div>
						</div>
					</div>
					<!-- 检查信息修改结束 -->
				</div>
				<!--/content-body -->
			</div>
		</div>
	<%-- 	<jsp:include page="Right.jsp" /> --%>
	</div>

	</section>


	<jsp:include page="Footer.jsp" />

	<jsp:include page="Js.jsp" />


	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>pages/js/healthinfo/healthinfo.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript">
		/* 百度富文本编辑器 */
		var healthinfo_mtm_ue = UE.getEditor('healthinfo_mtm_editor',{
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
		}
		);
		var healthinfo_mtm_ue1 = UE.getEditor('healthinfo_mtm_editor1',{
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
		
		var sickRecord_sickReson_editor = UE.getEditor('sickRecord_sickReson_editor',{
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
		var sickRecord_cureInfo_editor = UE.getEditor('sickRecord_cureInfo_editor',{
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
		var sickRecord_carePeopleInfo_editor = UE.getEditor('sickRecord_carePeopleInfo_editor',{
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
		var sickRecord_checkProjectInfo_editor = UE.getEditor('sickRecord_checkProjectInfo_editor',{
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
		var sickRecord_checkResult_editor = UE.getEditor('sickRecord_checkResult_editor',{
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
		var sickRecord_checkInfo_editor = UE.getEditor('sickRecord_checkInfo_editor',{
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
		var chaeckRecord_checkResult_editor = UE.getEditor('chaeckRecord_checkResult_editor',{
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
		var checkRecord_checkInfo_editor = UE.getEditor('checkRecord_checkInfo_editor',{
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
		
		var SR_sickResonHtmlSource = UE.getEditor('modify_sickRecord_sickResonHtmlSource',{
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
		var SR_cureInfoHtmlSource = UE.getEditor('modify_sickRecord_cureInfoHtmlSource',{
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
		var SR_carePeopleInfoHtmlSource = UE.getEditor('modify_sickRecord_carePeopleInfoHtmlSource',{
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
		var SR_checkProjectInfoHtmlSource = UE.getEditor('modify_sickRecord_checkProjectInfoHtmlSource',{
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
		var SR_sickResonHtmlSource = UE.getEditor('modify_sickRecord_sickResonHtmlSource',{
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
		var SR_checkResultHtmlSource = UE.getEditor('modify_sickRecord_checkResultHtmlSource',{
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
		var SR_checkInfoHtmlSource = UE.getEditor('modify_sickRecord_checkInfoHtmlSource',{
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
		
		var modify_oldPeopleCheckRecord_checkInfoHtmlSource = UE.getEditor('modify_oldPeopleCheckRecord_checkInfoHtmlSource',{
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
		var modify_oldPeopleCheckRecord_checkResultHtmlSource = UE.getEditor('modify_oldPeopleCheckRecord_checkResultHtmlSource',{
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
		//	healthinfo_mtm_ue.getContent();从富文本获取内容
	</script>



	<!-- 日期时间选择控件 -->
	<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.js"></script>
 <%--    <script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.zh-CN.js"></script> --%>
  	
  	<!-- <script type="text/javascript">
  		/* 健康信息的返回 */
    	function returnHealthInfo(){
			$('#healthinfo_table').show("normal");
			$('#healthInfo_form_modify_toolbar').hide("normal");
			$('#healthInfo_form_detail_toolbar').hide("normal");
			/* $('#healthInfo_form_toolbar').hide("normal"); */
		}
		/* 病例信息的返回 */
		function returnSickRecord(){
			$('#sickRecord_detail').hide("normal");
			$('#sickRecord_table').show("normal");
			$('#sickRecord_modify').hide("normal");
			$('#sickRecord_form_toolbar').hide("normal");
		}
		/* 检查信息的返回 */
		function returnCheckRecord(){
			$('#oldPeopleCheckRecord_modify').hide("normal");
			$('#checkRecord_table').show("normal");
			$('#oldPeopleCheckRecord_detail').hide("normal");
			$('#oldPeopleCheckRecord_form_toolbar').hide("normal");
		}
	</script> -->
</body>
</html>
