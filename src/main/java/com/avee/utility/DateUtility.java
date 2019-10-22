package com.avee.utility;

public class DateUtility {
	public static boolean isValidDate(String strDate){
		String datePattern = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
		boolean isDate = strDate.matches(datePattern);
		return isDate;
	}
}
