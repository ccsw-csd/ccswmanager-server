package com.ccsw.ccswmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CCSWManagerMainApplication {

  public static void main(String[] args) {

    SpringApplication.run(CCSWManagerMainApplication.class, args);
  }

}
