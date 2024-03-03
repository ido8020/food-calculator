package com.rubypaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.controller","com.rubypaper"})
public class Jungbo0129Application {

	public static void main(String[] args) {
		SpringApplication.run(Jungbo0129Application.class, args);
	}

}
