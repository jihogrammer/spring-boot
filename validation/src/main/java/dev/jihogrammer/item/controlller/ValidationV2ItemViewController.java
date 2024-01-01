package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationV2ItemViewController {
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

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(final Model model, @PathVariable final Long itemId) {
        model.addAttribute("item", this.service.findById(itemId));
        return "/validation/v2-item-update";
    }
}
