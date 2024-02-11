package dev.jihogrammer.spring.typeconverter.converter;

import dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import static dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort.DELIMITER_COLON;

/**
 * toString() 메서드를 오버라이드 하면 되는데, 왜 쓰는지 몰랐다.
 * 이는 view template 내부에서 호출하여 사용하므로, 별도 정의 및 등록이 필요하다.
 * TODO logging conversion for lazy format
 */
@Slf4j
public class IPPortToStringConverter implements Converter<InternetProtocolAndPort, String> {
    @Override
    public String convert(@NonNull final InternetProtocolAndPort source) {
        log.info("converting InternetProtocolAndPort to String. source = {}", source);

        return source.ip() + DELIMITER_COLON + source.port();
    }
}
