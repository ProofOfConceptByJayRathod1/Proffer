package com.proffer.endpoints.schedulers;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomTimerTask extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(CustomTimerTask.class);

	private Timer timer;

	@Override
	public void run() {
		log.warn("Im executed on some date");
	}

}
