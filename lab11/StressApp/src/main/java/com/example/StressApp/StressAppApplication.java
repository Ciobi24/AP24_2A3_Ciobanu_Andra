package com.example.StressApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StressAppApplication implements CommandLineRunner {

	@Autowired
	private StressTestTask stressTestTask;

	public static void main(String[] args) {
		SpringApplication.run(StressAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		stressTestTask.runStressTest();
	}
}
