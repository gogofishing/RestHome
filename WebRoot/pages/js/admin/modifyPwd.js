$(function() {
	$("#modify").click(function(){
		var oldPwd=$.trim($("#old_pwd").val());
		var newPwd1=$.trim($("#new_pwd1").val());
		var newPwd2=$.trim($("#new_pwd2").val());
		var uuid=$.trim($("#admin_uuid").val());
		
		if(oldPwd==null||oldPwd==""){
			showMessage("旧密码不允许为空！",$('#oldPwd_Label'));
		}else if(newPwd1==null||newPwd1==""){
			showMessage("新密码不允许为空！",$('#newPwd1_Label'));
		}else if(newPwd1.length!=6){
			showMessage("新密码必须为六位！",$('#newPwd1_Label'));
		}else if(newPwd2==null||newPwd2==""){
			showMessage("请再次输入新密码！",$('#newPwd2_Label'));
		}else if(newPwd1!=newPwd2){
			showMessage("新密码输入不一致，请重新输入！",$('#newPwd2_Label'));			
		}else{
			$.ajax({		
				url:'/RestHome/Admin/getAdminByUuid',
				type:'post',
				data:{
					'admin.uuid':uuid,			
				},
				dataType:'text',
				success:function(json){	
					var admin=$.parseJSON(json);
					if(admin.adminPwd!=oldPwd){
						showMessage("旧密码输入错误，请重新输入！",$('#oldPwd_Label'));						
					}else {
						$.ajax({		
							url:'/RestHome/Admin/modifyAdmin',
							type:'post',
							data:{
								'admin.uuid':uuid,	
								'admin.adminPwd':newPwd2
							},
							dataType:'text',
							success:function(){	
								showMessage("修改密码成功！",$('#message'));
								$("#old_pwd").val("");
								$("#new_pwd1").val("");
								$("#new_pwd2").val("");
							},
							error:function(){
								window.href="pages/500.jsp";
							}
						});
					}
				}
			});
		}
		
		
	});
});