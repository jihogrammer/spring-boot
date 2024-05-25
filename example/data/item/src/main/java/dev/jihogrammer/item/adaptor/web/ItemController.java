package dev.jihogrammer.item.adaptor.web;

import dev.jihogrammer.item.adaptor.web.entity.RegisterPayload;
import dev.jihogrammer.item.adaptor.web.entity.SearchPayload;
import dev.jihogrammer.item.adaptor.web.entity.UpdatePayload;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.domain.ItemId;
import dev.jihogrammer.item.domain.exception.ItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemQuery itemQuery;

    private final ItemUpdatePort itemUpdatePort;

    @GetMapping
    public String items(
            @ModelAttribute("payload") final SearchPayload payload,
            final Model model
    ) {
        final var command = payload == null ? null : payload.toCommand();
        final var items = this.itemQuery.search(command);

        model.addAttribute("items", items);

        return "items";
    }

    @GetMapping("/{id}")
    public String item(
            @PathVariable("id") final String id,
            final Model model
    ) {
        final var optionalItem = this.itemQuery.findById(new ItemId(id));

        if (optionalItem.isEmpty()) {
            throw new ItemException("Could not find a member.");
        }

        model.addAttribute("item", optionalItem.get());

        return "item";
    }

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("payload", new RegisterPayload());

        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("payload") final RegisterPayload payload,
            final RedirectAttributes redirectAttributes
    ) {
        final var command = payload.toCommand();
        final var item = this.itemUpdatePort.register(command);

        redirectAttributes.addAttribute("id", item.id().value());

        return "redirect:/items/{id}";
    }

    @GetMapping("/update/{id}")
    public String update(
            @PathVariable("id") final String id,
            final Model model
    ) {
        final var optionalItem = this.itemQuery.findById(new ItemId(id));

        if (optionalItem.isEmpty()) {
            throw new ItemException("Could not find a item.");
        }

        final var payload = UpdatePayload.of(optionalItem.get());

        model.addAttribute("payload", payload);

        return "update";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute("payload") final UpdatePayload payload,
            final RedirectAttributes redirectAttributes
    ) {
        final var command = payload.toCommand();
        final var item = this.itemUpdatePort.register(command);

        redirectAttributes.addAttribute("id", item.id().value());

        return "redirect:/items/{id}";
    }

}
