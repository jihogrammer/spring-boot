package dev.jihogrammer.product.validation;

import dev.jihogrammer.product.adaptor.in.web.entity.ProductRegisterPayload;
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
        ProductRegisterPayload request = new ProductRegisterPayload();
        request.setName(" ");
        request.setPrice(0);
        request.setQuantity(10000);
        // when
        Set<ConstraintViolation<ProductRegisterPayload>> violations = validator.validate(request);
        // then
        assertThat(violations).hasSize(3);
    }
}
