package ua.com.foxminded.quickpoll;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApiV1() {
        return GroupedOpenApi.builder()
                .group("v1")
                .pathsToMatch("/v1/**")
                .addOpenApiCustomiser(openapi -> {
                    openapi.setInfo(new Info()
                            .title("QuickPoll REST API")
                            .description("QuickPoll Api for creating and managing polls")
                            .version("1.0")
                            .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                            .contact(new Contact().url("http://example.com/terms-of-service").name("Leonid Kurovskiy")
                                    .email("info@example.com")));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi publicApiV2() {
        return GroupedOpenApi.builder()
                .group("v2")
                .pathsToMatch("/v2/**")
                .addOpenApiCustomiser(openapi -> {
                    openapi.setInfo(new Info()
                            .title("QuickPoll REST API")
                            .description("QuickPoll Api for creating and managing polls")
                            .version("2.0")
                            .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                            .contact(new Contact().url("http://example.com/terms-of-service").name("Leonid Kurovskiy")
                                    .email("info@example.com")));
                })
                .build();
    }

    @Bean
    public GroupedOpenApi publicApiV3() {
        return GroupedOpenApi.builder()
                .group("v3")
                .pathsToMatch("/v3/**")
                .addOpenApiCustomiser(openapi -> {
                    openapi.setInfo(new Info()
                            .title("QuickPoll REST API")
                            .description("QuickPoll Api for creating and managing polls")
                            .version("3.0")
                            .license(new License().name("MIT License").url("https://opensource.org/licenses/MIT"))
                            .contact(new Contact().url("http://example.com/terms-of-service").name("Leonid Kurovskiy")
                                    .email("info@example.com")));
                })
                .build();
    }
}
