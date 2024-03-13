package dev.jihogrammer.items;

import dev.jihogrammer.items.model.in.ItemUpdateSource;
import dev.jihogrammer.items.model.out.ItemView;
import dev.jihogrammer.items.model.DeliveryCode;
import dev.jihogrammer.items.model.ItemType;
import dev.jihogrammer.items.port.in.ItemService;
import dev.jihogrammer.items.model.ItemRegisterCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
public class ItemsController {

    private static final Map<String, String> REGIONS = Map.of("SEOUL", "서울", "BUSAN", "부산", "JEJU", "제주");

    private static final List<DeliveryCode> DELIVERY_CODES = List.of(
        new DeliveryCode("FAST", "빠른 배송"),
        new DeliveryCode("NORMAL", "일반 배송"),
        new DeliveryCode("SLOW", "느린 배송"));

    private final ItemService itemService;

    /**
     * ItemsController 내 각 요청 시마다 'regions' 이름을 가진 속성을 항상 같이 전달하게 처리
     */
    @ModelAttribute("regions")
    public Map<String, String> regions() {
        return REGIONS;
    }

    @ModelAttribute("itemTypes")
    public ItemType[] itemTypes() {
        return ItemType.values();
    }

    @ModelAttribute("deliveryCodes")
    public List<DeliveryCode> deliveryCodes() {
        return DELIVERY_CODES;
    }

    @GetMapping
    public String items(final Model model) {
        log.info("REQUEST GET /items");

        var items = this.itemService.findAll();
        var itemViews = ItemView.of(items);

        model.addAttribute("items", itemViews);

        return "/items/items";
    }

    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public String item(@PathVariable("itemId") final long itemIdValue, final Model model) {
        log.info("REQUEST GET /items/{}", itemIdValue);

        var item = this.itemService.findById(itemIdValue);
        var itemView = ItemView.of(item);

        model.addAttribute("item", itemView);

        return "/items/item";
    }

    @GetMapping("/save")
    public String saveForm(final Model model) {
        log.info("REQUEST GET /items/save");

        var blankCommand = ItemRegisterCommand.builder().build();

        model.addAttribute("item", blankCommand);

        return "/items/save";
    }

    @PostMapping("/save")
    public String saveItem(final ItemRegisterCommand command, final RedirectAttributes redirectAttributes) {
        log.info("REQUEST POST /items/save - {}", command);

        var item = this.itemService.register(command);

        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

    @GetMapping("/update/{itemId}")
    public String updateForm(@PathVariable("itemId") final long value, final Model model) {
        log.info("REQUEST GET /items/update/{}", value);

        var item = this.itemService.findById(value);
        var source = ItemUpdateSource.of(item);

        model.addAttribute("item", source);

        return "/items/update";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(
            @PathVariable("itemId") final long itemId,
            final ItemUpdateSource itemUpdateSource,
            final RedirectAttributes redirectAttributes
    ) {
        log.info("REQUEST POST /items/update/{} - {}", itemId, itemUpdateSource);

        var command = ItemUpdateSource.of(itemUpdateSource);
        var item = this.itemService.update(command);

        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/items/{itemId}";
    }

}
