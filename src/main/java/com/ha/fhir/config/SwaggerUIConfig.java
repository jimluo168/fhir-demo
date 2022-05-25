package com.ha.fhir.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableOpenApi
//@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerUIConfig {
    @Bean
    public Docket createDocket() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(new ApiInfoBuilder().title("FHIR example")
                        .description("FHIR example")
                        .version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ha.fhir"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}