package dev.jihogrammer.product.v2;

import dev.jihogrammer.product.model.ProductId;
import dev.jihogrammer.product.model.ProductViewModel;
import dev.jihogrammer.product.port.out.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/validation/v2/products")
@RequiredArgsConstructor
public class ValidationV2ProductViewController {

    private final Products products;

    @ModelAttribute("version")
    public String version() {
        return "v2";
    }

    @GetMapping
    public String products(final Model model) {
        var products = this.products.findAll();
        var productViewModels = ProductViewModel.of(products);

        model.addAttribute("products", productViewModels);

        return "/validation/products";
    }

    @GetMapping("/{productId}")
    public String product(@PathVariable("productId") final Long productId, final Model model) {
        var product = this.products.findById(new ProductId(productId)).orElseThrow();
        var productViewModel = ProductViewModel.of(product);

        model.addAttribute("product", productViewModel);

        return "/validation/product";
    }

}
