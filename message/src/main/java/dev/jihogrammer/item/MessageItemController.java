package dev.jihogrammer.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MessageItemController {
    private final ItemsService itemsService;

    @GetMapping("/message/items")
    public String items(final Model model) {
        model.addAttribute("items", itemsService.findAll());
        return "/message/items";
    }
}
