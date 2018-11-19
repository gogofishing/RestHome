<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>用户登录</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="" />
        <meta name="author" content="stilearning" />
        
        <!-- styles -->

        <jsp:include page="css.jsp"/>

    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>

    <body>

       <!-- section header -->
        <header class="header" data-spy="affix" data-offset-top="0">
            <!--nav bar helper-->
            <div class="navbar-helper">
                <div class="row-fluid">
                    <!--panel site-name-->
                    <div class="span3">
                        <div class="panel-sitename">
                            <h2><a href="index.jsp"><span class="color-teal">养老院管理系统</span></a></h2>
                        </div>
                    </div>
                    <!--/panel name-->
                </div>
            </div><!--/nav bar helper-->
        </header>
        
        <section class="section">
            <div class="container">
                <div class="signin-form row-fluid">
                    <div class="span5 offset3">
                        <div class="box corner-all">
                            <div class="box-header grd-teal color-white corner-top">
                                <span>用户登录</span>
                            </div>
                            <div class="box-body bg-white">
                             <!--    <form   action="../login/login" method="post" /> --> 
                             <form   action="/RestHome/Admin/login" method="post" />
                                    <div class="control-group">
                                        <label class="control-label">账户名：</label>
                                        <div class="controls">
                                            <input type="text" class="input-block-level grd-white" data-validate="{required: true, messages:{required:'请输入注册手机号，注册手机号不能为空！'}}" name="admin.adminName" id="name" autocomplete="off" />
                                        </div>
                                        <label id="name_label" style="display: none">账户名不能为空！</label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">密码：</label>
                                        <div class="controls">
                                            <input type="password" class="input-block-level grd-white" data-validate="{required: true, messages:{required:'请输入密码，密码不能为空！'}}" name="admin.adminPwd" id="pwd" autocomplete="off" />
                                        </div>
                                        <s:actionerror cssStyle="color:red"/>
                                    </div>
                                    <div class="form-actions">
                                        <input type="submit" class="btn btn-block btn-large btn-primary" value="登录" id="login"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
              
                </div>
            </div>
        </section>

		<%-- <jsp:include page="Footer.jsp"/> --%>
        <jsp:include page="Js.jsp"/>
        
     
    </body>
</html>
