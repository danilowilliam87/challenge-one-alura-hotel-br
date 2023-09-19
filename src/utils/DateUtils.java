package utils;

import java.time.LocalDate;
import java.util.Date;

public class DateUtils {
	
	@SuppressWarnings("deprecation")
	public static Date converterData(LocalDate date) {
		return new Date(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
	}

}
