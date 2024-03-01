package dev.jihogrammer.front_controller.service;

import dev.jihogrammer.front_controller.model.MemberView;
import dev.jihogrammer.member.port.out.Members;
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
