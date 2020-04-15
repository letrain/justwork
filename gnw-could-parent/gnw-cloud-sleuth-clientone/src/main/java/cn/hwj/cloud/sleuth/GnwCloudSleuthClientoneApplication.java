package cn.hwj.cloud.sleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GnwCloudSleuthClientoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(GnwCloudSleuthClientoneApplication.class, args);
	}

}
