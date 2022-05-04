package com.proxibid.schedulers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

	private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

	private final TaskScheduler executor;

	@Autowired
	public Scheduler(TaskScheduler taskExecutor) {
		this.executor = taskExecutor;
	}

	public void scheduleToday(final Runnable task, LocalDateTime dateTime, String eventTitle) {
		executor.schedule(task, Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
	}

	public void scheduleTodaysAuctionAlert(final Runnable task, LocalDateTime dateTime) {
		executor.schedule(task, Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
	}

	public void scheduleAlert(final Runnable task, LocalDateTime dateTime) {
		executor.schedule(task, Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
	}

	public void scheduleTodaysAuctionEnding(final Runnable task, LocalDateTime dateTime, String eventTitle) {
		log.info("\"" + eventTitle + "\"" + " scheduled to end on " + dateTime.toString());
		executor.schedule(task, Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant()));
	}

	public void scheduling(final Runnable task) {
		// Schedule a task to run once at the given date (here in 1minute)
		executor.schedule(task,
				Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant()));

		// Schedule a task that will run as soon as possible and every 1000ms
		executor.scheduleAtFixedRate(task, 1000);

		// Schedule a task that will first run at the given date and every 1000ms
		executor.scheduleAtFixedRate(task,
				Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant()), 1000);

		// Schedule a task that will run as soon as possible and every 1000ms after the
		// previous completion
		executor.scheduleWithFixedDelay(task, 1000);

		// Schedule a task that will run as soon as possible and every 1000ms after the
		// previous completion
		executor.scheduleWithFixedDelay(task,
				Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant()), 1000);

		// Schedule a task with the given cron expression
		executor.schedule(task, new CronTrigger("*/5 * * * * MON-FRI"));
	}

}
