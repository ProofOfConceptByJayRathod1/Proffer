package com.proxibid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.proxibid.controller.WelcomeController;

import java.io.File;

@SpringBootApplication
@EnableScheduling
@EnableCaching
@SpringBootConfiguration
public class Proffer {

	public static void main(String[] args) {
		new File(WelcomeController.uploadDirectory).mkdir();
		new File(WelcomeController.uploadDirectoryForCatalog).mkdir();
		SpringApplication.run(Proffer.class, args);
	}

}
