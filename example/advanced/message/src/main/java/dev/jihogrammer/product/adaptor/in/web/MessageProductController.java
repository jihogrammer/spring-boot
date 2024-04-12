package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.adaptor.in.web.entity.ProductRegisterPayload;
import dev.jihogrammer.product.adaptor.in.web.entity.ProductUpdatePayload;
import dev.jihogrammer.product.adaptor.in.web.mapper.ProductEntityMapper;
import dev.jihogrammer.product.application.port.in.ProductQuery;
import dev.jihogrammer.product.application.port.in.ProductRegisterUseCase;
import dev.jihogrammer.product.application.port.in.ProductUpdateUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j
public class MessageProductController {

    private final ProductQuery productQuery;

    private final ProductRegisterUseCase productRegisterUseCase;

    private final ProductUpdateUseCase productUpdateUseCase;

    @GetMapping("/products")
    public String products(final Model model) {
        final var products = this.productQuery.findAll();
        final var productViewModels = ProductEntityMapper.map(products);

        model.addAttribute("products", productViewModels);

        return "/message/products";
    }

    @GetMapping("/products/{productId}")
    public String product(
            @PathVariable("productId") final Long productId,
            final Model model
    ) {
        final var product = this.productQuery.findById(productId);
        final var productViewModel = ProductEntityMapper.map(product);

        model.addAttribute("product", productViewModel);

        return "/message/product";
    }

    @GetMapping("/products/register")
    public String register(final Model model) {
        final var payload = new ProductRegisterPayload();

        model.addAttribute("product", payload);

        return "/message/register";
    }

    @PostMapping("/products/register")
    public String register(
            final ProductRegisterPayload payload,
            final RedirectAttributes redirectAttributes
    ) {
        final var command = ProductEntityMapper.map(payload);
        final var product = this.productRegisterUseCase.register(command);

        redirectAttributes.addAttribute("productId", product.id().value());

        return "redirect:/message/products/{productId}";
    }

    @GetMapping("/products/update/{productId}")
    public String update(
            @PathVariable("productId") final Long productId,
            final Model model
    ) {
        final var product = this.productQuery.findById(productId);
        final var productViewModel = ProductEntityMapper.map(product);

        model.addAttribute("product", productViewModel);

        return "/message/update";
    }

    @PostMapping("/products/update")
    public String update(final ProductUpdatePayload payload, final RedirectAttributes redirectAttributes) {
        final var command = ProductEntityMapper.map(payload);
        final var product = this.productUpdateUseCase.update(command);

        redirectAttributes.addAttribute("productId", product.id().value());

        return "redirect:/message/products/{productId}";
    }

}
