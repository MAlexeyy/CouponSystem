package com.johnbryce.CouponSystem.Date;

import java.time.LocalDate;
import java.util.Date;

public class DateConfig {
	
	public static LocalDate now = LocalDate.now();
	
	public static Date dateNow() {
		LocalDate now = LocalDate.now();
		Date nowDate = java.sql.Date.valueOf(now);
		return nowDate;
	}

}
