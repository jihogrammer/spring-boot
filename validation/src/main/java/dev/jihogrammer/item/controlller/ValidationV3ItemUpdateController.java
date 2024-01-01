package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.item.validation.ItemUpdateHttpRequestValidator;
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
public class ValidationV3ItemUpdateController {
    private final ItemService service;
    private final ItemUpdateHttpRequestValidator itemUpdateHttpRequestValidator;

    @InitBinder
    public void init(final WebDataBinder webDataBinder) {
        webDataBinder.addValidators(itemUpdateHttpRequestValidator);
    }

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(@PathVariable final Long itemId, final Model model) {
        model.addAttribute("item", this.service.findById(itemId));
        return "/validation/v3-item-update";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(
        @Validated @ModelAttribute("item") final ItemUpdateHttpRequest request,
        final BindingResult bindingResult, // must be placed immediately after @ModelAttribute
        final RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            return "/validation/v3-item-update";
        } else {
            ItemView itemView = this.service.update(request.mapToCommand());
            redirectAttributes.addAttribute("itemId", itemView.id());
            return "redirect:/validation/v3/items/{itemId}";
        }
    }
}
