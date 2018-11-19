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
<body>
	<jsp:include page="Header.jsp" />

	<section class="section">
	<div class="row-fluid">
		<jsp:include page="LeftMenu.jsp" />

		<div class="span9">
			<div class="content">
				<div class="content-header" id="admin_top">
					<h2>
						<i class="icofont-table"></i>管理员个人中心
					</h2>
				</div>
				<div class="content-breadcrumb">
					<ul class="breadcrumb">
						<li><h6>
								<a href="<%=basePath%>pages/index.jsp"><i
									class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span>
							</h6></li>
						<li><h6>
								<a href="<%=basePath%>pages/ModifyPwd.jsp"><i
									class="icofont-user"></i> 个人中心</a> <span class="divider">&rsaquo;</span>
							</h6></li>
						<li><h6>
								<a href="#">修改密码</a>
							</h6></li>
					</ul>
				</div>
				<div class="content-body">
					
					<div id="modifyPwd_form_toolbar" class="row-fluid">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>请填写密码（请先填写旧密码，然后输入新密码）</span>
								</div>
								<div class="box-body">
									<!--wizard-->
									<div id="form-horizontal">
										<form id="modifyPwd_form" class="form-horizontal">
											<fieldset>
												<div class="row-fluid">
													<div class="span12">
														<div class="box-tab corner-all">
														<!-- 	<div class="box-body"> -->
																<div class="tab-content">
																		<div class="control-group" style="display:none">
																			<label class="control-label">管理员uuid :</label>
																			<div class="controls">
																				<input id="admin_uuid" type="text" value="<%=admin.getUuid() %>"
																					name="admin.uuid" maxlength="15"
																					class="grd-white" style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">旧密码：*</label>
																			<div class="controls">
																				<input type="password" id="old_pwd"
																					maxlength="15" class="grd-white require"
																					style="height:30px" />
																				<label id="oldPwd_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> 
																			</div>
																			<s:actionerror cssStyle="color:red"/>
																		</div>
																		<div class="control-group">
																			<label class="control-label">新密码：*</label>
																			<div class="controls">
																				<label id="newPwd1_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> <input type="password"
																					id="new_pwd1" 
																					maxlength="15" class="grd-white require"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">再次输入新密码：*</label>
																			<div class="controls">
																				<label id="newPwd2_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label>  <input type="password"
																					id="new_pwd2" 
																					maxlength="18" class="grd-white require"
																					style="height:30px" />
																			</div>
																		</div>		
																		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<label id="message"
																			class="text-error helper-font-small"
																			style="display:none;font-size: 15px"></label>
																		</div>						
																</div>

															<!-- </div> -->
															<div class="form-actions">																
																<button type="button" id="modify"
																	class="btn btn-primary">修改</button>
															</div>
														</div>
													</div>
													<!--/span-->
												</div>
											</fieldset>
										</form>
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
	<script src="<%=basePath%>pages/js/admin/modifyPwd.js"></script>
</body>
</html>
