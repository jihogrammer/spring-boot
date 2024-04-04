package dev.jihogrammer.spring.filestorage.controller;

import dev.jihogrammer.spring.filestorage.controller.dto.ItemForm;
import dev.jihogrammer.spring.filestorage.file.Files;
import dev.jihogrammer.spring.filestorage.items.Item;
import dev.jihogrammer.spring.filestorage.items.Items;
import dev.jihogrammer.spring.filestorage.items.UserFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final Items items;

    private final Files files;

    @GetMapping("/{id}")
    public String item(@PathVariable final Long id, final Model model) {
        model.addAttribute("item", this.items.findById(id));
        return "/item-view";
    }

    @GetMapping("/register")
    public String registerView(@ModelAttribute final ItemForm itemForm) {
        return "/item-form";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute final ItemForm itemForm, final RedirectAttributes redirectAttributes) {
        UserFile userFile = this.files.store(itemForm.getUserFile());
        Collection<UserFile> imageFiles = this.files.store(itemForm.getUserImageFiles());

        Item item = new Item();
        item.setItemName(itemForm.getItemName());
        item.setUserFile(userFile);
        item.setImageFiles(imageFiles);
        this.items.save(item);

        redirectAttributes.addAttribute("itemId", item.getId());
        return "redirect:/items/{itemId}";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public ResponseEntity<Resource> image(@PathVariable final String fileName) throws IOException {
        UrlResource resource = new UrlResource("file:" + this.files.fullPath(fileName));
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType(resource))
            .body(resource);
    }

    @ResponseBody
    @GetMapping("/file/{itemId}")
    public ResponseEntity<Resource> file(@PathVariable final Long itemId) throws IOException {
        Item item = this.items.findById(itemId);
        UrlResource resource = new UrlResource("file:" + this.files.fullPath(item.getUserFile().getStoredFileName()));
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType(resource))
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionHeaderValue(item))
            .body(resource);
    }

    private String contentType(final UrlResource urlResource) throws IOException {
        return urlResource.getURL().openConnection().getHeaderField("Content-Type");
    }

    private String contentDispositionHeaderValue(final Item item) {
        return "attachment; filename'\"" + UriUtils.encode(item.getUserFile().getUserDefinedFileName(), StandardCharsets.UTF_8) + "\"";
    }

}
