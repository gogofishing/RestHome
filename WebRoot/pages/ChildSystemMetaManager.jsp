<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String metaValueType = request.getParameter("metaValueType");
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
<title>系统参数管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="stilearning" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="css.jsp" />

<style type="text/css">
#DialogDiv1 {
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

#DialogDiv1 h2 {
	height: 25px;
	font-size: 14px;
	background-color: #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}

#DialogDiv1 h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;
}

#DialogDiv1 .form {
	padding: 10px;
}

#DialogDiv2 {
	position: absolute;
	width: 800px;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	height: 500px;
	z-index: 100;
	background-color: #ccdddd;
	border: 1px #8FA4F5 solid;
	padding: 1px;
}

#DialogDiv2 h2 {
	height: 25px;
	font-size: 14px;
	background-color: #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}

#DialogDiv2 h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;
}

#DialogDiv2 .form {
	padding: 10px;
}

#addlink1 {
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

#addlink1 h2 {
	height: 25px;
	font-size: 14px;
	background-color: #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}

#addlink1 h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;
}

#addlink1 .form {
	padding: 10px;
}

#addlink2 {
	position: absolute;
	width: 800px;
	left: 50%;
	top: 50%;
	margin-left: -200px;
	height: 500px;
	z-index: 100;
	background-color: #ccdddd;
	border: 1px #8FA4F5 solid;
	padding: 1px;
}

#addlink2 h2 {
	height: 25px;
	font-size: 14px;
	background-color: #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}

#addlink2 h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;
}

#addlink2 .form {
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
						<li><h6><a href="index.jsp"><i class="icofont-home"></i> 主页</a>
							<span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a href="<%=basePath %>pages/SystemMetaTypeManager.jsp"><i class="icofont-user"></i>系统参数类型</a>
							<span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a  id="SystemMetaManager" href="#">系统父参数管理</a>
							<span class="divider">&rsaquo;</span></h6></li>
						<li><h6><a href="#">系统子参数管理</a></h6></li>
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
									<input class="btn btn-info" type="button" value="添加参数"
											onclick="javascript:addLink1();">&nbsp;&nbsp;&nbsp;&nbsp;
											<input class="btn btn-info" type="button" value="添加富文本框" onclick="javascript:addLink2();">
								</div>
								<div class="box-body">
									<!--  表格顶部的菜单- -->
									<div class='row-fluid'>
										
										<!-- 添加参数 -->
										<div id="addlink1" style="display: none">
											<h2 id="xinjian">
												<span>添加参数:</span> <a href="#" id="btnClosed1">关闭</a>
											</h2>
											<center>
												参&nbsp;&nbsp;数&nbsp;&nbsp;值&nbsp;:&nbsp;&nbsp; <input
													style="height: 30px" class="grd-white" type="text" id="metaValue"><br>
													<lable style="color: red" id="metaValue_Lable"></lable>
											</center>
											<br>
											<center>
												参数昵称&nbsp;：&nbsp;<input style="height: 30px" class="grd-white"
													type="text" id="metaNickName1">
											</center>
											<center>
												<input class="btn btn-info" id="add1" type="reset"
													value="添加参数">
											</center>
											<br>
										</div>

										<!-- 添加富文本框 -->
										<div id="addlink2"  style="display: none; z-index: 1">
											<h2 id="xinjian">
												<span>添加富文本框:</span> <a href="#" id="btnClosed2">关闭</a>
											</h2>
											<center>
												参数昵称&nbsp;：&nbsp;<input style="height: 30px" class="grd-white"
													type="text" id="metaNickName2"><br>
												<lable style="color: red" id="metaNickName2_Lable"></lable>
											</center>
											<br>
											<center>
												<div class="tab-pane" id="boxtabpill-4">
													<div class="control-group">
														<script id="htmlSource" name="content" type="text/plain">请输入内容</script>
														
													</div>
												</div>
											</center>
											<center>
												<input class="btn btn-info" id="add2"  type="reset"
													value="添加参数">
											</center>
										</div>

										<!-- 修改参数 -->
										<div id="DialogDiv1" style="display:none ">
												<h2 id="bianji">
													<span>编辑参数：</span> <a href="#" id="btnClose1">关闭</a>
												</h2>
											
												<input id="uuid1" type="hidden" name="systemMetaVo.uuid">
												<center>
													参数昵称：<input type="text" style="height: 30px" class="grd-white" id="nickName1">
												</center>
												<br>
												<center>
													参数值&nbsp;：&nbsp;&nbsp;<input type="text" style="height: 30px" class="grd-white" id="value"><br>
													<lable style="color: red" id="value_Lable"></lable>
												</center>
												<br>
												<center>
													ListOrder：<input type="text" style="height: 30px" class="grd-white" id="listOrder"><br>
													<lable style="color: red" id="listOrder_Lable"></lable>
												</center>
												<br>
												<center>
													<input class="btn btn-info" id="modify1" type="reset" value="提交"
														onclick="javascript:modify1();">
												</center><br>
											
											</div>
											
											<!-- 修改富文本框 -->
										<div id="DialogDiv2" style="display:none; z-index: 1 ">
											<h2 id="bianji">
												<span>编辑参数：</span> <a href="#" id="btnClose2">关闭</a>
											</h2>
											
											<input id="uuid2" type="hidden" name="systemMetaVo.uuid">
											<center>
												参数昵称：<input id="nickName2"  type="text" style="height: 30px" class="grd-white"><br>
												<lable style="color: red" id="nickName2_Lable"></lable>
											</center>
											<br>
											<center>
												<div class="tab-pane" id="boxtabpill-4">
													<div class="control-group">
														<script id="htmlSourceModify" name="content" type="text/plain">老人家庭信息</script>
													</div>
												</div>
											</center>
											<center>
												<input class="btn btn-info" id="modify2" type="reset"
													value="提交" onclick="javascript:modify2();">
											</center>
										
										</div>
										
											</div>
									<!-- /表格顶部的菜单-  -->


									<table id="datatablestools"
										class="table table-hover responsive">
										<thead>
											<tr>

												<th style='display:none;'>uuid</th>
												<th style='display:none;'>HtmlSource</th>

												<th>参数昵称</th>
												<th>参数值</th>												
												<th>List_Order</th>
												<th>修改时间</th>
												<th colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;操&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作</th>
												

											</tr>
										</thead>
										<tbody id="tbody1">


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
		src="<%=basePath%>pages/js/systemMeta/ChildSystemMetaManager.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ueditor/ueditor.config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript"
		src="<%=basePath%>ueditor/ueditor.all.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="<%=basePath%>ueditor/lang/zh-cn/zh-cn.js"></script>

	<script type="text/javascript">
		var familyHtmlUe = UE.getEditor('htmlSource', {
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
							'lineheight' ],
					[ 'inserttable','deletetable','splittocells','splittorows', 
						 'deleterow', 'splittocols','charts', 
						 'deletecol','insertrow', 'insertcol', 'mergecells','mergeright','mergedown', 'edittable'],
					],
			initialFrameHeight : 250,
			initialFrameWidth : 700
		});
		var familyHtmlModifyUe = UE.getEditor('htmlSourceModify', {
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
					],
					
			initialFrameHeight : 250,
			initialFrameWidth : 700
		});
	</script>

</body>
</html>
