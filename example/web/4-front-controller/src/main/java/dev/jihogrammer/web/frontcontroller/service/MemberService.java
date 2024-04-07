package dev.jihogrammer.web.frontcontroller.service;

import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.web.frontcontroller.model.MemberView;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberService {

    private final Members members;

    private final String membersKey;

    public void fetchMembers(final Map<String, Object> model) {
        model.put(this.membersKey, members.findAll().stream().map(MemberView::new).toList());
    }

}
