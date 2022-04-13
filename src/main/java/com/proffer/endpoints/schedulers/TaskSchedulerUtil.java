package com.proffer.endpoints.schedulers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Timer;

import org.springframework.stereotype.Component;

@Component
public class TaskSchedulerUtil {

	public void executeTaskWithDelay(long delay) {
		Timer timer = new Timer(true);
		timer.schedule(new CustomTimerTask(), 1000);
	}

	public void executeTaskOn(LocalDateTime dateTime) {
		Timer timer = new Timer(true);
		Instant instant = dateTime.toInstant(ZoneOffset.UTC);
		Date date = Date.from(instant);
		timer.schedule(new CustomTimerTask(), date);
	}
}
