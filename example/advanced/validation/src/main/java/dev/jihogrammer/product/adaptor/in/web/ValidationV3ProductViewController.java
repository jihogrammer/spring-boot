package dev.jihogrammer.product.adaptor.in.web;

import dev.jihogrammer.product.adaptor.in.web.entity.ProductViewModel;
import dev.jihogrammer.product.application.port.in.ProductQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/validation/v3/products")
@RequiredArgsConstructor
public class ValidationV3ProductViewController {

    private final ProductQuery productQuery;

    @ModelAttribute("version")
    public String version() {
        return "v3";
    }

    @GetMapping
    public String products(final Model model) {
        var products = this.productQuery.findAll();
        var productViewModels = ProductViewModel.of(products);

        model.addAttribute("products", productViewModels);

        return "/validation/products";
    }

    @GetMapping("/{productId}")
    public String product(@PathVariable("productId") Long productId, final Model model) {
        var product = this.productQuery.findById(productId);
        var productViewModel = ProductViewModel.of(product);

        model.addAttribute("product", productViewModel);

        return "/validation/product";
    }

}
