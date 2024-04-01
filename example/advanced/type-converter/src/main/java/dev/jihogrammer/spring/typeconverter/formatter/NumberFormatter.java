package dev.jihogrammer.spring.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.lang.NonNull;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

@Slf4j
public class NumberFormatter implements Formatter<Number> {
    @Override
    @NonNull
    public Number parse(@NonNull final String text, @NonNull final Locale locale) throws ParseException {
        log.info("parsing text. '{}'({})", text, locale);
        return NumberFormat.getInstance(locale).parse(text);
    }

    @Override
    @NonNull
    public String print(@NonNull final Number object, @NonNull final Locale locale) {
        log.info("printing number. '{}'({})", object, locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
