package com.trip.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class Application {

    @Autowired
    private Configuration config;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @Bean
    public CommandLineRunner runner() {
        return (a) -> {
            System.out.println("==== Trip ReView Web Application ==== ");
            System.out.println(String.format("point audit thread service : [%b]", config.getThreadServiceFlag("pointAudit")));
        };
    }
}
