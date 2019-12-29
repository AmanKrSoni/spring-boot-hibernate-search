package com.aman.springbootfts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.aman.springbootfts.repository")
@ComponentScan("com.aman.springbootfts")
@EntityScan(basePackages = "com.aman.springbootfts.entity")
public class SpringBootFtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFtsApplication.class, args);
	}

}
