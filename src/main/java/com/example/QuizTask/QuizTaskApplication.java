package com.example.QuizTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class QuizTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizTaskApplication.class, args);
	}

}
