package dev.jihogrammer.member.adaptor.in.web.controller;

import dev.jihogrammer.member.adaptor.in.web.model.ViewNameController;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static dev.jihogrammer.member.adaptor.in.web.WebEnvironment.URI_PREFIX;

@RequiredArgsConstructor
public class MemberFormController extends ViewNameController {

    @Override
    public String uri() {
        return URI_PREFIX + "/members/new-form";
    }

    @Override
    public String view() {
        return "new-form";
    }

    @Override
    public String process(final Map<String, Object> model) {
        return this.view();
    }

}
