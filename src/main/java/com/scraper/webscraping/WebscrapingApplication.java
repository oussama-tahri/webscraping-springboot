package com.scraper.webscraping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.scraper.webscraping.repository")
@EnableScheduling
public class WebscrapingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebscrapingApplication.class, args);
    }

}
