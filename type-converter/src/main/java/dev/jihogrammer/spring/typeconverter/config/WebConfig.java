package dev.jihogrammer.spring.typeconverter.config;

import dev.jihogrammer.spring.typeconverter.converter.IntegerToStringConverter;
import dev.jihogrammer.spring.typeconverter.converter.StringToIPAndPortConverter;
import dev.jihogrammer.spring.typeconverter.converter.StringToIntegerConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIntegerConverter());
        registry.addConverter(new StringToIPAndPortConverter());
    }
}
