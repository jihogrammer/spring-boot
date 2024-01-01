package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.item.validation.ItemRegisterHttpRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v3/items")
@RequiredArgsConstructor
public class ValidationV3ItemRegisterController {
    private final ItemService service;
    private final ItemRegisterHttpRequestValidator itemRegisterHttpRequestValidator;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemRegisterHttpRequestValidator);
    }

    @GetMapping("/register")
    public String itemRegisterView(final Model model) {
        model.addAttribute("item", new ItemRegisterHttpRequest());
        return "/validation/v3-item-register";
    }

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
