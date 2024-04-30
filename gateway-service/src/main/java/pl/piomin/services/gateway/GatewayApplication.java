package pl.piomin.services.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	@Lazy(false)
	public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> apis(RouteDefinitionLocator locator,
																  SwaggerUiConfigParameters swaggerUiConfigParameters) {
		Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*-service")).forEach(routeDefinition -> {
			String name = routeDefinition.getId().replaceAll("-service", "");
			AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl(name, "/" + name, null);
			urls.add(swaggerUrl);
		});
		swaggerUiConfigParameters.setUrls(urls);
		return urls;
	}

}
