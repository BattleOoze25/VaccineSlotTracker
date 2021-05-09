package com.example.vaccineslottracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class VaccineslottrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineslottrackerApplication.class, args);
	}

}
