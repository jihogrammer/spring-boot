package dev.jihogrammer.items.app;

import dev.jihogrammer.items.domain.Item;
import dev.jihogrammer.items.domain.ItemId;
import dev.jihogrammer.items.domain.Items;
import dev.jihogrammer.items.model.mapper.DtoMapper;
import dev.jihogrammer.items.model.request.ItemCreateDto;
import dev.jihogrammer.items.model.request.ItemUpdateDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {
    private final Items items;

    @PostConstruct
    public void exampleItems() {
        items.save(new Item("a", 0, 1));
        items.save(new Item("b", 100, 100));
    }

    @GetMapping
    public String items(final Model model) {
        model.addAttribute("items", items.findAll());
        return "/items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable final long itemId, final Model model) {
        Item item = items.findById(new ItemId(itemId));
        model.addAttribute("item", item);
        return "/items/item";
    }

    @GetMapping("/save")
    public String saveForm() {
        return "/items/save";
    }

    @PostMapping("/save")
    public String saveItem(final ItemCreateDto source, final RedirectAttributes redirectAttributes) {
        Item item = items.save(DtoMapper.map(source));
        redirectAttributes.addAttribute("itemId", item.getId().value());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/update/{itemId}")
    public String updateForm(@PathVariable("itemId") final long value, final Model model) {
        model.addAttribute("item", items.findById(new ItemId(value)));
        return "/items/update";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(final ItemUpdateDto source, final RedirectAttributes redirectAttributes) {
        Item item = items.update(DtoMapper.map(source));
        redirectAttributes.addAttribute("itemId", item.getId().value());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }
}
