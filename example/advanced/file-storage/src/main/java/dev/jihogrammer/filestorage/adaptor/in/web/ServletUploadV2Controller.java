package dev.jihogrammer.filestorage.adaptor.in.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/file-storage/servlet/v2")
@RequiredArgsConstructor
@Slf4j
public class ServletUploadV2Controller {

    private final String fileRootDir;

    @GetMapping("/upload")
    public String upload() {
        return "/basic-upload";
    }

    @PostMapping("/upload")
    public String upload(final HttpServletRequest request) throws ServletException, IOException {
        log.info("uploading. request = {}", request);

        var name = request.getParameter("name");
        var parts = request.getParts();
        log.info("name = {}, parts = {}", name, parts);

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
                part.write(this.fileRootDir + part.getSubmittedFileName());
            }
        }

        return "/basic-upload";
    }

}
