package dev.jihogrammer.product.v2;

import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.domain.model.ProductRegisterPayload;
import dev.jihogrammer.product.validation.ProductRegisterPayloadValidator;
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

    private final ProductPort productPort;

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
            var product = this.productPort.save(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v2/products/{productId}";
        }
    }

}
