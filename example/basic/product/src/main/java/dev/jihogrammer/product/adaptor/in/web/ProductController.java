package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.domain.model.ProductDeliveryType;
import dev.jihogrammer.product.domain.model.ProductRegion;
import dev.jihogrammer.product.domain.model.ProductType;
import dev.jihogrammer.product.application.port.in.ProductDetailQuery;
import dev.jihogrammer.product.application.port.in.ProductQuery;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductQuery productQuery;

    private final ProductDetailQuery productDetailQuery;

    private final ProductRegisterUseCase productRegisterUseCase;

    private final ProductUpdateUseCase productUpdateUseCase;

    @ModelAttribute("regions")
    public Set<ProductRegion> regions() {
        return this.productDetailQuery.findAllProductRegions();
    }

    @ModelAttribute("types")
    public Set<ProductType> types() {
        return this.productDetailQuery.findAllProductTypes();
    }

    @ModelAttribute("deliveryTypes")
    public Set<ProductDeliveryType> deliveryTypes() {
        return this.productDetailQuery.findAllProductDeliveryTypes();
    }

    @GetMapping
    public String products(final Model model) {
        final var products = this.productQuery.findAll();
        final var productViewModels = ProductViewModel.of(products);

        model.addAttribute("products", productViewModels);

        return "/products/products";
    }

    @GetMapping("/{productId}")
    public String product(@PathVariable("productId") final Long productId, final Model model) {
        final var product = this.productQuery.findById(productId);
        final var productViewModel = ProductViewModel.of(product);

        model.addAttribute("product", productViewModel);

        return "/products/product";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("payload") final ProductRegisterPayload payload) {
        return "/products/register";
    }

    @PostMapping("/register")
    public String register(
        @ModelAttribute("payload") final ProductRegisterPayload payload,
        final RedirectAttributes redirectAttributes
    ) {
        final var command = payload.toCommand();
        final var product = this.productRegisterUseCase.register(command);

        redirectAttributes.addAttribute("productId", product.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/products/{productId}";
    }

    @GetMapping("/update/{productId}")
    public String update(
        @PathVariable("productId") final long productId,
        final Model model
    ) {
        final var product = this.productQuery.findById(productId);
        final var payload = ProductUpdatePayload.of(product);

        model.addAttribute("productId", productId);
        model.addAttribute("payload", payload);

        return "/products/update";
    }

    @PostMapping("/update")
    public String update(
        @ModelAttribute("payload") final ProductUpdatePayload payload,
        final RedirectAttributes redirectAttributes
    ) {
        final var command = payload.toCommand();
        final var product = this.productUpdateUseCase.update(command);

        redirectAttributes.addAttribute("productId", product.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/products/{productId}";
    }

}
