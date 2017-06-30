package com.battleship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class BattleshipApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BattleshipApplication.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BattleshipApplication.class);
	}
}
