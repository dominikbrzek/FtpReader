package com.example.ftpreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.example.ftpreader")
public class FtpReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FtpReaderApplication.class, args);
    }

}
