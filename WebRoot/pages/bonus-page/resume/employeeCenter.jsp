<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>员工个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<%=basePath%>pages/css/bootstrap.css" rel="stylesheet" />
<link href="<%=basePath%>pages/css/font-awesome.css" rel="stylesheet" />

<link href="<%=basePath%>pages/bonus-page/resume/css/resume.css"
	rel="stylesheet" />
<link href="<%=basePath%>pages/bonus-page/resume/css/resume-control.css"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<!--controller-->
	<div class="controller">
		<!-- <a class="btn btn-block btn-danger" href="../../">返回</a> -->
		<button id="print" class="btn btn-block">打印</button>
		<!-- <button id="pdf" class="btn btn-block">PDF</button> -->
		<ul>
			<li><a title="black" id="black" href="javascript:void(0)"
				class="avail-color bg-black"></a></li>
			<li><a title="teal" id="teal" href="javascript:void(0)"
				class="avail-color bg-teal"></a></li>
			<li><a title="win8" id="win8" href="javascript:void(0)"
				class="avail-color bg-win8"></a></li>
			<li><a title="blue" id="blue" href="javascript:void(0)"
				class="avail-color bg-blue"></a></li>
			<li><a title="purple" id="purple" href="javascript:void(0)"
				class="avail-color bg-purple"></a></li>
			<li><a title="purple-dark" id="purple-dark"
				href="javascript:void(0)" class="avail-color bg-purple-dark"></a></li>
			<li><a title="red" id="red" href="javascript:void(0)"
				class="avail-color bg-red"></a></li>
			<li><a title="orange" id="orange" href="javascript:void(0)"
				class="avail-color bg-orange"></a></li>
			<li><a title="green" id="green" href="javascript:void(0)"
				class="avail-color bg-green"></a></li>
			<li><a title="sky" id="sky" href="javascript:void(0)"
				class="avail-color bg-sky"></a></li>
		</ul>
	</div>
	<!--/controller-->

	<div class="container">
		<!--Header-->
		<div id="resume-head" class="grd-black">
			<div class="row-fluid">
				<div class="span4">
					<h1 class="pull-right">${employeeVo.employeeName}</h1>
				</div>
				<div class="span6">
					<p>${employeeVo.workName}</p>
				</div>
				<div class="span2">
					<div class="resume-photo">
						<img src="<%=basePath %>${employeeVo.headImage }" />
					</div>
				</div>
			</div>
		</div>
		<!--/Header-->

		<!--Content-->
		<div id="resume-content">
			<!--Profile-->
			<div class="row-fluid profile">
				<!--left-->
				<div class="span6">
					<div class="left">
						<h3 class="title-icon pull-left">
							<i class="icofont-user"></i>
						</h3>
						<div class="title">
							<h3>基本信息</h3>
						</div>
					</div>
				</div>
				<!--/left-->

				<!--right-->
				<div class="span6">
					<div class="right">
						<div class="title display-table">
							<p>姓名</p>
							<span class="pull-right">${employeeVo.employeeName}</span>
						</div>
						<div class="title display-table">
							<p>员工编号</p>
							<span class="pull-right">${employeeVo.employeeNo}</span>
						</div>
						<div class="title display-table">
							<p>身份证</p>
							<span class="pull-right">${employeeVo.idCard}</span>
						</div>
						<div class="title display-table">
							<p>出生日期</p>
							<span class="pull-right">${employeeVo.birthday}</span>
						</div>
						<div class="title display-table">
							<p>性别</p>
							<span class="pull-right">${employeeVo.sex}</span>
						</div>
						<div class="title display-table">
							<p>民族</p>
							<span class="pull-right">${employeeVo.nation}</span>
						</div>
						<div class="title display-table">
							<p>政治背景</p>
							<span class="pull-right">${employeeVo.party}</span>
						</div>
						<div class="title display-table">
							<p>婚姻状况</p>
							<span class="pull-right">${employeeVo.marriage}</span>
						</div>
						<div class="title display-table">
							<p>移动电话</p>
							<span class="pull-right"><i class="icofont-phone"></i>&nbsp;&nbsp;&nbsp;${employeeVo.phone }</span>
						</div>
						<div class="title display-table">
							<p>家庭电话</p>
							<span class="pull-right">${employeeVo.homeTel}</span>
						</div>
						<div class="title display-table">
							<p>E-Mail</p>
							<span class="pull-right"> &nbsp;&nbsp;&nbsp; <i
								class="icofont-envelope"></i> ${employeeVo.email }
							</span>
						</div>
						<div class="title display-table">
							<p>家庭电话</p>
							<span class="pull-right">${oldPeople.homeTel }</span>
						</div>
						<div class="title display-table">
							<p>家庭地址</p>
							<span class="pull-right">${employeeVo.address }</span>
						</div>


					</div>
				</div>
				<!--/right-->
			</div>
			<!--/Profile-->

			<div class="idCard_picture">
				<h3 class="title-icon pull-left">
					<i class="icon-picture"></i>
				</h3>
				<div class="title">
					<h3>证件照</h3>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">身份证照正面：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">
							<img alt="" src="${employeeVo.idCardImageA }">
						</p>
					</div>
				</div>

				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">身份证照反面：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">
							<img alt="" src="${employeeVo.idCardImageB }">
						</p>
					</div>
				</div>
			</div>
			<div class="experience">
				<h3 class="title-icon pull-left">
					<i class="icofont-briefcase"></i>
				</h3>
				<div class="title">
					<h3>工作信息</h3>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="experience-name">部门：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.department }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="experience-name">工作：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.workName }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="experience-name">工作状态：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.workStatus }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="experience-name">入职时间：</p>
							<p></p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.beginWorkTime }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="experience-name">等级：</p>
							<p></p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.grade }</p>
					</div>
				</div>
			</div>

			<div class="education">
				<h3 class="title-icon pull-left">
					<i class="icofont-bell"></i>
				</h3>
				<div class="title">
					<h3>工资福利</h3>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">基本工资：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.basicSalary }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">岗位工资：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.positionSalary }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">保险基数：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.baoxianjishu }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">住房基数：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.zhufangjishu }</p>
					</div>
				</div>
				<div class="row-fluid">
					<div class="span6">
						<div class="pull-right">
							<p class="education-name">保险补偿金：</p>
						</div>
					</div>
					<div class="span6">
						<p class="description">${employeeVo.baoxianbuchangjin }</p>
					</div>
				</div>



			</div>



			<div class="skills">
				<h3 class="title-icon pull-left">
					<i class="icofont-bar-chart"></i>
				</h3>
				<div class="title">
					<h3>家庭信息</h3>
				</div>
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid">
							<div class="row-fluid">
								<!--   <div class="span4"><p class="skill-name">居住地址</p></div>-->
								<div class="span8">
									<div class="row-fluid description">
										<div class="span5">
											<p class="">${employeeVo.familyHtmlSource}</p>
										</div>
									</div>
								</div>
							</div>




						</div>
					</div>
				</div>
			</div>
			<div class="skills">
				<h3 class="title-icon pull-left">
					<i class="icofont-bar-chart"></i>
				</h3>
				<div class="title">
					<h3>紧急联系人信息</h3>
				</div>
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid">
							<div class="row-fluid">
								<!--   <div class="span4"><p class="skill-name">居住地址</p></div>-->
								<div class="span8">
									<div class="row-fluid description">
										<div class="span5">
											<p class="">${employeeVo.emergencyPeopleHtmlSource}</p>
										</div>
									</div>
								</div>
							</div>
                        </div>
					</div>
				</div>
			</div>
					<div class="skills">
				<h3 class="title-icon pull-left">
					<i class="icofont-bar-chart"></i>
				</h3>
				<div class="title">
					<h3>主要工作</h3>
				</div>
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid">
							<div class="row-fluid">
								<!--   <div class="span4"><p class="skill-name">居住地址</p></div>-->
								<div class="span8">
									<div class="row-fluid description">
										<div class="span5">
											<p class="">${employeeVo.mianWorkHtmlSource}</p>
										</div>
									</div>
								</div>
							</div>
                        </div>
					</div>
				</div>
			</div>
				<div class="skills">
				<h3 class="title-icon pull-left">
					<i class="icofont-bar-chart"></i>
				</h3>
				<div class="title">
					<h3>工作经历</h3>
				</div>
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid">
							<div class="row-fluid">
								<!--   <div class="span4"><p class="skill-name">居住地址</p></div>-->
								<div class="span8">
									<div class="row-fluid description">
										<div class="span5">
											<p class="">${employeeVo.workExperienceHtmlSource}</p>
										</div>
									</div>
								</div>
							</div>
                        </div>
					</div>
				</div>
			</div>
			<div class="skills">
				<h3 class="title-icon pull-left">
					<i class="icofont-bar-chart"></i>
				</h3>
				<div class="title">
					<h3>教育经历</h3>
				</div>
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid">
							<div class="row-fluid">
								<!--   <div class="span4"><p class="skill-name">居住地址</p></div>-->
								<div class="span8">
									<div class="row-fluid description">
										<div class="span5">
											<p class="">${employeeVo.educationExperienceHtmlSource}</p>
										</div>
									</div>
								</div>
							</div>
                        </div>
					</div>
				</div>
			</div>
			<div class="skills">
				<h3 class="title-icon pull-left">
					<i class="icofont-bar-chart"></i>
				</h3>
				<div class="title">
				  <h3>更多</h3>
				</div>
				<div class="row-fluid">
					<div class="span10 offset1">
						<div class="row-fluid">
							<div class="row-fluid">
								<!--   <div class="span4"><p class="skill-name">居住地址</p></div>-->
								<div class="span8">
									<div class="row-fluid description">
										<div class="span5">
											<p class="">${employeeVo.moreHtmlSource}</p>
										</div>
									</div>
								</div>
							</div>
                        </div>
					</div>
				</div>
			</div>
		</div>
		<!--/Content -->

	</div>
	<!--/Container-->
	<script src="<%=basePath%>pages/js/jquery-2.1.1.min.js"></script>
	<script src="<%=basePath%>pages/js/jquery-ui.min.js"></script>
	<script src="<%=basePath %>pages/js/bootstrap.min.js"></script>
	<script type="text/javascript">

            $().ready(function() {
				
                $('a.avail-color').click(function(){
                    return false;
                })


                /* print
          --------------------------- */
                $('#print').click(function(){
                    window.print();
                })
                /* end print

                /* change color
          --------------------------- */
                $('.controller a').click(function(){
                    color = $(this).attr('title');

                    $('#resume-head').attr('class', 'grd-'+color);
                    $('.title h3, .title p').attr('class', 'border-'+color);
                });
            
                /* end change color
          --------------------------- */
            });
    
        </script>
</body>
</html>
