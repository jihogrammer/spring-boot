package dev.jihogrammer.item.validation.messages;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ErrorMessageSourceTest {
    @Autowired
    MessageSource messageSource;

    @Test
    void itemNameRequired() {
        // given
        String code = "item.name.required";
        // when
        String message = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        // then
        assertThat(message).isEqualTo("상품 이름은 필수입니다.");
    }
}
