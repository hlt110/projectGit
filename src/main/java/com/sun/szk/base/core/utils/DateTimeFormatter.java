package com.sun.szk.base.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// date time utils
public class DateTimeFormatter {
	public static final String SHORT_DATE = "MM-dd";
	public static final String SHORT_TIME = "HH:mm";
	public static final String SHORT_DATE_TIME = "yyyy-MM-dd HH:mm";
	public static final String FULL_DATE = "yyyy-MM-dd";
	public static final String FULL_TIME = "HH:mm:ss";
	public static final String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	public static final SimpleDateFormat SD = new SimpleDateFormat(SHORT_DATE);
	public static final SimpleDateFormat ST = new SimpleDateFormat(SHORT_TIME);
	public static final SimpleDateFormat SDT = new SimpleDateFormat(SHORT_DATE_TIME);
	public static final SimpleDateFormat FD = new SimpleDateFormat(FULL_DATE);
	public static final SimpleDateFormat FT = new SimpleDateFormat(FULL_TIME);
	public static final SimpleDateFormat FDT = new SimpleDateFormat(FULL_DATE_TIME);

	public static String sd() {
		return SD.format(new Date());
	}

	public static String st() {
		return ST.format(new Date());
	}

	public static String sdt() {
		return SDT.format(new Date());
	}

	public static String fd() {
		return FD.format(new Date());
	}

	public static String ft() {
		return FT.format(new Date());
	}

	public static String fdt() {
		return FDT.format(new Date());
	}

	public static String sd(Date date) {
		return SD.format(date);
	}

	public static String st(Date date) {
		return ST.format(date);
	}

	public static String sdt(Date date) {
		return SDT.format(date);
	}

	public static String fd(Date date) {
		return FD.format(date);
	}

	public static String ft(Date date) {
		return FT.format(date);
	}

	public static String fdt(Date date) {
		return FDT.format(date);
	}

	public static Date sd(String source) throws ParseException {
		return SD.parse(source);
	}

	public static Date st(String source) throws ParseException {
		return ST.parse(source);
	}

	public static Date sdt(String source) throws ParseException {
		return SDT.parse(source);
	}

	public static Date fd(String source) throws ParseException {
		return FD.parse(source);
	}

	public static Date ft(String source) throws ParseException {
		return FT.parse(source);
	}

	public static Date fdt(String source) throws ParseException {
		return FDT.parse(source);
	}
}
