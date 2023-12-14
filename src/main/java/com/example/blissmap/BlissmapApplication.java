package com.example.blissmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class BlissmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlissmapApplication.class, args);
	}

}
