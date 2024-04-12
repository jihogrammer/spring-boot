package dev.jihogrammer.member.adaptor.in.web.controller;

import dev.jihogrammer.member.adaptor.in.web.entity.FrontControllerMemberViewModel;
import dev.jihogrammer.member.adaptor.in.web.model.ViewNameController;
import dev.jihogrammer.member.application.port.in.MemberQuery;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static dev.jihogrammer.member.adaptor.in.web.WebEnvironment.URI_PREFIX;

@RequiredArgsConstructor
public class MemberListController extends ViewNameController {

    private final MemberQuery memberQuery;

    @Override
    public String uri() {
        return URI_PREFIX + "/members";
    }

    @Override
    public String view() {
        return "list";
    }

    @Override
    public String process(final Map<String, Object> model) {
        final var memberViewModels = this.memberQuery.findAll().stream().map(FrontControllerMemberViewModel::new).toList();

        model.put("members", memberViewModels);

        return this.view();
    }

}
