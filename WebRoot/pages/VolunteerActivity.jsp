<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
<html lang="en">
<head>
<meta charset="utf-8" />
<base href="<%=basePath%>">
<title>养老院管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="stilearning" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="css.jsp" />
<link href="<%=basePath%>pages/css/datepicker.css" rel="stylesheet">
<link href="<%=basePath%>pages/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=basePath%>pages/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
</head>
<body>
	<jsp:include page="Header.jsp" />

	<section class="section">
	<div class="row-fluid">
		<jsp:include page="LeftMenu.jsp" />

		<div class="span9">
			<div class="content">
				<div class="content-header" id="vltActivity_top">
					<h2>
						<i class="icofont-table"></i>志愿活动管理
					</h2>
				</div>
				<div class="content-breadcrumb">
					<ul class="breadcrumb">
						<li><h6>
								<a href="<%=basePath%>pages/index.jsp"><i
									class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span>
							</h6></li>
						<li><h6>
								<a href="<%=basePath%>pages/VolunteerActivity.jsp"><i
									class="icofont-user"></i> 志愿活动管理</a> <span class="divider">&rsaquo;</span>
							</h6></li>
						<li><h6>
								<a href="<%=basePath%>pages/VolunteerActivity.jsp">志愿活动信息</a>
							</h6></li>
					</ul>
				</div>
				<div class="content-body">
					<div id="vltActivity_showMessage" class="label label-warning"
						style="display:none;font-size: 14px;font-weight: bold;"></div>
					<s:actionerror  cssStyle="color:red"/>
					<div id="vltActivity_form_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>请填写志愿活动信息（只有基本信息中带*号的为必填项，其余选填）</span>
								</div>
								<div class="box-body">
									<!--form-horizontal-->
									<div id="form-horizontal">
										<tbody>
											<form id="vltActivity_form">
												<fieldset>
													<div class="row-fluid">
														<div class="span12">
															<br>

															<div class="control-group" style="display:none">
																<label class="control-label">志愿活动uuid :</label>
																<div class="controls">
																	<input id="vltActivity_uuid" type="text"
																		name="vltActivity.uuid" maxlength="15"
																		class="grd-white" style="height:30px" />
																</div>
															</div>
															<br>


															<div class="control-group" align="center">
																<label class="control-label col-md-2">志愿者编号&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">																		
																	<input type="text" id="vltActivity_vno"
																		name="volunteer.volunteerNo" class="grd-white require"
																		style="height: 30px;width: 400px" />																	 
																</div>
																<lable id="vltActivity_vno_label" class="text-error helper-font-small"></lable><br>
															</div>
															<br>


															<div class="control-group" align="center">
																<label class="control-label col-md-2">志愿者姓名&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	<input type="text"
																		id="vltActivity_vname" name="volunteer.volunteerName"
																		maxlength="15" class="grd-white require"
																		readonly="readonly" style="height: 30px;width: 400px" />																	
																</div>
																<!-- <lable id="vltActivity_vname_label"	class="text-error helper-font-small"></lable> --><br> 
															</div>
															<br>


															<div class="control-group" align="center">
																<label class="control-label col-md-2">老人编号&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	 <input type="text"
																		id="vltActivity_ono" name="oldPeople.oldPeopleNo"
																		maxlength="15" class="grd-white require"
																		style="height: 30px;width: 400px" />																	
																</div>
																<lable id="vltActivity_ono_label" class="text-error helper-font-small"></lable><br>
															</div>
															<br>


															<div class="control-group" align="center">
																<label class="control-label col-md-2">老人姓名&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	<input type="text"
																		id="vltActivity_oname" name="oldPeople.oldPeopleName"
																		maxlength="15" class="grd-white require"
																		readonly="readonly" style="height: 30px;width: 400px" />																	
																</div>
																<!-- <lable id="vltActivity_oname_label" class="text-error helper-font-small"></lable> --><br> 
															</div>
															<br>


															<div class="form-group" align="center">
																<label class="controls col-md-2">活动内容&nbsp;*&nbsp;</label>
																<div class="input-group col-md-5">																	
																	<textarea rows="3" cols="50" id="vltActivity_content"
																		name="vltActivity.activityContent" 
																		class="form-control"></textarea>	
																						
																</div>
																<br/>	<br/>	<br/>	
																<lable id="vltActivity_content_label" class="text-error helper-font-small"></lable><br>
															</div>
															<br>


															<div class="form-group" align="center">
																<label class="control-label col-md-2">活动类型&nbsp;*&nbsp;</label>
																<div class="input-group col-md-5">
																	<select id="vltActivity_type" 	name="vltActivity.activityType" class="form-control">
																	</select>
																</div>
																<br>
															</div>
															<br>
															
															
															


															<div class="control-group" align="center">
																<label class="control-label col-md-2">活动地点&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	<input type="text"
																		id="vltActivity_place"
																		name="vltActivity.activityPlace" maxlength="20"
																		class="grd-white require"
																		style="height: 30px;width: 400px" />																	
																</div>
																<lable id="vltActivity_place_label" class="text-error helper-font-small"></lable><br> 
															</div>
															<br>


															<div class="form-group" align="center">
																<label for="dtp_input3" class="control-label col-md-2">开始时间&nbsp;*&nbsp;</label>
																<div class="input-group date form_time col-md-5"
																	data-date="" data-date-format="hh:ii"
																	data-link-field="dtp_input3" data-link-format="hh:ii">
																	<input class="form-control grd-white"  type="text" style="height:30px;disabled:disabled;"
																		name="vltActivity.startTime" id="vltActivity_startTime" readonly/> <span
																		class="input-group-addon"><span
																		class="glyphicon glyphicon-time"></span></span>															
																</div>
																<lable id="vltActivity_startTime_label" class="text-error helper-font-small"></lable><br>
															</div>
															<br>
															
															
															<div class="form-group" align="center">
																<label for="dtp_input3" class="control-label col-md-2">结束时间&nbsp;*&nbsp;</label>
																<div class="input-group date form_time col-md-5"
																	data-date="" data-date-format="hh:ii"
																	data-link-field="dtp_input3" data-link-format="hh:ii">
																	<input class="form-control grd-white"  type="text" style="height:30px;disabled:disabled;"
																		name="vltActivity.endTime" id="vltActivity_endTime" readonly/> <span
																		class="input-group-addon"><span
																		class="glyphicon glyphicon-time"></span></span>															
																</div>
																<lable id="vltActivity_endTime_label" class="text-error helper-font-small"></lable><br>
															</div>
															<br>


															<div class="form-group" align="center">
																<label for="dtp_input2" class="control-label col-md-2">开始日期&nbsp;*&nbsp;</label>
																<div class="input-group date form_date col-md-5"
																	data-date="" data-date-format="yyyy-MM-dd"
																	data-link-field="dtp_input2"
																	data-link-format="yyyy-mm-dd">
																	<input class="form-control grd-white" type="text"
																		style="height:30px;disabled:disabled;"
																		name="vltActivity.beginDate" id="vltActivity_startDate" readonly /> <span
																		class="input-group-addon"><span
																		class="glyphicon glyphicon-calendar"></span></span>																
																</div>
																<lable id="vltActivity_startDate_label"
																		class="text-error helper-font-small"></lable><br>
															</div>
															<br>
															
															
															<div class="form-group" align="center">
																<label for="dtp_input2" class="control-label col-md-2">结束日期&nbsp;*&nbsp;</label>
																<div class="input-group date form_date col-md-5"
																	data-date="" data-date-format="yyyy-MM-dd"
																	data-link-field="dtp_input2"
																	data-link-format="yyyy-mm-dd">
																	<input class="form-control grd-white" type="text"
																		style="height:30px;disabled:disabled;"
																		name="vltActivity.endDate" id="vltActivity_endDate" readonly /> <span
																		class="input-group-addon"><span
																		class="glyphicon glyphicon-calendar"></span></span>																
																</div>
																<lable id="vltActivity_endDate_label"
																		class="text-error helper-font-small"></lable><br>
															</div>
															<br>


															<div class="control-group" align="center">
																<label class="control-label col-md-2">时长（H）&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	<input type="text"
																		id="vltActivity_hours" name="vltActivity.hours"
																		maxlength="40" class="grd-white"
																		style="height: 30px;width: 400px" />																	
																</div>
																<lable id="vltActivity_hours_label"
																		class="text-error helper-font-small"></lable> <br>
															</div>
															<br>


															<div class="control-group" align="center">
																<label class="control-label col-md-2">负责人编号&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	<input type="text"
																		id="vltActivity_empNo" name="employee.employeeNo"
																		maxlength="15" class="grd-white require"
																		style="height: 30px;width: 400px" />																	
																</div>
																<lable id="vltActivity_empNo_label"
																		class="text-error helper-font-small"></lable> <br>																
															</div>
															<br>
																														

															<div class="control-group" align="center">
																<label class="control-label col-md-2">负责人姓名&nbsp;*&nbsp;</label>
																<div class="controls col-md-3">
																	 <input type="text"
																		id="vltActivity_empName" name="employee.employeeName"
																		maxlength="15" class="grd-white require"
																		readonly="readonly" style="height: 30px;width: 400px" />
																	<lable id="vltActivity_empName_label"
																		class="text-error helper-font-small"></lable><br>
																</div>
															</div>
															<br>
															<br>														

															<div class="form-actions" align="center">
																<button type="button" id="vltActivity_submit"
																	class="btn btn-success">保存</button>
																<button type="button" id="vltActivity_modify"
																	class="btn btn-primary">修改</button>
																<button type="button" id="vltActivity_cancel"
																	class="btn">取消</button>
																<button type="button" id="vltActivity_reset"
																	class="btn" style="display: none"></button>
															</div>
																
															<!--/span12-->
														</div>
													</div>
												</fieldset>
											</form>
										</tbody>
									</div>
									
									<!--form-horizontal-->
								</div>
								<!-- box-body -->

							</div>
						</div>
					</div>
					

					<div class="row-fluid" id="vltActivity_table_toolbar">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<a class="btn btn-info" id="vltActivity_add"> <span>新增志愿活动</span>
									</a>
								</div>
								<div class="box-body">
									<div class='row-fluid'>
										<!-- 搜索框 -->
										<div class="span3">
											<div class="control-group">
												<div class="input-append">
													<input type="text" id="vltActivity_volunteerNo"
														class="grd-white" style="height:30px" maxlength="11"
														placeholder="请输入志愿者编号查询" />
												</div>
											</div>
										</div>
										<div class="span3">
											<div class="controls">
												<div class="input-append date" data-form="datepicker"
													data-date="" data-date-format="yyyy-mm-dd">
													<input id="startDate" class="grd-white"
														style="height:30px;disabled:disabled;" size="16"
														placeholder="请输入起始日期查询" type="text" /> <span
														class="add-on"><i class="icon-th"></i></span>
												</div>												
											</div>
										</div>
										<div class="span3">
											<div class="controls">
												<div class="input-append date" data-form="datepicker"
													data-date="" data-date-format="yyyy-mm-dd">
													<input id="endDate" class="grd-white"
														style="height:30px;disabled:disabled;" size="16"
														placeholder="请输入结束日期查询" type="text" /> <span
														class="add-on"><i class="icon-th"></i></span>
												</div>
											</div>
										</div>
										<div class="span2">
											<div class="controls">
												<div class="form-search">
													<button type="button" id="vltActivity_search"
														class="btn btn-info">搜索</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 列表显示 -->
									<table id="vltActivity_table"
										class="table table-hover responsive">
										<thead>
											<tr>
												<th style='display:none;'>uuid</th>
												<th><span class="badge">志愿者编号</span></th>
												<th><span class="badge">志愿者姓名</span></th>
												<th><span class="badge">老人编号</span></th>
												<th><span class="badge">老人姓名</span></th>
												<th><span class="badge">活动类型</span></th>
												<th><span class="badge">工作时长</span></th>
												<th><span class="badge">负责人</span></th>
												<th colspan="2"><span class="badge">操作</span></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>

									<!-- 分页 -->
									<div class='row-fluid'>
										<div class="span6">
											<div id="vltActivity_paging"
												class="dataTables_paginate paging_bootstrap pagination">
												<ul>
												</ul>
											</div>
										</div>
										<div class="span5">
											<p>
												<span>总共<a id="totalNum"></a>条记录
												</span>
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>
		</div>

		<%--    <jsp:include page="Right.jsp"/> --%>
	</div>
	</section>

	<jsp:include page="Footer.jsp" />

	<jsp:include page="Js.jsp" />
	<script type="text/javascript"
		src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="<%=basePath%>pages/js/community/volunteerActivity.js"></script>
</body>
</html>
