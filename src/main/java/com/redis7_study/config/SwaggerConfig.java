package com.redis7_study.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.swagger2.enabled}")
    private Boolean enabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.redis7_study")) //你自己的package
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("springboot利用swagger2构建api接口文档 " + "\t"
//                        + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()))
//                .description("springboot+redis整合")
//                .version("1.0")
//                .termsOfServiceUrl("")
//                .build();
        Contact contact = new Contact("name", "url", "email");
        return new ApiInfo(
                "swagger API 文档",
                "SpringBoot + Redis",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}