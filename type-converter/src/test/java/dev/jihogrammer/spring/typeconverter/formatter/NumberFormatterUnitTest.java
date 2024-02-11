package dev.jihogrammer.spring.typeconverter.formatter;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class NumberFormatterUnitTest {
    static NumberFormatter formatter = new NumberFormatter();
    @Test
    void parse() throws ParseException {
        // given
        String text = "1,000";
        Locale locale = Locale.KOREA;
        // when
        long actual = (long) formatter.parse(text, locale);
        // then
        assertThat(actual).isEqualTo(1_000);
    }

    @Test
    void print() {
        // given
        int object = 1_000;
        Locale locale = Locale.KOREA;
        // when
        String actual = formatter.print(object, locale);
        // then
        assertThat(actual).isEqualTo("1,000");
    }
}
