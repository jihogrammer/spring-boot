package dev.jihogrammer.spring.filestorage.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/spring")
@Slf4j
public class SpringUploadController {

    private final String fileDir;

    public SpringUploadController(@Value("${file.dir}") final String fileDir) {
        this.fileDir = new File(fileDir).getAbsolutePath() + "/";
    }

    @GetMapping("/upload")
    public String view() {
        return "/upload-view";
    }

    @PostMapping("/upload")
    public String upload(
        @RequestParam final String itemName,
        @RequestParam final MultipartFile file,
        final HttpServletRequest request
    ) {
        log.info("spring uploading. request = {}", request);
        log.info("itemName = {}", itemName);
        log.info("file = {}", file);

        if (file.isEmpty()) {
            log.info("file is empty");
        } else {
            String filePath = this.fileDir + file.getOriginalFilename();
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
