package com.example.seraphine;


import com.example.seraphine.model.User;
import com.example.seraphine.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@AllArgsConstructor
public class SeraphineApplication {
	@Autowired
	private AdminService adminService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SeraphineApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void createAdmin(){
		User admin = new User();

		admin.setUserRole("ADMIN");
		admin.setEnabled(true);
		admin.setUsername("admin");
		admin.setPassword(bCryptPasswordEncoder.encode("admin123"));

		this.adminService.createAdmin(admin);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/v*/**").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
