package com.mayankmadhav.demo.app.mobileappws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MobileAppWsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobileAppWsApplication.class, args);
    }
}
