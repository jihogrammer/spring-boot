package dev.jihogrammer.filestorage.adaptor.in.web;

import dev.jihogrammer.filestorage.adaptor.in.web.entity.ProductRegisterPayload;
import dev.jihogrammer.filestorage.adaptor.in.web.entity.ProductViewModel;
import dev.jihogrammer.filestorage.application.port.in.FileStorageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/file-storage/products")
@RequiredArgsConstructor
public class ProductController {

    private final FileStorageUseCase fileStorageUseCase;

    @GetMapping("/{productId}")
    public String item(@PathVariable("productId") final Long productId, final Model model) {
        final var product = this.fileStorageUseCase.findById(productId);
        final var productViewModel = ProductViewModel.of(product);

        model.addAttribute("product", productViewModel);

        return "/product";
    }

    @GetMapping("/register")
    public String register() {
        return "/register";
    }

    @PostMapping("/register")
    public String register(
        final ProductRegisterPayload payload,
        final RedirectAttributes redirectAttributes
    ) {
        final var command = payload.toCommand();
        final var product = this.fileStorageUseCase.register(command);

        redirectAttributes.addAttribute("productId", product.id().value());

        return "redirect:/file-storage/products/{productId}";
    }

}
