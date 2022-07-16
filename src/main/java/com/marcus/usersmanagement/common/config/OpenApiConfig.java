package com.marcus.usersmanagement.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    public static final String TAG_USER = "User";

    @Bean
    public OpenAPI customOpenAPI() {
        final Info info = new Info()
                .title("Users Management API")
                .description("API para la gestión de usuarios.")
                .version("1.0.0");

        return new OpenAPI().components(new Components())
                .addTagsItem(createTag(TAG_USER, "Operaciones sobre usuarios."))
                // Other tags here...
                .info(info);
    }

    private Tag createTag(String name, String description) {
        final Tag tag = new Tag();
        tag.setName(name);
        tag.setDescription(description);
        return tag;
    }
}
