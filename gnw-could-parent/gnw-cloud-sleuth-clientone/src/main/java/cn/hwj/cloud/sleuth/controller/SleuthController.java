package cn.hwj.cloud.sleuth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class SleuthController {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @PostMapping("/sleuth")
    public String trace() {
        log.info("=====cal, trace=1");
        return restTemplate().getForEntity("http://127.0.0.1:8950/sleuth2", String.class)
                .getBody();
    }
}

