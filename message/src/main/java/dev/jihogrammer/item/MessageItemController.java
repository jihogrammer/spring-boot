package dev.jihogrammer.item;

import dev.jihogrammer.item.model.in.ItemRegisterRequest;
import dev.jihogrammer.item.model.in.ItemUpdateRequest;
import dev.jihogrammer.item.model.out.ItemUpdateModel;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.domain.model.Item;
import lombok.RequiredArgsConstructor;
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
public class MessageItemController {
    private final ItemsService itemsService;

    @GetMapping("/items")
    public String itemListPage(final Model model) {
        model.addAttribute("items", itemsService.findAll().stream().map(ItemView::of).toList());
        return "/message/item-list";
    }

    @GetMapping("/items/{itemId}")
    public String itemViewPage(final Model model, @PathVariable final long itemId) {
        model.addAttribute("item", ItemView.of(itemsService.findById(itemId)));
        return "/message/item-view";
    }

    @GetMapping("/items/register")
    public String registerFormPage(final Model model) {
        model.addAttribute("item", new ItemRegisterRequest());
        return "/message/register-form";
    }

    @PostMapping("/items/register")
    public String registerItem(final ItemRegisterRequest request, final RedirectAttributes redirectAttributes) {
        Item item = itemsService.register(request);
        redirectAttributes.addAttribute("itemId", item.id().value());
        return "redirect:/message/items/{itemId}";
    }

    @GetMapping("/items/update/{itemId}")
    public String updateFormPage(final Model model, @PathVariable final long itemId) {
        model.addAttribute("item", ItemUpdateModel.of(itemsService.findById(itemId)));
        return "/message/update-form";
    }

    @PostMapping("/items/update")
    public String updateItem(final ItemUpdateRequest request, final RedirectAttributes redirectAttributes) {
        Item item = itemsService.update(request);
        redirectAttributes.addAttribute("itemId", item.id().value());
        return "redirect:/message/items/{itemId}";
    }
}
