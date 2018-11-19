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
                                <li><h6><a href="<%=basePath %>pages/TakeMedicineRecord.jsp">老人用药记录</a></h6></li>
                            </ul>
                        </div>
                        <div class="content-body">
                         	<div id="takeMedicineRecord_showMessage" class="label label-warning" style="display:none;font-size: 14px;font-weight: bold;"></div>
                          	<div id="takeMedicineRecord_form_toolbar" class="row-fluid" style="display:none">
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
                                                <form id="takeMedicineRecord_form" class="form-horizontal">
                                                	<fieldset>
                                                		<div class="row-fluid">
                                                			<div class="span12">
                                                				<div class="control-group" style="display:none;">
			                                                         <label class="control-label">老人uuid：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="takeMedicineRecord_uuid_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <input type="text" id="takeMedicineRecord_uuid" style="height:30px"/>
			                                                         </div>
			                                                    </div>
                                                				<div class="control-group">
			                                                         <label class="control-label">老人编号：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="takeMedicineRecord_opNo_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <input type="text" id="takeMedicineRecord_opNo" maxlength="11"  style="height:30px" onblur="isExistOldPeople(this.value,document.getElementById('takeMedicineRecord_opNo_Label'))" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="return false" onchange="lostFocusGlobal(this)"/>
			                                                         </div>
			                                                    </div>
			                                                    <div class="control-group">
			                                                    	<label class="control-label">饭前与否：*</label>
			                                                    	<div class="controls">
			                                                    		<label id="takeMedicineRecord_beforeEat_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                         	<input type="radio" name="beforeEat" id="takeMedicineRecord_beforeEat1" value="是" checked="checked"/>饭前&nbsp;&nbsp;&nbsp;&nbsp;
			                                                            <input type="radio" name="beforeEat" id="takeMedicineRecord_beforeEat2" value="否" />饭后
			                                                    	</div>
			                                                    </div>
			                                                    <div class="control-group">
			                                                    	<label class="control-label">用药次数：*</label>
		                                                            <div class="controls">
		                                                            	<label id="takeMedicineRecord_takeTimes_Label" class="text-error helper-font-small" style="display:none"></label>
		                                                                <select id="takeMedicineRecord_takeTimes" data-form="select2" style="width:200px" onchange="takeTimes_combobox(this)">
		                                                                </select>
		                                                                <input type="text" id="tmr_takeTimes_other" maxlength="2"  style="height:30px;display:none;" onkeyup="this.value=this.value.replace(/[^\d]/g,'')" onpaste="return false" onchange="lostFocusGlobal(this)"/>
		                                                            </div>
			                                                    </div>
			                                                    <div class="control-group">
			                                                         <label class="control-label">起始时间：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="takeMedicineRecord_beginDate_Label" class="text-error helper-font-small" style="display:none"></label>
		                                                             	 <div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
			                                                                 <input id="takeMedicineRecord_beginDate" style="height:30px;disabled:disabled;" size="16" type="text" class="grd-white" onchange="lostFocusGlobal(this)"/>
			                                                              <span class="add-on"><i class="icon-th"></i></span>
			                                                             </div>			                                                            
			                                                            <!--  &nbsp;&nbsp;&nbsp;&nbsp;eg:日/月/年 -->
			                                                         </div>
			                                                    </div>
			                                                    <div class="control-group">
			                                                         <label class="control-label">结束时间：*</label>
			                                                         <div class="controls">
			                                                         	 <label id="takeMedicineRecord_endDate_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                             <div class="input-append date" data-form="datepicker" data-date="" data-date-format="yyyy-mm-dd">
			                                                                 <input id="takeMedicineRecord_endDate" style="height:30px;disabled:disabled;" size="16" type="text" class="grd-white" onchange="lostFocusGlobal(this)"/>
			                                                             <span class="add-on"><i class="icon-th"></i></span>
			                                                             </div>			                                                             
			                                                         </div>
			                                                    </div>
			                                                    <div class="control-group" id="oldPeopleOutInRecord_center">
			                                                    	<label class="control-label">处&nbsp;方&nbsp;药：*</label>
			                                                    	<div class="controls">
			                                                    		<label id="takeMedicineRecord_prescription_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                         	<input type="radio" name="ifPrescriptionMedicine" id="takeMedicineRecord_prescription1" value="是" checked="" />处方药&nbsp;&nbsp;&nbsp;&nbsp;
			                                                            <input type="radio" name="ifPrescriptionMedicine" id="takeMedicineRecord_prescription2" value="否" />非处方药
			                                                    	</div>
			                                                    </div>
			                                                    <div class="control-group">
		                                                         	<label class="control-label">用药时间：*</label>
			                                                         <div class="controls">
			                                                         	<label id="takeMedicineRecord_takeTime_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                        	<textarea id="takeMedicineRecord_takeTime" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
			                                                        </div>
		                                                     	</div>
			                                                    <div class="control-group">
		                                                         	<label class="control-label">用&nbsp;药&nbsp;量：*</label>
			                                                         <div class="controls">
			                                                         	<label id="takeMedicineRecord_medicineNum_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                        	<textarea id="takeMedicineRecord_medicineNum" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
			                                                        </div>
		                                                     	</div>
			                                                    <div class="control-group">
		                                                         	<label class="control-label">药品名：*</label>
			                                                         <div class="controls">
			                                                         	<label id="takeMedicineRecord_medicineName_Label" class="text-error helper-font-small" style="display:none"></label>
			                                                        	<textarea id="takeMedicineRecord_medicineName" class="span10" rows="8" style="resize:none" onchange="lostFocusGlobal(this)"></textarea>
			                                                        </div>
		                                                     	</div>
						                                        <div class="form-actions">
			                                                    	<button type="button" id="takeMedicineRecord_submit" class="btn btn-info">保存</button>
			                                                    	<button type="button" id="takeMedicineRecord_modify" class="btn btn-primary">修改</button>
			                                                        <button type="button" id="takeMedicineRecord_cancel" class="btn">取消</button>
			                                                    </div>
							                                </div>
                                                		</div>
                                                    </fieldset>
                                                </form>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </div> 
                            </div>
                       		<div class="row-fluid" id="takeMedicineRecord_table_toolbar">
                                <div class="span12">
                                    <div class="box corner-all">
                                        <div class="box-header grd-white corner-top">
                                            <div class="header-control">
                                                <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                            </div>
                                            <a class="btn btn-info" id="takeMedicineRecord_add">
												<span>新增记录</span>
											</a>
                                        </div>
                                        <div class="box-body">
                                        	<div class='row-fluid'>
                                                 <div class="span4">
	                                                  <div class="controls">
	                                               		  <input type="text" id="takeMedicineRecord_opNo_search" class="grd-white" maxlength="20"  style="height:30px;" 
	                                               		  	placeholder=" 请输入老人编号查询" />
	                                                  </div>
                                                 </div>
                                                 <div class="span4">
														<div class="controls">
		                                                        <div class="input-append">
		                                                            <input type="text" id="takeMedicineRecord_opName_search" class="grd-white" style="height:30px" 
		                                                            	placeholder=" 请输入老人姓名查询"/>
		                                                        </div>
		                                                </div>
                                                </div>
                                                <div class="span3">
														<div class="controls">
		                                                    <div class="form-search">
		                                                    	 <button type="button" id="takeMedicineRecord_search" class="btn btn-info">搜索</button>
		                                                    </div>
		                                                </div>
		                                        </div>
												<div class="span6" style="display:none;">
													<input type="text" id="takeMedicineRecord_hidden" class="grd-white"/>
												</div>
											</div>
                                            <table id="takeMedicineRecord_table" class="table table-hover responsive">
                                            </table>
	                                        <div class='row-fluid'>
	                                         	<div class="span6">
	                                         		<div id="takeMedicineRecord_paging" class="dataTables_paginate paging_bootstrap pagination">
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
                            
                            <div id="takeMedicineRecord_moreInfo_toolbar" class="invoice-container" style="display: none;">
                                <div class="page-header">
                                    <div class="pull-right">
                                        <!-- <img data-src="holder.js/120x120" class="img-circle" /> -->
                                        <a href="javascript:void(0)" class="a-btn grd-black" title="返回" id="takeMedicineRecord_scan_return">
											<span>返回</span>
                                        </a>
                                    </div>
                                    <h3>老人用药记录 <small>详细信息</small></h3>
                                </div>
                                <div class="invoice-table" style="margin-top: 70px;">
                                    <table class="table table-bordered invoice responsive" align="center">
		                                <tbody>
		                                    <tr>
		                                        <td>老人姓名</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_opName"></span></td>
		                                        <td>老人编号</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_opNo"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td>起始时间</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_beginDate"></span></td>
		                                        <td>结束时间</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_endDate"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td>是否饭前</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_beforeEat"></span></td>
		                                        <td>是否处方药</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_ifPrescriptionMedicine"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td>用药次数</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_takeTimes"></span></td>
		                                        <td>登记时间</td>
		                                        <td colspan="2"><span id="takeMedicineRecord_moreInfo_insertTime"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td style="width:16%;">用药时间</td>
		                                        <td colspan="5"><span id="takeMedicineRecord_moreInfo_takeTime"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td style="width:16%;">用药量</td>
		                                        <td colspan="5"><span id="takeMedicineRecord_moreInfo_medicineNum"></span></td>
		                                    </tr>
		                                    <tr>
		                                        <td style="width:16%;">药品名</td>
		                                        <td colspan="5"><span id="takeMedicineRecord_moreInfo_medicineName"></span></td>
		                                    </tr>
		                                </tbody>
		                            </table>
		                        </div>
                           </div>
                                
                        </div>
                    </div>
                </div>
              
           <%--     <jsp:include page="Right.jsp"/> --%>
            </div>
        </section>
        
        <jsp:include page="Footer.jsp"/>
		 
		<jsp:include page="Js.jsp"/>
		<script type="text/javascript" src="<%=basePath%>pages/js/datepicker/bootstrap-datepicker.js"></script>
		<script src="<%=basePath%>pages/js/oldPeople/takeMedicineRecord.js"></script>
    </body>
</html>