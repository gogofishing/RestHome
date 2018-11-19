<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
Admin admin=(Admin)session.getAttribute("admin");
%>
 <!-- section header -->
        <header class="header">
            <!--nav bar helper-->
            <div class="navbar-helper">
                <div class="row-fluid">
                <!--  顶部的标题 -->
                    <!--panel site-name-->
                    <div class="span9">
                        <div class="panel-sitename">
                          <span class="color-teal" style="font-size: 31.5px;line-height:40px"><strong>养老院管理系统</strong></span>
                        </div>
                    </div>
                    <!--/panel name-->
				
                    <div class="span1">
                        <!--panel button ext-->
                        <div class="panel-ext">
                           
                            <div class="btn-group">
                                <%-- <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <a class="btn btn-danger btn-small" data-toggle="dropdown"><%=admin==null?null:admin.getAdminName()  %></a>
                                </a> --%>
                                <a class="btn btn-inverse btn-small dropdown-toggle" data-toggle="dropdown" href="#">
                                    	您好，<%=admin==null?null:admin.getAdminName()  %>
                                </a>
                                
                              <!--   用户信息显示 -->
                             <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                    <li><a tabindex="-1" href="/RestHome/pages/ModifyPwd.jsp">修改密码</a></li>
                                    <li><a tabindex="-1" href="/RestHome/pages/logOut.jsp">退出登录</a></li>                               		
                                </ul>
                               
                            </div>
                        </div><!--panel button ext-->
                    </div>
                </div>
            </div><!--/nav bar helper-->
        </header>