package com.ShoppingSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ShoppingSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSiteApplication.class, args);
	}

}
