package com.details.config;

import io.swagger.annotations.Api;
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

/***
 * @author zlp
 * @date 13:43 2020/1/13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String URL = "https://github.com/Jugol7/details";

    @Bean
    public Docket testVue() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Vue 测试")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.details.controller.vue"))
                .build();
    }

    @Bean
    public Docket checkAnnotation() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("注解")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.details.controller.annotation"))
                .build();
    }

    @Bean
    public Docket getDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo());
        docket.select()
                // 动态扫描 扫描controller层上面打有@Api注解的
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("details")
                .description(URL)
                .version("1.0")
                .termsOfServiceUrl(URL)
                .contact(new Contact("木月先生", URL, "971894709@qq.com"))
                .build();

    }
}
