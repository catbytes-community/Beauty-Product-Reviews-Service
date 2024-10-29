package com.catbytes.reviews.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Beauty Product Reviews Service")
                        .description("This is the API for Beauty Product Reviews Service.<br>" +
                                "[User specification]" +
                                "(https://github.com/catbytes-community/Beauty-Product-Reviews-Service/wiki/User-specification)<br>" +
                                "[Technical specification]" +
                                "(https://github.com/catbytes-community/Beauty-Product-Reviews-Service/wiki/Technical-specification)<br>")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Support Team")
                                .email("tanya-study@ukr.net"))
                );
    }

}
