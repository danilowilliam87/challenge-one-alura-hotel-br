package utils;

import java.sql.Date;
import java.time.LocalDate;

import exceptions.DateUtilsException;

public class DateUtils {
	
	public static Date converterData(LocalDate date) {
		if(date == null) {
			throw new DateUtilsException("data nula ou inválida");
		}
		return Date.valueOf(date);
	}
	
	public static LocalDate converterData(Date date) {
		if(date == null) {
			throw new DateUtilsException("data nula ou inválida");
		}
		return date.toLocalDate();
	}
	
	public static LocalDate converter(String date) {
		if(date == null) {
			throw new DateUtilsException("data nula ou inválida");
		}
		
		return LocalDate.parse(date);
	}
	
	

}
