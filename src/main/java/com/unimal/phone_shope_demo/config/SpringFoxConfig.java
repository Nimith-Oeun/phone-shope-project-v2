package com.unimal.phone_shope_demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//this clase is used to configure the swagger documentation

@Configuration
public class SpringFoxConfig {
    String schemeName = "bearerAuth";
    String bearerFormat = "JWT";
    String scheme = "Bearer";
private String port;
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .addSecurityItem(new SecurityRequirement().addList(schemeName))
                    .components(new Components()
                            .addSecuritySchemes(
                                    schemeName, new SecurityScheme()
                                            .name(schemeName)
                                            .type(SecurityScheme.Type.HTTP)
                                            .bearerFormat(bearerFormat)
                                            .in(SecurityScheme.In.HEADER)
                                            .scheme(scheme)
                            )
                    )
                    .info(new Info()
                            .title("Phone Shop API")
                            .version("1.0")
                            .description("Spring Boot 3.4.2 API documentation with OpenAPI"));

        }
}
