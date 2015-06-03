package org.wonderland.dev.levi9.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Levi9Zadatak2Application {

    public static void main(final String[] args) {
        SpringApplication.run(Levi9Zadatak2Application.class, args);
    }
}
