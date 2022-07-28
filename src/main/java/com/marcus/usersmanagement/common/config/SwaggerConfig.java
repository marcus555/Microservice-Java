package com.marcus.usersmanagement.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String TAG_USER = "User";

    public static final String TAG_TOKEN = "Token";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.marcus.usersmanagement.controller"))
                .build()
                .tags(new Tag(TAG_USER, "Operaciones sobre usuarios."))
                .tags(new Tag(TAG_TOKEN, "Token de autenticación."))
                // Other tags here...
                .apiInfo(apiInfo())
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Users Management API")
                .description("API para la gestión de usuarios.")
                .version("1.0.0").build();
    }
}
