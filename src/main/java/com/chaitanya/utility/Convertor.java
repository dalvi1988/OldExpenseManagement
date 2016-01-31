package com.chaitanya.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Convertor {
	public static Boolean convetStatusToBool(Character status){
		Boolean result;
		if(status=='Y')
			result= Boolean.TRUE;
		else
			result= Boolean.FALSE;
		return result;
	}
	
	public static Character convertStatusToChar(Boolean status){
		Character result;
		if(status==Boolean.TRUE){
			result='Y';
		}
		else{
			result='N';
		}
		return result;
	}
	public static String calendartoString(Calendar date){
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
		return format.format(date.getTime());
	}
	public static Calendar stringToCalendar(String date){
		Calendar foramttedDate=Calendar.getInstance();
		try {
			SimpleDateFormat format=new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
			foramttedDate.setTime(format.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foramttedDate;
		
	}
}
