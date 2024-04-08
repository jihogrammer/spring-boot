package dev.jihogrammer.product.v1;

import dev.jihogrammer.product.model.ProductRegisterPayload;
import dev.jihogrammer.product.model.ProductUpdatePayload;
import dev.jihogrammer.product.model.ProductId;
import dev.jihogrammer.product.model.ProductViewModel;
import dev.jihogrammer.product.port.out.Products;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/validation/v1/products")
@RequiredArgsConstructor
@Slf4j
public class ValidationV1ProductController {

    private final Products products;

    @ModelAttribute("version")
    public String version() {
        return "v1";
    }

    @GetMapping
    public String products(final Model model) {
        var products = this.products.findAll();
        var productViewModels = ProductViewModel.of(products);

        model.addAttribute("products", productViewModels);

        return "/validation/products";
    }

    @GetMapping("/{productId}")
    public String product(@PathVariable("productId") Long productId, final Model model) {
        var product = this.products.findById(new ProductId(productId)).orElseThrow();
        var productViewModel = ProductViewModel.of(product);

        model.addAttribute("product", productViewModel);

        return "/validation/product";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("payload") final ProductRegisterPayload payload) {
        return "/validation/register";
    }

    @PostMapping("/register")
    public String register(
        @ModelAttribute("payload") final ProductRegisterPayload payload,
        final RedirectAttributes redirectAttributes,
        final Model model
    ) {
        var errorMap = validateRequest(payload);

        // validated case
        if (errorMap.isEmpty()) {
            var product = this.products.save(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v1/products/{productId}";
        }
        // not validated case
        else {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", errorMap);
            return "/validation/register";
        }
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
            @ModelAttribute("payload") final ProductUpdatePayload payload,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        var errorMap = validateRequest(payload);

        // validated case
        if (errorMap.isEmpty()) {
            var product = this.products.save(payload.toCommand());

            redirectAttributes.addAttribute("productId", product.id().value());

            return "redirect:/validation/v1/products/{productId}";
        }
        // not validated case
        else {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", errorMap);
            return "/validation/update";
        }
    }


    private Map<String, String> validateRequest(final ProductRegisterPayload payload) {
        final Map<String, String> errorMap = new HashMap<>();

        // single field validation
        if (payload.getName() == null || payload.getName().isEmpty()) {
            errorMap.put("name", "이름은 꼭 입력해주세요.");
        }
        if (payload.getPrice() == null || (1_000 > payload.getPrice() || payload.getPrice() > 1_000_000)) {
            errorMap.put("price", "가격은 1,000 ~ 1,000,000 원 사이의 값으로 정해주세요.");
        }
        if (payload.getQuantity() == null || (1 > payload.getQuantity() || payload.getQuantity() > 10_000)) {
            errorMap.put("quantity", "수량은 0 ~ 9,999 개까지 입력해주세요.");
        }

        // complex fields validation
        if (payload.getPrice() != null && payload.getQuantity() != null && (payload.getPrice() * payload.getQuantity() < 10_000)) {
            errorMap.put("global", "(가격 * 수량 >= 10_000) 조건이 만족시켜주세요(현재: " + (payload.getPrice() * payload.getQuantity()) + ").");
        }

        return errorMap;
    }

    private Map<String, String> validateRequest(final ProductUpdatePayload payload) {
        var errorMap = new HashMap<String, String>();

        // single field validation
        if (payload.getName() == null || payload.getName().isEmpty()) {
            errorMap.put("name", "이름은 꼭 입력해주세요.");
        }
        if (payload.getPrice() == null || (1_000 > payload.getPrice() || payload.getPrice() > 1_000_000)) {
            errorMap.put("price", "가격은 1,000 ~ 1,000,000 원 사이의 값으로 정해주세요.");
        }
        if (payload.getQuantity() == null || (1 > payload.getQuantity() || payload.getQuantity() > 10_000)) {
            errorMap.put("quantity", "수량은 0 ~ 9,999 개까지 입력해주세요.");
        }

        // complex fields validation
        if (payload.getPrice() != null && payload.getQuantity() != null && (payload.getPrice() * payload.getQuantity() < 10_000)) {
            errorMap.put("global", "(가격 * 수량 >= 10_000) 조건이 만족시켜주세요(현재: " + (payload.getPrice() * payload.getQuantity()) + ").");
        }

        return errorMap;
    }

}
