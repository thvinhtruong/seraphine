package com.example.seraphine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.example.seraphine.model.User;


@SpringBootApplication
@RestController
public class SeraphineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeraphineApplication.class, args);
		// 		<dependency>
		//			<groupId>org.springframework.boot</groupId>
		//			<artifactId>spring-boot-starter-security</artifactId>
		//		</dependency>
	}
	@GetMapping("api/v1/user")
	public List<User>  getUserList() {
		return List.of(
				new User(
						"Trung",
						"Nguyen",
						"trung@gmail.com",
						"trung123",
						"abcacsjnajcna",
						"berliner 19",
						"12/12/2001",
						"private",
						"mawista"

				)
		);
	}

}
