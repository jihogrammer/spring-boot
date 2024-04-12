package dev.jihogrammer.spring.typeconverter.converter;

import dev.jihogrammer.spring.typeconverter.adaptor.in.web.converter.IntegerFromStringConverter;
import dev.jihogrammer.spring.typeconverter.adaptor.in.web.converter.IntegerToStringConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConverterUnitTest {
    @Test
    void stringToInteger() {
        // given
        IntegerFromStringConverter converter = new IntegerFromStringConverter();
        String source = "10";
        // when
        Integer actual = converter.convert(source);
        // then
        assertThat(actual).isEqualTo(10);
    }

    @Test
    void integerToString() {
        // given
        IntegerToStringConverter converter = new IntegerToStringConverter();
        Integer source = 10;
        // when
        String actual = converter.convert(source);
        // then
        assertThat(actual).isEqualTo("10");
    }
}
