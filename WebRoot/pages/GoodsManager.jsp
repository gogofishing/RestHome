<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
    <jsp:include page="css.jsp"/>
    <link href="<%=basePath%>pages/css/datepicker.css" rel="stylesheet">
</head>
<style type="text/css">
    #inStorage, #outStorage{
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
#inStorage h2, #outStorage h2{
	height: 25px;
	font-size: 14px;
	background-color: #ccdddd;
	position: relative;
	padding-left: 10px;
	line-height: 25px;
	color: #434343;
}
#inStorage h2 a,#outStorage h2 a {
	position: absolute;
	right: 5px;
	font-size: 12px;
	color: #434343;	
}
#inStorage .form,#outStorage .form {
	padding: 10px;
}
</style>
    <body>
       <jsp:include page="Header.jsp"/>
       
        <section class="section">
            <div class="row-fluid">
        		<jsp:include page="LeftMenu.jsp"/>

                <div class="span9">
                    <div class="content">
                        <div class="content-header" id="goods_top">
                            <h2><i class="icofont-table"></i>仓库管理</h2>
                        </div>
                        <div class="content-breadcrumb">
                            <ul class="breadcrumb">
                                <li><h6><a href="<%=basePath %>pages/index.jsp"><i class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span></h6></li>
                                <li><h6><a href="<%=basePath %>pages/GoodsManager.jsp"><i class="icofont-user"></i> 仓库管理</a> <span class="divider">&rsaquo;</span></h6></li>
                                <li><h6><a href="<%=basePath %>pages/GoodsManager.jsp">物品信息</a></h6></li>
                            </ul>
                        </div>
                        <div class="content-body">
                         	<div id="goods_showMessage" class="label label-warning" style="display:none;font-size: 14px;font-weight: bold;"></div>
                          	<s:actionerror cssStyle="color:red"/>
                          	<div id="goods_form_toolbar" class="row-fluid" style="display:none">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white color-silver-dark corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <span>请填写物品信息（只有基本信息中带*号的为必填项，其余选填）</span>
                                        </div>
                                        <div class="box-body">
                                           
                                             <form id="goods_form" method="post" action="" class="form-horizontal" enctype="multipart/form-data">
                                <fieldset>
                                    <table class="table table-bordered invoice responsive" align="center">
		                                <tbody>
		                                    <tr>
		                                    	<td style='display:none;'><input name="good.uuid" id="goods_uuid">uuid</input> </td>
		                                        <td colspan="3" style="vertical-align:middle;width:50%;">入库时间 *</td>
		                                        <td colspan="3" style="text-align: center;vertical-align:middle;width:50%;">
		                                      <!--   <input id="goods_inDate" name="good.goodsInDate"></input></td> -->
							                         	<div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
							                         		<input id="goods_inDate" name="good.goodsInDate" class="grd-white" style="height:30px;disabled:disabled;" size="16" type="text" />
							                         		<span class="add-on"><i class="icon-th"></i></span>
							                         		<label id="goods_inDate_Label" class="text-error helper-font-small" style="display:none"></label>
							                         	</div>							                         	
							                    </td>
		                                    </tr>
		                                    <tr>
		                                        <td>物品名称 *</td>
		                                        <td colspan="2"><input id="goods_gdName" name="good.goodsName"></input>
		                                        <label id="goods_gdName_Label" class="text-error helper-font-small" style="display:none"></label></td>
		                                        <td>物品编号 *</td>
		                                        <td colspan="2"><input id="goods_gdNo" name="good.goodsNo" readonly="readonly"></input>
		                                        <label id="goods_gdNo_Label" class="text-error helper-font-small" style="display:none"></label></td>
		                                    </tr>
		                                    <tr>
		                                    	<td>采购员姓名 *</td>
		                                        <td colspan="2"><input id="goods_empName" name="employee.employeeName" style="color: grd-white;" readonly="readonly"></input>
		                                        </td>
		                                        <td>采购员编号 *</td>
		                                        <td colspan="2"><input id="goods_empNo" name="employee.employeeNo" style="color: grd-white;"></input>
		                                        <label id="goods_empNo_Label" class="text-error helper-font-small" style="display:none"></label></td>
		                                    </tr>
		                                    <tr>
		                                        <td>生产厂家 *</td>
		                                        <td colspan="2"><input id="goods_manufacturer" name="good.goodsManufacturer"></input>
		                                        <label id="goods_manufacturer_Label" class="text-error helper-font-small" style="display:none"></label></td>
		                                        <td>生产日期 *</td>
		                                        <td colspan="2">
		                            <!--             <input id="goods_productionDate" name="good.goodsProductionDate"></input> -->
							                         <div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
							                         	<input id="goods_productionDate" name="good.goodsProductionDate" class="grd-white" style="height:30px;disabled:disabled;" size="16" type="text" />
							                         	<span class="add-on"><i class="icon-th"></i></span>							                         	
							                         </div>					                         						                         
							                    <label id="goods_productionDate_Label" class="text-error helper-font-small" style="display:none"></label>									                     
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td>物品单价 *</td>
		                                        <td colspan="2"><input id="goods_price" name="good.goodsPrice"></input>
		                                         <label id="goods_price_Label" class="text-error helper-font-small" style="display:none"></label></td>
		                                        <td>库存数量 *</td>
		                                        <td colspan="2"><input id="goods_num" name="good.goodsNum"></input>
		                                         <label id="goods_num_Label" class="text-error helper-font-small" style="display:none"></label></td>
		                                    </tr>
		                                    <tr>
		                                    	<td colspan="6"><center>
		                                    		<button type="button" id="goods_submit" class="btn btn-success">保存</button>
		                                    		<button type="button" id="goods_modify" class="btn btn-primary">修改</button>
			                                		<button type="button" id="goods_cancel" class="btn">取消</button>
			                                </center></td>
		                                    </tr>
		             
		                                </tbody>
		                            </table>
		                            </fieldset>
		                            </form>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                       		
                       		<div class="row-fluid" id="goods_table_toolbar">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <a class="btn btn-info" id="goods_add">
												<span>新增物品</span>
											</a>
                                        </div>
                                        <div class="box-body">
                                        	<div class='row-fluid'>
                                        	
                                        	<!-- 入库 -->
                                                <div id="inStorage" style="display: none">
											<h2>
												<span>入库操作:</span> <a href="/RestHome/pages/GoodsManager.jsp" id="btnClose">关闭</a>
											</h2>
											<div class="form">
											<form id="inStorage_form"  method="post">
												<input id="inUuid" type="hidden" name="good.uuid" action="RestHome/Goods/in_Storage">
												<center>
												采购员编号&nbsp;&nbsp;:&nbsp;&nbsp;<input style="height: 30px;width:200px;" class="grd-white" type="text" name="employee.employeeNo" id="in_empNo"><br>
												<lable style="color: red" id="in_empNo_Lable"></lable>
												</center><br>
												<center>入&nbsp;库&nbsp;数量&nbsp;&nbsp;:&nbsp;&nbsp;<input style="height: 30px;width: 200px" class="grd-white" type="text" name="inStorage.inNum" id="in_inNum">
												<lable style="color: red" id="in_inNum_Lable"></lable>
												</center><br>
												<center>入&nbsp;库&nbsp;日期&nbsp;&nbsp;:&nbsp;&nbsp;
												<div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">							                                                             
							                         <input id="in_inDate" name="inStorage.inDate" class="grd-white" style="height:30px;width:150px;disabled:disabled;" size="16" type="text" />
							                         <span class="add-on"><i class="icon-th"></i></span>							                         	
							                    </div>
							                    <lable style="color: red" id="in_inDate_Lable"></lable>
							                    </center><br>
												<center><button id="inSr" class="btn btn-info" type="reset">入库</button></center>
											</form>
											
											</div>
										</div>
										
										<!-- 出库 -->
										 <div id="outStorage" style="display: none">
											<h2>
												<span>出库操作:</span> <a href="/RestHome/pages/GoodsManager.jsp" id="btnClosed">关闭</a>
											</h2>
											<div class="form">
											<form id="outStorage_form"  method="post">
												<input id="outUuid" type="hidden" name="good.uuid">
												<center>
												采购员编号&nbsp;&nbsp;:&nbsp;&nbsp;<input style="height: 30px;width:200px;" class="grd-white" type="text" name="employee.employeeNo" id="out_empNo"><br>
												<lable style="color: red" id="out_empNo_Lable"></lable>
												</center><br>
												<center>出&nbsp;库&nbsp;数量&nbsp;&nbsp;:&nbsp;&nbsp;<input style="height: 30px;width: 200px" class="grd-white" type="text" name="outStorage.outNum" id="out_outNum">
												<lable style="color: red" id="out_outNum_Lable"></lable>
												</center><br>
												<center>出&nbsp;库&nbsp;日期&nbsp;&nbsp;:&nbsp;&nbsp;
												<div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">							                                                             
							                         <input id="out_outDate" name="outStorage.outDate" class="grd-white" style="height:30px;width:150px;disabled:disabled;" size="16" type="text" />
							                         <span class="add-on"><i class="icon-th"></i></span>							                         	
							                    </div>
							                    <lable style="color: red" id="out_outDate_Lable"></lable>
							                    </center><br>
												<center><button id="outSr" class="btn btn-info" type="reset">出库</button></center>
											</form>
											
											</div>
										</div>
										
										<!-- 搜索框 -->
                                                 <div class="span4">
														<div class="controls">		                                                    
		                                                        <div class="input-append">
		                                                            <input type="text" id="goods_goodsName" class="grd-white" style="height:30px" 
		                                                            	placeholder="请输入物品名称查询"/>
		                                                        </div>		                                                   
		                                                </div>
                                                </div>
                                                 <div class="span4">
														<div class="controls">
		                                                    <div class="form-search">
		                                                    	 <button type="button" id="goods_search" class="btn btn-info">搜索</button>
		                                                    </div>
		                                                </div>
		                                         </div>
												<div class="span6" style="display:none;">
													<input type="text" id="goods_hidden" class="grd-white"/>
												</div>
											</div>
										<!-- 列表显示 -->
                                            <table id="goods_table" class="table table-hover responsive">
	                                            <thead>
													<tr>
														<th style='display:none;'>uuid</th>
														<th><span class="badge">物品编号</span></th>
														<th><span class="badge">物品名称</span></th>
														<th><span class="badge">采购员</span></th>							
														<th><span class="badge">物品数量</span></th>
														<th colspan="4"><span class="badge">操作</span></th>
													</tr>
												</thead>
												<tbody>
												</tbody>
                                            </table>
	                                    
	                                    <!-- 分页 -->   
	                                        <div class='row-fluid'>
	                                         	<div class="span6">
	                                         		<div id="goods_paging" class="dataTables_paginate paging_bootstrap pagination">
	                                       				<ul>
	                                       				</ul>
                                        			</div>
	                                			</div>
	                                			<div class="span5">
													<p ><span>总共<a id="totalNum"></a>条记录</span></p>
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
        
        <jsp:include page="Footer.jsp"/>
		 
		<jsp:include page="Js.jsp"/>
		<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.js"></script>
		<script src="<%=basePath%>pages/js/storage/storage.js"></script>
    </body>
</html>
