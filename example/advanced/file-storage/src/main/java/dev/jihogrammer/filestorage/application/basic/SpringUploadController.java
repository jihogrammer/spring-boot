package dev.jihogrammer.filestorage.application.basic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file-storage/spring")
@RequiredArgsConstructor
@Slf4j
public class SpringUploadController {

    private final String fileRootDir;

    @GetMapping("/upload")
    public String view() {
        return "/upload-view";
    }

    @PostMapping("/upload")
    public String upload(
        @RequestParam("itemName") final String itemName,
        @RequestParam("file") final MultipartFile file
    ) {
        log.info("itemName = {}", itemName);
        log.info("file = {}", file);

        if (file.isEmpty()) {
            log.info("file is empty");
        } else {
            String filePath = this.fileRootDir + file.getOriginalFilename();
            log.info("filePath = {}", filePath);

            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                log.info("failed to write file", e);
            }
        }

        return "/upload-view";
    }

}
