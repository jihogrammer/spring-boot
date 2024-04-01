package dev.jihogrammer.items.application.update;

import dev.jihogrammer.items.application.DefaultItemController;
import dev.jihogrammer.items.exception.ItemException;
import dev.jihogrammer.items.port.in.ItemReadUsage;
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
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemUpdateController extends DefaultItemController {

    private final ItemReadUsage itemReadUsage;

    private final ItemUpdateUsage itemUpdateUsage;

    @GetMapping("/update/{itemId}")
    public String update(
            @PathVariable("itemId") final long itemIdValue,
            final Model model
    ) {
        try {
            var item = this.itemReadUsage.findById(itemIdValue);
            var itemUpdatePayload = ItemUpdatePayload.of(item);

            model.addAttribute("itemId", itemIdValue);
            model.addAttribute("itemUpdatePayload", itemUpdatePayload);

            return "/items/update";
        } catch (final ItemException e) {
            log.warn("Could not find a item.", e);
            return "/items/item-empty";
        }
    }

    @PostMapping("/update/{itemId}")
    public String update(
            @PathVariable("itemId") final long itemIdValue,
            final ItemUpdatePayload itemUpdatePayload,
            final RedirectAttributes redirectAttributes
    ) {
        var command = itemUpdatePayload.toCommand(itemIdValue);
        var item = this.itemUpdateUsage.update(command);

        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

}
