package com.johnbryce.CouponSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class CouponSystemApplication {

	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(CouponSystemApplication.class, args);
		System.out.println("@@@@@ GO @@@@");
		

	}

}
