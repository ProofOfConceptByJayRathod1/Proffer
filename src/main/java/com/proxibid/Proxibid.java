package com.proxibid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.proxibid.controller.WelcomeController;
import java.io.File;

@SpringBootConfiguration
@SpringBootApplication
@EnableScheduling
@EnableCaching
//@EnableEurekaClient
public class Proxibid {

	public static void main(String[] args) {
		new File(WelcomeController.uploadDirectory).mkdir();
		new File(WelcomeController.uploadDirectoryForCatalog).mkdir();
		SpringApplication.run(Proxibid.class, args);
	}

}
