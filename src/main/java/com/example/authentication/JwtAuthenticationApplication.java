package com.example.authentication;

import com.example.authentication.entity.User;
import com.example.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtAuthenticationApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	// Adding below two user in H2 Database
	@Bean
	CommandLineRunner run(UserService userService ){
		return args ->
		{
			String password1 = passwordEncoder.encode("password1");
			String password2 = passwordEncoder.encode("password2");

			userService.saveUser(new User(1,"test1", password1));
			userService.saveUser(new User(2,"test2", password2));

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthenticationApplication.class, args);
	}

}
