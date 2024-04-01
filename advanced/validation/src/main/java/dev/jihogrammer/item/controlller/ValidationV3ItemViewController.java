package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.items.exception.ItemException;
import dev.jihogrammer.items.port.in.ItemReadUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/validation/v3/items")
@RequiredArgsConstructor
public class ValidationV3ItemViewController {

    private final ItemReadUsage itemReadUsage;

    @GetMapping
    public String itemListView(final Model model) {
        model.addAttribute("items", this.itemReadUsage.findAll());
        return "/validation/v3-item-list";
    }

    @GetMapping("/{itemId}")
    public String itemDetailView(final Model model, @PathVariable Long itemId) {
        try {
            model.addAttribute("item", this.itemReadUsage.findById(itemId));
            return "/validation/v3-item-detail";
        } catch (final ItemException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping("/register")
    public String itemRegisterView(final Model model) {
        model.addAttribute("item", new ItemRegisterHttpRequest());
        return "/validation/v3-item-register";
    }

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(@PathVariable final Long itemId, final Model model) {
        try {
            model.addAttribute("item", this.itemReadUsage.findById(itemId));
            return "/validation/v3-item-update";
        } catch (final ItemException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
