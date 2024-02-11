package dev.jihogrammer.spring.filestorage.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/servlet/v1")
@Slf4j
public class ServletUploadV1Controller {
    @GetMapping("/upload")
    public String view() {
        return "/upload-view";
    }

    @PostMapping("/upload")
    public String upload(final HttpServletRequest request) throws ServletException, IOException {
        log.info("uploading. request: {}", request);

        var itemName = request.getParameter("itemName");
        var parts = request.getParts();
        log.info("itemName: {}, parts: {}", itemName, parts);

        return "/upload-view";
    }
}
