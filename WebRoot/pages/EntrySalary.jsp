<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("/RestHome/pages/login.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="en">
<head>
<title>工资管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="stilearning" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=basePath%>pages/css/bootstrap.min.css" rel="stylesheet" media="screen">
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
						<i class="icofont-table"></i> 工资管理
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
                        <li><h6><a href="<%=basePath %>pages/EntrySalary.jsp">录入工资</a></h6></li>
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
									<span>录入工资</span>
								</div>
								<div class="box-body">
									<!-- /表格顶部的菜单-  -->

									<tbody>
									<form id="form1"  method="post" target="_blank">
									<fieldset>
									<div class="row-fluid">
											<div class="span12">
									
									<input id="empUuid"  style="display: none;" name="employee.uuid" ><br>
									<input id="salaryUuid" style="display: none;" name="salary.uuid">
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 基本工资&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="PBasicSalary" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white"/>														
											</div><br>
									</div><br>
													
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 岗位工资&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="PPositionSalary" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white"/>													
											</div><br>
									</div><br>
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 保险基数&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="baoxianjishu" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">													
											</div><br>
									</div><br>	
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 住房基数&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="zhufangjishu" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">											
											</div><br>
									</div><br>							
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 保险补偿金&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="PBaoxianbuchangjin" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">		
											</div><br><br><br><br>
									</div><br>
																
									<div class="control-group" align="center">
									<label class="control-label col-md-2"> 平日加班&nbsp;:&nbsp; </label> 
									<div class="controls col-md-3"><input type="text" class="shuzhi grd-white" style="height:30px;width: 400px"   name="salary.PNormalPlusSalary" id="PNormalPlusSalary" maxlength="20" />
									<label class="shuzhilabel" style="color: red"></label></div> <br>									
									</div>
									<br>
									
									<div class="control-group" align="center">
									<label class="control-label col-md-2"> 公休加班&nbsp;:&nbsp; </label> 
									<div class="controls col-md-3"><input type="text" class="shuzhi grd-white" style="height:30px;width: 400px"   name="salary.PHolidaySalary" id="PHolidaySalary" maxlength="20" />
									<label class="shuzhilabel" style="color: red"></label></div><br><br><br><br>
										</div>
									<br>
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 养老保险（个人）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="PYanglaobaoxian" disabled="disabled" type="text" style="height:30px;width: 400px" class="grd-white">
											</div><br>
									</div><br>
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 失业保险（个人）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="PShiyebaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>
											
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 住房保险（个人）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="PZhufangbaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>		
									
									<div class="control-group" align="center">
									<label class="control-label col-md-2"> 大额保险（个人）&nbsp;:&nbsp; </label> 
									<div class="controls col-md-3"><input type="text" class="shuzhi grd-white" style="height:30px;width: 400px"   id="PDaebaoxian" name="salary.PDaebaoxian" id="PDaebaoxian" ><label class="shuzhilabel" style="color: red"></label></div><br>
									</div><br>
									
									<div class="control-group" align="center">
									<label class="control-label col-md-2"> 冷暖费（个人）&nbsp;:&nbsp; </label> 
									<div class="controls col-md-3"><input type="text" class="shuzhi grd-white" style="height:30px;width: 400px"  id="PLengnuanfei" name="salary.PLengnuanfei">
									<label class="shuzhilabel" style="color: red"></label></div><br><br><br><br>
									</div><br>
									
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 养老保险（公司）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="CYanglaobaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>	
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2"> 医疗保险（公司）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="CYiliaobaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>	
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2">工商保险（公司）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="CGongshangbaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2">失业保险（公司）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="CShiyebaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2">生育保险（公司）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="CShengyubaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>
									
									<div class="control-group" align="center">
										<label class="control-label col-md-2">住房保险（公司）&nbsp;:&nbsp; </label> 
											<div class="controls col-md-3"><input id="CZhufangbaoxian" disabled="disabled" type="text" style="height:30px;width: 400px"  class="grd-white">
											</div><br>
									</div><br>
								
																		
									<div class="form-actions" align="center">
										<input id="addSalary" class="btn btn-info" type="reset" value="录入">
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
		src="<%=basePath%>pages/js/employee/entrySalary.js"></script>


</body>
</html>
