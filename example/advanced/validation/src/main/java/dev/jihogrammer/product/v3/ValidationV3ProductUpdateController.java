package dev.jihogrammer.product.v3;

import dev.jihogrammer.product.model.ProductUpdatePayload;
import dev.jihogrammer.product.model.ProductId;
import dev.jihogrammer.product.port.out.Products;
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

    private final Products products;

    @ModelAttribute("version")
    public String version() {
        return "v3";
    }

    @GetMapping("/update/{productId}")
    public String update(@PathVariable("productId") final Long productId, final Model model) {
        var product = this.products.findById(new ProductId(productId)).orElseThrow();
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
            var product = this.products.save(payload.toCommand());

            redirectAttributes.addAttribute("product", product.id().value());

            return "redirect:/validation/v3/products/{product}";
        }
    }

}
