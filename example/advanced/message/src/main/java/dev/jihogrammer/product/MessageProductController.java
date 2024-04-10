package dev.jihogrammer.product;

import dev.jihogrammer.product.application.port.out.ProductPort;
import dev.jihogrammer.product.domain.model.ProductRegisterPayload;
import dev.jihogrammer.product.domain.model.ProductUpdatePayload;
import dev.jihogrammer.product.domain.model.ProductId;
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

    private final ProductPort productPort;

    @GetMapping("/products")
    public String products(final Model model) {
        var products = this.productPort.findAll();
        var productViewModels = ProductEntityMapper.map(products);

        model.addAttribute("products", productViewModels);

        return "/message/products";
    }

    @GetMapping("/products/{productId}")
    public String product(
            @PathVariable("productId") final long productId,
            final Model model
    ) {
        var product = this.productPort.findById(new ProductId(productId)).orElseThrow();
        var productViewModel = ProductEntityMapper.map(product);

        model.addAttribute("product", productViewModel);

        return "/message/product";
    }

    @GetMapping("/products/register")
    public String register(final Model model) {
        var payload = new ProductRegisterPayload();

        model.addAttribute("product", payload);

        return "/message/register";
    }

    @PostMapping("/products/register")
    public String register(
            final ProductRegisterPayload payload,
            final RedirectAttributes redirectAttributes
    ) {
        var command = ProductEntityMapper.map(payload);
        var product = this.productPort.save(command);

        redirectAttributes.addAttribute("productId", product.id().value());

        return "redirect:/message/products/{productId}";
    }

    @GetMapping("/products/update/{productId}")
    public String update(
            @PathVariable("productId") final long productId,
            final Model model
    ) {
        var product = this.productPort.findById(new ProductId(productId)).orElseThrow();
        var productViewModel = ProductEntityMapper.map(product);

        model.addAttribute("product", productViewModel);

        return "/message/update";
    }

    @PostMapping("/products/update")
    public String update(final ProductUpdatePayload payload, final RedirectAttributes redirectAttributes) {
        var command = ProductEntityMapper.map(payload);
        var product = this.productPort.save(command);

        redirectAttributes.addAttribute("productId", product.id().value());

        return "redirect:/message/products/{productId}";
    }

}
