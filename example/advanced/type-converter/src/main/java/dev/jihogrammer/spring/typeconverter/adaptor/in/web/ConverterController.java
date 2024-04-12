package dev.jihogrammer.spring.typeconverter.adaptor.in.web;

import dev.jihogrammer.spring.typeconverter.adaptor.in.web.entity.InternetProtocolAndPort;
import dev.jihogrammer.spring.typeconverter.adaptor.in.web.entity.InternetProtocolAndPortPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(final Model model) {
        model.addAttribute("number", 123456789);
        model.addAttribute("ipPort", new InternetProtocolAndPort("127.0.0.1", 8080));
        return "/converter-view";
    }

    @GetMapping("/converter-edit")
    public String edit(final Model model) {
        var ipAndPort = new InternetProtocolAndPort("8.8.8.8", 80);
        model.addAttribute("form", new InternetProtocolAndPortPayload(ipAndPort));
        return "/converter-edit";
    }

    @PostMapping("/converter-edit")
    public String submit(
        @ModelAttribute("form") final InternetProtocolAndPortPayload form,
        final Model model
    ) {
        log.info("submitted. form = {}", form);
        model.addAttribute("ipPort", form.getIpPort());
        return "/converter-view";
    }

}
