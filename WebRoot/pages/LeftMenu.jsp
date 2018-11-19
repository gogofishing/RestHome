<%@ page language="java" import="java.util.*,com.resthome.entity.Admin"  
pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
Admin admin=(Admin)session.getAttribute("admin");
%>

                <!-- span side-left -->
                <div class="span1">
                    <!--side bar-->
                    <aside class="side-left">
                        <ul class="sidebar">
                            <li class="active first"> <!--always define class .first for first-child of li element sidebar left-->
                                <a href="<%=basePath %>pages/index.jsp" title="dashboard">
                                    <div class="helper-font-24">
                                        <i class="icofont-home"></i>
                                    </div>
                                    <span class="sidebar-text">主页</span>
                                </a>
                            </li>
                            <%
                            	if(admin==null?false:admin.getIdentify().equals("超级管理员")){
                            %>
                             <li>
                                <a href="<%=basePath %>pages/AdminManager.jsp" title="对管理员进行增删改查操作">
                                    <div class="helper-font-24">
                                        <i class="icofont-user"></i>
                                    </div>
                                    <span class="sidebar-text">管理员设置</span>
                                </a>
                                 <ul class="sub-sidebar-form corner-top shadow-white">
                                <li>
                                    	<a href="<%=basePath%>pages/AdminManager.jsp" title="管理员信息列表" class="corner-all">
                                            <i class="icofont-signout"></i>
                                            <span class="sidebar-text">管理员信息列表</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <%} %>
                            <%
                            	if(admin==null?false:admin.getSystemMetaAuthority().equals("true")){
                            %>
                             <li>
                                <a href="<%=basePath %>pages/SystemMetaTypeManager.jsp" title="对系统参数进行管理">
                                    <div class="helper-font-24">
                                        <i class="icofont-user"></i>
                                    </div>
                                    <span class="sidebar-text">参数设置</span>
                                </a>
                                <ul class="sub-sidebar-form corner-top shadow-white">
                                <li>
                                    	<a href="<%=basePath%>pages/SystemMetaTypeManager.jsp" title="系统参数列表" class="corner-all">
                                            <i class="icofont-signout"></i>
                                            <span class="sidebar-text">系统参数列表</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <%} %>
                            <%
                            	if(admin==null?false:admin.getOldpeopleAuthority().equals("true")){
                            %>
                            <li>
                                <a href="<%=basePath %>pages/OldPeople.jsp" title="对老人信息进行管理">
                                    <div class="helper-font-24">
                                        <i class="icofont-user"></i>
                                    </div>
                                    <span class="sidebar-text">老人管理</span>
                                </a>
                                <ul class="sub-sidebar-form corner-top shadow-white">
                                    <li style="display:block;">
                                        <a href="<%=basePath %>pages/OldPeople.jsp" title="审核员工提交的通知" class="corner-all">
                                            <i class="icofont-signout"></i>
                                            <span class="sidebar-text">老人信息</span>
                                        </a>
                                    </li>
                                    <li class="divider"></li>
                                    <li style="display:block;">
                                        <a href="<%=basePath %>pages/HealthInfo.jsp" title="老人健康信息" class="corner-all">
                                           <i class="icofont-signout"></i>
                                            <span class="sidebar-text">老人健康信息</span>
                                        </a>
                                    </li>
                                    <li>
                                    	<a href="<%=basePath %>pages/OldPeopleOutInRecord.jsp" title="老人进出养老院记录" class="corner-all">
                                            <i class="icofont-signout"></i>
                                            <span class="sidebar-text">老人进出记录</span>
                                        </a>
                                    </li>
                                    <li>
                                    	<a href="<%=basePath %>pages/TakeMedicineRecord.jsp" title="老人用药记录" class="corner-all">
                                            <i class="icofont-signout"></i>
                                            <span class="sidebar-text">老人用药记录</span>
                                        </a>
                                    </li>
                                   <%--  <li>
                                    	<a href="<%=basePath %>pages/OldPeoplePay.jsp" title="老人用药记录" class="corner-all">
                                            <i class="icofont-signout"></i>
                                            <span class="sidebar-text">老人消费记录</span>
                                        </a>
                                    </li> --%>
                                </ul>
                            </li>
                            <%} %>                           
                           <%
                           	if(admin==null?false:admin.getEmployeeAuthority().equals("true")){
                           %>
                           <li>
                             <a href="<%=basePath %>pages/Employee2.jsp" title="对员工信息进行管理">

                                 <div class="helper-font-24">
                                     <i class="icofont-user"></i>
                                 </div>
                                 <span class="sidebar-text">员工管理</span>
                             </a>
                             <ul class="sub-sidebar-form corner-top shadow-white">
                                  <li>
                                 	<a href="<%=basePath %>pages/Employee2.jsp" title="员工信息" class="corner-all">
                                         <i class="icofont-signout"></i>
                                         <span class="sidebar-text">员工信息</span>
                                     </a>
                                 </li>
                             	 <li>
                                 	<a href="<%=basePath %>pages/SalaryManager.jsp" title="工资信息" class="corner-all">
                                         <i class="icofont-signout"></i>
                                         <span class="sidebar-text">工资信息</span>
                                     </a>
                                 </li>
                                 <li>
                                 	<a href="<%=basePath%>pages/EmployeeList.jsp" title="录入工资" class="corner-all">
                                         <i class="icofont-signout"></i>
                                         <span class="sidebar-text">录入工资</span>
                                     </a>
                                 </li>
                                 <li>
                                 	<a href="<%=basePath %>pages/CheckInManager.jsp" title="考勤信息" class="corner-all">
                                         <i class="icofont-signout"></i>
                                         <span class="sidebar-text">考勤信息</span>
                                     </a>
                                 </li>
                                 <li>
                                 	<a href="<%=basePath%>pages/AddCheckIn.jsp" title="排班和班次" class="corner-all">
                                         <i class="icofont-signout"></i>
                                         <span class="sidebar-text">录入考勤</span>
                                     </a>
                                 </li>                                   
                             </ul>
                         </li>  
                         <%} %>
                         <%
                         	if(admin==null?false:admin.getStorageAuthority().equals("true")){
                         %>                           
                         <li>
                            <a href="<%=basePath %>pages/GoodsManager.jsp" title="对仓库物品进行管理">
                                <div class="helper-font-24">
                                    <i class="icofont-user"></i>
                                </div>
                                <span class="sidebar-text">仓库管理</span>
                            </a>
                            <ul class="sub-sidebar-form corner-top shadow-white">
                            	<li>
                                	<a href="<%=basePath%>pages/GoodsManager.jsp" title="仓库物品列表" class="corner-all">
                                        <i class="icofont-signout"></i>
                                        <span class="sidebar-text">仓库物品列表</span>
                                    </a>
                            	</li>
                            	<li>
                                	<a href="<%=basePath%>pages/InStorage.jsp" title="入库记录" class="corner-all">
                                        <i class="icofont-signout"></i>
                                        <span class="sidebar-text">入库记录</span>
                                    </a>
                            	</li>
                            	<li>
                                	<a href="<%=basePath%>pages/OutStorage.jsp" title="出库记录" class="corner-all">
                                        <i class="icofont-signout"></i>
                                        <span class="sidebar-text">出库记录</span>
                                    </a>
                            	</li>
                            </ul>
                        </li>
                        <%} %>
                        <%
                        	if(admin==null?false:admin.getCommunityAuthority().equals("true")){
                        %>
                        <li>
                           <a href="<%=basePath %>pages/CommunityManager.jsp" title="对社区进行管理">
                               <div class="helper-font-24">
                                   <i class="icofont-user"></i>
                               </div>
                               <span class="sidebar-text">社区管理</span>
                           </a>
                           <ul class="sub-sidebar-form corner-top shadow-white">
                           	<li>
                               	<a href="<%=basePath%>pages/VolunteerManager.jsp" title="志愿者信息" class="corner-all">
                                       <i class="icofont-signout"></i>
                                       <span class="sidebar-text">志愿者信息</span>
                                   </a>
                           	</li>
                           	<li>
                               	<a href="<%=basePath%>pages/VolunteerActivity.jsp" title="志愿活动" class="corner-all">
                                       <i class="icofont-signout"></i>
                                       <span class="sidebar-text">志愿活动</span>
                                   </a>
                           	</li>
                           	<li>
                               	<a href="<%=basePath%>pages/CommunityActivity.jsp" title="社区活动" class="corner-all">
                                       <i class="icofont-signout"></i>
                                       <span class="sidebar-text">社区活动</span>
                                   </a>
                           	</li>
                           </ul>
                       </li>  
                       <%} %>                                              
                        </ul>
                    </aside><!--/side bar -->
                </div>
