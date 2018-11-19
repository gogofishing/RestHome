package com.resthome.utils;

import java.util.ArrayList;
import java.util.List;

public class GetPageLine {
	public static List<Integer> getPageLine(int nowPage,int totalPage){
		//Integer[] pageLine = new Integer()[];
		List<Integer> pageLine = new ArrayList<Integer>();
		if(totalPage<=8){//一次显示8个页码
			for(int i=1;i<=totalPage;i++){
				pageLine.add(i);
			}
		}else{
			if(nowPage<=5){
				for(int i=1;i<=8;i++){
					pageLine.add(i);
				}
			}else{
				if(nowPage>=totalPage-3){
				for(int i=totalPage-7;i<=totalPage;i++){
					pageLine.add(i);
				}
				}else{
					for(int i=nowPage-4;i<=nowPage+3;i++){
						pageLine.add(i);
					}
				}
				
			}
		}
		
		return pageLine;
	}


}
