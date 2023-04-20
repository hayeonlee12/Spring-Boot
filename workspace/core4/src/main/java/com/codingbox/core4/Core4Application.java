package com.codingbox.core4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class Core4Application {

	public static void main(String[] args) {
		SpringApplication.run(Core4Application.class, args);
	}

}
