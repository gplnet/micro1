package com.ntt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class SpringMicro1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicro1Application.class, args);
	}

}
