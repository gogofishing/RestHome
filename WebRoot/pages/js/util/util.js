/*页面提示信息方法*/
this.showMessage = function(msg,this_id){
	this_id.text(msg);
	this_id.show("slow");
	setTimeout(function(){
		this_id.fadeOut(900);
		this_id.hide("fast");
	},3000);
};
/*时间格式验证*/
this.isTimeHHMM_Util = function(str){
	var regu = /^(([0-1][0-9])|([1-2][0-3])):([0-5][0-9])$/;
	var re = new RegExp(regu);
	return re.test(str);
};
/*非空判断 */
this.isNull = function(str){
	if(str==""){
		return true;
	}
	var regu = /^[\s'　']*$/;
	var re = new RegExp(regu);
	return re.test(str);
};
/*只能是数字*/
this.isNum = function(str){
	var regu = /^(([1-9]\d*))$|(0)$/;
	var re = new RegExp(regu);
	return re.test(str);
};
/*金额正则表达式*/
this.isMoney = function(str){
	var regu = /^(([1-9]\d*)|0)(\.\d{1,2})?$/;
	var re = new RegExp(regu);
	return re.test(str);
};

/*获取根目录地址 如localhost:8080/RestHome*/
this.getRootPath = function(){
	//获取当前网址，如： http://localhost:8080/ems/Pages/Basic/Person.jsp
	var curWwwPath = window.document.location.href;
	//获取主机地址之后的目录，如： /ems/Pages/Basic/Person.jsp
	var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8080
	var localhostPath = curWwwPath.substring(0, pos);
	//获取带"/"的项目名，如：/RestHome
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPath+projectName);
};

/*表单验证函数(格式只能为金额且不为空)*/
this.onlyNum = function(input,this_id){
	var textValue = input;
	if(isNull(textValue)){
		showMessage("不能为空,请填写...",$(this_id));
		return false;
	}else if(!isMoney(textValue)){
		showMessage("格式不正确,尾数最多保留两位小数，如：0.00",$(this_id));
		return false;
	}else{
		return true;
	}
};
/*邮箱格式验证*/
this.testEmail_Util = function(str){
	var regu = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	var re = new RegExp(regu);
	return re.test(str);
};
/*日期转换 mm/dd/yyyy to yyyy-mm-dd*/
this.dateFormat_Util = function(str){
	var a=str;
	var b=a.split('/');
	var a=b[2]+"-"+b[0]+"-"+b[1];
	return a;
};

/*yyyy-mm-dd to mm/dd/yyyy*/
this.timeFormat_Util = function(str){
	var a=str;
	var b=a.split('-');
	var a=b[1]+"/"+b[2]+"/"+b[0];
	return a;
};
