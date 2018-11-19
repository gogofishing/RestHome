<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>养老院管理系统</title>
    
	 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
     <meta name="description" content="" />
     <meta name="author" content="stilearning" />
 
	<jsp:include page="css.jsp"/>

  </head>
  
  <body>
  
  <!-- section header -->
        <header class="header" data-spy="affix" data-offset-top="0">
            <!--nav bar helper-->
            <div class="navbar-helper">
                <div class="row-fluid">
                    <!--panel site-name-->
                    <div class="span4">
                        <div class="panel-sitename">
                            <h2><a href="index.html"><span class="color-teal">养老院</span>管理系统</a></h2>
                        </div>
                    </div>
                    <!--/panel name-->
                </div>
            </div><!--/nav bar helper-->
        </header>
   
     <!-- section content -->
        <section class="section">
            <div class="container">
                <div class="error-page">
                    <h1 class="error-code color-red">Error 500</h1>
                    <p class="error-description muted">I'm sorry,服务器繁忙，请稍后再试。。。</p>
                    <a href="<%=basePath %>pages/login.jsp" class="btn btn-small btn-primary"><i class="icofont-arrow-left"></i>返回登录</a>
                </div>
            </div>
        </section>
        
		   
        
        <script src="<%=basePath %>pages/js/jquery.js"></script>
        <script src="<%=basePath %>pages/js/bootstrap.js"></script>
  </body>
</html>
