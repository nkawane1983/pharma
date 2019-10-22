package com.avee.utility;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.avee.form.Denomination;
import com.avee.form.TakingsCash;

@Component
public class StringUtility {

	public boolean checkNullOrEmptyString(String string) {
		boolean flag = false;
		if (string != null && StringUtils.isNotEmpty(string) && StringUtils.isNotBlank(string)) {
			flag = true;
		}
		return flag;
	}

	public boolean isEmptyInteger(int number) {
		boolean flag = false;
		String string = number + "";
		if (string != null && StringUtils.isNotEmpty(string) && StringUtils.isNotBlank(string)) {
			flag = true;
		}
		return flag;
	}

	public Date geTimeStamp(Date date) {

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String currentTimeStamp = dateFormat.format(cal.getTime());

			String hhMMSS = "";
			String strDate = "";

			if (checkNullOrEmptyString(currentTimeStamp)) {
				String sp[] = currentTimeStamp.split(" ");
				hhMMSS = sp[1];
			}

			if (date != null) {

				String effTimeStamp = dateFormat.format(date);

				if (checkNullOrEmptyString(effTimeStamp)) {
					String sp[] = effTimeStamp.split(" ");
					strDate = sp[0] + " " + hhMMSS;
				}

				if (checkNullOrEmptyString(strDate)) {
					date = dateFormat.parse(strDate);
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getCurrencyFormatedString(BigDecimal num) {
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		return defaultFormat.format(num);
	}

	public List<TakingsCash> initialize(List<TakingsCash> takingCashlist) {
		List<TakingsCash> takingCashlists = new ArrayList<>(12);

		for (int i = 1; i <= 12; i++) {
			for (int j = 0; j < takingCashlist.size(); j++) {

				if (takingCashlist.get(j).getDenominationId() != i) {
					takingCashlists.add(new TakingsCash());
					break;
				} else {

					takingCashlists.add(takingCashlist.get(j));
					takingCashlist.remove(j);
					break;

				}

			}

		}

		return takingCashlists;
	}

	public List<Denomination> initializeDenomination(List<Denomination> denominationList,
			List<TakingsCash> takingCashlist) {

		for (int i = 0; i < denominationList.size(); i++) {
			for (int j = 0; j < takingCashlist.size(); j++) {

				if (denominationList.get(i).getId() == takingCashlist.get(j).getDenominationId()) {
					denominationList.get(i)
							.setQuantity(denominationList.get(i).getQuantity() + takingCashlist.get(j).getQuantity());
				}

			}

			denominationList.get(i)
					.setAmount(denominationList.get(i).getQuantity() * denominationList.get(i).getMultiplier());

		}

		return denominationList;
	}

	public int getMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}

	public int getWeekNumber(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int weekNumber = cal.get(Calendar.WEEK_OF_YEAR);
		return weekNumber;
	}

	public int getYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public String getDate(int month) throws ParseException {
		int day = 0;
		int mo = getMonth();
		if (month <= mo)
			day = month - mo;
		if (month > mo)
			day = month - (12 + 1);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, day);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		String currentTimeStamp = dateFormat.format(cal.getTime());

		return currentTimeStamp;

	}

	public String getEndDate(int month) throws ParseException {
		int day = 0;
		int mo = getMonth();
		if (month <= mo)
			day = (month - mo) + 1;
		if (month > mo)
			day = month - (12);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, day);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		String currentTimeStamp = dateFormat.format(cal.getTime());
		return currentTimeStamp;
	}

	public String getfirstdate(String enddate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(enddate));
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String currentTimeStamp = dateFormat.format(cal.getTime());
		return currentTimeStamp;
	}

	public String getReferenceNumber(String bname, int count) {

		String temp = bname.substring(0, 3).toUpperCase();
		temp += String.valueOf(getYear()).substring(0, 1);
		temp += String.valueOf(getYear()).substring(2, 4);
		temp += String.valueOf(getMonth());
		temp += getAlphabetic();
		temp += String.valueOf(count);
		temp += getAlphabetic();
		return temp;
	}

	public String getReferenceNum(String bname) {
		String temp = bname.substring(0, 3).toUpperCase();
		temp += String.valueOf(getYear()).substring(0, 1);
		temp += String.valueOf(getYear()).substring(2, 4);
		temp += String.valueOf(getMonth());
		return temp;
	}

	public String getAlphabetic() {
		final String alphabet = "ABCDEEFGHIJKLMNOPQRSTUVWXYZ";
		final int N = alphabet.length();
		String temp = null;
		Random r = new Random();

		for (int i = 0; i < 1; i++) {
			temp = String.valueOf(alphabet.charAt(r.nextInt(N)));
		}
		return temp;
	}

	public String chageDateFormat(String edate) {
		final String OLD_FORMAT = "dd/MM/yyyy";
		final String NEW_FORMAT = "yyyy-MM-dd";
		String getdate = null;
		try {
			if (checkNullOrEmptyString(edate)) {
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date d = sdf.parse(edate);
				sdf.applyPattern(NEW_FORMAT);
				getdate = sdf.format(d);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getdate;
	}
	public String chageDateFormatPart(String edate,String oldFormat,String newFormat) {
		String getdate = null;
		try {
			if (checkNullOrEmptyString(edate)) {
				SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
				Date d = sdf.parse(edate);
				sdf.applyPattern(newFormat);
				getdate = sdf.format(d);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getdate;
	}

	public boolean extendDateForEndMonth(int extendNoOfDay, int period) {
		boolean val = false;
		Calendar calExtend = Calendar.getInstance();
		try {
			calExtend.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(getEndDate(period)));
			Calendar cal = Calendar.getInstance();
			calExtend.add(Calendar.DATE, extendNoOfDay);
			if (cal.getTime().after(calExtend.getTime())) {
				val = true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return val;
	}

	public String extendDate(int extendNoOfDay, int period) {
		String val = "";
		Calendar calExtend = Calendar.getInstance();
		try {
			calExtend.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(getEndDate(period)));
			calExtend.add(Calendar.DATE, extendNoOfDay);
			val = new SimpleDateFormat("yyyy-MM-dd").format(calExtend.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(val);
		return val;
	}

	public String covertDateToString(Date dateObj, String pattern) {
		return new SimpleDateFormat(pattern).format(dateObj);
	}

	public Date covertStringToDate(String strObj, String pattern) {
		Date dateObj = null;
		try {
			dateObj = new SimpleDateFormat(pattern).parse(strObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateObj;
	}
	
	
	public String encodeString(String str){
		 byte[] encoded =  Base64.encodeBase64(str.getBytes());
		return new String(encoded);
	}
	
	public String decodeString(String str){
		byte[] encoded =  Base64.decodeBase64(str.getBytes());
		return new String(encoded);
	}
	
}
