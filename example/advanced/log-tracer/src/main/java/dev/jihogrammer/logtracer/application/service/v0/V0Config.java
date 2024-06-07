package dev.jihogrammer.logtracer.application.service.v0;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "dev.jihogrammer.logtracer.adaptor.persistence.v0",
        "dev.jihogrammer.logtracer.adaptor.web.v0"
})
public class V0Config {

}
