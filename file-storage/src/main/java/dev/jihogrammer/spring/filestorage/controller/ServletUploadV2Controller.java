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

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/servlet/v2")
@Slf4j
public class ServletUploadV2Controller {

    private final String fileDir;

    public ServletUploadV2Controller(@Value("${file.dir}") final String fileDir) {
        this.fileDir = new File(fileDir).getAbsolutePath() + "/";
    }

    @GetMapping("/upload")
    public String view() {
        return "/upload-view";
    }

    @PostMapping("/upload")
    public String upload(final HttpServletRequest request) throws ServletException, IOException {
        log.info("uploading. request = {}", request);

        var itemName = request.getParameter("itemName");
        var parts = request.getParts();
        log.info("itemName = {}, parts = {}", itemName, parts);

        for (Part part : parts) {
            log.info("=== PART ===");
            log.info("name = {}", part.getName());

            for (String headerName : part.getHeaderNames()) {
                log.info("header {} = {}", headerName, part.getHeader(headerName));
            }

            log.info("submittedFileName = {}", part.getSubmittedFileName());
            log.info("size = {}", part.getSize());

            log.info("body = {}", StreamUtils.copyToString(part.getInputStream(), StandardCharsets.UTF_8));

            if (StringUtils.hasText(part.getSubmittedFileName())) {
                part.write(this.fileDir + part.getSubmittedFileName());
            }
        }

        return "/upload-view";
    }
}
