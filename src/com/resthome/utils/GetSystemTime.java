package com.resthome.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class GetSystemTime {

	@SuppressWarnings("static-access")
	public final static String currentTime(){
		Calendar calendar=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.getInstance(Locale.CHINESE);
		String time=simpleDateFormat.format(calendar.getTime()).toString();
		return time;
	}
	public final static String currentDate(){
		Calendar c=Calendar.getInstance();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		c.getInstance(Locale.CHINESE);
		String date=simpleDateFormat.format(c.getTime()).toString();
		return date;
		
	}
	public static void main(String[] args) {
		System.out.println(currentDate());
	}
}
