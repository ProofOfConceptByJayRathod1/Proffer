package com.proxibid.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

	// convert one format date to another
	public static String format(SimpleDateFormat inputFormat, SimpleDateFormat outputFormat, LocalDateTime dateTime) {
		Date date = null;
		try {
			date = inputFormat.parse(dateTime.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outputFormat.format(date);
	}

	// converts yyyy-MM-dd'T'HH:mm to MMM dd,yyyy HH:mm:ss
	// used for countdown in JS
	public static String formatToFullDateTime(String date, String time) {
		LocalDate datePart = LocalDate.parse(date);
		LocalTime timePart = LocalTime.parse(time);
		LocalDateTime dateTime = LocalDateTime.of(datePart, timePart);

		return DateFormatter.format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US),
				new SimpleDateFormat("MMM dd,yyyy HH:mm:ss", Locale.US), dateTime);
	}

	// merge date and time
	public static LocalDateTime getFormattedLocalDateTime(String date, String time) {
		LocalDate datePart = LocalDate.parse(date);
		LocalTime timePart = LocalTime.parse(time);
		return LocalDateTime.of(datePart, timePart);
	}
}
