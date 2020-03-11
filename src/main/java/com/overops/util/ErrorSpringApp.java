package com.overops.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
// public class ErrorSpringApp {
public class ErrorSpringApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ErrorSpringApp.class, args);

/* the following is for the embedded tomcat server
		SpringApplication app = new SpringApplication(ErrorSpringApp.class);
        	app.run(args);
*/

	}
}
