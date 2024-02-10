package dev.jihogrammer.spring.typeconverter.config;

import dev.jihogrammer.spring.typeconverter.converter.IPPortToStringConverter;
import dev.jihogrammer.spring.typeconverter.converter.IntegerToStringConverter;
import dev.jihogrammer.spring.typeconverter.converter.IPPortFromStringConverter;
import dev.jihogrammer.spring.typeconverter.converter.IntegerFromStringConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new IntegerFromStringConverter());
        registry.addConverter(new IPPortFromStringConverter());
        registry.addConverter(new IPPortToStringConverter());
    }
}
