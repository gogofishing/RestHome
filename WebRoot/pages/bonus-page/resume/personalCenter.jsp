<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>个人中心</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="<%=basePath%>pages/css/bootstrap.css" rel="stylesheet" />
        <link href="<%=basePath%>pages/css/font-awesome.css" rel="stylesheet" />

        <link href="<%=basePath%>pages/bonus-page/resume/css/resume.css" rel="stylesheet" />
        <link href="<%=basePath%>pages/bonus-page/resume/css/resume-control.css" rel="stylesheet" />
    	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    </head>
    <body>
        <!--controller-->
        <div class="controller">
            <!-- <a class="btn btn-block btn-danger" href="../../">返回</a> -->
            <button id="print" class="btn btn-block">打印</button>
            <!-- <button id="pdf" class="btn btn-block">PDF</button> -->
            <ul>
                <li><a title="black" id="black" href="javascript:void(0)" class="avail-color bg-black"></a></li>
                <li><a title="teal" id="teal" href="javascript:void(0)" class="avail-color bg-teal"></a></li>
                <li><a title="win8" id="win8" href="javascript:void(0)" class="avail-color bg-win8"></a></li>
                <li><a title="blue" id="blue" href="javascript:void(0)" class="avail-color bg-blue"></a></li>
                <li><a title="purple" id="purple" href="javascript:void(0)" class="avail-color bg-purple"></a></li>
                <li><a title="purple-dark" id="purple-dark" href="javascript:void(0)" class="avail-color bg-purple-dark"></a></li>
                <li><a title="red" id="red" href="javascript:void(0)" class="avail-color bg-red"></a></li>
                <li><a title="orange" id="orange" href="javascript:void(0)" class="avail-color bg-orange"></a></li>
                <li><a title="green" id="green" href="javascript:void(0)" class="avail-color bg-green"></a></li>
                <li><a title="sky" id="sky" href="javascript:void(0)" class="avail-color bg-sky"></a></li>
            </ul>
        </div><!--/controller-->

        <div class="container">
            <!--Header-->
            <div id="resume-head" class="grd-black">
                <div class="row-fluid">
                    <div class="span4">
                        <h1 class="pull-right">${oldPeople.oldPeopleName}</h1>
                    </div>
                    <div class="span6">
                        <p>${oldPeople.oldWorkName}</p>
                    </div>
                    <div class="span2">
                        <div class="resume-photo">
                            <img src="<%=basePath %>${oldPeople.headImage }" />
                        </div>
                    </div>
                </div>
            </div><!--/Header-->

            <!--Content-->
            <div id="resume-content">
                <!--Profile-->
                <div class="row-fluid profile">
                    <!--left-->
                    <div class="span6">
                        <div class="left">
                            <h3 class="title-icon pull-left"><i class="icofont-user"></i></h3>
                            <div class="title">
                                <h3>基本信息</h3>
                            </div>
                        </div>
                    </div><!--/left-->

                    <!--right-->
                    <div class="span6">
                        <div class="right">
                            <div class="title display-table">
                                <p>姓名</p>
                                <span class="pull-right">${oldPeople.oldPeopleName}</span>
                            </div>
                            <div class="title display-table">
                                <p>老人编号</p>
                                <span class="pull-right">${oldPeople.oldPeopleNo}</span>
                            </div>
                            <div class="title display-table">
                                <p>身份证</p>
                                <span class="pull-right">${oldPeople.idCard}</span>
                            </div>
                            <div class="title display-table">
                                <p>出生日期</p>
                                <span class="pull-right">${oldPeople.birthday}</span>
                            </div>
                            <div class="title display-table">
                                <p>民族</p>
                                <span class="pull-right">${oldPeople.nation}</span>
                            </div>
                            <div class="title display-table">
                                <p>政治背景</p>
                                <span class="pull-right">${oldPeople.party}</span>
                            </div>
                            <div class="title display-table">
                                <p>婚姻状况</p>
                                <span class="pull-right">${oldPeople.marriage}</span>
                            </div>
                            <div class="title display-table">
                                <p>一卡通余额</p>
                                <span class="pull-right"><i class="icofont-money"></i>&nbsp;&nbsp;&nbsp;${oldPeople.walletMoney }￥</span>
                            </div>
                            <div class="title display-table">
                                <p>等级</p>
                                <span class="pull-right">${oldPeople.grade}</span>
                            </div>
                            <div class="title display-table">
                                <p>联系方式</p>
                                <span class="pull-right"><i class="icofont-phone"></i> ${oldPeople.phone } &nbsp;&nbsp;&nbsp; <i class="icofont-envelope"></i> ${oldPeople.email }</span>
                            </div>
                            <div class="title display-table">
                                <p>家庭电话</p>
                                <span class="pull-right"><i class="icofont-phone-sign"></i>${oldPeople.homeTel }</span>
                            </div>
                            <div class="title display-table">
                                <p>房间号</p>
                                <span class="pull-right">${oldPeople.roomNo }</span>
                            </div>
                            <div class="title display-table">
                                <p>床号</p>
                                <span class="pull-right">${oldPeople.bedNo }</span>
                            </div>
                            <div class="title display-table">
                                <p>爱好</p>
                                <span class="pull-right">${oldPeople.hobby }</span>
                            </div>
                        </div>
                    </div><!--/right-->
                </div><!--/Profile-->

                <div class="education">
                    <h3 class="title-icon pull-left"><i class="icofont-heart"></i></h3>
                    <div class="title">
                        <h3>常见病</h3>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">过敏史：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.allergicHistory }</p>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">糖尿病：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.sugarSick }</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">血压：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.bloodPressure }</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">心脏病：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.heartSick }</p>
                        </div>
                    </div>
					<div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">脑血管：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.brainBloodSick }</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">眼睛：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.eyeSick }</p>
                        </div>
                    </div>
                </div>

				<div class="education">
                    <h3 class="title-icon pull-left"><i class="icofont-bell"></i></h3>
                    <div class="title">
                        <h3>生活习惯</h3>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">是否早起：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.getUpEarly }</p>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">大便时间：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.shitTime }</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="education-name">忌口：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.canNotEat }</p>
                        </div>
                    </div>
                </div>
				
                <div class="experience">
                    <h3 class="title-icon pull-left"><i class="icofont-briefcase"></i></h3>
                    <div class="title">
                        <h3>工作信息</h3>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="experience-name">原工作单位：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.oldWorkCompany }</p>
                        </div>
                    </div>
					<div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="experience-name">职务：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.oldWork }</p>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="experience-name">职称：</p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.oldWorkName }</p>
                        </div>
                    </div>
					<div class="row-fluid">
                        <div class="span6">
                            <div class="pull-right">
                                <p class="experience-name">工作内容：</p>
                                <p></p>
                            </div>
                        </div>
                        <div class="span6">
                            <p class="description">${oldPeople.oldWorkContent }</p>
                        </div>
                    </div>
                </div>

                <div class="skills">
                    <h3 class="title-icon pull-left"><i class="icofont-bar-chart"></i></h3>
                    <div class="title">
                        <h3>更多信息</h3>
                    </div>
                    <div class="row-fluid">
                        <div class="span10 offset1">
                            <div class="row-fluid">
                                    <div class="row-fluid">
                                        <div class="span4"><p class="skill-name">居住地址</p></div>
                                        <div class="span8">
                                            <div class="row-fluid description">
                                                <div class="span5"><p class="">${oldPeople.liveAddress }</p></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span4"><p class="skill-name">家庭地址</p></div>
                                        <div class="span8">
                                            <div class="row-fluid description">
                                                <div class="span5"><p class="">${oldPeople.idCardAddress }</p></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span4"><p class="skill-name">家庭信息</p></div>
                                        <div class="span8">
                                            <!--block skills list-->
                                            <div class="row-fluid description">
                                                <div class="span5"><p class="">${oldPeople.familyInfoHtmlSource }</p></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span4"><p class="skill-name">紧急联系地址</p></div>
                                        <div class="span8">
                                            <!--block skills list-->
                                            <div class="description">
                                                <div class="span6"><p class="">${oldPeople.emergencyPeopleHtmlSource }</p></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row-fluid">
                                        <div class="span4"><p class="skill-name">附加项</p><p></p></div>
                                        <div class="span6">
                                            <div class="description">
                                                <div class="span6"><p class="">${oldPeople.moreHtmlSource }</p></div>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/Content -->

        </div><!--/Container-->
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
