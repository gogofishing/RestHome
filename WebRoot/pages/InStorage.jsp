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
                                <li><h6><a href="<%=basePath %>pages/InStorage.jsp">入库记录</a></h6></li>
                            </ul>
                        </div>
                        <div class="content-body">
                       		<div class="row-fluid" id="inStorage_table_toolbar">
                                <div class="span12">
                                    <div class="box corner-all">
	                                    <div class="box-header grd-white corner-top">
										<div class="header-control">
											<a data-box="collapse"><i class="icofont-caret-up"></i></a>
										</div>
										<span>入库记录</span>
										</div>
                                        <div class="box-body">
                                        	<div class='row-fluid'>
                                        	
                                      
										
										<!-- 搜索框 -->
                                                 <div class="span4">
														<div class="controls">		                                                    
		                                                        <div class="input-append">
		                                                            <input type="text" id="inStorage_goodsName" class="grd-white" style="height:30px" 
		                                                            	placeholder="请输入物品名称查询"/>		                                                            	
		                                                        </div>		                                                   
		                                                </div>
                                                </div>
                                                <div class="span4">
														<div class="controls">		                                                  
	                                                        <div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
						                         				<input id="inStorage_inDate" class="grd-white" style="height:30px;disabled:disabled;" size="16"
						                         				placeholder="请输入入库日期查询" type="text" />
						                         				<span class="add-on"><i class="icon-th"></i></span>
						                         			</div>	                                               
		                                                </div>
                                                </div>
                                                 <div class="span3">
														<div class="controls">
		                                                    <div class="form-search">
		                                                    	 <button type="button" id="inStorage_search" class="btn btn-info">搜索</button>
		                                                    </div>
		                                                </div>
		                                         </div>
											</div>
										<!-- 列表显示 -->
                                            <table id="inStorage_table" class="table table-hover responsive">
	                                            <thead>
													<tr>
														<th style='display:none;'>uuid</th>
														<th><span class="badge">物品编号</span></th>
														<th><span class="badge">物品名称</span></th>
														<th><span class="badge">采购员编号</span></th>	
														<th><span class="badge">采购员姓名</span></th>							
														<th><span class="badge">入库数量</span></th>
														<th><span class="badge">入库日期</span></th>
														<th colspan="1"><span class="badge">操作</span></th>
													</tr>
												</thead>
												<tbody>
												</tbody>
                                            </table>
	                                    
	                                    <!-- 分页 -->   
	                                        <div class='row-fluid'>
	                                         	<div class="span6">
	                                         		<div id="inStorage_paging" class="dataTables_paginate paging_bootstrap pagination">
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
		<script src="<%=basePath%>pages/js/storage/inStorage.js"></script>
    </body>
</html>
