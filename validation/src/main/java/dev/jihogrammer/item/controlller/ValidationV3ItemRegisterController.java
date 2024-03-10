package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.items.port.in.ItemService;
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
        final BindingResult requestBindingResult,
        final RedirectAttributes redirectAttributes
    ) {
        if (requestBindingResult.hasErrors()) {
            return "/validation/v3-item-register";
        } else {
            var command = request.mapToCommand();
            var item = this.service.register(command);

            redirectAttributes.addAttribute("itemId", item.id().value());

            return "redirect:/validation/v3/items/{itemId}";
        }
    }

}
