package org.wonderland.dev.levi9.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@EnableAutoConfiguration
public class Levi9Zadatak2Application {

    @RequestMapping("/")
    @ResponseBody
    String home() {
    	RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject("http://localhost:9000", Page.class);
        return "GOT: " + "<br/>" + page.getName() + "<br/>" + page.getAbout() + "<br/>" + page.getPhone() + "<br/>" + page.getWebsite();
    }

    public static void main(final String[] args) {
        SpringApplication.run(Levi9Zadatak2Application.class, args);
    }
}
