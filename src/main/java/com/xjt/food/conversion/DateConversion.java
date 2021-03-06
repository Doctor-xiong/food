package com.xjt.food.conversion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * 定义日期格式转换器
 * 在页面上提交上来的是字符串，但是在实体类中用的格式是 Date类型
 * @author junliu
 *
 */
@Slf4j
public class DateConversion implements Converter<String, Date>{

	@Override
	public Date convert(String date) {
		log.info("日期转换器接受到---------"+date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
}	
