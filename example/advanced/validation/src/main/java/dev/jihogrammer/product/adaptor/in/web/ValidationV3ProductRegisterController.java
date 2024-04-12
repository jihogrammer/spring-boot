package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.adaptor.in.web.entity.ProductRegisterPayload;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v3/products")
@RequiredArgsConstructor
public class ValidationV3ProductRegisterController {

    private final ProductRegisterUseCase productRegisterUseCase;

    @ModelAttribute("version")
    public String version() {
        return "v3";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("payload") final ProductRegisterPayload payload) {
        return "/validation/register";
    }

    @PostMapping("/register")
    public String register(
        @Validated @ModelAttribute("payload") final ProductRegisterPayload payload,
        final BindingResult bindingResult,
        final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            String defaultMessage = fieldError.getDefaultMessage();
            System.out.println("defaultMessage = " + defaultMessage);

            return "/validation/register";
        } else {
            var product = this.productRegisterUseCase.register(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v3/products/{productId}";
        }
    }

}
