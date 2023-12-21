package dev.jihogrammer.item.validation;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationV2ItemController {
    private final ItemService service;

    @GetMapping
    public String itemListView(final Model model) {
        model.addAttribute("items", this.service.findAll());
        return "/validation/v2-item-list";
    }

    @GetMapping("/{itemId}")
    public String itemDetailView(final Model model, @PathVariable Long itemId) {
        model.addAttribute("item", this.service.findById(itemId));
        return "/validation/v2-item-detail";
    }

    @GetMapping("/register")
    public String itemRegisterView(final Model model) {
        model.addAttribute("item", new ItemRegisterHttpRequest());
        return "/validation/v2-item-register";
    }

    @PostMapping("/register")
    public String registerItem(
        @ModelAttribute("item") final ItemRegisterHttpRequest request,
        // must be placed immediately after @ModelAttribute
        final BindingResult bindingResult,
        final RedirectAttributes redirectAttributes
    ) {
        validateRequest(request, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/validation/v2-item-register";
        } else {
            ItemView itemView = this.service.register(request.mapToCommand());
            redirectAttributes.addAttribute("itemId", itemView.id());
            return "redirect:/validation/v2/items/{itemId}";
        }
    }

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(final Model model, @PathVariable final Long itemId) {
        model.addAttribute("item", this.service.findById(itemId));
        return "/validation/v2-item-update";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(
        @ModelAttribute("item") final ItemUpdateHttpRequest request,
        // must be placed immediately after @ModelAttribute
        final BindingResult bindingResult,
        final RedirectAttributes redirectAttributes
    ) {
        validateRequest(request, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/validation/v2-item-update";
        } else {
            ItemView itemView = this.service.update(request.mapToCommand());
            redirectAttributes.addAttribute("itemId", itemView.id());
            return "redirect:/validation/v2/items/{itemId}";
        }
    }

    private void validateFields(final String name, final Integer price, final Integer quantity, final BindingResult bindingResult) {
        // single field validation
        if (name == null || name.isEmpty()) {
            bindingResult.addError(new FieldError(
                "item",
                "name",
                name,
                false,
                null,
                null,
                "이름은 꼭 입력해주세요."));
        }
        if (price == null || 1_000 > price || price > 1_000_000) {
            bindingResult.addError(new FieldError(
                "item",
                "price",
                price,
                false,
                null,
                null,
                "가격은 1,000 ~ 1,000,000 원 사이의 값으로 정해주세요."));
        }
        if (quantity == null || 1 > quantity || quantity > 10_000) {
            bindingResult.addError(new FieldError(
                "item",
                "quantity",
                quantity,
                false,
                null,
                null,
                "수량은 0 ~ 9,999 개까지 입력해주세요."));
        }
        // complex fields validation
        if (price != null && quantity != null && (price * quantity < 10_000)) {
            bindingResult.addError(new ObjectError(
                "item",
                null,
                null,
                "(가격 * 수량 >= 10_000) 조건이 만족시켜주세요(현재: " + (price * quantity) + ")."));
        }
    }

    private void validateRequest(final ItemRegisterHttpRequest request, final BindingResult bindingResult) {
        validateFields(request.getName(), request.getPrice(), request.getQuantity(), bindingResult);
    }

    private void validateRequest(final ItemUpdateHttpRequest request, final BindingResult bindingResult) {
        validateFields(request.getName(), request.getPrice(), request.getQuantity(), bindingResult);
    }
}
