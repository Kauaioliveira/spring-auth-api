package com.github.kauaioliveira.coderjava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI coderJavaOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("CoderJava API")
                        .version("1.0")
                        .description("API REST de demonstração — autenticação em memória. "
                                + "Não use em produção sem camadas de segurança adequadas."));
    }
}
