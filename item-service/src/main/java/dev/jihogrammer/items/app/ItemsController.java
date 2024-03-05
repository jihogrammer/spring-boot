package dev.jihogrammer.items.app;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.Items;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateSource;
import dev.jihogrammer.items.model.in.ItemUpdateSourceMapper;
import dev.jihogrammer.items.model.out.ItemViewMapper;
import dev.jihogrammer.items.model.vo.DeliveryCode;
import dev.jihogrammer.items.model.vo.ItemId;
import dev.jihogrammer.items.model.vo.ItemType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsController {
    private static final Map<String, String> REGIONS = Map.of("SEOUL", "서울", "BUSAN", "부산", "JEJU", "제주");
    private static final List<DeliveryCode> DELIVERY_CODES = List.of(
        new DeliveryCode("FAST", "빠른 배송"),
        new DeliveryCode("NORMAL", "일반 배송"),
        new DeliveryCode("SLOW", "느린 배송"));

    private final Items items;

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
        model.addAttribute("items", ItemViewMapper.mapToView(items.findAll()));
        return "/items/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable final long itemId, final Model model) {
        model.addAttribute("item", ItemViewMapper.mapToView(items.findById(new ItemId(itemId))));
        return "/items/item";
    }

    @GetMapping("/save")
    public String saveForm(final Model model) {
        model.addAttribute("item", ItemSaveCommand.EMPTY_COMMAND);
        return "/items/save";
    }

    @PostMapping("/save")
    public String saveItem(final ItemSaveCommand command, final RedirectAttributes redirectAttributes) {
        Item item = items.save(command);
        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/update/{itemId}")
    public String updateForm(@PathVariable("itemId") final long value, final Model model) {
        model.addAttribute("item", ItemUpdateSourceMapper.mapToUpdateSource(items.findById(new ItemId(value))));
        return "/items/update";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(final ItemUpdateSource itemUpdateSource, final RedirectAttributes redirectAttributes) {
        Item item = items.update(ItemUpdateSourceMapper.mapToUpdateCommand(itemUpdateSource));
        redirectAttributes.addAttribute("itemId", item.id().value());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/{itemId}";
    }
}
