package dev.jihogrammer.filestorage.adaptor.in.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/file-storage/servlet/v1")
@Slf4j
public class ServletUploadV1Controller {

    @GetMapping("/upload")
    public String upload() {
        return "/basic-upload";
    }

    @PostMapping("/upload")
    public String upload(final HttpServletRequest request) throws ServletException, IOException {
        log.info("uploading. request: {}", request);

        var name = request.getParameter("name");
        var parts = request.getParts();
        log.info("name: {}, parts: {}", name, parts);

        return "/basic-upload";
    }

}
