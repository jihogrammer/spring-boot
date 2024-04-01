package dev.jihogrammer.item;

import dev.jihogrammer.items.port.in.*;
import dev.jihogrammer.items.port.out.Items;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SuppressWarnings("unused")
@SpringBootApplication
public class MessageApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MessageApplication.class, args);
    }

    @Bean
    public ItemReadUsage itemReadUsage(final Items items) {
        return new ItemReadInteractor(items);
    }

    @Bean
    public ItemRegisterUsage itemRegisterUsage(final Items items) {
        return new ItemRegisterInteractor(items);
    }

    @Bean
    public ItemUpdateUsage itemUpdateUsage(final Items items) {
        return new ItemUpdateInteractor(items);
    }

}
