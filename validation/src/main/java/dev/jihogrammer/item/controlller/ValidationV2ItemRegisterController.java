package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.item.validation.ItemRegisterHttpRequestValidator;
import dev.jihogrammer.items.port.in.ItemRegisterCommand;
import dev.jihogrammer.items.port.in.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationV2ItemRegisterController {

    private final ItemService service;

    private final ItemRegisterHttpRequestValidator itemRegisterHttpRequestValidator;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(this.itemRegisterHttpRequestValidator);
    }

    @PostMapping("/register")
    public String registerItem(
        @Validated @ModelAttribute("item") final ItemRegisterHttpRequest request,
        // must be placed immediately after @ModelAttribute
        final BindingResult bindingResult,
        final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/validation/v2-item-register";
        } else {
            var command = request.mapToCommand();
            var item = this.service.register(command);

            redirectAttributes.addAttribute("itemId", item.id().value());

            return "redirect:/validation/v2/items/{itemId}";
        }
    }

}
