package com.hc.sap.anywhere.api.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

/**
 * JDK8 时间操作 begin
 * Instant——它代表的是时间戳
 * LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。
 * LocalTime——它代表的是不含日期的时间
 * LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。
 * ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的。
 * 
 */
public class DateUtils {

	public static final String DDATE_PATTERN_FULL = "yyyy-MM-dd KK:mm:ss";
	public static final String DATE_PATTERN_FULL_CONNECT = "yyyyMMddHHmmss";

	public static final String DATE_PATTERN_DATE_DAY = "yyyy-MM-dd";
	public static final String DATE_PATTERN_DATE_DAY_CONNECT = "yyyyMMdd";
	
	public static final String DATE_PATTERN_DATE_TIME_CONNECT = "HHmmss";
	
	
	public static final String DATE_PATTERN_DATE_MONTH = "yyyy-MM";
	
	public static final String ALL_MONTH = "0";
	
	
	public static String getNowUTCZonedDateString(){
		return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC));
	}
	
	public static String date2String(Date date,String pattern){
		LocalDateTime localDateTime = dateToLocaLDateTime(date);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(localDateTime);
	}
	
	public static String localDateTime2String(LocalDateTime localDateTime,String pattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return formatter.format(localDateTime);
	}
	
	
	public static Date string2Date(String date_text,String pattern){
		LocalDateTime localDateTime = string2LocalDateTime(date_text, pattern);
		return localDateTimeToDate(localDateTime);
	}
	
	public static LocalDateTime string2LocalDateTime(String date_text,String pattern){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDateTime.parse(date_text, formatter);
	}
	
	/**
	 * 获得今天
	 * @return
	 */
	public static Date getTodayDate(){
		LocalDate now = LocalDate.now();
		return localDateTimeToDate(now.atStartOfDay());
	}
	
	/**
	 * 获得今天
	 * @return
	 */
	public static Date getTodayDateTime(){
		LocalDateTime now = LocalDateTime.now();
		return localDateTimeToDate(now);
	}
	
	/**
	 * 获得几天后的时间
	 * @return
	 */
	public static Date getTomorrowDate(long days){
		LocalDate now = LocalDate.now();
		return localDateTimeToDate(now.plusDays(days).atStartOfDay());
	}
	
	/**
	 * 获得时间戳
	 * @param localDate
	 * @return
	 */
	public static Instant getInstant(LocalDateTime localDateTime){
		//转换为包含时区信息的ZonedDateTime
		ZonedDateTime zdt = localDateTime.atZone(getZoneId());
		return zdt.toInstant();
	}
	
	/**
	 * 时区
	 * @return
	 */
	public static ZoneId getZoneId(){
		return ZoneId.systemDefault();
	}
	
	
	/**
	 * 获得时间戳
	 * @param localDate
	 * @return
	 */
	public static Instant getInstant(Date date){
		return date.toInstant();
	}
	
	/**
	 * 时间类型转换
	 * @param localDate
	 * @return
	 */
	public static LocalDateTime dateToLocaLDateTime(Date date){
		return getInstant(date).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	/**
	 * 时间类型转换
	 * @param localDate
	 * @return
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime){
		return Date.from(getInstant(localDateTime));
	}
	
	public static Date[] getStartAndEndTime(String year,String month){
		Date[] dates = null;
		if(null == year && "".equals(year)){
			return dates;
		}
		try {
			dates = new Date[2];
			int year_int = Integer.parseInt(year);
			if(null != month && !"".equals(month)){
				//全年时间区间
				if(ALL_MONTH.equals(month)){
					LocalDate localDate = LocalDate.of(year_int, 1, 1);
					LocalDate nextLocalDate = localDate.plusYears(1l);
					dates[0] = localDateTimeToDate(localDate.atStartOfDay());
					dates[1] = localDateTimeToDate(nextLocalDate.atStartOfDay());
					return dates;
				}
				//某月时间区间
				else{
					int month_int = Integer.parseInt(month);
					LocalDate localDate = LocalDate.of(year_int, month_int, 1);
					LocalDate nextLocalDate = localDate.plusMonths(1l);
					dates[0] = localDateTimeToDate(localDate.atStartOfDay());
					dates[1] = localDateTimeToDate(nextLocalDate.atStartOfDay());
					return dates;
				}
			}
		} catch (Exception e) {
			return dates;
		} 
		return dates;
	}
	
	public static Date[] getMonthStartAndEndTime(Date date){
		LocalDateTime localDateTime = DateUtils.dateToLocaLDateTime(date);
		String year = String.valueOf(localDateTime.getYear());
		String month = String.valueOf(localDateTime.getMonth().getValue());
		return DateUtils.getStartAndEndTime(year, month);
	}
	
	public static String getNanoOfDay(){
		LocalTime localTime = LocalTime.now();
		try {
			Thread.sleep(10l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String nanoofday = "";
		nanoofday = String.valueOf(localTime.getLong(ChronoField.NANO_OF_DAY)/1000000);
		return nanoofday;
	}
	
	public static void main(String[] args) {
		
		 String text = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
		 String text1 = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now(getZoneId()));
		 String text2 = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now(ZoneId.ofOffset("UTC", ZoneOffset.UTC)));
		 
		 String text3 = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ZonedDateTime.now(ZoneOffset.UTC));
		 System.out.println(text);
		 System.out.println(text1);
		 System.out.println(text2);
		 System.out.println(text3);
	}
}
