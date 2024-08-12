package br.com.systemit.auth.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static final String PATTERN_DATE_TIME_US = "yyyy-MM-dd'T'HH:mm:ss";

	public static String getDataFormatada(Date date, String mascara) {

		SimpleDateFormat formatDate = new SimpleDateFormat(mascara);

		return formatDate.format(date);
	}

	public static LocalDateTime  getDataFormatada(String date, String pattern) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME_US);
        
        return LocalDateTime.parse(date, formatter);

	}

}