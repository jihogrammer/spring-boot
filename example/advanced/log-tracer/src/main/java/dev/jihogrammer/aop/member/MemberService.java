package dev.jihogrammer.aop.member;

import dev.jihogrammer.aop.member.annotation.MethodAop;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class MemberService implements MemberGreeting {

    @MethodAop("a test value")
    @Override
    public String hello(final String someone) {
        return "Hello, " + someone;
    }

    public String internal(final String param) {
        return "ok; " + param;
    }

}
