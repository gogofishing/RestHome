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
<link href="<%=basePath%>pages/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="<%=basePath%>pages/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
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
						<i class="icofont-table"></i> 考勤列表
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
                        <li><h6><a href="<%=basePath %>pages/CheckInManager.jsp">考勤记录</a></h6></li>
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
									<span>考勤管理</span>
								</div>
								<div class="box-body">
									<!--  表格顶部的菜单- -->
									<div class='row-fluid'>

										<div>					

											<div class="control-group">												
												<div class="controls col-md-3">
													<input  type="text" id="empNo" placeholder="请输入员工编号查询"
														name="checkIn.employee.employeeNo" maxlength="11"
														class="grd-white" style="height:30px" />
												</div>
											</div>


											<div class="input-group date form_date col-md-3" data-date=""
												data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
												data-link-format="yyyy-mm-dd">
												<input class="form-control grd-white" type="text"
													style="height:30px" name="checkIn.beginDate" id="beginDate"
													placeholder="开始日期" /> <span class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
											<div class="input-group date form_date col-md-3" data-date=""
												data-date-format="yyyy-MM-dd" data-link-field="dtp_input2"
												data-link-format="yyyy-mm-dd">
												<input class="form-control grd-white" type="text"
													style="height:30px" name="checkIn.endDate" id="endDate"
													placeholder="结束日期" /> <span class="input-group-addon"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
											<div class="col-md-1">
											<input id="sousuo" type="submit" value="搜索" class="btn btn-info"></div>
										</div>
										
									</div>
									<!-- /表格顶部的菜单-  -->


									<table id="datatablestools"
										class="table table-hover responsive">
										<thead>
											<tr>

												<th style='display:none;'>uuid</th>

												<th><span class="badge">员工编号</span></th>
												<th><span class="badge">员工名字</span></th>
												<th><span class="badge">序号</span></th>										
											
												<th><span class="badge">时间长度</span></th>
												<th><span class="badge">理由类型</span></th>
												<th colspan="2"><span class="badge">操作</span></th>

											</tr>
										</thead>
										<tbody>




										</tbody>
									</table>

									<!--    分页开始 -->
									<div class='row-fluid'>
										<div class="span6">
											<div id="checkIn_paging"
												class="dataTables_paginate paging_bootstrap pagination">
												<ul>
												</ul>
											</div>
										</div>
										<div class="span5">
										<p ><span>总共<a id="totalNum"></a>条记录</span></p>
										</div>
									</div>

									<!--    分页完成 -->

								</div>
								<div style="display:none;" id="nowPage">1</div>
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

		<!-- <div id="params"></div> -->
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
		src="<%=basePath%>pages/js/employee/checkInManager.js"></script>


</body>
</html>
