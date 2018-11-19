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
<title>系统参数管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="stilearning" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="css.jsp" />

<style type="text/css">
#DialogDiv,#addlink1 {
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
#DialogDiv h2,#addlink1 h2 {
	height: 25px;
	font-size: 14px;
	background-color:  #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}

#DialogDiv h2 a,#addlink1 h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;
}

#DialogDiv .form,#addlink1 .form {
	padding: 10px;
}

</style>
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
						<i class="icofont-table"></i> 系统参数管理
					</h2>
				</div>
				<!-- /content-header 右侧图标-->

				<!-- content-breadcrumb -->
				<div class="content-breadcrumb">
					<!--breadcrumb-nav -->

					<!--/breadcrumb-nav  右侧图标-->

					<!--breadcrumb-->
					<ul class="breadcrumb">
						<li><h6><a href="<%=basePath  %>pages/index.jsp"><i class="icofont-home"></i> 主页</a>
							<span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a href="#"><i class="icofont-user"></i>系统参数类型</a></h6></li>
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
									<input type="button" value="添加参数类型" onclick="javascript:addLink();" class="btn btn-info">
										<lable style="color: red"><s:actionerror cssStyle="color:red"/></lable>
								</div>
								<div class="box-body">
									<!--  表格顶部的菜单- -->
									<div class='row-fluid'>
									
										<!-- 添加 -->
										<div id="addlink1" style="display: none">
											<h2 id="xinjian">
												<span>添加参数类型:</span> <a href="#" id="btnClosed">关闭</a>
											</h2>
											<div class="form">
											<form id="addSystemMeta_form"  method="post">
												<center>
												参数名称:<input style="height: 30px" class="grd-white" type="text" name="systemMetaVo.parentMetaName" id="pn"><br>
												<lable style="color: red" id="pn_Lable"></lable>
												</center><br>
												<center>参数昵称:<input style="height: 30px" class="grd-white" type="text" name="systemMetaVo.metaNickName"></center><br>
												<center><button id="add" class="btn btn-info" type="reset">添加</button></center>
											</form>
											
											</div>
										</div>

										<!-- 修改 -->
										<div id="DialogDiv" style="display:none ">
											<h2 id="bianji">
												<span>编辑参数类型： </span><a href="#" id="btnClose">关闭</a>
											</h2>
											<div class="form">
											<input id="uuid" type="hidden" name="systemMetaVo.uuid">
											<center>
												参数名称：<input type="text"  id="parentMetaName" style="height: 30px" class="grd-white"
													name="systemMetaVo.parentMetaName"><br>
												<lable style="color: red" id="parentMetaName_Lable"></lable>
											</center>
											<br>
											<center>
												参数昵称：<input type="text" id="metaNickName"  style="height: 30px" class="grd-white"
													name="systemMetaVo.metaNickName">
											</center>
											<br>
											<center>
												<input class="btn btn-info" id="modify" type="reset" value="提交"
													onclick="javascript:modify();">
											</center><br>
											</div>
										</div>						
										

										</div>
										<!-- /表格顶部的菜单-  -->


										<table id="datatablestools"
											class="table table-hover responsive">
											<thead>
												<tr>

													<th style='display:none;'>uuid</th>

													<th>参数名称</th>
													<th>参数昵称</th>
													<th>修改时间</th>
													<th colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;操&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作</th>
												</tr>
											</thead>
											<tbody>


											</tbody>
										</table>

										<!--    分页开始 -->
										<div class='row-fluid'>
											<div class="span6">
												<div id="sysm_paging"
													class="dataTables_paginate paging_bootstrap pagination">
													<ul>
													</ul>
												</div>
											</div>
											<div class="span5">
										<p ><span>总共<a id="totalNum"></a>条记录</span></p>
										</div>
										</div>
										<!-- <div style="display: none;" >
										<input style="display: none" id="totalPage" value="">
									</div> -->
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
		src="<%=basePath%>pages/js/systemMeta/systemMetaTypeManager.js"></script>


</body>
</html>
