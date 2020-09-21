package com.rest.assignment.daofab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DaofabCtBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DaofabCtBeApplication.class, args);
	}

}
