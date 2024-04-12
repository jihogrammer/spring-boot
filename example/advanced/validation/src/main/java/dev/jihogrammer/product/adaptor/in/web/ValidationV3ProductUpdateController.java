package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.adaptor.in.web.entity.ProductUpdatePayload;
import dev.jihogrammer.product.application.port.in.ProductQuery;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v3/products")
@RequiredArgsConstructor
public class ValidationV3ProductUpdateController {

    private final ProductQuery productQuery;

    private final ProductUpdateUseCase productUpdateUseCase;

    @ModelAttribute("version")
    public String version() {
        return "v3";
    }

    @GetMapping("/update/{productId}")
    public String update(@PathVariable("productId") final Long productId, final Model model) {
        var product = this.productQuery.findById(productId);
        var payload = ProductUpdatePayload.of(product);

        model.addAttribute("payload", payload);

        return "/validation/update";
    }

    @PostMapping("/update")
    public String update(
            @Validated @ModelAttribute("payload") final ProductUpdatePayload payload,
            final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/validation/update";
        } else {
            var product = this.productUpdateUseCase.update(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v3/products/{productId}";
        }
    }

}
