package com.resthome.utils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.resthome.entity.Employee;

public class CheckNullUtil {
	
	/**
	 * @param objects  object数组或者obj1,obj2,obj3,...的形式
	 * @return 可以传入多个Object对象,只要有一个为null或者""返回true,否则返回false
	 * 说明信息：该方法用于判断是否为null
	 */
	public static boolean ifOneNullOrEmpty(Object ...objects){
		if(null==objects||"".equals(objects)){
			return true;
		}
		for(int i=0;i<objects.length;i++){
			if(null==objects[i]||"".equals(objects[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param objects object数组或者obj1,obj2,obj3,...的形式
	 * @return 为空则返回true,否则返回false
	 * 说明信息：该方法用于判断是否为空
	 */
	public static boolean ifNull(String ...objects){
		 String regex = "^[\\s'　']*$";
		 Boolean flag;
		 Pattern pattern = Pattern.compile(regex);
		 for (int i = 0; i < objects.length; i++) {
			 Matcher matcher = pattern.matcher(objects[i]);
			 flag = matcher.matches();
			 if(flag == true){
				 return true;
			 }
		}
		 return false;
	}
	public static boolean isaNum(String ...objects){
		String regex = "^(([1-9]\\d*))$";
		 Boolean flag;
		 Pattern pattern = Pattern.compile(regex);
		 for (int i = 0; i < objects.length; i++) {
			 Matcher matcher = pattern.matcher(objects[i]);
			 flag = matcher.matches();
			 if(flag == false){
				 return false;
			 }
		}
		 return true;
	}
	public static boolean checkString(String str){
		if(str==null){
			return true;
		}
		if(str.trim().isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	
	
}
