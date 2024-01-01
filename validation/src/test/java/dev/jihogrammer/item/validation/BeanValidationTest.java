package dev.jihogrammer.item.validation;

import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BeanValidationTest {
    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void validItemRegisterHttpRequest() {
        // given
        ItemRegisterHttpRequest request = new ItemRegisterHttpRequest();
        request.setName(" ");
        request.setPrice(0);
        request.setQuantity(10000);
        // when
        Set<ConstraintViolation<ItemRegisterHttpRequest>> violations = validator.validate(request);
        // then
        assertThat(violations).hasSize(3);
    }
}
