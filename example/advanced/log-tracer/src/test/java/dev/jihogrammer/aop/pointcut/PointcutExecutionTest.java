package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PointcutExecutionTest {

    // modifier-pattern? return-type-pattern declaring-type-pattern.?name-pattern(param-pattern) throws-pattern?
    // 접근제한자? 반환타입 선언타입?메서드이름(파라미터) 예외?
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

    Method helloMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        this.helloMethod = MemberService.class.getMethod("hello", String.class);
    }

    @Test
    void loadMethod() {
        // public java.lang.String dev.jihogrammer.aop.member.MemberService.hello(java.lang.String)
        log.info("helloMethod={}", this.helloMethod);
        assertThat(this.helloMethod).isNotNull();
    }

    @Test
    void pointcutExactMatch() {
        // given
        var expression = "execution(public String dev.jihogrammer.aop.member.MemberService.hello(String))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutAllMatch() {
        // given
        var expression = "execution(* *(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutNameMatch() {
        // given
        var expression = "execution(* hello(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutNamePatternMatch() {
        // given
        var expression = "execution(* *el*(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutWrongMatch() {
        // given
        var expression = "execution(* goodBye(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @Test
    void pointcutPackageExactMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.MemberService.hello(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutPackagePatternMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.*.*(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutPackageWrongPatternMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop.*.*(..))"; // aop, * 사이에 member 패키지명이 누락

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @Test
    void pointcutSubPackagePatternMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop..*.*(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutExactTypeMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.MemberService.*(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutSuperTypeMatch() {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.MemberGreeting.*(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutExactTypeAndInternalMethodMatch() throws NoSuchMethodException {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.MemberService.*(..))";
        var internalMethod = MemberService.class.getMethod("internal", String.class);

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(internalMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutSuperTypeAndInternalMethodMatch() throws NoSuchMethodException {
        // given
        var expression = "execution(* dev.jihogrammer.aop.member.MemberGreeting.*(..))";
        var internalMethod = MemberService.class.getMethod("internal", String.class);

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(internalMethod, MemberService.class)).isFalse();
    }

    @Test
    void pointcutParameterMatch() {
        // given
        var expression = "execution(* *(String))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutNoneParameterMatch() {
        // given
        var expression = "execution(* *())";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @Test
    void pointcutWrongParameterMatch() {
        // given
        var expression = "execution(* *(Integer))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @Test
    void pointcutAnyParametersMatch() {
        // given
        var expression = "execution(* *(..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @Test
    void pointcutManyParametersMatch() {
        // given
        var expression = "execution(* *(*,*))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @Test
    void pointcutStringAndManyParametersMatch() {
        // given
        var expression = "execution(* *(String,..))";

        // when
        this.pointcut.setExpression(expression);

        // then
        assertThat(this.pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

}
