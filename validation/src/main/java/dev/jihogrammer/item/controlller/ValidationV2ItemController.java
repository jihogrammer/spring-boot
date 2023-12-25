package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.item.validation.ItemRegisterHttpRequestValidator;
import dev.jihogrammer.item.validation.ItemUpdateHttpRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationV2ItemController {
    private final ItemService service;
    private final ItemRegisterHttpRequestValidator itemRegisterHttpRequestValidator;
    private final ItemUpdateHttpRequestValidator itemUpdateHttpRequestValidator;

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
        itemRegisterHttpRequestValidator.validate(request, bindingResult);

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
        itemUpdateHttpRequestValidator.validate(request, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/validation/v2-item-update";
        } else {
            ItemView itemView = this.service.update(request.mapToCommand());
            redirectAttributes.addAttribute("itemId", itemView.id());
            return "redirect:/validation/v2/items/{itemId}";
        }
    }
}
