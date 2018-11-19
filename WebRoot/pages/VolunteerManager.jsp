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
				<div class="content-header" id="volunteer_top">
					<h2>
						<i class="icofont-table"></i>志愿者管理
					</h2>
				</div>
				<div class="content-breadcrumb">
					<ul class="breadcrumb">
						<li><h6>
								<a href="<%=basePath%>pages/index.jsp"><i
									class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span>
							</h6></li>
						<li><h6>
								<a href="<%=basePath%>pages/VolunteerManager.jsp"><i
									class="icofont-user"></i> 志愿者管理</a> <span class="divider">&rsaquo;</span>
							</h6></li>
						<li><h6>
								<a href="<%=basePath%>pages/VolunteerManager.jsp">志愿者信息</a>
							</h6></li>
					</ul>
				</div>
				<div class="content-body">
					<div id="volunteer_showMessage" class="label label-warning"
						style="display:none;font-size: 14px;font-weight: bold;"></div>
					<s:actionerror cssStyle="color:red"/>
					<div id="volunteer_form_toolbar" class="row-fluid"
						style="display:none">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white color-silver-dark corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<span>请填写志愿者信息（只有基本信息中带*号的为必填项，其余选填）</span>
								</div>
								<div class="box-body">
									<!--wizard-->
									<div id="form-horizontal">
										<form id="volunteer_form" class="form-horizontal">
											<fieldset>
												<div class="row-fluid">
													<div class="span12">
														<div class="box-tab corner-all">
														<!-- 	<div class="box-body"> -->
																<div class="tab-content">
																<!-- 	<div class="tab-pane fade active in" id="boxtabpill-1"> -->
																		<div class="control-group" style="display:none">
																			<label class="control-label">志愿者uuid :</label>
																			<div class="controls">
																				<input id="vlt_uuid" type="text"
																					name="volunteer.uuid" maxlength="15"
																					class="grd-white" style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">志愿者编号：*</label>
																			<div class="controls">
																				<!--  <label id="vlt_vlt_Label" class="text-error helper-font-small" style="display:none">不能为空！</label> -->
																				<input type="text" id="vlt_no"
																					name="volunteer.volunteerNo" maxlength="15"
																					readonly="readonly" class="grd-white require"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">志愿者姓名：*</label>
																			<div class="controls">
																				<label id="vlt_name_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> <input type="text"
																					id="vlt_name" name="volunteer.volunteerName"
																					maxlength="15" class="grd-white require"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">身份证号：*</label>
																			<div class="controls">
																				<label id="vlt_idCard_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> <input type="text"
																					id="vlt_idCard" name="volunteer.idCard"
																					maxlength="18" class="grd-white require"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">性别：*</label>
																			<div class="controls">
																				<label id="vlt_sex_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> <input type="radio"
																					name="volunteer.sex" id="vlt_sex_radios1" value="男"
																					checked="" />男&nbsp;&nbsp;&nbsp;&nbsp; <input
																					type="radio" name="volunteer.sex"
																					id="vlt_sex_radios2" value="女" />女
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">移动电话：*</label>
																			<div class="controls">
																				<label id="vlt_phone_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> <input type="text"
																					id="vlt_phone" name="volunteer.phone"
																					maxlength="20" class="grd-white require"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">E-Mail：</label>
																			<div class="controls">
																				<label id="vlt_email_Label"
																					class="text-error helper-font-small"
																					style="display:none"></label> <input type="text"
																					id="vlt_email" name="volunteer.email"
																					maxlength="40" class="grd-white"
																					style="height:30px" />
																			</div>
																		</div>
																		<div class="control-group">
																			<label class="control-label">家庭地址：</label>
																			<div class="controls">
																				<input type="text" id="vlt_address"
																					name="volunteer.address" maxlength="11"
																					class="grd-white require" style="height:30px" />
																			</div>
																		</div>

																	<!-- </div> -->
																</div>

															<!-- </div> -->
															<div class="form-actions">
																<button type="button" id="volunteer_submit"
																	class="btn btn-success">保存</button>
																<!-- <button type="button" id="finsh" class="btn btn-primary">完成</button> -->
																<button type="button" id="volunteer_modify"
																	class="btn btn-primary">修改</button>
																<button type="button" id="volunteer_cancel" class="btn">取消</button>
																<button type="button" id="volunteer_reset" class="btn" style="display: none"></button>
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

					<div class="row-fluid" id="volunteer_table_toolbar">
						<div class="span12">
							<div class="box corner-all">
								<div class="box-header grd-white corner-top">
									<div class="header-control">
										<a data-box="collapse"><i class="icofont-caret-up"></i></a>
									</div>
									<a class="btn btn-info" id="volunteer_add"> <span>新增志愿者</span>
									</a>
								</div>
								<div class="box-body">
									<div class='row-fluid'>



										<!-- 搜索框 -->
										<div class="span4">
											<div class="controls">
												<div class="input-append">
													<input type="text" id="volunteer_volunteerNo"
														class="grd-white" style="height:30px"
														placeholder="请输入志愿者编号查询" />
												</div>
											</div>
										</div>
										<div class="span4">
											<div class="controls">
												<div class="input-append">
													<input type="text" id="volunteer_volunteerName"
														class="grd-white" style="height:30px"
														placeholder="请输入志愿者姓名查询" />
												</div>
											</div>
										</div>
										<div class="span3">
											<div class="controls">
												<div class="form-search">
													<button type="button" id="volunteer_search"
														class="btn btn-info">搜索</button>
												</div>
											</div>
										</div>
									</div>
									<!-- 列表显示 -->
									<table id="volunteer_table"
										class="table table-hover responsive">
										<thead>
											<tr>
												<th style='display:none;'>uuid</th>
												<th><span class="badge">志愿者编号</span></th>
												<th><span class="badge">姓名</span></th>
												<th><span class="badge">性别</span></th>
												<th><span class="badge">联系电话</span></th>
												<th><span class="badge">工作时长</span></th>
												<th colspan="2"><span class="badge">操作</span></th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>

									<!-- 分页 -->
									<div class='row-fluid'>
										<div class="span6">
											<div id="volunteer_paging"
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
	<script src="<%=basePath%>pages/js/community/volunteer.js"></script>
</body>
</html>
