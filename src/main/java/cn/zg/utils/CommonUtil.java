package cn.zg.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义工具类
 * @author Jiang, Hang
 *
 */
@Slf4j
public class CommonUtil {
	
	/**
	 * 日期格式化成指定格式的字符串
	 * @param date 日期参数
	 * @return 格式化后的字符串
	 */
	public static String dateFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
		return format.format(date);
	}
	
	/**
	 * 日期格式化成指定格式字符串  "1970-01-01T09:00:00Z"
	 * @param date 日期参数
	 * @return 格式化后的字符串
	 */
	public static String dateTransfer(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return format.format(date);
	}
	
	/**
	 * 格式化时间，并向前/向后推移时间
	 * @param time
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static String timeFormate(String time, Integer value) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = format.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, value);
		
		return format.format(cal.getTime());
	}
	
	/**
	 * 格式化时间，并向前/向后推移时间， 并清除分钟和秒数
	 * @param time
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static String timeFormateAndClear(String time, Integer value) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		Date date = format.parse(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if(value != null) {
			cal.add(Calendar.HOUR_OF_DAY, value);
		}
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return format.format(cal.getTime());
	}
	
	/**
	 * 获取日期中的小时数
	 * @param time 日期参数
	 * @return 格式化后的字符串
	 * @throws ParseException 
	 */
	public static Integer getHour(String time) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sd.parse(time));
		cal.add(Calendar.HOUR_OF_DAY, 8);
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 动态生成问题图片保存路径地址
	 * @param rootPath 根目录
	 * @return 最终生成的文件对象
	 */
	public static File imageSaved(String rootPath, String parentFolder) {
		rootPath = rootPath.replace("file:", "");
		File dirParent = null;
		if(parentFolder == null || "".equals(parentFolder.trim())) {
			dirParent = new File(rootPath);
		}else {
			dirParent = new File(rootPath+"/"+parentFolder);
		}
		
		if(!dirParent.exists()) {
			log.debug("----------文件路径{}不存在！", rootPath);
			try {
				dirParent.mkdirs();
			}catch (Exception e) {
				log.error("----------文件夹{}创建失败！", rootPath);
				return null;
			}
		}

		//使用指定日期创建文件夹
		File dirDate = new File(dirParent, dateFormat(new Date()));
		dirDate.mkdir();
		
		return dirDate;
	}

}

