package dev.jihogrammer.basic.items.api;

import dev.jihogrammer.basic.items.dto.ItemRegisterModel;
import dev.jihogrammer.basic.items.dto.ItemUpdateModel;
import dev.jihogrammer.basic.items.dto.ItemViewModel;
import dev.jihogrammer.items.model.DeliveryCode;
import dev.jihogrammer.items.model.ItemType;
import dev.jihogrammer.items.port.in.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController implements ItemModelAttributes {

    private final ItemService itemService;

    @ModelAttribute("regions")
    public Map<String, String> regions() {
        return REGIONS;
    }

    @ModelAttribute("itemTypes")
    public Collection<ItemType> itemTypes() {
        return ITEM_TYPES;
    }

    @ModelAttribute("deliveryCodes")
    public Collection<DeliveryCode> deliveryCodes() {
        return DELIVERY_CODES;
    }

    @GetMapping
    public String items(final Model model) {
        var items = this.itemService.findAll();
        var itemViews = ItemViewModel.of(items);

        model.addAttribute("items", itemViews);

        return "/items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") final long itemIdValue, final Model model) {
        var item = this.itemService.findById(itemIdValue);
        var itemViewModel = ItemViewModel.of(item);

        model.addAttribute("item", itemViewModel);

        return "/items/item";
    }

    @GetMapping("/register")
    public String register(final Model model) {
        var blankItemRegisterModel = ItemRegisterModel.builder().build();

        model.addAttribute("item", blankItemRegisterModel);

        return "/items/register";
    }

    @PostMapping("/register")
    public String register(final ItemRegisterModel itemRegisterModel, final RedirectAttributes redirectAttributes) {
        var registerCommand = itemRegisterModel.toCommand();
        var item = this.itemService.register(registerCommand);

        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/update/{itemId}")
    public String update(@PathVariable("itemId") final long itemIdValue, final Model model) {
        var item = this.itemService.findById(itemIdValue);
        var itemUpdateModel = ItemUpdateModel.of(item);

        model.addAttribute("item", itemUpdateModel);

        return "/items/update";
    }

    @PostMapping("/update/{itemId}")
    public String update(
            @PathVariable("itemId") final long itemIdValue,
            final ItemUpdateModel itemUpdateModel,
            final RedirectAttributes redirectAttributes
    ) {
        if (itemIdValue != itemUpdateModel.id()) {
            throw new IllegalArgumentException("아이디 값 다름");
        }

        var updateCommand = itemUpdateModel.toCommand();
        var item = this.itemService.update(updateCommand);

        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

}
