package com.proffer.endpoints;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.proffer.endpoints.controller.WelcomeController;
import com.proffer.endpoints.schedulers.TaskSchedulerUtil;

import java.io.File;

@SpringBootApplication
@ComponentScan("com.proffer.endpoints")
@EnableScheduling
public class Proffer {


	public static void main(String[] args) {
		new File(WelcomeController.uploadDirectory).mkdir();
		new File(WelcomeController.uploadDirectoryForCatalog).mkdir();
		SpringApplication.run(Proffer.class, args);

		TaskSchedulerUtil schedulerUtil = new TaskSchedulerUtil();
		// schedulerUtil.executeTaskWithDelay(1000);
		//schedulerUtil.executeTaskOn(new Proffer().auctionRepository.findByeventNo(6).getDate());
	}

}
