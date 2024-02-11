package dev.jihogrammer.spring.typeconverter.config;

import dev.jihogrammer.spring.typeconverter.converter.IPPortFromStringConverter;
import dev.jihogrammer.spring.typeconverter.converter.IPPortToStringConverter;
import dev.jihogrammer.spring.typeconverter.formatter.NumberFormatter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new IPPortFromStringConverter());
        registry.addConverter(new IPPortToStringConverter());
        // 기본적으로 우선순위는 [converter > formatter] 순으로 적용되므로 기존 [Integer, String] Converter 제외함
        registry.addFormatter(new NumberFormatter());
    }
}
