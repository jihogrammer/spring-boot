package dev.jihogrammer.item;

import dev.jihogrammer.item.model.in.ItemRegisterRequest;
import dev.jihogrammer.item.model.in.ItemUpdateRequest;
import dev.jihogrammer.items.port.in.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String itemListPage(final Model model) {
        var items = itemService.findAll();
        var itemViews = ItemEntityMapper.map(items);

        model.addAttribute("items", itemViews);

        return "/message/item-list";
    }

    @GetMapping("/items/{itemId}")
    public String itemViewPage(
            @PathVariable("itemId") final long itemId,
            final Model model
    ) {
        var item = this.itemService.findById(itemId);
        var itemView = ItemEntityMapper.map(item);

        model.addAttribute("item", itemView);

        return "/message/item-view";
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
        var item = this.itemService.register(command);

        redirectAttributes.addAttribute("itemId", item.id().value());

        return "redirect:/message/items/{itemId}";
    }

    @GetMapping("/items/update/{itemId}")
    public String updateFormPage(
            @PathVariable("itemId") final long itemId,
            final Model model
    ) {
        var item = this.itemService.findById(itemId);
        var itemView = ItemEntityMapper.map(item);

        model.addAttribute("item", itemView);

        return "/message/update-form";
    }

    @PostMapping("/items/update")
    public String updateItem(final ItemUpdateRequest request, final RedirectAttributes redirectAttributes) {
        var command = ItemEntityMapper.map(request);
        var item = this.itemService.update(command);

        redirectAttributes.addAttribute("itemId", item.id().value());

        return "redirect:/message/items/{itemId}";
    }
}
