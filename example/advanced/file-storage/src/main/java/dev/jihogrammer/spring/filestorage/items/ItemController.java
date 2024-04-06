package dev.jihogrammer.spring.filestorage.items;

import dev.jihogrammer.files.port.in.FileSaveUsage;
import dev.jihogrammer.spring.filestorage.items.adaptor.in.MultipartFileSaveCommand;
import dev.jihogrammer.spring.filestorage.items.dto.ItemForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/file-storage/items")
@RequiredArgsConstructor
public class ItemController {

    private final Items items;

    private final FileSaveUsage<MultipartFile> fileSaveUsage;

    @GetMapping("/{id}")
    public String item(@PathVariable("id") final Long id, final Model model) {
        var item = this.items.findById(id);

        model.addAttribute("item", item);

        return "/item-view";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("itemForm") final ItemForm itemForm) {
        return "/item-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("itemForm") final ItemForm itemForm, final RedirectAttributes redirectAttributes) {
        var userFile = this.fileSaveUsage.save(MultipartFileSaveCommand.of(itemForm.getUserFile()));
        var imageFiles = this.fileSaveUsage.save(MultipartFileSaveCommand.of(itemForm.getUserImageFiles()));

        var item = new Item();
        item.setItemName(itemForm.getItemName());
        item.setUserFile(userFile);
        item.setImageFiles(imageFiles);
        this.items.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/file-storage/items/{itemId}";
    }

}
