package com.proffer.endpoints.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

	public static String format(SimpleDateFormat inputFormat, SimpleDateFormat outputFormat, LocalDateTime dateTime) {
		Date date = null;
		try {
			date = inputFormat.parse(dateTime.toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return outputFormat.format(date);
	}

	public static String formatToFullDateTime(String date, String time) {
		LocalDate datePart = LocalDate.parse(date);
		LocalTime timePart = LocalTime.parse(time);
		LocalDateTime dateTime = LocalDateTime.of(datePart, timePart);

		return DateFormatter.format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.US),
				new SimpleDateFormat("MMM dd,yyyy HH:mm:ss", Locale.US), dateTime);
	}

	public static LocalDateTime getFormattedLocalDateTime(String date, String time) {
		LocalDate datePart = LocalDate.parse(date);
		LocalTime timePart = LocalTime.parse(time);
		return LocalDateTime.of(datePart, timePart);
	}
}
