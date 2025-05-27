package com.IS336.PROJECT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.IS336.PROJECT.config.JwtProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtProperties.class})
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
