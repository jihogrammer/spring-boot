package dev.jihogrammer.item;

import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Arrays;
import java.util.Locale;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class MessageSourceTest {
    private final Supplier<String> helloCodeSupplier = () -> "hello";
    private final Supplier<String> helloNameCodeSupplier = () -> "hello.name";
    private final Supplier<String> undefinedCodeSupplier = () -> "jihogrammer";
    private final Supplier<Object[]> nullObjectArraySupplier = () -> null;
    private final Supplier<Locale> nullLocaleSupplier = () -> null;

    @Autowired
    MessageSource messageSource;

    @Test
    void defaultHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = nullLocaleSupplier.get();
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("Hello");
    }

    @Test
    void germanyHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = Locale.GERMANY;
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("Hello");
    }

    @Test
    void usHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = Locale.US;
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("Hello");
    }

    @Test
    void ukHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = Locale.UK;
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("Hello");
    }

    @Test
    void englishHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = Locale.ENGLISH;
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("Hello");
    }

    @Test
    void koreaHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = Locale.KOREA;
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("안녕");
    }

    @Test
    void koreanHello() {
        // given
        String code = helloCodeSupplier.get();
        Object[] nullArgs = nullObjectArraySupplier.get();
        Locale nullLocale = Locale.KOREAN;
        // when
        String message = messageSource.getMessage(code, nullArgs, nullLocale);
        // then
        assertThat(message).isEqualTo("안녕");
    }

    @Test
    void undefinedCode() {
        // given
        String code = undefinedCodeSupplier.get();
        // when
        ThrowingCallable callable = () -> messageSource.getMessage(code, null, Locale.KOREA);
        // then
        assertThatThrownBy(callable).isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void undefinedCodeDefaultMessage() {
        // given
        String code = undefinedCodeSupplier.get();
        String defaultMessage = "jihogrammer";
        // when
        String message = messageSource.getMessage(code, null, defaultMessage, Locale.KOREA);
        // then
        assertThat(message).isEqualTo(defaultMessage);
    }

    @Test
    void helloNameEnglish() {
        // given
        String code = helloNameCodeSupplier.get();
        Object[] args = {"jihogrammer"};
        // when
        String message = messageSource.getMessage(code, args, Locale.ENGLISH);
        // then
        assertThat(message).isEqualTo("Hello, jihogrammer!");
    }

    @Test
    void helloNameKorean() {
        // given
        String code = helloNameCodeSupplier.get();
        Object[] args = {"jihogrammer"};
        // when
        String message = messageSource.getMessage(code, args, Locale.KOREAN);
        // then
        assertThat(message).isEqualTo("안녕, jihogrammer!");
    }
}
