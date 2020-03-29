package cn.hwj.gnw.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GnwCloudRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(GnwCloudRabbitmqApplication.class, args);
    }

}
