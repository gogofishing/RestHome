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
</head>
<style type="text/css">
    #addAdmin{
	position: absolute;
	width: 400px;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	height: auto;
	z-index: 100;
	background-color: #ccdddd;
	border: 1px #8FA4F5 solid;
	padding: 1px;
}
#addAdmin h2{
	height: 25px;
	font-size: 14px;
	background-color: #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}
#addAdmin h2 a{
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;	
}
#addAdmin .form{
	padding: 10px;
}
</style>
<body>
	<jsp:include page="Header.jsp" />

	<section class="section">
	<div class="row-fluid">
		<jsp:include page="LeftMenu.jsp" />

		<div class="span9">
			<div class="content">
				<div class="content-header" id="adminManager_top">
					<h2>
						<i class="icofont-table"></i>管理员设置
					</h2>
				</div>
				<div class="content-breadcrumb">
					<ul class="breadcrumb">
						<li><h6><a href="<%=basePath%>pages/index.jsp"><i class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a href="<%=basePath%>pages/AdminManager.jsp"><iclass="icofont-user"></i> 管理员管理</a> <span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a >管理员信息</a></h6></li>
					</ul>
				</div>
				<div class="content-body">
					<div id="adminManager_showMessage" class="label label-warning"
						style="display:none;font-size: 14px;font-weight: bold;"></div>
					<s:actionerror cssStyle="color:red"/>
					<div id="addAuthority_form_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>分配权限</span>
								</div>
								<div class="box-body">
									<!--wizard-->
									<div id="form-horizontal">
										<tbody>
										<form id="addAuthority_form">
											<fieldset>
													<div class="row-fluid">
														<div class="span12">
															<br>

															<div class="control-group" style="display:none">
																<label class="control-label">管理员uuid :</label>
																<div class="controls">
																	<input id="admin_uuid" type="text"
																		name="admin.uuid" maxlength="15"
																		class="grd-white" style="height:30px" />
																</div>
															</div>
															<br>
															<div class="control-group" align="center">
																<div class="controls col-md-3">																		
																	<label><input type="checkbox" name="oldpeopleAuthority" id="oldpeopleAuthority" value="">&nbsp;&nbsp;&nbsp;老人管理</label>																 
																</div>
															</div>
															<br>
															<div class="control-group" align="center">
																<div class="controls col-md-3">																		
																	<label><input type="checkbox" name="employeeAuthority" id="employeeAuthority" value="">&nbsp;&nbsp;&nbsp;员工管理</label>																 
																</div>
															</div>
															<br>
															<div class="control-group" align="center">
																<div class="controls col-md-3">																		
																	<label><input type="checkbox" name="communityAuthority" id="communityAuthority" value="">&nbsp;&nbsp;&nbsp;社区管理</label>															 
																</div>
															</div>
															<br>
															<div class="control-group" align="center">
																<div class="controls col-md-3">																		
																	<label><input type="checkbox" name="storageAuthority" id="storageAuthority" value="" >&nbsp;&nbsp;&nbsp;仓库管理</label>															 
																</div>
															</div>
															<br>
															<div class="control-group" align="center">
																<div class="controls col-md-3">																		
																	<label><input type="checkbox" name="systemMetaAuthority" id="systemMetaAuthority" value="">&nbsp;&nbsp;&nbsp;参数管理</label>																 
																</div>
															</div>
															<br>
																												

															<div class="form-actions" align="center">
																<button type="button" id="add_authority"
																	class="btn btn-success">保存</button>
																<!-- <button type="button" id="addAuthority_modify"
																	class="btn btn-primary">修改</button> -->
																<button type="button" id="addAuthority_cancel"
																	class="btn">取消</button>																
															</div>
																
															<!--/span12-->
														</div>
													</div>
												</fieldset>
										</form>
									</tbody>
									</div>

								</div>

							</div>
						</div>
					</div>

					<div class="row-fluid" id="adminManager_table_toolbar">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<a class="btn btn-info" id="adminManager_add"> <span>新增管理员</span>
									</a>
								</div>
								<div class="box-body">
									<div class='row-fluid'>
																		
										<!-- 新增 -->
                                        <div id="addAdmin" style="display: none">
											<h2>
												<span>新增管理员:</span> <a href="/RestHome/pages/AdminManager.jsp" id="btnClose">关闭</a>
											</h2>
											<div class="form">
											<form id="addAmin_form"  method="post">
												<center>
												管理员姓名&nbsp;&nbsp;:&nbsp;&nbsp;<input style="height: 30px;width:200px;" class="grd-white" type="text" name="admin.adminName" id="adminName"><br>
												<lable style="color: red" id="adminName_Lable"></lable>
												</center><br>										
												<center><button id="add_admin" class="btn btn-info" type="reset">新增</button></center>
											</form>											
											</div>
										</div>										
										
										
										
										<!-- 搜索框 -->
										<div class="span3">
											<div class="control-group">
												<div class="input-append">
													<input type="text" id="adName"
														class="grd-white" style="height:30px" maxlength="11"
														placeholder="请输入管理员姓名查询" />
												</div>
											</div>
										</div>																	
										<div class="span3">
											<div class="controls">
												<div class="form-search">
													<button type="button" id="adminManager_search"
														class="btn btn-info">搜索</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 列表显示 -->
									<table id="adminManager_table"
										class="table table-hover responsive">
										<thead>
											<tr>
												<th style='display:none;'>uuid</th>
												<th><span class="badge">管理员姓名</span></th>														
												<th colspan="2"><span class="badge">操作</span></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>

									<!-- 分页 -->
									<div class='row-fluid'>
										<div class="span6">
											<div id="adminManager_paging"
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
	<script src="<%=basePath%>pages/js/admin/adminManager.js"></script>
</body>
</html>
