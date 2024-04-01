package dev.jihogrammer.item;

import dev.jihogrammer.item.model.in.ItemRegisterRequest;
import dev.jihogrammer.item.model.in.ItemUpdateRequest;
import dev.jihogrammer.items.exception.ItemException;
import dev.jihogrammer.items.port.in.ItemReadUsage;
import dev.jihogrammer.items.port.in.ItemRegisterUsage;
import dev.jihogrammer.items.port.in.ItemUpdateUsage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
@Slf4j
public class MessageItemController {

    private final ItemReadUsage itemReadUsage;

    private final ItemRegisterUsage itemRegisterUsage;

    private final ItemUpdateUsage itemUpdateUsage;

    @GetMapping("/items")
    public String itemListPage(final Model model) {
        var items = this.itemReadUsage.findAll();
        var itemViews = ItemEntityMapper.map(items);

        model.addAttribute("items", itemViews);

        return "/message/item-list";
    }

    @GetMapping("/items/{itemId}")
    public String itemViewPage(
            @PathVariable("itemId") final long itemId,
            final Model model
    ) {
        try {
            var item = this.itemReadUsage.findById(itemId);
            var itemView = ItemEntityMapper.map(item);

            model.addAttribute("item", itemView);

            return "/message/item-view";
        } catch (final ItemException e) {
            log.error("Failed to read the item.", e);
            throw new IllegalArgumentException(e);
        }
    }

    @GetMapping("/items/register")
    public String registerFormPage(final Model model) {
        var blankRequest = new ItemRegisterRequest();

        model.addAttribute("item", blankRequest);

        return "/message/register-form";
    }

    @PostMapping("/items/register")
    public String registerItem(
            final ItemRegisterRequest request,
            final RedirectAttributes redirectAttributes
    ) {
        var command = ItemEntityMapper.map(request);
        var item = this.itemRegisterUsage.register(command);

        redirectAttributes.addAttribute("itemId", item.id().value());

        return "redirect:/message/items/{itemId}";
    }

    @GetMapping("/items/update/{itemId}")
    public String updateFormPage(
            @PathVariable("itemId") final long itemId,
            final Model model
    ) {
        try {
            var item = this.itemReadUsage.findById(itemId);
            var itemView = ItemEntityMapper.map(item);

            model.addAttribute("item", itemView);

            return "/message/update-form";
        } catch (final ItemException e) {
            log.error("Failed to read the item.", e);
            throw new IllegalArgumentException(e);
        }
    }

    @PostMapping("/items/update")
    public String updateItem(final ItemUpdateRequest request, final RedirectAttributes redirectAttributes) {
        var command = ItemEntityMapper.map(request);
        var item = this.itemUpdateUsage.update(command);

        redirectAttributes.addAttribute("itemId", item.id().value());

        return "redirect:/message/items/{itemId}";
    }

}
