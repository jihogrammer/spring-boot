package dev.jihogrammer.product.v3;

import dev.jihogrammer.product.domain.model.ProductId;
import dev.jihogrammer.product.domain.model.ProductViewModel;
import dev.jihogrammer.product.application.port.out.ProductPort;
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

    private final ProductPort productPort;

    @ModelAttribute("version")
    public String version() {
        return "v3";
    }

    @GetMapping
    public String products(final Model model) {
        var products = this.productPort.findAll();
        var productViewModels = ProductViewModel.of(products);

        model.addAttribute("products", productViewModels);

        return "/validation/products";
    }

    @GetMapping("/{productId}")
    public String product(@PathVariable("productId") Long productId, final Model model) {
        var product = this.productPort.findById(new ProductId(productId)).orElseThrow();
        var productViewModel = ProductViewModel.of(product);

        model.addAttribute("product", productViewModel);

        return "/validation/product";
    }

}
