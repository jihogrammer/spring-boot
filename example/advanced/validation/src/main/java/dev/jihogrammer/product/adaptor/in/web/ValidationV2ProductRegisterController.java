package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.adaptor.in.web.entity.ProductRegisterPayload;
import dev.jihogrammer.product.adaptor.in.web.validation.ProductRegisterPayloadValidator;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v2/products")
@RequiredArgsConstructor
public class ValidationV2ProductRegisterController {

    private final ProductRegisterUseCase productRegisterUseCase;

    private final ProductRegisterPayloadValidator productRegisterPayloadValidator;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(this.productRegisterPayloadValidator);
    }

    @ModelAttribute("version")
    public String version() {
        return "v2";
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
            return "/validation/register";
        } else {
            var product = this.productRegisterUseCase.register(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v2/products/{productId}";
        }
    }

}
