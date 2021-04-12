package com.estudo.space;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpaceApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpaceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpaceApplication.class);
	}
}
