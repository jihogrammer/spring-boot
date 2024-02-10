package dev.jihogrammer.spring.typeconverter.converter;

import dev.jihogrammer.spring.typeconverter.model.InternetProtocolAndPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InternetProtocolAndPortConverterTest {
    @Test
    void stringToIPPort() {
        // given
        StringToIPAndPortConverter converter = new StringToIPAndPortConverter();
        String source = "127.0.0.1:8080";
        // when
        InternetProtocolAndPort actual = converter.convert(source);
        // then
           assertThat(actual).isEqualTo(new InternetProtocolAndPort("127.0.0.1", 8080));
        assertThat(actual.toString()).isEqualTo(source);
    }
}
