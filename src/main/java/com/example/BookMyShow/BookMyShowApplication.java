package com.example.BookMyShow;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class BookMyShowApplication {




	public static void main(String[] args) {
		SpringApplication.run(BookMyShowApplication.class, args);
	}

}
