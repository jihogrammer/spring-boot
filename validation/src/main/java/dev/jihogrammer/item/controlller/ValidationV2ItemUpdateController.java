package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
import dev.jihogrammer.item.validation.ItemUpdateHttpRequestValidator;
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
public class ValidationV2ItemUpdateController {
    private final ItemService service;
    private final ItemUpdateHttpRequestValidator itemUpdateHttpRequestValidator;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemUpdateHttpRequestValidator);
    }

    @PostMapping("/update")
    public String updateItem(
        @Validated @ModelAttribute("item") final ItemUpdateHttpRequest request,
        final BindingResult bindingResult, // must be placed immediately after @ModelAttribute
        final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/validation/v2-item-update";
        } else {
            var command = request.mapToCommand();
            var item = this.service.update(command);

            redirectAttributes.addAttribute("itemId", item.id().value());

            return "redirect:/validation/v2/items/{itemId}";
        }
    }
}
