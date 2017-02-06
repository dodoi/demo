package com.dodoi.demo.tools;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class StrTool {
	/**
	 * 全部替换字符串里符合指定内容的为其它内容，与String类中不同，它使用的不是正则表达式的。
	 * 
	 * @param String
	 *            strin 源初始字符串
	 * @param String
	 *            regex, 需要替换的内容
	 * @param String
	 *            replacement, 用来替换的内容
	 * @return ,字符串替换后的内容
	 */
	public static String replaceAll(String strin, String target,
			String replacement) {
		StringBuffer sb = new StringBuffer();
		int rbegin = 0;
		int rend = strin.indexOf(target);
		int rlen = target.length();
		while (rend >= 0) {
			sb.append(strin.substring(rbegin, rend));
			sb.append(replacement);
			rbegin = rend + rlen;
			rend = strin.indexOf(target, rbegin);
		}
		sb.append(strin.substring(rbegin));
		strin = sb.toString();
		return strin;
	}

	/**
	 * 限制字符串长度
	 * 
	 * @param str
	 * @param len
	 * @param suffix
	 * @return
	 */
	public static String subStrShow(String str, int len, String suffix) {
		if (str == null)
			return str;

		str = str.trim();
		int rel = str.length();
		int end = rel;
		if (rel > len) {
			int k = 0, limit2 = 2 * len - 3;
			char c;
			for (int i = 0; i < rel; i++) {
				c = str.charAt(i);
				if (c < 256) {
					k++;
				} else {
					k = k + 2;
				}
				if (k >= limit2) {
					end = ++i;
					if (k > limit2)
						end--;
					break;
				}
			}
		}

		if (end < rel) {
			return str.substring(0, end) + suffix;
		}

		return str;
	}

	public static String subStr(String str, int len, String suffix) {
		if (str != null) {
			if (str.length() > len) {
				if (len - suffix.length() > 0) {
					str = str.substring(0, len - suffix.length()) + suffix;
				} else {
					return "";
				}
			}
		}
		return str;
	}

	/**
	 * 限制字符串长度
	 * 
	 * @param str
	 * @param len
	 * @param suffix
	 * @return
	 */
	public static String subStr(String str, int start, int len, String suffix) {
		if (str != null) {
			if (str.length() > len + start) {
				if (len - suffix.length() > 0) {
					str = str.substring(start, start + len - suffix.length())
							+ suffix;
				} else {
					return "";
				}
			} else if (start < str.length()) {
				str = str.substring(start);
			}
		}
		return str;
	}

	/**
	 * 将指定时间转为UTC0 时区时间
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getUTC0TimeStr(Date dateTime) {
		if (dateTime == null)
			return "";
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd-HH:mm:ss");
		TimeZone timeZone = TimeZone.getTimeZone("GMT-0");
		sd.setTimeZone(timeZone);
		String utdDate = sd.format(dateTime);
		utdDate = utdDate.replace('-', 'T');
		return utdDate;
	}

	/**
	 * 获取当前日期对象，时间为 00：00：00
	 * 
	 * @return
	 */
	public static Date getDate() {
		Date date = setDays(new Date());
		return date;
	}

	/**
	 * 计算两个日期计间的天数
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static int getDays(Date firstDate, Date secondDate) {
		int days = 0;

		firstDate = setDays(firstDate);
		secondDate = setDays(secondDate);
		days = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
		return Math.abs(days);
	}

	public static Date getSimpleDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(formatter.format(date));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 忽略时分秒微秒，只保留年月日
	 * 
	 * @param date
	 * @return
	 */
	public static Date setDays(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

	
	/**
	 * 将字符串转换成Date日期类型对象
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static Date strToDate(String str, String format) throws Exception {
		if (str == null)
			return null;
		Date toDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			toDate = formatter.parse(str);
		} catch (Exception e) {
		}
		return toDate;
	}

	// 本例将当前日期加减天数。
	public static String dateAdd(int strTo) {
		Calendar strDate = Calendar.getInstance(); // java.util包
		strDate.add(Calendar.DATE, strTo); // 日期减 如果不够减会将月变动
		// 生成 (年-月-日) 字符串
		String meStrDate = strDate.get(Calendar.YEAR) + "-"
				+ String.valueOf(strDate.get(Calendar.MONTH) + 1) + "-"
				+ strDate.get(Calendar.DATE);
		return meStrDate;
	}

	// 本例将当前日期加减天数。
	public static long getLongDateAdd(int strTo) {
		Calendar strDate = Calendar.getInstance(); // java.util包
		strDate.add(Calendar.DATE, strTo); // 日期减 如果不够减会将月变动
		return strDate.getTimeInMillis();
	}

	// 本例将当前日期加减月数。
	public static String monthAdd(int strTo) {
		Calendar strDate = Calendar.getInstance(); // java.util包
		strDate.add(Calendar.MONTH, strTo);
		// 生成 (年-月-日) 字符串
		String meStrDate = strDate.get(Calendar.YEAR) + "-"
				+ String.valueOf(strDate.get(Calendar.MONTH) + 1) + "-"
				+ strDate.get(Calendar.DATE);
		return meStrDate;
	}

	/**
	 * 转换日期为字符串 格式(yyMMdd)
	 * 
	 * @param str
	 * @return
	 */
	public static String getDateToStr(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String strDate = formatter.format(d);
		return strDate;
	}

	/**
	 * 转换日期为字符串 格式(yyyy-MM-dd HH:mm:ss) 默认时区是东8。 有缺陷。
	 * 
	 * @param str
	 * @return
	 */
	public static String getDateToStrlongDetail(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
		formatter.setTimeZone(timeZone);

		String strDate = formatter.format(d);
		return strDate;
	}

	/**
	 * 转换日期为字符串 格式(yyyy-MM-dd )
	 * 
	 * @param str
	 * @return
	 */
	public static String getDateToStrlong(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(d);
		return strDate;
	}
	

	public static List<Long> arrayToList(String[] arr) {
			List<Long> list = new ArrayList<Long>();

			if (arr!=null) {			
				Long l;
				for (int i = 0; i < arr.length; i++) {
					if(isNumeric(arr[i])){
						l = new Long(arr[i]);
						if(!list.contains(l))
							list.add(l);
					}
				}
			}
			return list;
		}


	/**
	 * 将字符串转换成Date日期类型对象 （简单的格式）
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		Date date = null;
		if (str != null && !isBlank(str)) {
			str.replaceAll("  ", " ");
			try {
				date = strToDate(str, "yyyy-MM-dd HH:mm");
			} catch (Exception e) {
				return stringSimpleToDate(str);
			}
		}
		return date;
	}

	// 转化为列表
	public static List<Long> stringToList(String str) {
		List<Long> list = new ArrayList<Long>();

		if (!isBlank(str)) {
			String temp[] = str.split(",");
			Long l;
			for (int i = 0; i < temp.length; i++) {
				if(!isNumeric(temp[i]))
					continue;
				l = new Long(temp[i]);
				list.add(l);
			}
		}
		return list;
	}	
	// 转化为列表
	public static List<String> stringToList(String str,String split) {
		List<String> list = new ArrayList<String>();

		if (!isBlank(str)) {
			String temp[] = str.split(split);
			for (int i = 0; i < temp.length; i++) {
				if(isBlank(temp[i]))
					continue;
				list.add(temp[i]);
			}
		}
		return list;
	}

	// 转化为字符串
	public static String listToString(List<Long> list) {
		if(list == null)
			return "";
		
		StringBuffer buf = new StringBuffer();

		int len = list.size();
		for (int i = 0; i < len - 1; i++)
			buf.append(list.get(i)).append(",");
		if (len >= 1)
			buf.append(list.get(len - 1));

		return buf.toString();
	}
	
	// 转化为字符串
		public static String arrToString(int[] list) {
			if(list == null)
				return "";
			
			StringBuffer buf = new StringBuffer();

			int len = list.length;
			for (int i = 0; i < len - 1; i++)
				buf.append(list[i]).append(",");
			if (len >= 1)
				buf.append(list[len - 1]);

			return buf.toString();
		}
		
	public static String listStrToString(List<String> list) {
		StringBuffer buf = new StringBuffer();

		int len = list.size();
		for (int i = 0; i < len - 1; i++)
			buf.append(list.get(i)).append(",");
		if (len >= 1)
			buf.append(list.get(len - 1));

		return buf.toString();
	}

	/**
	 * 将字符串转换成Date日期类型对象 （简单的格式）
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringSimpleToDate(String str) {
		Date date = null;
		if (str != null && !isBlank(str)) {
			str.replaceAll("  ", " ");
			try {
				date = strToDate(str, "yyyy-MM-dd");
			} catch (Exception e) {
			}
		}
		return date;
	}

	/**
	 * 判断字符串是否是有效值 如果无效返回true 有效返回false StrTool.isBlank(null)= true;
	 * StrTool.isBlank("")= true; StrTool.isBlank(" ")= true; StrTool.isBlank("
	 * boo ")= false; StrTool.isBlank("boo")= false;
	 * 
	 * @return
	 */
	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNumeric(String str) {
		if (StringUtils.isNotBlank(str) && StringUtils.isNumeric(str))
			return true;
		return false;
	}

	public static boolean isBlank(Collection col) {
		if (col == null || col.size() == 0)
			return true;
		return false;
	}

	public static Long negtiveLong(Long a) {
		Long b = Long.valueOf(0 - a.longValue());
		return b;
	}

	/**
	 * 获得一个string的html表现形式（对一些转义字符进行转义）
	 * 
	 * @param res
	 * @return
	 */
	public static String textToHtml(String res) {
		try {
			// 回车
			res = res.replaceAll("\r\n", "<br>");
			res = res.replaceAll("\n", "<br>");
			res = res.replaceAll("\r", "<br>");

			// 空格
			res = res.replaceAll(" ", "&nbsp;");

			// 制表符
			res = res.replaceAll("\t",
					"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");

		} catch (Exception e) {
			return res;
		}
		return res;
	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public static Set getSetFromString(String a) {
		Set<Long> set = new HashSet<Long>();
		if (StringUtils.isBlank(a))
			return set;

		String[] b = a.split(",");
		Long x;
		for (int i = 0; i < b.length; i++) {
			if (StringUtils.isBlank(b[i]))
				continue;
			x = new Long(b[i]);
			set.add(x);
		}
		return set;
	}

	/**
	 * 比较两个日历类的时间先后 ,如果是同一天，会认为相等
	 * 
	 * @param firstCalendar
	 * @param secondCalendar
	 * @return 0 ：firstCalendar = secondCalendar 1 ：firstCalendar >
	 *         secondCalendar -1 ：firstCalendar < secondCalendar
	 */
	public static int compareDate(Calendar firstCalendar,
			Calendar secondCalendar) {
		int result = 0;

		if (firstCalendar.get(Calendar.YEAR) > secondCalendar
				.get(Calendar.YEAR)) {
			result = 1;
		} else if (firstCalendar.get(Calendar.YEAR) < secondCalendar
				.get(Calendar.YEAR)) {
			result = -1;
		} else {
			if (firstCalendar.get(Calendar.MONTH) > secondCalendar
					.get(Calendar.MONTH)) {
				result = 1;
			} else if (firstCalendar.get(Calendar.MONTH) < secondCalendar
					.get(Calendar.MONTH)) {
				result = -1;
			} else {
				if (firstCalendar.get(Calendar.DATE) > secondCalendar
						.get(Calendar.DATE)) {
					result = 1;
				} else if (firstCalendar.get(Calendar.DATE) < secondCalendar
						.get(Calendar.DATE)) {
					result = -1;
				} else {
					result = 0;
				}
			}
		}

		return result;
	}

	public static int compareDate(Date firstDate, Date secondDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(firstDate);
		c2.setTime(secondDate);
		return compareDate(c1, c2);
	}

	public static String getStringFromCollection(HashSet<Long> ids) {
		StringBuffer buf = new StringBuffer();
		Long temp;
		int i = 0;
		Iterator iter = ids.iterator();

		while (iter.hasNext()) {
			temp = (Long) iter.next();
			if (i > 0)
				buf.append(",");
			buf.append(temp);
			i++;
		}
		return buf.toString();
	}

	public static String getStringFromSet(Set<Long> ids) {
		StringBuffer buf = new StringBuffer();
		Long temp;
		int i = 0;
		Iterator iter = ids.iterator();

		while (iter.hasNext()) {
			temp = (Long) iter.next();
			if (i > 0)
				buf.append(",");
			buf.append(temp);
			i++;
		}
		return buf.toString();
	}

	public static String getStringFromSetByNum(Set<Long> ids, int num) {
		if (num >= ids.size()) {
			num = ids.size();
		}
		StringBuffer buf = new StringBuffer();
		Long temp;
		List<Long> idsTemp = new ArrayList<Long>();
		idsTemp.addAll(ids);
		if (idsTemp.size() != 0) {
			for (int i = 0; i < num; i++) {
				temp = (Long) idsTemp.get(i);
				if (i > 0)
					buf.append(",");
				buf.append(temp);
			}
		}
		return buf.toString();
	}

	
	public static String subString(String srcStr ,int n,String str) {
		if (srcStr != null && srcStr.length() > n) {
			return srcStr.substring(0, n) + str;
		} else {
			return srcStr;
		}
	}

	public static String getSHA1Code(String yourContent) {
		if (yourContent == null)
			return null;
		String code = "";

		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] bytes = md.digest(yourContent.getBytes());

			StringBuilder buf = new StringBuilder(bytes.length * 2);
			int i;

			for (i = 0; i < bytes.length; i++) {
				if (((int) bytes[i] & 0xff) < 0x10) {
					buf.append("0");
				}
				buf.append(Long.toString((int) bytes[i] & 0xff, 16));
			}

			code = buf.toString();

		} catch (Exception e) {
		}

		return code;
	}

	


	public static String StrToTextarea(String res) {
		try {
			// 回车
			res = res.replaceAll("<", "&lt;");
			res = res.replaceAll(">", "&gt;");
			res = res.replaceAll(" ", "&nbsp;");
		} catch (Exception e) {
			return res;
		}
		return res;
	}

	public static String textareaToStr(String res) {
		try {
			// 回车
			res = res.replaceAll("&lt;", "<");
			res = res.replaceAll("&gt;", ">");
			res = res.replaceAll("&nbsp;", " ");
		} catch (Exception e) {
			return res;
		}
		return res;
	}

	/**
	 * 获得有效的页码
	 * 
	 * @param total
	 * @param curr
	 * @param max
	 * @return
	 */
	public static int getAvailCurrentPage(int total, int curr, int max) {
		if (curr > 1) {
			int last = total / max;
			if (total % max > 0)
				last++;

			if (curr > last)
				curr = last;
		}
		
		if (curr <= 0) {
			return 1;
		}

		return curr;
	}

	/**
	 * 获得有效的页码
	 * 
	 * @param total
	 * @param curr
	 * @param max
	 * @return
	 */
	public static List<Long> getAvailIDList(List<Long> list, int curr, int max) {
		int toIndex = max;
		if (curr <= 0) {
			curr = 1;
		}

		if (curr > 1) {
			int last = list.size() / max;
			if (list.size() % max > 0) {

				last++;
			}

			if (curr > last)
				curr = last;
		}
		if (list.size() / (curr * max) <= 0)
			toIndex = list.size() % max;
		return list.subList((curr - 1) * max, (curr - 1) * max + toIndex);
	}

	/**
	 * 把字符串变为long 如果是非法，则返回0
	 * 
	 * @param str
	 * @return
	 */
	public static long str2long(String str) {
		if (StringUtils.isBlank(str))
			return 0L;
		long id = 0L;

		try {
			id = Long.valueOf(str);
		} catch (NumberFormatException e) {
		}
		return id;
	}

	

	/**
	 * 这个函数负责将字符转换成十六进制
	 */
	public static String string2hex(String str) {
		byte[] b = str.getBytes();
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + " 0" + stmp;
			else
				hs = hs + " " + stmp;
		}
		return hs;
	}

	public static String getBlank(HttpServletRequest request) {
		return "javascript:false";
		//		
		// String blankSrc = "";
		// String s = request.getHeader("user-agent");
		// // 判断user-agent的信息
		// if ((s != null) && (s.indexOf("MSIE")) != -1) {
		// String cp = request.getContextPath();
		// blankSrc = cp + "/login/blank.html";
		// }
		// return blankSrc;
	}
	
	public static long getId(String id) {
		if(isNumeric(id))
			return new Long(id);
		return 0;
	}
	
	public static int getRealEnd(int showLimit,String str) {
		int rel = str.length();
		int end = rel;
		if (rel > showLimit) {
			int k = 0,  limit2 = 2 * showLimit-3;
			char c;
			for (int i = 0; i < rel; i++) {					
				c = str.charAt(i);
				if (c < 256) {
					k++;
				} else {
					k = k + 2;
				}
				if (k >= limit2) {
					end = ++i;
					if(k>limit2)
						end--;
					break;
				}
			}
		}
		return end;
	}
	
	public static String getUrlDecode(String str){
		try {
			str = str.replaceAll("_", "%");
			str = URLDecoder.decode(str, "utf-8"); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	   * 产生随机字符串
	   * */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
				+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

}