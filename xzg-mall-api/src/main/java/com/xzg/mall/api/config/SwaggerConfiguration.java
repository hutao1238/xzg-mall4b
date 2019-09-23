package com.xzg.mall.api.config;

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
	 public Docket baseRestApi() {
	     return new Docket(DocumentationType.SWAGGER_2)
	     .apiInfo(apiInfo())
		 .groupName("基础版")
	     .select()
	     .apis(RequestHandlerSelectors.basePackage("com.xzg.mall.api"))
	     .paths(PathSelectors.any())
	     .build();
	 }

	 @Bean
	 public ApiInfo apiInfo() {
	     return new ApiInfoBuilder()
	     .title("寻珠阁商城接口文档")
	     .description("寻珠阁商城接口文档Swagger版")
	     .termsOfServiceUrl("http://www.aihtt.com/")
	     .contact(new Contact("寻珠阁","https://www.aihtt.com/", ""))
	     .version("1.0")
	     .build();
	 }
}