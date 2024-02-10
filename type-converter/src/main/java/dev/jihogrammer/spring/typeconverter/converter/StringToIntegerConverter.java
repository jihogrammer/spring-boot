package dev.jihogrammer.spring.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(@NonNull final String source) {
        log.info("source = {}", source);
        return Integer.valueOf(source);
    }
}
