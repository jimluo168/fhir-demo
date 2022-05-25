package com.ha.fhir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class FhirDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FhirDemoApplication.class, args);
    }

}
