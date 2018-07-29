package pl.piomin.services.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableSwagger2
public class GatewayApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
	
}
