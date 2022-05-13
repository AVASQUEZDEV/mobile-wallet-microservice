package com.nttdata.mobilewallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MobileWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileWalletApplication.class, args);
	}

}
