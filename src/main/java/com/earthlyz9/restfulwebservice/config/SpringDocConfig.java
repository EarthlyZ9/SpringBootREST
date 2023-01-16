package com.earthlyz9.restfulwebservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI apiInfo() {
    Contact contact = new Contact();
    contact.name("earthlyz9").email("earthlyz9.dev@gmail.com").url("http://www.sampleapi.com");
    Info info = new Info().title("Spring boot 3, REST API")
        .version("1.0.0")
        .description("springfox 는 아직 Spring boot 3 을 지원하지 않음")
        .contact(contact);
    return new OpenAPI().info(info);
  }

  @Bean
  public GroupedOpenApi httpApi() {
    return GroupedOpenApi.builder()
        .group("http")
        .pathsToMatch("/**")
        .build();
  }
}
