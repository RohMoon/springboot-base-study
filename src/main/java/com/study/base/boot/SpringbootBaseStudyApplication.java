package com.study.base.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootBaseStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBaseStudyApplication.class, args);
    }

}
