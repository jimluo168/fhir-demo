package com.ha.fhir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@ServletComponentScan(basePackageClasses = {FhirRestfulServer.class})
@SpringBootApplication(exclude = {ElasticsearchRestClientAutoConfiguration.class})
public class FhirApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(FhirApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FhirApplication.class);
    }

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Bean
    public ServletRegistrationBean hapiServletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        FhirRestfulServer fhirRestfulServer = new FhirRestfulServer();
        beanFactory.autowireBean(fhirRestfulServer);
        servletRegistrationBean.setServlet(fhirRestfulServer);
        servletRegistrationBean.addUrlMappings("/fhir/*");
        servletRegistrationBean.setLoadOnStartup(1);

        return servletRegistrationBean;
    }


}
