package com.quickly.devploment.redis.webvote.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author lidengjin
 * @Date 2020/8/4 2:36 下午
 * @Version 1.0
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static ZoneId zoneId = ZoneId.systemDefault();

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYMMDD = "yyMMdd";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String HHmm = "HHmm";
	private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

	public static Date parseDefaultPatternDate(String dateString) {
		try {
			return parseDate(dateString, DEFAULT_DATE_PATTERN);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date parse(String strDate, String pattern) {
		try {
			return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException("Unable to parse the date: " + strDate + ", pattern:" + pattern);
		}
	}

	/**
	 * @param batchDate
	 * @return
	 * @author ChengChun
	 * @see ：getLastDayOfMonth
	 * @see ：获得月底最后一天的日期
	 * @see ：2015-6-24下午5:34:55
	 * @see ：
	 * @see [编号：日期]，[修改人：*** ]，[修改说明：***]
	 */
	public static Date getLastDayOfMonth(Date batchDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(batchDate);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DATE, 1);
		c.add(Calendar.DATE, -1);
		return c.getTime();
	}

	/**
	 * @param from
	 * @param to
	 * @return
	 * @see：获得给定两个日期之间相隔的月数，算到日
	 * @author ChengChun
	 * @see ：getMonthInterval
	 * @see ：2015-6-24下午5:35:07
	 * @see ：
	 * @see [编号：日期]，[修改人：*** ]，[修改说明：***]
	 */
	public static int getMonthInterval(Date from, Date to) {
		Calendar f = Calendar.getInstance();
		f.setTime(from);
		Calendar t = Calendar.getInstance();
		t.setTime(to);
		int year = t.get(Calendar.YEAR) - f.get(Calendar.YEAR);
		int month = t.get(Calendar.MONTH) - f.get(Calendar.MONTH);
		return year * 12 + month;
	}

	/**
	 * @param from
	 * @param to
	 * @return
	 * @author ChengChun
	 * @see ：getIntervalDays
	 * @see ：获得两个日期间隔天数
	 * @see ：2015-6-24下午5:35:21
	 * @see ：
	 * @see [编号：日期]，[修改人：*** ]，[修改说明：***]
	 */
	public static int getIntervalDays(Date from, Date to) {
		long interval = truncate(to, Calendar.DATE).getTime() - truncate(from, Calendar.DATE).getTime();
		int result = (int) (interval / (1000 * 3600 * 24));
		return result;
	}

	/**
	 * @param date
	 * @return
	 * @author ChengChun
	 * @see ：isMonthEnd
	 * @see ：判断给定日期是否月底
	 * @see ：2015-6-24下午5:35:35
	 * @see ：
	 * @see [编号：日期]，[修改人：*** ]，[修改说明：***]
	 */
	public static boolean isMonthEnd(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.get(Calendar.DATE) == 1;
	}

	/**
	 * 格式化日期
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate2String(Date date, String pattern) {
		if (date == null) {return null;}
		SimpleDateFormat myFmt = new SimpleDateFormat(pattern);
		return myFmt.format(date);
	}

	/**
	 * @param b007
	 * @param batchDate
	 * @return
	 * @author ChengChun
	 * @see ：fixYear
	 * @see ：b007补年
	 * @see ：2015-6-24下午4:57:01
	 * @see ：
	 * @see [编号：日期]，[修改人：*** ]，[修改说明：***]
	 */
	public static Date fixYear(String b007, Date batchDate) {
		Calendar batchDateTime = Calendar.getInstance();
		//对于交易日期大于当前营业日期2天及以内的交易，视为正常交易，交易年份按当日顺推处理
		batchDateTime.setTime(DateUtils.addDays(batchDate, 2));
		Calendar txnDateTime = Calendar.getInstance();
		txnDateTime.set(batchDateTime.get(Calendar.YEAR), Integer.parseInt(b007.substring(0, 2)) - 1,
				Integer.parseInt(b007.substring(2, 4)), Integer.parseInt(b007.substring(4, 6)),
				Integer.parseInt(b007.substring(6, 8)), Integer.parseInt(b007.substring(8, 10)));
		if (txnDateTime.get(Calendar.DAY_OF_YEAR) > batchDateTime.get(Calendar.DAY_OF_YEAR)) {
			txnDateTime.add(Calendar.YEAR, -1);
		}
		return txnDateTime.getTime();
	}

	/**
	 * 获取日期指定部分，如年，月，日，时，分秒，周第几天等等
	 *
	 * @param date
	 * @param item Calendar.YEAR/MONTH/DATE/HOUR/MINUTE/SECOND/DAY_OF_WEEK
	 */
	public static int getTimeByCalendar(Date date, int item) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		//获取月份是特殊处理 +1
		if (Calendar.MONTH == item) {
			return cal.get(item) + 1;
		}

		return cal.get(item);
	}

	/**
	 * 获取日期指定部分，如年，月，日，时，分秒，周第几天等等并进行左侧填充
	 * 例如：月份是1月，则返回01，月份是10月则原样返回
	 *
	 * @param date
	 * @param item Calendar.YEAR/MONTH/DATE/HOUR/MINUTE/SECOND/DAY_OF_WEEK
	 */
	public static String leftPadTimeByCalendar(Date date, int item) {
		int timeByCalendar = getTimeByCalendar(date, item);
		if (Calendar.YEAR == item) {
			return StringUtils.leftPad(String.valueOf(timeByCalendar), 4, "0");
		}
		return StringUtils.leftPad(String.valueOf(timeByCalendar), 2, "0");
	}

	/**
	 * 给时间加上或减去指定毫秒，秒，分，时，天、月或年等，返回变动后的时间
	 *
	 * @param date   要加减前的时间，如果不传，则为当前日期
	 * @param field  时间域，有Calendar.MILLISECOND,Calendar.SECOND,Calendar.MINUTE,<br>
	 *               Calendar.HOUR,Calendar.DATE, Calendar.MONTH,Calendar.YEAR
	 * @param amount 按指定时间域加减的时间数量，正数为加，负数为减。
	 * @return 变动后的时间
	 */
	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);

		return cal.getTime();
	}


	/**
	 * 拼接获得业务日期的时间: 业务/批量日期 + 自然时间的时分秒
	 *
	 * @param bizDate
	 * @return
	 */
	public static Date parseBusinessTime(Date bizDate) {
		if (bizDate == null) {
			return null;
		}
		// 业务日期
		Calendar biz = Calendar.getInstance();
		biz.setTimeInMillis(bizDate.getTime());
		// 当前时间
		Calendar now = Calendar.getInstance();
		// 设置时分秒
		biz.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
		biz.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
		biz.set(Calendar.SECOND, now.get(Calendar.SECOND));

		return biz.getTime();
	}

	/**
	 * 判断middDate是否在startDate,endDate区间，不包含等于
	 *
	 * @param startDate
	 * @param middDate
	 * @param endDate
	 * @return boolean
	 * @throws
	 * @since 1.0.0
	 */
	public static boolean dateBetwen(String startDate, String middDate, String endDate) {
		return dateCompare(middDate, startDate) > 0 && dateCompare(endDate, middDate) > 0;
	}

	/**
	 * 判断middDate是否在startDate,endDate区间，包含等于
	 *
	 * @param startDate
	 * @param middDate
	 * @param endDate
	 * @return boolean
	 * @throws
	 * @since 1.0.0
	 */
	public static boolean dateBetwen(Date startDate, Date middDate, Date endDate) {
		return dateCompare(middDate, startDate) > 0 && dateCompare(endDate, middDate) >= 0;
	}

	/**
	 * 判断middDate是否在startDate,endDate区间，包含等于
	 *
	 * @param startDate
	 * @param middDate
	 * @param endDate
	 * @return boolean
	 * @throws
	 * @since 1.0.0
	 */
	public static boolean dateBetwenAndSame(Date startDate, Date middDate, Date endDate) {
		return dateCompare(middDate, startDate) >= 0 && dateCompare(endDate, middDate) >= 0;
	}

	/**
	 * 日期比较
	 *
	 * @param date1
	 * @param date2
	 * @return int date1 > date2 =1；date1 = date2 =0；date1 <date2 =-1
	 * @throws
	 * @since 1.0.0
	 */
	public static int dateCompare(String date1, String date2) {
		int i = 0;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			i = sm.parse(date1).compareTo(sm.parse(date2));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return i;
	}

	/**
	 * 日期比较
	 *
	 * @param date1
	 * @param date2
	 * @return int date1 > date2 =1；date1 = date2 =0；date1 <date2 =-1
	 * @throws
	 * @since 1.0.0
	 */
	public static int dateTimeCompare(String date1, String date2) {
		int i = 0;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			i = sm.parse(date1).compareTo(sm.parse(date2));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return i;
	}

	/**
	 * 日期比较
	 *
	 * @param date1
	 * @param date2
	 * @return int date1 > date2 =1；date1 = date2 =0；date1 <date2 =-1
	 * @throws
	 * @since 1.0.0
	 */
	public static int dateCompare(Date date1, Date date2) {
		int i = 0;
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String bigenDate = sm.format(date1);
		String endDate = sm.format(date2);
		sm.format(date2);
		try {
			i = sm.parse(bigenDate).compareTo(sm.parse(endDate));
		} catch (ParseException e) {
			log.error("ParseException", e);
		}
		return i;
	}

	/**
	 * 用批量日期计算逾期期数
	 *
	 * @param overDueDate
	 * @param overDueDate
	 * @return
	 */
	public static int countOverDueTerm(Date overDueDate, Date batchDate) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(overDueDate);
		int month1 = calendar1.get(Calendar.MONTH);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(batchDate);
		int month2 = calendar2.get(Calendar.MONTH);
		int interval = month2 - month1;
		int year = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);

		if (interval >= 0) {
			calendar1.add(Calendar.MONTH, interval);
			int days = calendar2.get(Calendar.DAY_OF_MONTH) - calendar1.get(Calendar.DAY_OF_MONTH);
			if (days < 0) {
				//还没到一个月
			} else {
				//到了一个月
				interval++;
			}
		}
		return year * 12 + interval;
	}

	/**
	 * 获取下一个固定日期
	 * 如固定日期为20号，则获取下一个即将到来的20号
	 *
	 * @param targetDate
	 * @param fixedDay
	 * @return
	 */
	public static Date nextFixedDayOfMonth(Date targetDate, int fixedDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(targetDate);
		int currDay = calendar.get(Calendar.DAY_OF_MONTH);
		if (currDay >= fixedDay) {//说明已经过了当月的该固定日
			calendar.set(Calendar.DAY_OF_MONTH, fixedDay);
			calendar.add(Calendar.MONTH, 1);
		} else {
			calendar.set(Calendar.DAY_OF_MONTH, fixedDay);
		}
		return calendar.getTime();
	}

	/**
	 * 获得指定日期的前一天
	 *
	 * @param dateSpecified
	 * @return
	 * @throws Exception
	 */
	public static Date getSpecifiedDayBefore(Date dateSpecified) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String specifiedDay = simpleDateFormat.format(dateSpecified);
		Calendar c = Calendar.getInstance();
		Date date = null;
		Date specifiedDate = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
			c.setTime(date);
			int day = c.get(Calendar.DATE);
			c.set(Calendar.DATE, day - 1);
			String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			specifiedDate = simpleDateFormat.parse(dayBefore);
		} catch (ParseException e) {
			log.error("ParseException", e);
		}

		return specifiedDate;
	}

	public static Date getThreeDaysBefore(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = simpleDateFormat.format(date);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(simpleDateFormat.parse(dateStr));
			c.set(Calendar.DATE, c.get(Calendar.DATE) - 3);
			return c.getTime();
		} catch (ParseException e) {
			throw new RuntimeException("getThreeDaysBefore failed");
		}
	}

	/**
	 * 获得今天Date（年月日）
	 * System.out = "Wed Aug 01 00:00:00 CST 2018"
	 */
	public static Date getToday0H0M0S0MS() {
		LocalDate today = LocalDate.now();
		ZonedDateTime zdt = today.atStartOfDay(zoneId);
		return Date.from(zdt.toInstant());
	}

	/**
	 * 截断到小时开始, 保留指定日期到整点, 2019-10-01 12:55:43 -> 2019-10-01 12:00:00
	 *
	 * @return 如果参数为null则返回null
	 */
	public static Date atStartOfHour(Date date) {
		if (date == null) {
			return null;
		}
		return truncate(date, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 截断到天开始, 保留指定日期到整天, 2019-10-01 12:55:43 -> 2019-10-01
	 *
	 * @return 如果参数为null则返回null
	 */
	public static Date atStartOfDay(Date date) {
		if (date == null) {
			return null;
		}
		return truncate(date, Calendar.DAY_OF_MONTH);
	}
}
