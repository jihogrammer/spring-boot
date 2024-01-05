package dev.jihogrammer.item.validation;

import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemRegisterHttpRequestValidator implements Validator {
    @Override
    public boolean supports(@NonNull final Class<?> clazz) {
        return ItemRegisterHttpRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull final Object target, @NonNull final Errors errors) {
        ItemRegisterHttpRequest request = (ItemRegisterHttpRequest) target;
        String name = request.getName();
        Integer price = request.getPrice();
        Integer quantity = request.getQuantity();

        // single field validation
        if (name == null || name.isEmpty()) {
            errors.rejectValue("name", "required");
        }
        if (price == null || 1_000 > price || price > 1_000_000) {
            errors.rejectValue("price", "range", new Object[] {1_000, 1_000_000}, null);
        }
        if (quantity == null || 1 > quantity || quantity > 10_000) {
            errors.rejectValue("quantity", "range", new Object[] {1, 9_999}, null);
        }

        // complex fields validation
        if (price != null && quantity != null && (price * quantity < 10_000)) {
            errors.reject("total-max-price", new Object[] {10_000, price * quantity}, null);
        }
    }
}
