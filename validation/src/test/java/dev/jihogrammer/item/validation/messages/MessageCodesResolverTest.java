package dev.jihogrammer.item.validation.messages;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

class MessageCodesResolverTest {
    MessageCodesResolver resolver = new DefaultMessageCodesResolver();

    @Test
    void object() {
        // given
        String errorCode = "required";
        String objectName = "item";
        // when
        String[] codes = resolver.resolveMessageCodes(errorCode, objectName);
        // then
        assertThat(codes).containsExactly("required.item", "required");
    }

    @Test
    void field() {
        // given
        String errorCode = "required";
        String objectName = "item";
        String field = "name";
        Class<String> fieldType = String.class;
        // when
        String[] codes = resolver.resolveMessageCodes(errorCode, objectName, field, fieldType);
        // then
        assertThat(codes).containsExactly("required.item.name", "required.name", "required.java.lang.String", "required");
    }
}
