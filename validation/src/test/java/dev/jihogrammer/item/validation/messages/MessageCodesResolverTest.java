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
        var errorCode = "required";
        var objectName = "item";

        // when
        var codes = this.resolver.resolveMessageCodes(errorCode, objectName);

        // then
        assertThat(codes).containsExactly("required.item", "required");
    }

    @Test
    void field() {
        // given
        var errorCode = "required";
        var objectName = "item";
        var field = "name";
        var fieldType = String.class;

        // when
        var codes = this.resolver.resolveMessageCodes(errorCode, objectName, field, fieldType);

        // then
        assertThat(codes).containsExactly("required.item.name", "required.name", "required.java.lang.String", "required");
    }

}
