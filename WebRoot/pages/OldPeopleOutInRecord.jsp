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
                        <div class="content-header" id="oldPeopleOutInRecord_top">
                            <h2><i class="icofont-table"></i>老人管理</h2>
                        </div>
                        <div class="content-breadcrumb">
                            <ul class="breadcrumb">
                                <li><h6><a href="<%=basePath %>pages/index.jsp"><i class="icofont-home"></i> 主页</a> <span class="divider">&rsaquo;</span></h6></li>
                                <li><h6><a href="<%=basePath %>pages/OldPeople.jsp"><i class="icofont-user"></i> 老人管理</a> <span class="divider">&rsaquo;</span></h6></li>
                                <li><h6><a href="<%=basePath %>pages/OldPeopleOutInRecord.jsp">老人进出信息记录</a></h6></li>
                            </ul>
                        </div>
                        <div class="content-body">
                         	<div id="oldPeopleOutInRecord_showMessage" class="label label-warning" style="display:none;font-size: 14px;font-weight: bold;"></div>
                          	<div id="oldPeopleOutInRecord_form_toolbar" class="row-fluid" style="display:none">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white color-silver-dark corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <span>请填老人进出信息</span>
                                        </div>
                                        <div class="box-body">
                                            <div id="form-horizontal">
                                                <form id="oldPeopleOutInRecord_form" class="form-horizontal">
                                                	<fieldset>
                                                		<div class="row-fluid">
                                                			<div class="span12">
                                                				<div class="control-group">
			                                                         <label class="control-label">老人编号：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="oldPeopleOutInRecord_opNo_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <input type="text" id="oldPeopleOutInRecord_opNo" maxlength="11"  style="height:30px" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="return false" onblur="isExistOldPeople(this.value,document.getElementById('oldPeopleOutInRecord_opNo_Label'))" onchange="lostFocusGlobal(this)"/>
			                                                         </div>
			                                                    </div>
                                                				<div class="control-group">
			                                                         <label class="control-label">员工编号：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="oldPeopleOutInRecord_empNo_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <input type="text" id="oldPeopleOutInRecord_empNo" maxlength="20"  style="height:30px" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="return false" onblur="isExistEmployee(this.value,document.getElementById('oldPeopleOutInRecord_empNo_Label'))" onchange="lostFocusGlobal(this)"/>
			                                                         </div>
			                                                    </div>
			                                                    <div class="control-group">
		                                                         	<label class="control-label">登记时间：*</label>
			                                                         <div class="controls">
			                                                         	<label id="oldPeopleOutInRecord_recordDate_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                         	<div class="input-append date" data-form="datepicker" data-date="2014-09-02" data-date-format="yyyy-mm-dd">
			                                                                <input readonly id="oldPeopleOutInRecord_recordDate" style="height:30px;disabled:disabled;" size="30" type="text" value="2014-09-02" onchange="lostFocusGlobal(this)"/>
			                                                                <span class="add-on"><i class="icon-th"></i></span>
			                                                            </div>
			                                                        </div>
		                                                     	</div>
			                                                    <div class="control-group">
			                                                         <label class="control-label">开始时间：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="oldPeopleOutInRecord_beginTime_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <div class="input-append date" data-form="datepicker" data-date="2014-09-02" data-date-format="yyyy-mm-dd">
			                                                                <input readonly id="oldPeopleOutInRecord_beginTime" style="height:30px;disabled:disabled;" size="30" type="text" value="2014-09-02" onchange="lostFocusGlobal(this)"/>
			                                                                <span class="add-on"><i class="icon-th"></i></span>
			                                                             </div>
			                                                             <span>具体时间点00:00~23:59(如：HH:MM)<span><input type="text" id="oldPeopleOutInRecord_beginTime2" maxlength="15" style="height:30px" onchange="lostFocusGlobal(this)"/>
			                                                         </div>
			                                                    </div>
			                                                    <div class="control-group">
			                                                         <label class="control-label">结束时间：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="oldPeopleOutInRecord_endTime_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <div class="input-append date" data-form="datepicker" data-date="2014-09-02" data-date-format="yyyy-mm-dd">
			                                                                <input readonly id="oldPeopleOutInRecord_endTime" style="height:30px;disabled:disabled;" size="30" type="text" value="2014-09-02" onchange="lostFocusGlobal(this)"/>
			                                                                <span class="add-on"><i class="icon-th"></i></span>
			                                                             </div>
			                                                         	 <span>具体时间点00:00~23:59(如：HH:MM)<span><input type="text" id="oldPeopleOutInRecord_endTime2" maxlength="15" style="height:30px" onchange="lostFocusGlobal(this)"/>
			                                                         </div>
			                                                    </div>
			                                                    <div class="control-group">
		                                                         	<label class="control-label">外出原因：*</label>
			                                                         <div class="controls">
			                                                         	<label id="oldPeopleOutInRecord_reason_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                        	<textarea id="oldPeopleOutInRecord_reason" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
			                                                        </div>
		                                                     	</div>
		                                                     	<div class="control-group">
		                                                         	<label class="control-label">陪同家属：*</label>
			                                                         <div class="controls">
			                                                         	<label id="oldPeopleOutInRecord_familyInfo_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                        	<textarea id="oldPeopleOutInRecord_familyInfo" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
			                                                        </div>
		                                                     	</div>
						                                        <div class="form-actions">
			                                                    	<button type="button" id="oldPeopleOutInRecord_submit" class="btn btn-success">保存</button>
			                                                        <button type="button" id="oldPeopleOutInRecord_cancel" class="btn">取消</button>
			                                                    </div>
							                                </div><!--/span-->
                                                		</div>
                                                    </fieldset>
                                                </form>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div> 
                            </div>
                       		<div class="row-fluid" id="oldPeopleOutInRecord_table_toolbar">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <a class="btn btn-info" id="oldPeopleOutInRecord_add">
												<span>新增记录</span>
											</a>
                                        </div>
                                        <div class="box-body">
                                        	<div class='row-fluid'>
                                                 <div class="span4">
	                                                  <div class="controls">
	                                               		  <input type="text" id="oldPeopleOutInRecord_opNo_search" class="grd-white" maxlength="20"  style="height:30px;" 
	                                               		  	placeholder="请输入老人编号查寻"	 />			<!-- onkeyup="this.value=this.value.replace(/[^\d]/g,'')" -->
	                                                  </div>
                                                 </div>
                                                 <div class="span4">
														<div class="controls">		                                                    
		                                                        <div class="input-append">
		                                                            <input type="text" id="oldPeopleOutInRecord_opName_search" class="grd-white" maxlength="20" style="height:30px" 
		                                                            	placeholder="请输入老人姓名查询"/>
		                                                        </div>		                                                   
		                                                </div>
                                                </div>
                                                 <div class="span3">
														<div class="controls">
		                                                    <div class="form-search">
		                                                    	 <button type="button" id="oldPeopleOutInRecord_search" class="btn btn-info">搜索</button>
		                                                    </div>
		                                                </div>
		                                         </div>
												<div class="span6" style="display:none;">
													<input type="text" id="oldPeopleOutInRecord_hidden" class="grd-white"/>
												</div>
											</div>
                                            <table id="oldPeopleOutInRecord_table" class="table table-hover responsive">
                                            </table>
	                                        <div class='row-fluid'>
	                                         	<div class="span6">
	                                         		<div id="oldPeopleOutInRecord_paging" class="dataTables_paginate paging_bootstrap pagination">
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
                            
                            <div id="oldPeopleOutInRecord_moreInfo_toolbar" class="invoice-container" style="display: none;">
                                <div class="page-header">
                                    <div class="pull-right">
                                        <!-- <img data-src="holder.js/120x120" class="img-circle" /> -->
                                        <a href="javascript:void(0)" class="a-btn grd-black" title="返回" id="oldPeopleOutInRecord_scan_return">
											<span>返回</span>
                                        </a>
                                    </div>
                                    <h3>老人进出记录 <small>详细信息</small></h3>
                                </div>
                                <div class="invoice-table" style="margin-top: 70px;">
                                    <table class="table table-bordered invoice responsive" align="center">
		                                <tbody>
		                                    <tr>
		                                        <td colspan="3" style="vertical-align:middle;width:50%;">登记时间</td>
		                                        <td colspan="3" style="text-align: center;vertical-align:middle;width:50%;"><span id="oldPeopleOutInRecord_moreInfo_recordDate"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td>老人姓名</td>
		                                        <td colspan="2"><span id="oldPeopleOutInRecord_moreInfo_opName"></span></td>
		                                        <td>老人编号</td>
		                                        <td colspan="2"><span id="oldPeopleOutInRecord_moreInfo_opNo"></span></td>
		                                    </tr>
		                                    <tr>
		                                    	<td>员工姓名</td>
		                                        <td colspan="2"><span id="oldPeopleOutInRecord_moreInfo_empName"></span></td>
		                                        <td>员工编号</td>
		                                        <td colspan="2"><span id="oldPeopleOutInRecord_moreInfo_empNo"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td>开始时间</td>
		                                        <td colspan="2"><span id="oldPeopleOutInRecord_moreInfo_beginTime"></span></td>
		                                        <td>结束时间</td>
		                                        <td colspan="2"><span id="oldPeopleOutInRecord_moreInfo_endTime"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td style="width:16%;">外出原因</td>
		                                        <td colspan="5"><span id="oldPeopleOutInRecord_moreInfo_reason"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td style="width:16%;">陪同家属</td>
		                                        <td colspan="5"><span id="oldPeopleOutInRecord_moreInfo_familyInfo"></span></td>
		                                    </tr>
		                                </tbody>
		                            </table>
		                        </div>
                           </div>
                                
                        </div>
                    </div>
                </div>
              
               <%-- <jsp:include page="Right.jsp"/> --%>
            </div>
        </section>
        
        <jsp:include page="Footer.jsp"/>
		 
		<jsp:include page="Js.jsp"/>
		<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.js"></script>
		<script src="<%=basePath%>pages/js/oldPeople/oldPeopleOutInRecord.js"></script>
    </body>
</html>
