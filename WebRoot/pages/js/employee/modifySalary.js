 

$(function(){
	// 获取request请求参数：employee的uuid
	var mY_url=window.location.search;
	if(mY_url.indexOf("?") != -1){
		mY_url=mY_url.substring(1, (mY_url.length) );
		var arr_url=mY_url.split("&");
		var salaryuuid=arr_url[0].split("=")[1];
		var empuuid=arr_url[1].split("=")[1];
	}
	
	$.ajax({
		url:'/RestHome/Salary/findSalary',
		data:{
			'salary.uuid' : salaryuuid,
			'employee.uuid':empuuid,			
		},
		type : 'post',
		dataType : 'json',
		success : function(json) {
			var emp=json.emp;
			var salary=json.salary;
			
			
			$("#empUuid").attr('value',emp.uuid);
			$("#salaryUuid").attr('value',salary.uuid);
			$("#employeeNo").attr('value',emp.employeeNo);
			$("#employeeName").attr('value',emp.employeeName);	
			$("#PBasicSalary").attr('value',fixed(salary.PBasicSalary));
			$("#PPositionSalary").attr('value',fixed(salary.PPositionSalary));
			$("#baoxianjishu").attr('value',fixed(emp.baoxianjishu));
			$("#zhufangjishu").attr('value',fixed(emp.zhufangjishu));
			$("#PBaoxianbuchangjin").attr('value',fixed(salary.PBaoxianbuchangjin));
			$("#PYanglaobaoxian").attr('value',fixed(salary.PYanglaobaoxian));
			$("#PYiliaobaoxian").attr('value',fixed(salary.PYiliaobaoxian));
			$("#PShiyebaoxian").attr('value',fixed(salary.PShiyebaoxian));
			$("#PZhufangbaoxian").attr('value',fixed(salary.PZhufangbaoxian));
			$("#personalBaoxianSummary").attr('value',fixed(salary.personalBaoxianSummary));
			$("#PNormalPlusSalary").attr('value',fixed(salary.PNormalPlusSalary));
			$("#PHolidaySalary").attr('value',fixed(salary.PHolidaySalary));
			
			$("#CYanglaobaoxian").attr('value',fixed(salary.CYanglaobaoxian));
			$("#CYiliaobaoxian").attr('value',fixed(salary.CYiliaobaoxian));
			$("#CGongshangbaoxian").attr('value',fixed(salary.CGongshangbaoxian));
			$("#CShiyebaoxian").attr('value',fixed(salary.CShiyebaoxian));
			$("#CShengyubaoxian").attr('value',fixed(salary.CShengyubaoxian));
			$("#CZhufangbaoxian").attr('value',fixed(salary.CZhufangbaoxian));
			$("#companyBaoxianSummary").attr('value',fixed(salary.companyBaoxianSummary));			
			$("#PDaebaoxian").attr('value',fixed(salary.PDaebaoxian));
			$("#PLengnuanfei").attr('value',fixed(salary.PLengnuanfei));
			
			$("#personalTax").attr('value',fixed(salary.personalTax));
			$("#gongjijinSummary").attr('value',fixed(salary.gongjijinSummary));
			$("#realSalary").attr('value',fixed(salary.realSalary));
			
		}
	});	
	
	
	$(".shuzhi").blur(function(){
		var $this = $(this);
		var num = $.trim($this.val());
		if(isNaN(num)){
			$this.parent().find('.shuzhilabel').text("输入的不是数值").show('normal');
			$this.val("");
		
		}else{
		    $this.parent().find('.shuzhilabel').hide('normal'); 
		}
	});
	
	$("#modifySalary").click(function(){
		var PNormalPlusSalary=$.trim($("#PNormalPlusSalary").val());
		var PHolidaySalary=$.trim($("#PHolidaySalary").val());
		var PDaebaoxian=$.trim($("#PDaebaoxian").val());
		var PLengnuanfei=$.trim($("#PLengnuanfei").val());
		
		if(isNaN(PNormalPlusSalary)||isNaN(PHolidaySalary)||isNaN(PDaebaoxian)||isNaN(PLengnuanfei)){
			$("#form1").removeAttr('action');
		}else{
			$(this).attr('type','submit');
			$("#form1").attr('action','/RestHome/Salary/modifySalary');
		}
	});
	
}
);


var fixed=function(num){
	if(!isNaN(num)){	
		var n=new Number(num);
		return n.toFixed(2);
	}		
}






