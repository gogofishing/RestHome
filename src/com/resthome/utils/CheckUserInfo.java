package com.resthome.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUserInfo {
	/**
	 * @param phone
	 * @return
	 * 功能   检验手机号是否合法
	 */
	public static boolean isPhone(String phone){
		if(phone==null){
			return false;
		}
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(phone);  
		return m.matches();
	}
	
	
	
	
	
	
	

}
