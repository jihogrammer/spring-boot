package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PointcutWithinTest {

    // modifier-pattern? return-type-pattern declaring-type-pattern.?name-pattern(param-pattern) throws-pattern?
    // 접근제한자? 반환타입 선언타입?메서드이름(파라미터) 예외?
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    Method helloMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        this.helloMethod = MemberService.class.getMethod("hello", String.class);
    }

    @Test
    void withinExactMatch() {
        // given
        var expression = "within(dev.jihogrammer.aop.member.MemberService)";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    @DisplayName("within 표현식은 상위 타입으로 표현할 수 없으며, 구현 클래스를 대상으로 동작한다.")
    void withinSuperTypeMatch() {
        // given
        var expression = "within(dev.jihogrammer.aop.member.MemberGreeting)";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @Test
    @DisplayName("execution 표현식은 상위 타입으로 표현 가능하다.")
    void executionSuperTypeMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.MemberGreeting.*(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void withinPatternMatch() {
        // given
        var expression = "within(dev.jihogrammer.aop.member.Member*)";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void withinSubPackageMatch() {
        // given
        var expression = "within(dev.jihogrammer..*)";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

}
