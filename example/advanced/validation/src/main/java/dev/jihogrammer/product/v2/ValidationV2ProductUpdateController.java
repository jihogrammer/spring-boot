package dev.jihogrammer.product.v2;

import dev.jihogrammer.product.model.ProductId;
import dev.jihogrammer.product.model.ProductUpdatePayload;
import dev.jihogrammer.product.model.ProductViewModel;
import dev.jihogrammer.product.port.out.Products;
import dev.jihogrammer.product.validation.ProductUpdatePayloadValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v2/products")
@RequiredArgsConstructor
public class ValidationV2ProductUpdateController {

    private final Products products;

    private final ProductUpdatePayloadValidator productUpdatePayloadValidator;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(productUpdatePayloadValidator);
    }

    @ModelAttribute("version")
    public String version() {
        return "v2";
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
        final BindingResult bindingResult, // must be placed immediately after @ModelAttribute
        final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/validation/update";
        } else {
            var product = this.products.save(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v2/products/{productId}";
        }
    }

}
