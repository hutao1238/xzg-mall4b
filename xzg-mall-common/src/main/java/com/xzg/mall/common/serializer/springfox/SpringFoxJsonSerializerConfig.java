package com.xzg.mall.common.serializer.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.json.JsonSerializer;

import java.util.List;

/**
 * @author LGH
 */
@Configuration
public class SpringFoxJsonSerializerConfig {

    @Bean
    @Primary
    public JsonSerializer xzgSpringfoxJsonSerializer(List<JacksonModuleRegistrar> moduleRegistrars) {
        return new SpringfoxJsonSerializer(moduleRegistrars);
    }
}
