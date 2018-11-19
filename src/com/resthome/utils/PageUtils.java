package com.resthome.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

public class PageUtils {
	private static final Integer SIZE = 8;
	public static List<Object> getPageLine(Integer pageNow,Integer totalPage){
		
		List<Object> pageLine = new ArrayList<Object>();
		if(totalPage==null){
			return pageLine;
		}
		if(pageNow==null){
			pageNow = 0;
		}
		if(totalPage<=SIZE){
			fillList(pageLine, 1, totalPage);
		}else{
			if(pageNow<=5){
				fillList(pageLine, 1, SIZE);
			}else{
				if(pageNow+3<=totalPage){
				fillList(pageLine, pageNow-4, pageNow+3);
				}else{
				fillList(pageLine, pageNow-4, totalPage);
				}
			}
		}
		return pageLine;
	}
	private static void fillList(List<Object> list,int begin,int end){
		for(int i=begin;i<=end;i++){
			list.add(i);
		}
	}

	
}
