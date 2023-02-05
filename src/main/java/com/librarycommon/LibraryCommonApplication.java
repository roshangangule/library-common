package com.librarycommon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@Slf4j
public class LibraryCommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryCommonApplication.class, args);
	}

}
