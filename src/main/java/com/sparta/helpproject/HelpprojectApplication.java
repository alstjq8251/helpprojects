package com.sparta.helpproject;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@RequiredArgsConstructor
@EnableJpaAuditing
@SpringBootApplication
public class HelpprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpprojectApplication.class, args);
    }

}
