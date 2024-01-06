package org.supercoding.server.config.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpHeaders;

@Configuration
public class SpringDocsConfig {

    @Bean
    public  OpenAPI openAPI(){
        Info info = new Info()
                .title("Api 문서")
                .description("잘못된 부분이나 오류 발생 시 바로 말씀해주세요")
                .contact(new Contact()
                        .name("접근하지말아주세요"));

        String jwtSchemeName = "jwtAuth";

        SecurityScheme bearerAuth = new SecurityScheme()
                .name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

        SecurityRequirement addSecurityItem = new SecurityRequirement();
        addSecurityItem.addList("Authorization");


        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Authorization", bearerAuth))
                .addSecurityItem(addSecurityItem)
                .info(info);
    }
}
