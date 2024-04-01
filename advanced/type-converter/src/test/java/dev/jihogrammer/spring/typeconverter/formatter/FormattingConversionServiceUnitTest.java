package dev.jihogrammer.spring.typeconverter.formatter;

import dev.jihogrammer.spring.typeconverter.converter.IPPortFromStringConverter;
import dev.jihogrammer.spring.typeconverter.converter.IPPortToStringConverter;
import dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class FormattingConversionServiceUnitTest {
    static FormattingConversionService service;

    @BeforeAll
    static void setUpClass() {
        service = new DefaultFormattingConversionService();
        // registry converters
        service.addConverter(new IPPortToStringConverter());
        service.addConverter(new IPPortFromStringConverter());
        // registry formatters
        service.addFormatter(new NumberFormatter());
    }

    @Test
    void converter() {
        // given
        var source = "127.0.0.1:8080";
        // when
        var actual = service.convert(source, InternetProtocolAndPort.class);
        // then
        assertThat(actual).isEqualTo(new InternetProtocolAndPort("127.0.0.1", 8080));
    }

    @Test
    void formatterStringToNumber() {
        // given
        var text = "1,000,000";
        // when
        var actual = service.convert(text, Number.class);
        // then
        assertThat(actual).isEqualTo(1_000_000L);
    }

    @Test
    void formatterNumberToString() {
        // given
        var text = 1_000_000;
        // when
        var actual = service.convert(text, String.class);
        // then
        assertThat(actual).isEqualTo("1,000,000");
    }
}
