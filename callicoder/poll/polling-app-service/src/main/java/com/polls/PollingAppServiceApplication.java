package com.polls;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@Log4j2
@EntityScan(basePackageClasses = {
		PollingAppServiceApplication.class,
		Jsr310JpaConverters.class
})
public class PollingAppServiceApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(PollingAppServiceApplication.class, args);
	}

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Override
	public void run(ApplicationArguments args) {
		log.info("Info log");
	}
}
