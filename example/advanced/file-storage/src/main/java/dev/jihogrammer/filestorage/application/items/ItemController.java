package dev.jihogrammer.filestorage.application.items;

import dev.jihogrammer.fileitem.port.in.ItemFileFindUsage;
import dev.jihogrammer.filestorage.application.items.model.ItemRegisterPayload;
import dev.jihogrammer.filestorage.application.items.model.ItemViewModel;
import dev.jihogrammer.product.domain.model.ItemId;
import dev.jihogrammer.product.port.out.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/file-storage/items")
@RequiredArgsConstructor
public class ItemController {

    private final Items items;

    private final FileStoreUsage fileStoreUsage;

    private final ItemFileFindUsage itemFileFindUsage;

    @GetMapping("/{id}")
    public String item(@PathVariable("id") final Long id, final Model model) {
        var itemId = new ItemId(id);
        var item = this.itemFileFindUsage.findItemById(itemId);
        var mainFile = this.itemFileFindUsage.findMainFileByItemId(itemId);
        var subFiles = this.itemFileFindUsage.findSubFilesByItemId(itemId);

        var itemViewModel = ItemViewModel.of(item, mainFile, subFiles);

        model.addAttribute("item", itemViewModel);

        return "/item-view";
    }

    @GetMapping("/register")
    public String register() {
        return "/item-form";
    }

    @PostMapping("/register")
    public String register(
        final ItemRegisterPayload payload,
        final RedirectAttributes redirectAttributes
    ) {
        var item = this.items.save(payload.toItemRegisterCommand());
        this.fileStoreUsage.saveFiles(item.id(), payload);

        redirectAttributes.addAttribute("itemId", item.id().value());

        return "redirect:/file-storage/items/{itemId}";
    }

}
