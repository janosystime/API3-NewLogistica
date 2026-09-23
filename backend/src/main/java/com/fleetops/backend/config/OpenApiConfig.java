package com.fleetops.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do SpringDoc OpenAPI (Swagger UI).
 * Documentação automática da API – SCRUM-48.
 * Acesse em: http://localhost:8080/swagger-ui.html
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fleetOpsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FleetOps – API de Avaliações de Frete")
                        .description(
                                """
                                API para registro de avaliações de motoristas (nota 1-5 + feedback).
                                Estruturada para futura migração de monorepo para microsserviço de avaliações/ranking.
                                """)
                        .version("1.0.0")
                        .contact(new Contact().name("FleetOps Team").email("contato@fleetops.local")));
    }
}
