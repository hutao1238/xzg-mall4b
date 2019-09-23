package com.xzg.mall.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger文档，只有在测试环境才会使用
 * @author hutao
 */
//@Profile("dev")
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	 @Bean
	 public Docket createRestApi() {
	     return new Docket(DocumentationType.SWAGGER_2)
	     .apiInfo(apiInfo())
	     .select()
	     .apis(RequestHandlerSelectors.basePackage("com.zxg"))
	     .paths(PathSelectors.any())
	     .build();
	 }

	 @Bean
	 public ApiInfo apiInfo() {
	     return new ApiInfoBuilder()
	     .title("寻珠阁管理系统接口文档")
	     .description("寻珠阁接口文档Swagger版")
	     .termsOfServiceUrl("https://www.aihtt.com/")
	     .contact(new Contact("寻珠阁","https://www.aihtt.com/", ""))
	     .version("1.0")
	     .build();
	 }
}