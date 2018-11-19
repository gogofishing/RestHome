package com.resthome.utils;


import java.util.List;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.resthome.vo.*;

public class ToJson {

	/**
	* @param pageBean
	* @return 状态码 为0 成功获取数据
	*/
	public static JSONObject ToJsonPageVo(PageVo pageVo){
		JSONObject jsonObject=null;
		new JSONObject();
		jsonObject=JSONObject.fromObject(pageVo);
		return jsonObject;
	}
	/**
	* @param object
	* 把传进来的任何对象都转换成json然后返回
	* @return json对象
	*/
	public static JSONObject ToJsonObject(Object object) {
	JSONObject jsonObject = null;
	new JSONObject();
	jsonObject = JSONObject.fromObject(object);
	return jsonObject;
	}
	/**
	* @param list
	* @return jsonArray一组json数据 把查询得到的list集合转换成json数组，全部返回
	*/
	public static JSONArray ToJsonArray(List<Object> list) {
	JSONArray jsonArray = null;
	new JSONArray();
	jsonArray = jsonArray.fromObject(list);
	return jsonArray;
	}
	
	
}
