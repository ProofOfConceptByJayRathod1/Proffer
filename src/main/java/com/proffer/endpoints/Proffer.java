package com.proffer.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.proffer.endpoints.controller.WelcomeController;
import com.proffer.endpoints.entity.User;
import com.proffer.endpoints.repository.UserRepository;

import javax.annotation.PostConstruct;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@ComponentScan("com.proffer.endpoints")
public class Proffer {
    @Autowired
    private UserRepository repository;


    public static void main(String[] args) {
    	new File(WelcomeController.uploadDirectory).mkdir();
    	new File(WelcomeController.uploadDirectoryForCatalog).mkdir();
        SpringApplication.run(Proffer.class, args);
    }

}
