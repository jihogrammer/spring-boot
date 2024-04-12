package dev.jihogrammer.member.adaptor.out.persistence;

import dev.jihogrammer.member.application.port.out.MemberPort;

public class MemberPersistenceAdaptorFactory {

    public static MemberPort createMemberPort() {
        return new InMemoryMemberAdaptor();
    }

}
