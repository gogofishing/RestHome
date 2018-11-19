package com.resthome.utils;

public class GetSerialNum {
	
	public static String createOldPeopleNo(int nowYear,int oldPeopleNum){
		StringBuffer sb = new StringBuffer();
		sb.append("20"+nowYear+""+oldPeopleNum);
		return sb.toString();
	}
	public static String createEmployeeNo(int nowYear,int employeeNum){
		StringBuffer sbf = new StringBuffer();
		String year = (nowYear+"").substring(2, (nowYear+"").length());
		String strNum = getNumStr(employeeNum);
		sbf.append("10"+year+""+strNum);
		return sbf.toString();
		
	}
	public static String createGoodsNo(int nowYear,int goodsNum){
		StringBuffer sbf = new StringBuffer();
		String year = (nowYear+"").substring(2, (nowYear+"").length());
		String strNum = getNumStr(goodsNum);
		sbf.append("30"+year+""+strNum);
		return sbf.toString();
		
	}
	public static String createVolunteerNo(int nowYear,int volunteerNum){
		StringBuffer sbf = new StringBuffer();
		String year = (nowYear+"").substring(2, (nowYear+"").length());
		String strNum = getNumStr(volunteerNum);
		sbf.append("40"+year+""+strNum);
		return sbf.toString();
		
	}
	public static String createCheckInNo(int nowYear,int checkInsNum){
		StringBuffer sbf = new StringBuffer();
		String year = (nowYear+"").substring(2, (nowYear+"").length());
		String strNum = getNumStr(checkInsNum);
		sbf.append("50"+year+""+strNum);
		return sbf.toString();
		
	}
	private static String getNumStr(int employeeNum){
		String str = employeeNum+"";
		String numStr = null;
		switch (str.length()) {
		case 1:
			numStr = "000"+str;
			break;
        case 2:
        	numStr = "00"+str;
        	break;
        case 3:
        	numStr = "0"+str;
        	break;
        case 4:
        	numStr = str;
        	break;
		default:
			break;
		}
		
		return numStr;
	}
	
}
