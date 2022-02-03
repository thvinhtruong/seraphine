package com.example.seraphine;



import com.example.seraphine.model.Appointment;
import com.example.seraphine.model.Doctor;
import com.example.seraphine.model.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.LocalTime;


@SpringBootApplication
public class SeraphineApplication {
	public static void main(String[] args) {
		SpringApplication.run(SeraphineApplication.class, args);
		// 		<dependency>
		//			<groupId>org.springframework.boot</groupId>
		//			<artifactId>spring-boot-starter-security</artifactId>
		//		</dependency>
	}
}
