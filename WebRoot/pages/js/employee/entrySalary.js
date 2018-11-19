 
$(function(){
	// 获取request请求参数：employee的uuid
	var mY_url=window.location.search;
	mY_url=mY_url.substring(1, (mY_url.length) );
	var arr_url=mY_url.split("=");
	
	
	$.ajax({
		url:'/RestHome/Salary/addSalary',
		data:{
			'employee.uuid':arr_url[1]
		},
		type : 'post',
		dataType : 'json',
		success : function(json) {
			var emp=json.emp;
			var salary=json.salary;
			
			$("#empUuid").attr('value',emp.uuid);
			$("#salaryUuid").attr('value',salary.uuid);			
			$("#PBasicSalary").attr('value',fixed(salary.PBasicSalary));
			$("#PPositionSalary").attr('value',fixed(salary.PPositionSalary));
			$("#baoxianjishu").attr('value',fixed(emp.baoxianjishu));
			$("#zhufangjishu").attr('value',fixed(emp.zhufangjishu));
			$("#PBaoxianbuchangjin").attr('value',fixed(salary.PBaoxianbuchangjin));
			$("#PYanglaobaoxian").attr('value',fixed(salary.PYanglaobaoxian));
			$("#PYiliaobaoxian").attr('value',fixed(salary.PYiliaobaoxian));
			$("#PShiyebaoxian").attr('value',fixed(salary.PShiyebaoxian));
			$("#PZhufangbaoxian").attr('value',fixed(salary.PZhufangbaoxian));
			$("#CYanglaobaoxian").attr('value',fixed(salary.CYanglaobaoxian));
			$("#CYiliaobaoxian").attr('value',fixed(salary.CYiliaobaoxian));
			$("#CGongshangbaoxian").attr('value',fixed(salary.CGongshangbaoxian));
			$("#CShiyebaoxian").attr('value',fixed(salary.CShiyebaoxian));
			$("#CShengyubaoxian").attr('value',fixed(salary.CShengyubaoxian));
			$("#CZhufangbaoxian").attr('value',fixed(salary.CZhufangbaoxian));
			
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
	
	$("#addSalary").click(function(){
		var PNormalPlusSalary=$.trim($("#PNormalPlusSalary").val());
		var PHolidaySalary=$.trim($("#PHolidaySalary").val());
		var PDaebaoxian=$.trim($("#PDaebaoxian").val());
		var PLengnuanfei=$.trim($("#PLengnuanfei").val());
		
		if(isNaN(PNormalPlusSalary)||isNaN(PHolidaySalary)||isNaN(PDaebaoxian)||isNaN(PLengnuanfei)){
			$("#form1").removeAttr('action');
		}else{
			//alert("===");
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


/*var nan=function(e){
	if(isNaN($.trim(e.value))){
		//alert("请输入数字");
		e.find(".message").text("输入的不是数值！").show('normal');
		$(e).value="";
		e.focus();
	}else{
		$(".message").hide('normal');
	}
}*/




