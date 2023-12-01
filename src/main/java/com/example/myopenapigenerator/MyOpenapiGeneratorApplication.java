package com.example.myopenapigenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MyOpenapiGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOpenapiGeneratorApplication.class, args);
	}

}
