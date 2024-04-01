package dev.jihogrammer.items.application.register;

import dev.jihogrammer.items.application.DefaultItemController;
import dev.jihogrammer.items.port.in.ItemRegisterUsage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemRegisterController extends DefaultItemController {

    private final ItemRegisterUsage itemRegisterUsage;

    @GetMapping("/register")
    public String register(final ItemRegisterPayload itemRegisterPayload) {
        return "/items/register";
    }

    @PostMapping("/register")
    public String register(final ItemRegisterPayload itemRegisterPayload, final RedirectAttributes redirectAttributes) {
        var registerCommand = itemRegisterPayload.toCommand();
        var item = this.itemRegisterUsage.register(registerCommand);

        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

}
