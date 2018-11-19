package com.resthome.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.resthome.vo.EmployeeVo;
import com.resthome.vo.SystemMetaVo;

public class HqlUtils {

	public static String getUpdateSet(Object objectVo) {
		if(objectVo==null){
			return null;
		}
		StringBuffer set = new StringBuffer();
		String setStr = "";
		Class cls = objectVo.getClass();
		Field[] flds = cls.getDeclaredFields();
		if (flds != null) {
			for (Field f : flds) {
				f.setAccessible(true);
				try {
					//System.out.println(f.getName() + ":" + f.get(objectVo));
					if(f.get(objectVo)!=null&&!f.getName().equals("uuid")&&!f.get(objectVo).toString().trim().equals("")){
					   set.append(f.getName()+"='"+f.get(objectVo)+"',");
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		setStr = set.toString().trim();
		if(setStr.length()>0){
			return setStr = setStr.substring(0, setStr.length()-1);
		}else{
			return null;
		}
		
		

	}
	
	
	public static String getQueryConditions(Object objectVo,List<String> except){
		if(objectVo==null){
			return null;
		}
		if(except==null){
			except = new ArrayList<String>();
		}
		StringBuffer strbf= new StringBuffer();
		String str = null;
		Class cls = objectVo.getClass();
		Field[] flds = cls.getDeclaredFields();
		if (flds != null) {
			for (Field f : flds) {
				f.setAccessible(true);
				try {
					//System.out.println(f.getName() + ":" + f.get(objectVo));
					if(f.get(objectVo)!=null&&!except.contains(f.getName())&&checkStrNotNullorNotEmpty(f.get(objectVo).toString())){
						strbf.append(f.getName()+"='"+f.get(objectVo)+"' and ");
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		str = strbf.toString().trim();
		if(str.endsWith("and")){
		str = str.substring(0,str.length()-3);
		}
		return str;
				
	}

	public static boolean checkStrNotNullorNotEmpty(String str){
		if(str==null){
			return false;
		}else if(str.trim().isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}

	public static String getUpdateSetStr(Object vo){
		
		if(vo==null){
			return null;
		}
		StringBuffer set = new StringBuffer();
		String setStr = "";
		Class cls = vo.getClass();
		Field[] flds = cls.getDeclaredFields();
		if (flds != null) {
			for (Field f : flds) {
				f.setAccessible(true);
				try {
					//System.out.println(f.getName() + ":" + f.get(objectVo));
					if(f.get(vo)!=null&&!f.getName().equals("uuid")&&!f.get(vo).toString().trim().equals("")){
					   set.append(f.getName()+"=?,");
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		setStr = set.toString().trim();
		if(setStr.length()>0){
			return setStr = setStr.substring(0, setStr.length()-1);
		}else{
			return null;
		}
		
	}
	
	public static List<Object> getParams(Object vo){
		if(vo==null){
			return null;
		}
		Class cls = vo.getClass();
		Field[] flds = cls.getDeclaredFields();
		List<Object> params = new ArrayList<Object>();
		if (flds != null) {
			for(Field f:flds){
				try {
					if(f.get(vo)!=null&&!f.getName().equals("uuid")&&!f.get(vo).toString().trim().equals("")){
						params.add(f.get(vo));
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return params;
	}

}
