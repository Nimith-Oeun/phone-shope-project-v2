package com.unimal.phone_shope_demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("My API Documentation")
                            .version("1.0")
                            .description("Spring Boot 3.4.2 API documentation with OpenAPI"));
        }
}
