package com.MEW.demo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.MEW.demo.controller.GameController;

@SpringBootApplication
public class GamerMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamerMatchApplication.class, args);
	}

	@Bean
	CommandLineRunner testScan(GameController gameController) {
   		return args -> System.out.println("GameController scanned: " + gameController);
	}
}