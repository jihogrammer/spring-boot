package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.ItemService;
import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
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
public class ValidationV3ItemUpdateController {
    private final ItemService service;

    @PostMapping("/update/{itemId}") // TODO path variable 값으로 들어오는 ID 사용이 옳다만, 사용하고 있지 않음.
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
