package dev.jihogrammer.spring.boot.autoconfig.selector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class HelloConfig {

    @Bean
    HelloBean helloBean() {
        return new HelloBean();
    }

}
