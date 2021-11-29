package com.czx.yimem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YIMEMApplication {

	public static void main(String[] args) {
		SpringApplication.run(YIMEMApplication.class, args);
	}

}
