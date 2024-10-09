package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PointcutArgsExpressionTest {

    AspectJExpressionPointcut pointcut;

    Method helloMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        this.pointcut = new AspectJExpressionPointcut();
        this.helloMethod = MemberService.class.getMethod("hello", String.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "args(String)",                 // exactly
            "args(Object)",                 // super type 1
            "args(java.io.Serializable)",   // super type 2
            "args(..)",                     // any types
            "args(*)",                      // an any type
            "args(String,..)",              // a String and any types
            "args(Object,..)",              // super type and any types
    })
    void argsValidExpression(final TestPointcut pointcut) {
        assertThat(pointcut.matches(this.helloMethod, MemberService.class)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "args()",           // empty params
            "args(Integer)",    // not super type
            "args(String,*)",   // a String and an any type
            "args(Object,*)",   // super type and an any type
    })
    void argsInvalidExpression(final TestPointcut pointcut) {
        assertThat(pointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "args(Object):execution(* *(Object))",
            "args(java.io.Serializable):execution(* *(java.io.Serializable))",
    }, delimiter = ':')
    @DisplayName("args 표현식은 런타임 상황에서 타입 판단, execution 표현식은 정적 타입 판단; args 표현식이 조금 더 유연하다.")
    void compareArgsAndExecution(final TestPointcut argsPointcut, final TestPointcut executionPointcut) {
        assertThat(argsPointcut.matches(this.helloMethod, MemberService.class)).isTrue();
        assertThat(executionPointcut.matches(this.helloMethod, MemberService.class)).isFalse();
    }

    static class TestPointcut extends AspectJExpressionPointcut {
        TestPointcut(final String expression) {
            this.setExpression(expression);
        }
    }

}
