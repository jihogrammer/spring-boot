package dev.jihogrammer.spring.typeconverter.converter;

import dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

public class ConversionUnitTest {
    static DefaultConversionService conversionService;

    @BeforeAll
    static void setUpClass() {
        conversionService = new DefaultConversionService();
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new IntegerFromStringConverter());
        conversionService.addConverter(new IPPortFromStringConverter());
    }

    @Test
    void integerToString() {
        // given
        Integer source = 10;
        // when
        String actual = conversionService.convert(source, String.class);
        // then
        assertThat(actual).isEqualTo("10");
    }

    @Test
    void stringToInteger() {
        // given
        String source = "10";
        // when
        Integer actual = conversionService.convert(source, Integer.class);
        // then
        assertThat(actual).isEqualTo(10);
    }

    @Test
    void stringToIPPort() {
        // given
        String source = "127.0.0.1:8080";
        // when
        InternetProtocolAndPort ipAndPort = conversionService.convert(source, InternetProtocolAndPort.class);
        // then
        assertThat(ipAndPort).isEqualTo(new InternetProtocolAndPort("127.0.0.1", 8080));
    }
}
