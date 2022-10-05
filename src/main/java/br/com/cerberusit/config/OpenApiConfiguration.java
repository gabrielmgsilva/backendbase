package br.com.cerberusit.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI baseApplicationOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Basic application")
                        .description("Basic application with oauth authentication")
                        .version("v0.0.1")
                        .license(new License().name("Cerberus it").url("http://www.cerberusit.com.br"))
                );
    }
}
