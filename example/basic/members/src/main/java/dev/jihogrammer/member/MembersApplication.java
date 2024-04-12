package dev.jihogrammer.member;

import dev.jihogrammer.web.core.WebCoreApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MembersApplication extends WebCoreApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MembersApplication.class, args);
    }

}
