package com.ioms.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class IomsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IomsApiApplication.class, args);
	}

}
