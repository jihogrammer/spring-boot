package dev.jihogrammer.spring.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

@Slf4j
public class IntegerToStringConverter implements Converter<Integer, String> {
    @Override
    public String convert(@NonNull final Integer source) {
        log.info("converting Integer to String. source = {}", source);

        return String.valueOf(source);
    }
}
