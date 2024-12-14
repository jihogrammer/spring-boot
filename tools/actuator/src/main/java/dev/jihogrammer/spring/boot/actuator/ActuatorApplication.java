package dev.jihogrammer.spring.boot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ActuatorApplication {

    public static void main(final String[] args) {
        SpringApplication.run(ActuatorApplication.class, args);
    }

    @Bean
    @Profile("dev")
    InMemoryHttpExchangeRepository httpExchangeRepository() {
        return new InMemoryHttpExchangeRepository();
    }

}
