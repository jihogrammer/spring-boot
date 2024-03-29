package dev.jihogrammer.item.controlller;

import dev.jihogrammer.item.model.in.ItemRegisterHttpRequest;
import dev.jihogrammer.item.model.in.ItemUpdateHttpRequest;
import dev.jihogrammer.items.port.in.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/validation/v1/items")
@RequiredArgsConstructor
public class ValidationV1ItemController {
    private final ItemService service;

    @GetMapping
    public String itemListView(final Model model) {
        var items = this.service.findAll();

        model.addAttribute("items", items);

        return "/validation/v1-item-list";
    }

    @GetMapping("/{itemId}")
    public String itemDetailView(final Model model, @PathVariable Long itemId) {
        var item = this.service.findById(itemId);

        model.addAttribute("item", item);

        return "/validation/v1-item-detail";
    }

    @GetMapping("/register")
    public String itemRegisterView(final Model model) {
        var blankRequest = new ItemRegisterHttpRequest();

        model.addAttribute("item", blankRequest);

        return "/validation/v1-item-register";
    }

    @PostMapping("/register")
    public String registerItem(final Model model, final ItemRegisterHttpRequest request, final RedirectAttributes redirectAttributes) {
        var errorMap = validateRequest(request);

        // validated case
        if (errorMap.isEmpty()) {
            var command = request.mapToCommand();
            var item = this.service.register(command);

            redirectAttributes.addAttribute("itemId", item.id().value());

            return "redirect:/validation/v1/items/{itemId}";
        }
        // not validated case
        else {
            model.addAttribute("item", request);
            model.addAttribute("errors", errorMap);
            return "/validation/v1-item-register";
        }
    }

    @GetMapping("/update/{itemId}")
    public String itemUpdateView(final Model model, @PathVariable final Long itemId) {
        var item = this.service.findById(itemId);

        model.addAttribute("item", item);

        return "/validation/v1-item-update";
    }

    @PostMapping("/update")
    public String updateItem(
            final ItemUpdateHttpRequest request,
            final RedirectAttributes redirectAttributes,
            final Model model
    ) {
        var errorMap = validateRequest(request);

        // validated case
        if (errorMap.isEmpty()) {
            var item = this.service.update(request.mapToCommand());

            redirectAttributes.addAttribute("itemId", item.id().value());

            return "redirect:/validation/v1/items/{itemId}";
        }
        // not validated case
        else {
            model.addAttribute("item", request);
            model.addAttribute("errors", errorMap);
            return "/validation/v1-item-update";
        }
    }


    private Map<String, String> validateRequest(final ItemRegisterHttpRequest request) {
        final Map<String, String> errorMap = new HashMap<>();

        // single field validation
        if (request.getName() == null || request.getName().isEmpty()) {
            errorMap.put("name", "이름은 꼭 입력해주세요.");
        }
        if (request.getPrice() == null || (1_000 > request.getPrice() || request.getPrice() > 1_000_000)) {
            errorMap.put("price", "가격은 1,000 ~ 1,000,000 원 사이의 값으로 정해주세요.");
        }
        if (request.getQuantity() == null || (1 > request.getQuantity() || request.getQuantity() > 10_000)) {
            errorMap.put("quantity", "수량은 0 ~ 9,999 개까지 입력해주세요.");
        }

        // complex fields validation
        if (request.getPrice() != null && request.getQuantity() != null && (request.getPrice() * request.getQuantity() < 10_000)) {
            errorMap.put("global", "(가격 * 수량 >= 10_000) 조건이 만족시켜주세요(현재: " + (request.getPrice() * request.getQuantity()) + ").");
        }

        return errorMap;
    }

    private Map<String, String> validateRequest(final ItemUpdateHttpRequest request) {
        var errorMap = new HashMap<String, String>();

        // single field validation
        if (request.getName() == null || request.getName().isEmpty()) {
            errorMap.put("name", "이름은 꼭 입력해주세요.");
        }
        if (request.getPrice() == null || (1_000 > request.getPrice() || request.getPrice() > 1_000_000)) {
            errorMap.put("price", "가격은 1,000 ~ 1,000,000 원 사이의 값으로 정해주세요.");
        }
        if (request.getQuantity() == null || (1 > request.getQuantity() || request.getQuantity() > 10_000)) {
            errorMap.put("quantity", "수량은 0 ~ 9,999 개까지 입력해주세요.");
        }

        // complex fields validation
        if (request.getPrice() != null && request.getQuantity() != null && (request.getPrice() * request.getQuantity() < 10_000)) {
            errorMap.put("global", "(가격 * 수량 >= 10_000) 조건이 만족시켜주세요(현재: " + (request.getPrice() * request.getQuantity()) + ").");
        }

        return errorMap;
    }
}
