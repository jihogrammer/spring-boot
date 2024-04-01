package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.EntityMapper;
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
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationV2ItemViewController {

    private final ItemReadUsage itemReadUsage;

    @GetMapping
    public String itemListView(final Model model) {
        var items = this.itemReadUsage.findAll();
        var itemViews = EntityMapper.map(items);

        model.addAttribute("items", itemViews);

        return "/validation/v2-item-list";
    }

    @GetMapping("/{itemId}")
    public String itemDetailView(final Model model, @PathVariable Long itemId) {
        try {
            var item = this.itemReadUsage.findById(itemId);
            var itemView = EntityMapper.map(item);

            model.addAttribute("item", itemView);

            return "/validation/v2-item-detail";
        } catch (final ItemException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping("/register")
    public String itemRegisterView(final Model model) {
        var blankRequest = new ItemRegisterHttpRequest();

        model.addAttribute("item", blankRequest);

        return "/validation/v2-item-register";
    }

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(final Model model, @PathVariable final Long itemId) {
        try {
            var item = this.itemReadUsage.findById(itemId);
            var itemView = EntityMapper.map(item);

            model.addAttribute("item", itemView);

            return "/validation/v2-item-update";
        } catch (final ItemException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
