package com.mew.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import com.MEW.demo.controller.GameController;
//import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class GamerMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamerMatchApplication.class, args);
	}

	// this section can be used to verify that Spring is correctly scanning components
	// @Bean
	// CommandLineRunner testScan(GameController gameController) {
   	// 	return args -> System.out.println("GameController scanned: " + gameController);
	// }
}