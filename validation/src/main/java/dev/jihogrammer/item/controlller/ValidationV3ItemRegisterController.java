package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v3/items")
@RequiredArgsConstructor
public class ValidationV3ItemRegisterController {
    private final ItemService service;

    @PostMapping("/register")
    public String registerItem(
        @Validated @ModelAttribute("item") final ItemRegisterHttpRequest request,
        final BindingResult bindingResult, // must be placed immediately after @ModelAttribute
        final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/validation/v3-item-register";
        } else {
            ItemView itemView = this.service.register(request.mapToCommand());
            redirectAttributes.addAttribute("itemId", itemView.id());
            return "redirect:/validation/v3/items/{itemId}";
        }
    }
}
