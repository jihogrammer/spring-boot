package dev.jihogrammer.items.application.read;

import dev.jihogrammer.items.application.DefaultItemController;
import dev.jihogrammer.items.exception.ItemException;
import dev.jihogrammer.items.port.in.ItemReadUsage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemReadController extends DefaultItemController {

    private final ItemReadUsage itemReadUsage;

    @GetMapping
    public String items(final Model model) {
        final var items = this.itemReadUsage.findAll();
        final var itemViewModels = ItemViewModel.of(items);

        model.addAttribute("items", itemViewModels);

        return "/items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") final long itemIdValue, final Model model) {
        try {
            final var item = this.itemReadUsage.findById(itemIdValue);
            final var itemViewModel = ItemViewModel.of(item);

            model.addAttribute("item", itemViewModel);

            return "/items/item";
        } catch (final ItemException e) {
            log.warn("Could not find a item.", e);
            return "/items/item-empty";
        }
    }

}
