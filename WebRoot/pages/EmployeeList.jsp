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
						<i class="icofont-table"></i> 工资列表
					</h2>
				</div>
				<!-- /content-header 右侧图标-->

				<!-- content-breadcrumb -->
				<div class="content-breadcrumb">
					<!--breadcrumb-nav -->

					<!--/breadcrumb-nav  右侧图标-->

					<!--breadcrumb-->
					<ul class="breadcrumb">
						<li><h6><a href="index.jsp"><i class="icofont-home"></i> 主页</a>
							<span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a href="#">工资管理</a></h6></li>
						<!-- <li class="active">Data elements</li> -->
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
									<span>工资管理</span>
								</div>
								<div class="box-body">
									<!--  表格顶部的菜单- -->
									<div class='row-fluid'>					
																																		
												<input id="empNo" class="grd-white" placeholder="请输入员工编号查询" maxlength="20"
													type="text" style="height:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;									
												<input id="department" class="grd-white"  placeholder="请输入员工所属部门查询" maxlength="20"
												type="text" style="height:30px" />	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;																				
												<input id="sousuo" type="submit" value="搜索" class="btn btn-info">
									
									</div>
									<!-- /表格顶部的菜单-  -->


									<table id="datatablestools"
										class="table table-hover responsive">
										<thead>
											<tr>

												<th style='display:none;'>uuid</th>
												<th>员工编号</th>
												<th>员工名字</th>
												<th>部门 </th>
												<th>职位	</th>							
												<th>操作</th>
											</tr>
										</thead>
										<tbody>



										</tbody>
									</table>

									<!--    分页开始 -->
									<div class='row-fluid'>
										<div class="span6">
											<div id="empl_paging"
												class="dataTables_paginate paging_bootstrap pagination">
												<ul>						
												</ul>
												
											</div>
										</div>
										<div class="span5">
										<p ><span>总共<a id="totalNum"></a>条记录</span></p>
										</div>
									</div>
									<div style="display:none;" id="totalPage">
										<s:property value="pageVo.totalPage" />
									</div>
									<!--  <div style="display:none;" id="nowPage">1</div>-->

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
		src="<%=basePath%>pages/js/employee/employeeList.js"></script>


</body>
</html>
