<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("/RestHome/pages/login.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">
<head>
<title>考勤管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="stilearning" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>pages/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="<%=basePath%>pages/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<jsp:include page="css.jsp" />
</head>

<body>


	<!-- section header -->
	<jsp:include page="Header.jsp" />
	<!-- section content -->
	<section class="section">
	<div class="row-fluid">
		<jsp:include page="LeftMenu.jsp" />
		<!-- span content -->
		<div class="span9">
			<!-- content -->
			<div class="content">
				<!-- content-header 右侧图标 -->
				<div class="content-header">

					<h2>
						<i class="icofont-table"></i> 考勤管理
					</h2>
				</div>
				<!-- /content-header 右侧图标-->

				<!-- content-breadcrumb -->
				<div class="content-breadcrumb">
					<!--breadcrumb-nav -->

					<!--/breadcrumb-nav  右侧图标-->

					<!--breadcrumb-->
					<ul class="breadcrumb">
						<li><h6><a href="<%=basePath %>pages/index.jsp"><i class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span></h6></li>
                        <li><h6><a href="<%=basePath %>pages/Employee2.jsp"><i class="icofont-user"></i> 员工管理</a> <span class="divider">&rsaquo;</span></h6></li>
                        <li><h6><a href="<%=basePath %>pages/AddCheckIn.jsp">录入考勤</a></h6></li>
                    </ul>
					<!--/breadcrumb-->
				</div>
				<!-- /content-breadcrumb -->

				<!-- content-body -->
				<div class="content-body">
					<!--datatables tools  -->
					<div class="row-fluid">
					<div class="span12">
						<div class="box corner-all">
							<div class="box-header grd-white corner-top">
								<div class="header-control">
									<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									<!--  <a data-box="close" data-hide="bounceOutRight">&times;</a> -->
								</div>
								<span>添加员工考勤信息</span>
							</div>
							<div class="box-body">
								<!-- /表格顶部的菜单-  -->
								<tbody>
									<form id="addCheckIn_form" method="post">
										<fieldset>
											<div class="row-fluid">
												<div class="span12">
													<br>
													
													<div class="control-group" align="center">
															<label class="control-label col-md-2"> 员工编号&nbsp;*&nbsp; </label> 
															<div class="controls col-md-3"><input
																id="employeeNo" type="text"style="height:30px;width: 400px" 
																class="grd-white" name="employee.employeeNo" />															
															</div>
															<lable id="employeeNo_Lable" style="color: red"></lable><br>
															<lable style="color: red"><s:actionerror cssStyle="color:red"/></lable>
													</div>
													<br>
													
													
													<div class="control-group" align="center">
														<label class="col-md-2 control-label"> 考勤序号&nbsp;*&nbsp;</label>
														<div class="controls col-md-3">
															<input id="checkNo" type="text" name="checkIn.checkNo" readonly="readonly"
																style="height: 30px;width: 400px" class="grd-white"/>															
														</div>
														<lable id="checkNo_Lable" style="color: red"></lable><br>
													</div>												
													<br>

													<div class="form-group" align="center">
														<label for="dtp_input3" class="col-md-2 control-label">开始时间&nbsp;*&nbsp;</label>
														<div class="input-group date form_time col-md-4"
															data-date="" data-date-format="hh:ii"
															data-link-field="dtp_input3" data-link-format="hh:ii">
															<input class="form-control grd-white"  type="text" style="height: 30px;disabled:disabled;"
																name="checkIn.beginTime" id="beginTime" readonly/> <span
																class="input-group-addon"><span
																class="glyphicon glyphicon-time"></span></span>															
														</div>
														<lable id="beginTime_Lable" style="color: red"></lable>
														<br />
													</div><br>
													
													<div class="form-group" align="center">
														<label for="dtp_input3" class="col-md-2 control-label">结束时间&nbsp;*&nbsp;</label>
														<div class="input-group date form_time col-md-4"
															data-date="" data-date-format="hh:ii"
															data-link-field="dtp_input3" data-link-format="hh:ii">
															<input class="form-control grd-white"  type="text" style="height: 30px;disabled:disabled;"
																name="checkIn.endTime" id="endTime" readonly/> <span
																class="input-group-addon"><span
																class="glyphicon glyphicon-time"></span></span>																
														</div>
														<lable id="endTime_Lable" style="color: red"></lable><br />
													</div><br>
													

													<div class="form-group" align="center">
														<label for="dtp_input2" class="col-md-2 control-label">开始日期&nbsp;*&nbsp;</label>
														<div class="input-group date form_date col-md-4"
															data-date="" data-date-format="yyyy-MM-dd"
															data-link-field="dtp_input2"
															data-link-format="yyyy-mm-dd">
															<input class="form-control grd-white" type="text"
																style="height: 30px;disabled:disabled;"
																name="checkIn.beginDate" id="beginDate" readonly /> <span
																class="input-group-addon"><span
																class="glyphicon glyphicon-calendar"></span></span>																
														</div>
														<lable id="beginDate_Lable" style="color: red"></lable><br />
													</div>
													<br>

													<div class="form-group" align="center">
														<label for="dtp_input2" class="col-md-2 control-label">结束日期&nbsp;*&nbsp;</label>
														<div class="input-group date form_date col-md-4"
															data-date="" data-date-format="yyyy-MM-dd"
															data-link-field="dtp_input2"
															data-link-format="yyyy-mm-dd">
															<input class="form-control grd-white" type="text"
																style="height: 30px;disabled:disabled;"
																name="checkIn.endDate" id="endDate" readonly /> <span
																class="input-group-addon"><span
																class="glyphicon glyphicon-calendar"></span></span>																
														</div>
														<lable id="endDate_Lable" style="color: red"></lable><br />
													</div>
													<br>
													
													


													<div class="control-group" align="center">
														<label class="col-md-2 control-label">时间长度 &nbsp;*&nbsp;</label>
														<div class="controls col-md-3">
															<input type="text" name="checkIn.tiemLength" id="tiemLength"
																style="height: 30px;width: 400px;" class="grd-white" size="16"/>
														</div><br>
													</div>
													<br>
													<div class="control-group" align="center">
														<label class="col-md-2 control-label">理 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;由 &nbsp;*&nbsp;</label>
														<div class="controls col-md-3">
															<input type="text" name="checkIn.reson" id="reson"
																style="height: 30px;width: 400px;" class="grd-white" size="16"/>
														</div><br>
													</div>
													<br>
													<div class="form-group" align="center">
														<label class="col-md-2 control-label">理由类型 &nbsp;*&nbsp;</label>
														<div class="input-group col-md-3">
															<select id="reasonType" name="checkIn.resonType" class="form-control" style="height: 30px;width: 400px;" >
															</select>
														</div><br>
													</div>
													<br>

													<div class="form-actions" align="center">
														
															<button type="button" id="addCheckIn_submit"
																class="btn btn-info">添加</button>
														
													</div>
												</div>
											</div>
											</fieldset>
									</form>
								</tbody>

							</div>

							<!-- /box-body -->
						</div>
						<!-- /box -->
						</div>
						<!-- /span -->
					</div>
					<!--/datatables tools-->
					<!--/tables-->
				</div>
				<!--/content-body -->
			</div>
			<!-- /content -->
		</div>
		<!-- /span content -->

		<div id="params"></div>
		<!-- <div id="deptParams" style='display:none;'></div> -->
		<!--  <div id="CEO" style="display:none;"><s:property value="#session.CEO."/>  </div>-->
		<%-- <jsp:include page="Right.jsp" /> --%>
	</div>
	</section>

	<!-- section footer -->
	<jsp:include page="Footer.jsp" />
	<jsp:include page="Js.jsp" />

	<script type="text/javascript"
		src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>pages/js/datepicker/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>pages/js/employee/addCheckIn.js"></script>


</body>
</html>
