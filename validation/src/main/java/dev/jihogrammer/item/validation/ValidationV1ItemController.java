package dev.jihogrammer.item.validation;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v1/items")
@RequiredArgsConstructor
public class ValidationV1ItemController {
    private final ItemService service;

    @GetMapping
    public String itemListView(final Model model) {
        model.addAttribute("items", this.service.findAll());
        return "/validation/v1-item-list";
    }

    @GetMapping("/{itemId}")
    public String itemDetailView(final Model model, @PathVariable Long itemId) {
        model.addAttribute("item", this.service.findById(itemId));
        return "/validation/v1-item-detail";
    }

    @GetMapping("/register")
    public String itemRegisterView(final Model model) {
        model.addAttribute("item", new ItemRegisterHttpRequest());
        return "/validation/v1-item-register";
    }

    @PostMapping("/register")
    public String registerItem(final Model model, final ItemRegisterHttpRequest request, final RedirectAttributes redirectAttributes) {
        ItemView itemView = this.service.register(request.mapToCommand());
        redirectAttributes.addAttribute("itemId", itemView.id());
        return "redirect:/validation/v1/items/{itemId}";
    }

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(final Model model, @PathVariable final Long itemId) {
        model.addAttribute("item", this.service.findById(itemId));
        return "/validation/v1-item-update";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(final ItemUpdateHttpRequest request, final RedirectAttributes redirectAttributes) {
        ItemView itemView = this.service.update(request.mapToCommand());
        redirectAttributes.addAttribute("itemId", itemView.id());
        return "redirect:/validation/v1/items/{itemId}";
    }
}
